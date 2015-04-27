package com.screen.project100;

import com.screen.project100.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class QuickTab extends Fragment {
	

	private static final Uri CONTACTS_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
	private static final int PICK_CONTACT_REQUEST = 0;
	static String TAG = "QuickTab";	
	private static final String CONTACTS_ID = ContactsContract.Contacts._ID;
	private static  String number = "";
	
	
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View android = inflater.inflate(R.layout.quicktab_frag, container, false);
	        
     
	        
	        ((TextView)android.findViewById(R.id.textView)).setText("QuickTab");
	        
			final Button button = (Button) android.findViewById(R.id.people);
			button.setOnClickListener(new Button.OnClickListener() {

				// Called when user clicks the Show Map button
				@Override
				public void onClick(View v) {
					try {
						// Create Intent object for picking data from Contacts database
						Intent intent = new Intent(Intent.ACTION_PICK,
								CONTACTS_CONTENT_URI);
						
						// Use intent to start Contacts application
						// Variable PICK_CONTACT_REQUEST identifies this operation 
						startActivityForResult(intent, PICK_CONTACT_REQUEST);
						
					} catch (Exception e) {
						// Log any error messages to LogCat using Log.e()
						Log.e(TAG, e.toString());
					}
				}
			});	        
	        return android;
		}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		// Ensure that this call is the result of a successful PICK_CONTACT_REQUEST request
		if (resultCode == Activity.RESULT_OK
				&& requestCode == PICK_CONTACT_REQUEST) {

			// These details are covered in the lesson on ContentProviders
			ContentResolver cr = getActivity().getContentResolver();
			Cursor cursor = cr.query(data.getData(), null, null, null, null);

			if (null != cursor && cursor.moveToFirst()) {
				String id = cursor
						.getString(cursor.getColumnIndex(CONTACTS_ID));
				
				Cursor phones = cr.query(Phone.CONTENT_URI,  null, Phone.CONTACT_ID +" = " + id, null, null);

				if (null != phones && phones.moveToNext()) {
					number = phones.getString(phones.getColumnIndex(Phone.NUMBER));
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setMessage("Do you want to tab?");
					builder.setTitle("Tab confirmation");
					builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {								
							    SmsManager sms = SmsManager.getDefault();
							    sms.sendTextMessage(number, null, "100yrs", null, null);
							    Toast.makeText(getActivity(), "Tabbed successfull to "+ number, Toast.LENGTH_SHORT).show();
				           }
				       });
					builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				        	   Toast.makeText(getActivity(), "Not tabbed", Toast.LENGTH_SHORT).show();
				           }
				       });
					AlertDialog dialog = builder.create();
					dialog.show();
					

				}

				if (null != phones)
					phones.close();
			}
			if (null != cursor)
				cursor.close();
		}
	}
}

