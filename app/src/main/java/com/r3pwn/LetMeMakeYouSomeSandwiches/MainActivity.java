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
import android.preference.PreferenceManager;
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
        this.finskyDebugToggle = (ToggleButton) findViewById(R.id.FinskyDebugToggle);
        this.finskyDebug2Toggle = (ToggleButton) findViewById(R.id.FinskyDebug2Toggle);
        this.babelDebugToggle = (ToggleButton) findViewById(R.id.BabelDebugToggle);
        this.babelGVToggle = (ToggleButton) findViewById(R.id.BabelGVToggle);
        this.musicDebugToggle = (ToggleButton) findViewById(R.id.MusicDebugToggle);
        this.gamesDogfoodToggle = (ToggleButton) findViewById(R.id.GamesDogfoodToggle);
        this.moviesDogfoodToggle = (ToggleButton) findViewById(R.id.MoviesDogfoodToggle);
        this.booksDebugToggle = (ToggleButton) findViewById(R.id.BooksDebugToggle);
        this.newsstandButton = (Button) findViewById(R.id.newsstandButton);
        this.mainActivity = this;
    }

//    class 100000000 implements OnClickListener {
//        private final MainActivity mainActivity;
//        private final AlertDialog alertDialog;
//
//        100000000(MainActivity mainActivity, AlertDialog alertDialog) {
//            this.mainActivity = mainActivity;
//            this.val$unsupported_version = alertDialog;
//        }
//
//        static MainActivity access$0(100000000 100000000) {
//            return 100000000.mainActivity;
//        }
//
//        public void onClick(DialogInterface dialogInterface, int finsky_status) {
//            this.val$unsupported_version.dismiss();
//        }
//    }
//
//
//
//    class 100000003 implements View.OnClickListener {
//        private final MainActivity mainActivity;
//        private final ToggleButton FinskyDebugToggle;
//        private final int val$incompatible_status;
//        private final Editor val$prefs_edit;
//
//        100000003(MainActivity mainActivity, ToggleButton toggleButton, Editor editor, int i) {
//            this.mainActivity = mainActivity;
//            this.FinskyDebugToggle = toggleButton;
//            this.val$prefs_edit = editor;
//            this.val$incompatible_status = i;
//        }
//
//        public void onClick(View view) {
//            if (this.FinskyDebugToggle.isChecked()) {
//                try {
//                    Process exec = Runtime.getRuntime().exec("su");
//                    DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("finsky_status", 1);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"INSERT INTO overrides (name, value) VALUES ('finsky.debug_options_enabled', 'true');\"\n");
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='true' WHERE name='finsky.debug_options_enabled';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.android.vending\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e) {
//                } catch (InterruptedException e2) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//            if (!this.FinskyDebugToggle.isChecked()) {
//                try {
//                    exec = Runtime.getRuntime().exec("su");
//                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("finsky_status", 0);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='false' WHERE name='finsky.debug_options_enabled';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.android.vending\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e3) {
//                } catch (InterruptedException e4) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//        }
//    }
//
//    class 100000004 implements View.OnClickListener {
//        private final MainActivity mainActivity;
//        private final ToggleButton FinskyDebugToggle2;
//        private final int val$incompatible_status;
//        private final Editor val$prefs_edit;
//
//        100000004(MainActivity mainActivity, ToggleButton toggleButton, Editor editor, int i) {
//            this.mainActivity = mainActivity;
//            this.FinskyDebugToggle2 = toggleButton;
//            this.val$prefs_edit = editor;
//            this.val$incompatible_status = i;
//        }
//
//        static MainActivity access$0(100000004 100000004) {
//            return 100000004.mainActivity;
//        }
//
//        public void onClick(View view) {
//            if (this.FinskyDebugToggle2.isChecked()) {
//                try {
//                    Process exec = Runtime.getRuntime().exec("su");
//                    DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("finsky2_status", 1);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"INSERT INTO overrides (name, value) VALUES ('finsky.dcb_debug_options_enabled', 'true');\"\n");
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='true' WHERE name='finsky.dcb_debug_options_enabled';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.android.vending\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e) {
//                } catch (InterruptedException e2) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//            if (!this.FinskyDebugToggle2.isChecked()) {
//                try {
//                    exec = Runtime.getRuntime().exec("su");
//                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("finsky2_status", 0);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='false' WHERE name='finsky.dcb_debug_options_enabled';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.android.vending\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e3) {
//                } catch (InterruptedException e4) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//        }
//    }
//
//    class 100000005 implements View.OnClickListener {
//        private final MainActivity mainActivity;
//        private final ToggleButton val$babel;
//        private final int val$incompatible_status;
//        private final Editor val$prefs_edit;
//
//        100000005(MainActivity mainActivity, ToggleButton toggleButton, Editor editor, int i) {
//            this.mainActivity = mainActivity;
//            this.val$babel = toggleButton;
//            this.val$prefs_edit = editor;
//            this.val$incompatible_status = i;
//        }
//
//        static MainActivity access$0(100000005 100000005) {
//            return 100000005.mainActivity;
//        }
//
//        public void onClick(View view) {
//            if (this.val$babel.isChecked()) {
//                try {
//                    Process exec = Runtime.getRuntime().exec("su");
//                    DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("babel_status", 1);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"INSERT INTO overrides (name, value) VALUES ('babel_debugging', 'true');\"\n");
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='true' WHERE name='babel_debugging';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.talk\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e) {
//                } catch (InterruptedException e2) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//            if (!this.val$babel.isChecked()) {
//                try {
//                    exec = Runtime.getRuntime().exec("su");
//                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("babel_status", 0);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='false' WHERE name='babel_debugging';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.talk\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e3) {
//                } catch (InterruptedException e4) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//        }
//    }
//
//    class 100000006 implements View.OnClickListener {
//        private final MainActivity mainActivity;
//        private final ToggleButton val$babelgv;
//        private final int val$incompatible_status;
//        private final Editor val$prefs_edit;
//
//        100000006(MainActivity mainActivity, ToggleButton toggleButton, Editor editor, int i) {
//            this.mainActivity = mainActivity;
//            this.val$babelgv = toggleButton;
//            this.val$prefs_edit = editor;
//            this.val$incompatible_status = i;
//        }
//
//        static MainActivity access$0(100000006 100000006) {
//            return 100000006.mainActivity;
//        }
//
//        public void onClick(View view) {
//            if (this.val$babelgv.isChecked()) {
//                try {
//                    Process exec = Runtime.getRuntime().exec("su");
//                    DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("babelgv_status", 1);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"INSERT INTO overrides (name, value) VALUES ('babel_gv_sms', 'true');\"\n");
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='true' WHERE name='babel_gv_sms';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.talk\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e) {
//                } catch (InterruptedException e2) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//            if (!this.val$babelgv.isChecked()) {
//                try {
//                    exec = Runtime.getRuntime().exec("su");
//                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("babelgv_status", 0);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='false' WHERE name='babel_gv_sms';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.talk\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e3) {
//                } catch (InterruptedException e4) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//        }
//    }
//
//    class 100000007 implements View.OnClickListener {
//        private final MainActivity mainActivity;
//        private final int val$incompatible_status;
//        private final ToggleButton val$music;
//        private final Editor val$prefs_edit;
//
//        100000007(MainActivity mainActivity, ToggleButton toggleButton, Editor editor, int i) {
//            this.mainActivity = mainActivity;
//            this.val$music = toggleButton;
//            this.val$prefs_edit = editor;
//            this.val$incompatible_status = i;
//        }
//
//        static MainActivity access$0(100000007 100000007) {
//            return 100000007.mainActivity;
//        }
//
//        public void onClick(View view) {
//            if (this.val$music.isChecked()) {
//                try {
//                    Process exec = Runtime.getRuntime().exec("su");
//                    DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("music_status", 1);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"INSERT INTO overrides (name, value) VALUES ('music_debug_logs_enabled', 'true');\"\n");
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='true' WHERE name='music_debug_logs_enabled';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.music\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e) {
//                } catch (InterruptedException e2) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//            if (!this.val$music.isChecked()) {
//                try {
//                    exec = Runtime.getRuntime().exec("su");
//                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("music_status", 0);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='false' WHERE name='music_debug_logs_enabled';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.music\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e3) {
//                } catch (InterruptedException e4) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//        }
//    }
//
//    class 100000008 implements View.OnClickListener {
//        private final MainActivity mainActivity;
//        private final ToggleButton val$games;
//        private final int val$incompatible_status;
//        private final Editor val$prefs_edit;
//
//        100000008(MainActivity mainActivity, ToggleButton toggleButton, Editor editor, int i) {
//            this.mainActivity = mainActivity;
//            this.val$games = toggleButton;
//            this.val$prefs_edit = editor;
//            this.val$incompatible_status = i;
//        }
//
//        static MainActivity access$0(100000008 100000008) {
//            return 100000008.mainActivity;
//        }
//
//        public void onClick(View view) {
//            if (this.val$games.isChecked()) {
//                try {
//                    Process exec = Runtime.getRuntime().exec("su");
//                    DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("games_status", 1);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"INSERT INTO overrides (name, value) VALUES ('games.play_games_dogfood', 'true');\"\n");
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='true' WHERE name='games.play_games_dogfood';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.play.games\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e) {
//                } catch (InterruptedException e2) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//            if (!this.val$games.isChecked()) {
//                try {
//                    exec = Runtime.getRuntime().exec("su");
//                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("games_status", 0);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='false' WHERE name='games.play_games_dogfood';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.play.games\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e3) {
//                } catch (InterruptedException e4) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//        }
//    }
//
//    class 100000009 implements View.OnClickListener {
//        private final MainActivity mainActivity;
//        private final int val$incompatible_status;
//        private final ToggleButton val$movies;
//        private final Editor val$prefs_edit;
//
//        100000009(MainActivity mainActivity, ToggleButton toggleButton, Editor editor, int i) {
//            this.mainActivity = mainActivity;
//            this.val$movies = toggleButton;
//            this.val$prefs_edit = editor;
//            this.val$incompatible_status = i;
//        }
//
//        static MainActivity access$0(100000009 100000009) {
//            return 100000009.mainActivity;
//        }
//
//        public void onClick(View view) {
//            if (this.val$movies.isChecked()) {
//                try {
//                    Process exec = Runtime.getRuntime().exec("su");
//                    DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("movies_status", 1);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"INSERT INTO overrides (name, value) VALUES ('videos:dogfood_enabled', 'true');\"\n");
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='true' WHERE name='videos:dogfood_enabled';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.videos\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e) {
//                } catch (InterruptedException e2) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//            if (!this.val$movies.isChecked()) {
//                try {
//                    exec = Runtime.getRuntime().exec("su");
//                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("movies_status", 0);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='false' WHERE name='videos:dogfood_enabled';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.videos\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e3) {
//                } catch (InterruptedException e4) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//        }
//    }
//
//    class 100000010 implements View.OnClickListener {
//        private final MainActivity mainActivity;
//        private final ToggleButton val$books;
//        private final int val$incompatible_status;
//        private final Editor val$prefs_edit;
//
//        100000010(MainActivity mainActivity, ToggleButton toggleButton, Editor editor, int i) {
//            this.mainActivity = mainActivity;
//            this.val$books = toggleButton;
//            this.val$prefs_edit = editor;
//            this.val$incompatible_status = i;
//        }
//
//        static MainActivity access$0(100000010 100000010) {
//            return 100000010.mainActivity;
//        }
//
//        public void onClick(View view) {
//            if (this.val$books.isChecked()) {
//                try {
//                    Process exec = Runtime.getRuntime().exec("su");
//                    DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("books_status", 1);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"INSERT INTO overrides (name, value) VALUES ('books:show_testing_ui', 'true');\"\n");
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='true' WHERE name='books:show_testing_ui';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.apps.books\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e) {
//                } catch (InterruptedException e2) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//            if (!this.val$books.isChecked()) {
//                try {
//                    exec = Runtime.getRuntime().exec("su");
//                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
//                    this.val$prefs_edit.putInt("books_status", 0);
//                    this.val$prefs_edit.commit();
//                    dataOutputStream.writeBytes("sqlite3 /data/data/com.google.android.gsf/databases/gservices.db \"UPDATE overrides SET value='false' WHERE name='books:show_testing_ui';\"\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.gsf\n");
//                    dataOutputStream.writeBytes("am force-stop com.google.android.apps.books\n");
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//                    exec.waitFor();
//                } catch (IOException e3) {
//                } catch (InterruptedException e4) {
//                }
//                if (this.val$incompatible_status == 1) {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Please reboot for changes to take effect.", 1).show();
//                } else {
//                    Toast.makeText(this.mainActivity.getApplicationContext(), "Changes applied.", 1).show();
//                }
//            }
//        }
//    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int finsky_status = defaultSharedPreferences.getInt("finsky_status", 0);
        int finsky2_status = defaultSharedPreferences.getInt("finsky2_status", 0);
        int babel_status = defaultSharedPreferences.getInt("babel_status", 0);
        int babelgv_status = defaultSharedPreferences.getInt("babelgv_status", 0);
        int music_status = defaultSharedPreferences.getInt("music_status", 0);
        int books_status = defaultSharedPreferences.getInt("books_status", 0);
        int games_status = defaultSharedPreferences.getInt("games_status", 0);
        int movies_status = defaultSharedPreferences.getInt("movies_status", 0);

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
