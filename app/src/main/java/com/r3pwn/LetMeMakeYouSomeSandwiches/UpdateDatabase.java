package com.r3pwn.LetMeMakeYouSomeSandwiches;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import eu.chainfire.libsuperuser.Shell;

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
        Shell.SU.run("cp /data/data/com.google.android.gsf/databases/gservices.db /data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/gservices.db\n");
        SQLiteDatabase db = mainActivity.openOrCreateDatabase("gservices.db", Context.MODE_WORLD_READABLE, null);
        // All your overrides are belong to me.
        // If the toggle button is on already
        if (toggleButton.isChecked()) {
            // Let's disable debugging.
            db.execSQL("DELETE FROM overrides WHERE name='" + database_name + "';");
			editor.putInt(editor_name, 0);
        } else {
            // It's off, so we'll enable debugging.
            try {
                db.execSQL("INSERT INTO overrides (name, value) VALUES ('" + database_name + "', 'true');");
            } catch (android.database.SQLException sqle) {
                // if this errors out, then that means the key is already in the database.
            }
            db.execSQL("UPDATE overrides SET value='true' WHERE name='" + database_name + "';");
			editor.putInt(editor_name, 1);
        }
        // Just kidding. You can have it back now.
        Shell.SU.run("cp /data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/gservices.db /data/data/com.google.android.gsf/databases/gservices.db\n");
        Shell.SU.run("rm -f /data/data/com.r3pwn.LetMeMakeYouSomeSandwiches/databases/*\n");
        // Here in Android land, we call the following "reloading".
        Shell.SU.run("am force-stop com.google.android.gsf\n");
        Shell.SU.run("am force-stop " + app_name + "\n");
		
		editor.commit();

        // Re-enable buttons
        mainActivity.enableAll();
        Toast.makeText(mainActivity.getApplicationContext(), "Changes applied.", Toast.LENGTH_SHORT).show();
    }
}
