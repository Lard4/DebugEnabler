package com.r3pwn.LetMeMakeYouSomeSandwiches;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

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

    public MainActivity() {
        mainActivity = this;
    }

    @Override
    public void onCreate(Bundle bundle) {
        // Show view
        super.onCreate(bundle);
        setContentView(R.layout.main);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
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
        finskyDebugToggle = (ToggleButton) mainActivity.findViewById(R.id.FinskyDebugToggle);
        finskyDebug2Toggle = (ToggleButton) mainActivity.findViewById(R.id.FinskyDebug2Toggle);
        babelDebugToggle = (ToggleButton) mainActivity.findViewById(R.id.BabelDebugToggle);
        babelGVToggle = (ToggleButton) mainActivity.findViewById(R.id.BabelGVToggle);
        musicDebugToggle = (ToggleButton) mainActivity.findViewById(R.id.MusicDebugToggle);
        gamesDogfoodToggle = (ToggleButton) mainActivity.findViewById(R.id.GamesDogfoodToggle);
        moviesDogfoodToggle = (ToggleButton) mainActivity.findViewById(R.id.MoviesDogfoodToggle);
        booksDebugToggle = (ToggleButton) mainActivity.findViewById(R.id.BooksDebugToggle);
        newsstandButton = (Button) mainActivity.findViewById(R.id.newsstandButton);

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
            AlertDialog.Builder noRoot = new AlertDialog.Builder(this);//Context parameter
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

//        if (!(new File("/system/bin/sqlite3").exists() || new File("/system/xbin/sqlite3").exists())) {
//            new NoSQLDialog(this).noBusyboxDialog();
//        }


//        finskyDebugToggle.setOnClickListener(new 100000003(this, toggleButton, edit, incompatible_status));
//        finskyDebug2Toggle.setOnClickListener(new 100000004(this, toggleButton2, edit, incompatible_status));
//        babelDebugToggle.setOnClickListener(new 100000005(this, toggleButton3, edit, incompatible_status));
//        babelGVToggle.setOnClickListener(new 100000006(this, toggleButton4, edit, incompatible_status));
//        musicDebugToggle.setOnClickListener(new 100000007(this, toggleButton5, edit, incompatible_status));
//        gamesDogfoodToggle.setOnClickListener(new 100000008(this, toggleButton6, edit, incompatible_status));
//        moviesDogfoodToggle.setOnClickListener(new 100000009(this, toggleButton7, edit, incompatible_status));
//        booksDebugToggle.setOnClickListener(new 100000010(this, toggleButton8, edit, incompatible_status));
        // Launch newsstand class
        newsstandButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    mainActivity.startActivity(new Intent(mainActivity, Class.forName("com.r3pwn.LetMeMakeYouSomeSandwiches.NewsstandDebug")));
                } catch (Throwable e) {
                    Toast.makeText(mainActivity.getApplicationContext(), "Could not find Newsstand class.", 1).show();
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
}
