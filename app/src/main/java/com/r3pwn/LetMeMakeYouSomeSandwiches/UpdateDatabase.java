/* UpdateDatabase.java - the "brain" behind the whole thing
 Copyright (C) 2016 Greg Willard <wickett06@gmail.com>

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
// Please note that I did not write all of this myself. I had some
// help from Joshua Jones <awesomebing1@gmail.com>.
package com.r3pwn.LetMeMakeYouSomeSandwiches;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import android.widget.ToggleButton;

import eu.chainfire.libsuperuser.Shell;
import java.io.*;
import android.util.*;
import android.widget.*;
import android.os.*;
import android.app.*;
import android.view.View.*;
import android.view.*;

class UpdateDatabase implements ToggleButton.OnCheckedChangeListener, View.OnClickListener
{
	private ToggleButton toggleButton;
	private Button button;
    private String database_name;
	private String database_value = "";
    private String editor_name;
    private String app_name;
    private MainActivity mainActivity;
	private CustomDebug customDebug;
	private ProgressDialog writeProgress;
	private Boolean override = false;
	private SQLiteDatabase db;
	private SharedPreferences.Editor editor;

    UpdateDatabase(MainActivity mainActivity, ToggleButton toggleButton, String database_name, String editor_name, String app_name)
	{
		this.toggleButton = toggleButton;
        this.database_name = database_name;
        this.editor_name = editor_name;
        this.app_name = app_name;
        this.mainActivity = mainActivity;
    }

	UpdateDatabase(CustomDebug customDebug, Button button, String entry_name, String entry_value, String app_name, Boolean override)
	{
		this.button = button;
        this.database_name = entry_name;
		this.database_value = entry_value;
        this.app_name = app_name;
        this.customDebug = customDebug;
		this.override = override;
    }

    @Override
    public void onCheckedChanged(CompoundButton cb, boolean checked)
	{
		// load the operation in the background, as to not lag the hell out of the main thread
		new BackgroundDatabaseInjector().execute();
	}

	@Override
	public void onClick(View p1)
	{
		new BackgroundDatabaseInjector().execute();
	}

	private boolean isCompletelyWritten(File file)
	{
		// this was taken from http://stackoverflow.com/a/11242648
		RandomAccessFile stream = null;
		try
		{
			stream = new RandomAccessFile(file, "rw");
			return true;
		}
		catch (Exception e)
		{
			Log.d("INFO", "Skipping file " + file.getName() + " for this iteration due to it not being completely written.");
		}
		finally
		{
			if (stream != null)
			{
				try
				{
					stream.close();
				}
				catch (IOException e)
				{
					Log.d("INFO", "Exception during closing file " + file.getName());
				}
			}
		}
		return false;
	}

	private class BackgroundDatabaseInjector extends AsyncTask<String, Void, String>
	{

        @Override
        protected String doInBackground(String... params)
		{
			// Grab preferences
			if (!override)
			{
				editor = mainActivity.defaultSharedPreferences.edit();
			}

			// Look at me. I'm the captain now.
			File databasesDir = new File("/data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases");
			File gservicesDb = new File("/data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/gservices.db");
			File gservicesWorkingDb = new File("/data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/gservices_working.db");

			if (!databasesDir.exists())
			{
				databasesDir.mkdir();
			} else
			{
				// Clean up beforehand
				Shell.SU.run("rm -f " + databasesDir + "/*\n");
			}

			// Copy and set proper permissions
			Shell.SU.run("cp /data/data/com.google.android.gsf/databases/gservices.db " + gservicesDb + "\n");
			Shell.SU.run("restorecon " + gservicesDb);
			Shell.SU.run("chmod 0777 " + gservicesDb);

			// This is a horrible hack and I don't recommend anyone use it ever.
			while (!isCompletelyWritten(gservicesDb))
			{
				try
				{
					Thread.sleep(15);
				}
				catch (InterruptedException e)
				{}
			}

			// pack up and move to gservices_working.db
			gservicesDb.renameTo(gservicesWorkingDb);

			while (!isCompletelyWritten(gservicesWorkingDb))
			{ // needs moar memes, erm, I mean time to finish writing the file
				try
				{
					Thread.sleep(5);
				}
				catch (InterruptedException e)
				{}
			}

			// Open the database for writing
			if (!override)
			{
				db = mainActivity.openOrCreateDatabase(gservicesWorkingDb.toString(), Context.MODE_WORLD_READABLE, null);
			} else
			{
				db = customDebug.openOrCreateDatabase(gservicesWorkingDb.toString(), Context.MODE_WORLD_READABLE, null);
			}

			// Check toggle button status
			if (!override)
			{
				if (toggleButton.isChecked())
				{
					// It's on, so we'll enable debugging.
					try
					{
						db.execSQL("INSERT INTO overrides (name, value) VALUES ('" + database_name + "', 'true');");
					}
					catch (android.database.SQLException sqle)
					{
						// If this errors out, then that means the key is already in the database.
						// Nothing needs to be inserted, so we can just update the key.
					}
					db.execSQL("UPDATE overrides SET value='true' WHERE name='" + database_name + "';");
					editor.putInt(editor_name, 1);
					editor.commit();
				} else
				{
					// Let's disable debugging.
					db.execSQL("DELETE FROM overrides WHERE name='" + database_name + "';");
					editor.putInt(editor_name, 0);
					editor.commit();
				}
			} else
			{
				try
				{
					db.execSQL("INSERT INTO overrides (name, value) VALUES ('" + database_name + "', '" + database_value + "');");
				}
				catch (android.database.SQLException sqle)
				{
					// If this errors out, then that means the key is already in the database.
					// Nothing needs to be inserted, so we can just update the key.
				}
				db.execSQL("UPDATE overrides SET value='" + database_value + "' WHERE name='" + database_name + "';");
			}
            // Close database, as we have written to it.
			db.close();

			// Just kidding. You can have it back now.
			Shell.SU.run("mv  /data/data/com.google.android.gsf/databases/gservices.db /data/data/com.google.android.gsf/databases/gservices.db.old\n");
			Shell.SU.run("rm  /data/data/com.google.android.gsf/databases/gservices.db-journal\n");
			Shell.SU.run("cp " + gservicesWorkingDb + " /data/data/com.google.android.gsf/databases/gservices.db\n");
			Shell.SU.run("restorecon /data/data/com.google.android.gsf/databases/gservices.db\n");
			Shell.SU.run("chmod 0777 /data/data/com.google.android.gsf/databases/gservices.db\n");

			// Here in Android land, we call the following "reloading".
			Shell.SU.run("am force-stop com.google.android.gsf\n");
			Shell.SU.run("am force-stop " + app_name + "\n");

            // Make sure nothing is left behind, as to not interrupt future changes.
			Shell.SU.run("rm -f /data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/*\n");

			return "Done";
		}

		@Override
        protected void onPostExecute(String result)
		{
			if (!override)
			{
				Toast.makeText(mainActivity.getApplicationContext(), "Changes applied.", Toast.LENGTH_SHORT).show();
			} else
			{
				Toast.makeText(customDebug.getApplicationContext(), "Changes applied.", Toast.LENGTH_SHORT).show();
			}
			writeProgress.cancel();
		}

        @Override
        protected void onPreExecute()
		{
			if (!override)
			{
				writeProgress = new ProgressDialog(mainActivity);
			} else
			{
				writeProgress = new ProgressDialog(customDebug);
			}
			writeProgress.setCancelable(false);
			writeProgress.setMessage("Writing to database...");
			writeProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			writeProgress.show();
		}

        @Override
        protected void onProgressUpdate(Void... values)
		{}
	}
}
