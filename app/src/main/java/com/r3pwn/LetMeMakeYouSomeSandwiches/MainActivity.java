package com.r3pwn.LetMeMakeYouSomeSandwiches;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import eu.chainfire.libsuperuser.Shell;

public class MainActivity extends Activity {

    ToggleButton finskyDebugToggle;
    ToggleButton finskyDebug2Toggle;
    ToggleButton babelDebugToggle;
    ToggleButton babelGVToggle;
    ToggleButton musicDebugToggle;
    ToggleButton gamesDogfoodToggle;
    ToggleButton moviesDogfoodToggle;
    ToggleButton booksDebugToggle;
    Button newsstandButton;
    MainActivity mainActivity;
    SharedPreferences defaultSharedPreferences;


    public MainActivity() {
        mainActivity = this;
    }

    class bridge implements View.OnClickListener {
        private ToggleButton toggleButton;
        private String database_name;
        private String editor_name;
        private String app_name;
        public bridge(ToggleButton toggleButton, String database_name, String editor_name, String app_name) {
            this.toggleButton = toggleButton;
            this.database_name = database_name;
            this.editor_name = editor_name;
            this.app_name = app_name;
        }

        @Override
        public void onClick(View view) {
            // Disable buttons
            disableAll();

            // Grab preferences
            Editor editor = defaultSharedPreferences.edit();

            // Look at me. I'm the captain now.
            Shell.SU.run("cp /data/data/com.google.android.gsf/databases/gservices.db /data/data/com.r3pwn.configurator/databases/gservices.db\n");
            SQLiteDatabase db = openOrCreateDatabase("gservices.db", Context.MODE_WORLD_READABLE, null);
            // All your overrides are belong to me.
            // If the toggle button is on
            if (toggleButton.isChecked()) {
                // Let's disable debugging.
                db.execSQL("UPDATE overrides SET value='false' WHERE name='" + database_name + "';");
            } else {
                // It's not on, so we'll enable debugging.
                db.execSQL("INSERT INTO overrides (name, value) VALUES ('" + database_name + "', 'true');");
                db.execSQL("UPDATE overrides SET value='true' WHERE name='" + database_name + "';");
            }
            // Just kidding. You can have it back now.
            Shell.SU.run("cp /data/data/com.r3pwn.configurator/databases/gservices.db /data/data/com.google.android.gsf/databases/gservices.db\n");
            Shell.SU.run("rm -f /data/data/com.r3pwn.configurator/databases/gservices.db\n");
            // Here in Android land, we call the following "reloading".
            Shell.SU.run("am force-stop com.google.android.gsf\n");
            Shell.SU.run("am force-stop " + app_name + "\n");

            editor.putInt(editor_name, 1);
            editor.commit();
            // Re-enable buttons
            enableAll();
            Toast.makeText(mainActivity.getApplicationContext(), "Changes applied.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onCreate(Bundle bundle) {
        // Show view
        super.onCreate(bundle);
        setContentView(R.layout.main);
        defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Get preferences
        int finsky_status = defaultSharedPreferences.getInt("finsky_status", 0);
        int finsky2_status = defaultSharedPreferences.getInt("finsky2_status", 0);
        int babel_status = defaultSharedPreferences.getInt("babel_status", 0);
        int babelgv_status = defaultSharedPreferences.getInt("babelgv_status", 0);
        int music_status = defaultSharedPreferences.getInt("music_status", 0);
        int books_status = defaultSharedPreferences.getInt("books_status", 0);
        int games_status = defaultSharedPreferences.getInt("games_status", 0);
        int movies_status = defaultSharedPreferences.getInt("movies_status", 0);

        // Setup
        finskyDebugToggle = (ToggleButton) findViewById(R.id.FinskyDebugToggle);
        finskyDebug2Toggle = (ToggleButton) findViewById(R.id.FinskyDebug2Toggle);
        babelDebugToggle = (ToggleButton) findViewById(R.id.BabelDebugToggle);
        babelGVToggle = (ToggleButton) findViewById(R.id.BabelGVToggle);
        musicDebugToggle = (ToggleButton) findViewById(R.id.MusicDebugToggle);
        gamesDogfoodToggle = (ToggleButton) findViewById(R.id.GamesDogfoodToggle);
        moviesDogfoodToggle = (ToggleButton) findViewById(R.id.MoviesDogfoodToggle);
        booksDebugToggle = (ToggleButton) findViewById(R.id.BooksDebugToggle);
        newsstandButton = (Button) findViewById(R.id.newsstandButton);

        if (finsky_status == 1) {
            finskyDebugToggle.setChecked(true);
        }
        if (finsky_status == 0) {
            finskyDebugToggle.setChecked(false);
        }
        if (finsky2_status == 1) {
            finskyDebug2Toggle.setChecked(true);
        }
        if (finsky2_status == 0) {
            finskyDebug2Toggle.setChecked(false);
        }
        if (babel_status == 1) {
            babelDebugToggle.setChecked(true);
        }
        if (babel_status == 0) {
            babelDebugToggle.setChecked(false);
        }
        if (babelgv_status == 1) {
            babelGVToggle.setChecked(true);
        }
        if (babelgv_status == 0) {
            babelGVToggle.setChecked(false);
        }
        if (music_status == 1) {
            musicDebugToggle.setChecked(true);
        }
        if (music_status == 0) {
            musicDebugToggle.setChecked(false);
        }
        if (games_status == 1) {
            gamesDogfoodToggle.setChecked(true);
        }
        if (games_status == 0) {
            gamesDogfoodToggle.setChecked(false);
        }
        if (movies_status == 1) {
            moviesDogfoodToggle.setChecked(true);
        }
        if (movies_status == 0) {
            moviesDogfoodToggle.setChecked(false);
        }
        if (books_status == 1) {
            booksDebugToggle.setChecked(true);
        }
        if (books_status == 0) {
            booksDebugToggle.setChecked(false);
        }
        if (Shell.SU.available() == false) {
            // Disable all buttons
            disableAll();
            // Create dialog
            AlertDialog.Builder noRoot = new AlertDialog.Builder(this); //Context parameter
            noRoot.setTitle("No root");
            noRoot.setMessage("You aren't rooted. This app can't function without root. All functionality has been disabled.");
            noRoot.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Dismiss dialog
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = noRoot.create();
            alertDialog.show();
        }


        finskyDebugToggle.setOnClickListener(new bridge(finskyDebug2Toggle, "finsky.debug_options_enabled", "finsky_status", "com.android.vending"));
        finskyDebug2Toggle.setOnClickListener(new bridge(finskyDebugToggle, "finsky.dcb_debug_options_enabled", "finsky2_status", "com.android.vending"));
        babelDebugToggle.setOnClickListener(new bridge(babelDebugToggle, "babel_debugging", "babel_status", "com.google.android.talk"));
        babelGVToggle.setOnClickListener(new bridge(babelGVToggle, "babel_gv_sms", "babelgv_status", "com.google.android.talk"));
        musicDebugToggle.setOnClickListener(new bridge(musicDebugToggle, "music_debug_logs_enabled", "music_status", "com.google.android.music"));
        gamesDogfoodToggle.setOnClickListener(new bridge(gamesDogfoodToggle, "games.play_games_dogfood", "games_status", "com.google.android.play.games"));
        moviesDogfoodToggle.setOnClickListener(new bridge(moviesDogfoodToggle, "videos:dogfood_enabled", "movies_status", "com.google.android.videos"));
        booksDebugToggle.setOnClickListener(new bridge(booksDebugToggle, "books:show_testing_ui", "books_status", "com.google.android.apps.books"));
        // Launch newsstand class
        newsstandButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    mainActivity.startActivity(new Intent(mainActivity, Class.forName("com.r3pwn.LetMeMakeYouSomeSandwiches.NewsstandDebug")));
                } catch (Throwable e) {
                    Toast.makeText(mainActivity.getApplicationContext(), "Could not find Newsstand class.", Toast.LENGTH_LONG).show();
                    throw new NoClassDefFoundError(e.getMessage());
                }
            }
        });
    }

    public void disableAll() {
        finskyDebugToggle.setEnabled(false);
        finskyDebug2Toggle.setEnabled(false);
        babelDebugToggle.setEnabled(false);
        babelGVToggle.setEnabled(false);
        musicDebugToggle.setEnabled(false);
        gamesDogfoodToggle.setEnabled(false);
        moviesDogfoodToggle.setEnabled(false);
        booksDebugToggle.setEnabled(false);
    }

    public void enableAll() {
        finskyDebugToggle.setEnabled(false);
        finskyDebug2Toggle.setEnabled(false);
        babelDebugToggle.setEnabled(false);
        babelGVToggle.setEnabled(false);
        musicDebugToggle.setEnabled(false);
        gamesDogfoodToggle.setEnabled(false);
        moviesDogfoodToggle.setEnabled(false);
        booksDebugToggle.setEnabled(false);
    }
}
