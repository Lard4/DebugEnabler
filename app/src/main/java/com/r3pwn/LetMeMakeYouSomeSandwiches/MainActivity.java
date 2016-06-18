/* MainActivity.java - the activity that gets launched when the user taps the icon
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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import eu.chainfire.libsuperuser.Shell;
import android.database.*;

public class MainActivity extends Activity {

    ToggleButton finskyDebugToggle;
    ToggleButton babelDebugToggle;
	ToggleButton bugleDebugToggle;
	ToggleButton booksTestingToggle;
    /*ToggleButton babelGVToggle;
    ToggleButton musicDebugToggle;
    ToggleButton gamesDogfoodToggle;
    ToggleButton moviesDogfoodToggle;
    ToggleButton booksDebugToggle;
    Button newsstandButton;*/
    MainActivity mainActivity;
    SharedPreferences defaultSharedPreferences;

    public MainActivity() {
        mainActivity = this;
    }

    @Override
    public void onCreate(Bundle bundle) {
        // Show view
        super.onCreate(bundle);
        setContentView(R.layout.layout_main);
        defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Get preferences
        int finsky_status = defaultSharedPreferences.getInt("finsky_status", 0);
        int babel_status = defaultSharedPreferences.getInt("babel_status", 0);
		int bugle_status = defaultSharedPreferences.getInt("bugle_status", 0);
		int books_status = defaultSharedPreferences.getInt("books_status", 0);
        /*int music_status = defaultSharedPreferences.getInt("music_status", 0);
        int books_status = defaultSharedPreferences.getInt("books_status", 0);
        int games_status = defaultSharedPreferences.getInt("games_status", 0);
        int movies_status = defaultSharedPreferences.getInt("movies_status", 0);*/

        // Setup
        finskyDebugToggle = (ToggleButton) findViewById(R.id.finskyDebugToggle);
        babelDebugToggle = (ToggleButton) findViewById(R.id.babelDebugToggle);
		bugleDebugToggle = (ToggleButton) findViewById(R.id.bugleDebugToggle);
		booksTestingToggle = (ToggleButton) findViewById(R.id.booksTestingToggle);
        /*babelGVToggle = (ToggleButton) findViewById(R.id.BabelGVToggle);
        musicDebugToggle = (ToggleButton) findViewById(R.id.MusicDebugToggle);
        gamesDogfoodToggle = (ToggleButton) findViewById(R.id.GamesDogfoodToggle);
        moviesDogfoodToggle = (ToggleButton) findViewById(R.id.MoviesDogfoodToggle);
        booksDebugToggle = (ToggleButton) findViewById(R.id.BooksDebugToggle);
        newsstandButton = (Button) findViewById(R.id.newsstandButton);*/

        if (finsky_status == 1) {
            finskyDebugToggle.setChecked(true);
        }
        if (babel_status == 1) {
            babelDebugToggle.setChecked(true);
        }
		if (bugle_status == 1) {
            bugleDebugToggle.setChecked(true);
        }
		if (books_status == 1) {
            booksTestingToggle.setChecked(true);
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


        finskyDebugToggle.setOnCheckedChangeListener(new UpdateDatabase(this, finskyDebugToggle, "finsky.debug_options_enabled", "finsky_status", "com.android.vending"));
        babelDebugToggle.setOnCheckedChangeListener(new UpdateDatabase(this, babelDebugToggle, "babel_debugging", "babel_status", "com.google.android.talk"));
		bugleDebugToggle.setOnCheckedChangeListener(new UpdateDatabase(this, bugleDebugToggle, "bugle_debugging", "bugle_status", "com.google.android.apps.messaging"));
		booksTestingToggle.setOnCheckedChangeListener(new UpdateDatabase(this, booksTestingToggle, "books:show_testing_ui", "books_status", "com.google.android.apps.books"));
        /*babelGVToggle.setOnClickListener(new UpdateDatabase(this, babelGVToggle, "babel_gv_sms", "babelgv_status", "com.google.android.talk"));
        musicDebugToggle.setOnClickListener(new UpdateDatabase(this, musicDebugToggle, "music_debug_logs_enabled", "music_status", "com.google.android.music"));
        gamesDogfoodToggle.setOnClickListener(new UpdateDatabase(this, gamesDogfoodToggle, "games.play_games_dogfood", "games_status", "com.google.android.play.games"));
        moviesDogfoodToggle.setOnClickListener(new UpdateDatabase(this, moviesDogfoodToggle, "videos:dogfood_enabled", "movies_status", "com.google.android.videos"));
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
        });*/
    }
    public void disableAll() {
        finskyDebugToggle.setEnabled(false);
        babelDebugToggle.setEnabled(false);
		bugleDebugToggle.setEnabled(false);
		booksTestingToggle.setEnabled(false);
        /*babelGVToggle.setEnabled(false);
        musicDebugToggle.setEnabled(false);
        gamesDogfoodToggle.setEnabled(false);
        moviesDogfoodToggle.setEnabled(false);
        booksDebugToggle.setEnabled(false);*/
    }

    public void enableAll() {
        finskyDebugToggle.setEnabled(true);
        babelDebugToggle.setEnabled(true);
		bugleDebugToggle.setEnabled(true);
		booksTestingToggle.setEnabled(true);
        /*babelGVToggle.setEnabled(true);
        musicDebugToggle.setEnabled(true);
        gamesDogfoodToggle.setEnabled(true);
        moviesDogfoodToggle.setEnabled(true);
        booksDebugToggle.setEnabled(true);*/
    }
}
