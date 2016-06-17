package com.r3pwn.LetMeMakeYouSomeSandwiches;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

public class NoSQLDialog implements OnClickListener {
    private final Activity mainActivity;

    NoSQLDialog(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        mainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=ptSoft.util.sqlite3forroot")));
    }

    void noBusyboxDialog() {
        Builder builder = new Builder(mainActivity);
        builder.setTitle(R.string.dialog_error_title);
        builder.setMessage(R.string.dialog_error);
        builder.setPositiveButton(R.string.dialog_yes, new NoSQLDialog(mainActivity));
        builder.create();
        builder.setCancelable(false);
        builder.show();
    }
}
