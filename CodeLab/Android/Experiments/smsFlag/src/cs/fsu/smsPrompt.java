package cs.fsu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

public class smsPrompt extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		Log.i("cs.fsu", "smsActivity : onCreate");
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
	}

	public void onResume(){
		Log.i("cs.fsu", "smsActivity : onResume");
		super.onResume();
		setContentView(R.layout.main);

		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("mySMS");

		if (bundle != null) {
			Object[] pdus = (Object[])bundle.get("pdus");
			SmsMessage sms = SmsMessage.createFromPdu((byte[])pdus[0]);
			Log.i("cs.fsu", "smsActivity : SMS is <" +  sms.getMessageBody() +">");
			
			//strip flag
			String message = sms.getMessageBody();
			while (message.contains("FLAG"))
				message = message.replace("FLAG", "");
			
			TextView tx = (TextView) findViewById(R.id.TextBox);
			tx.setText(message);			
		} else
			Log.i("cs.fsu", "smsActivity : NULL SMS bundle");
	}

}

