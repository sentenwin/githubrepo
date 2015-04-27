package com.android.app;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeScreen extends Activity 
	{
	private Button btnTap;
	private EditText etEmailId,etPwd;
	String strEmailId,strPwd;
	public static final Pattern EMAIL_ADDRESS
	= Pattern.compile(
	    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
	    "\\@" +
	    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
	    "(" +
	        "\\." +
	        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
	    ")+"
	);

		
			@Override
			protected void onCreate(Bundle savedInstanceState) 
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.signin);
				btnTap=(Button)findViewById(R.id.taphere);
				etEmailId=(EditText)findViewById(R.id.et_emailid);
				etPwd=(EditText)findViewById(R.id.et_password);
				btnTap.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						strEmailId=etEmailId.getText().toString();
						strPwd=etPwd.getText().toString();
						if(strEmailId.equals(""))
						{
							Toast.makeText(WelcomeScreen.this, "Enter Your Gmail Id", 12).show();
						}
						else if(!strEmailId.equals("")&& strPwd.equals(""))
						{
							Toast.makeText(WelcomeScreen.this, "Enter Your Password", 12).show();
						}
						else
						{
							if(validEmail(strEmailId))
							{
								if(strEmailId.contains("gmail.com"))
								{
									startActivity(new Intent(WelcomeScreen.this,TabBar.class));
								}
							}
							else
							{
								Toast.makeText(WelcomeScreen.this, "Check your ID", 12).show();
							}
						}
						
					}
				});
			}	
			 private boolean validEmail(String email) {
				    Pattern pattern = Patterns.EMAIL_ADDRESS;
				    return pattern.matcher(email).matches();
				}

}
