package com.r3pwn.LetMeMakeYouSomeSandwiches;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import eu.chainfire.libsuperuser.Shell;
import java.io.*;
import android.util.*;

class UpdateDatabase implements View.OnClickListener {
    private ToggleButton toggleButton;
    private String database_name;
    private String editor_name;
    private String app_name;
    private MainActivity mainActivity;

    UpdateDatabase(MainActivity mainActivity, ToggleButton toggleButton, String database_name, String editor_name, String app_name) {
        this.toggleButton = toggleButton;
        this.database_name = database_name;
        this.editor_name = editor_name;
        this.app_name = app_name;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        // Disable buttons
        mainActivity.disableAll();

        // Grab preferences
        SharedPreferences.Editor editor = mainActivity.defaultSharedPreferences.edit();

        // Look at me. I'm the captain now.
		File databasesDir = new File("/data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases");
		File gservicesDb = new File("/data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/gservices.db");
		File gservicesWorkingDb = new File("/data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/gservices_working.db");
		
		if (!databasesDir.exists())
		{
			databasesDir.mkdir();
		} else {
			// Clean up beforehand
			Shell.SU.run("rm -f /data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/*\n");
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
        SQLiteDatabase db = mainActivity.openOrCreateDatabase(gservicesWorkingDb.toString(), Context.MODE_WORLD_WRITEABLE, null);
        // All your overrides are belong to me.
        // If the toggle button is on already
        if (toggleButton.isChecked()) {
            // Let's disable debugging.
            db.execSQL("UPDATE overrides SET value='false' WHERE name='" + database_name + "';");
			editor.putInt(editor_name, 0);
			editor.commit();
        } else {
            // It's off, so we'll enable debugging.
            try {
                db.execSQL("INSERT INTO overrides (name, value) VALUES ('" + database_name + "', 'true');");
            } catch (android.database.SQLException sqle) {
                // If this errors out, then that means the key is already in the database.
                // Nothing needs to be inserted, so we can just update the key.
            }
            db.execSQL("UPDATE overrides SET value='true' WHERE name='" + database_name + "';");
			editor.putInt(editor_name, 1);
			editor.commit();
        }
        // Just kidding. You can have it back now.
        Shell.SU.run("cp " + gservicesWorkingDb + "/data/data/com.google.android.gsf/databases/gservices.db\n");
        Shell.SU.run("rm -f /data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/*\n");
        // Here in Android land, we call the following "reloading".
        Shell.SU.run("am force-stop com.google.android.gsf\n");
        Shell.SU.run("am force-stop " + app_name + "\n");

        // Re-enable buttons
        mainActivity.enableAll();
        Toast.makeText(mainActivity.getApplicationContext(), "Changes applied.", Toast.LENGTH_SHORT).show();
    }
	
	private boolean isCompletelyWritten(File file) {
		// this was taken from http://stackoverflow.com/a/11242648
		RandomAccessFile stream = null;
		try {
			stream = new RandomAccessFile(file, "rw");
			return true;
		} catch (Exception e) {
			Log.d("INFO", "Skipping file " + file.getName() + " for this iteration due to it not being completely written.");
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					Log.d("INFO", "Exception during closing file " + file.getName());
				}
			}
		}
		return false;
	}
}
