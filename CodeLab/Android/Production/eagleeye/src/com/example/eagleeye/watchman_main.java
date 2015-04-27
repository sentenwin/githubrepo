package com.example.eagleeye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class watchman_main extends ActionBarActivity {
	public static final String EXTRA_MESSAGE = "com.example.eagleeye.MESSAGE";
	public String number;
	public EditText phno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.watchman_mode_main);
		Button watch = (Button)findViewById(R.id.startwatch);

		
		
		
		watch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				sendPhoneNo(v);
				
			}
		});

	}

	public void sendPhoneNo(View view) {
	    Intent intent = new Intent(this, SoundAlert.class);
	    EditText editText = (EditText) findViewById(R.id.phoneno);
	    if (editText != null) { 
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	    } else {
	    	Toast.makeText(getApplicationContext(), "Please enter the correct phone No. .", 
      			  Toast.LENGTH_LONG).show();
	    }
	}
			
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
	
	


