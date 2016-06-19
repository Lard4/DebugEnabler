package com.r3pwn.LetMeMakeYouSomeSandwiches;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.View.*;
import android.view.*;

public class CustomDebug extends Activity
{
	EditText itemNameET;
	EditText itemValueET;
	EditText associatedAppET;
	Button configureBtn;
	Button setBtn;
	String name;
	String value;
	String app;

    @Override
    public void onCreate(Bundle bundle)
	{
        super.onCreate(bundle);
        setContentView(R.layout.layout_custom_entry);
		itemNameET = (EditText)findViewById(R.id.itemNameEditText);
		itemValueET = (EditText)findViewById(R.id.itemValueEditText);
		associatedAppET = (EditText)findViewById(R.id.itemAppEditText);
		configureBtn = (Button)findViewById(R.id.configureButton);
		setBtn = (Button)findViewById(R.id.setButton);

		setBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View p1)
				{
					configureBtn.setOnClickListener(new UpdateDatabase(CustomDebug.this, configureBtn, 
																	   itemNameET.getText().toString(),
																	   itemValueET.getText().toString(),
																	   associatedAppET.getText().toString(), true));
				}
			});
    }
}
