package com.android.app;

/***
 *    Application Name : SMSApp 
 *    Author : Vimal Rughani
 *    Website : http://pulse7.net
 *    For more details visit http://pulse7.net/android/contacts-content-provider-android/
 */

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ContactsListActivity extends Activity {

	// Cursor Adapter for storing contacts data
	SimpleCursorAdapter adapter;

	// List View Widget
	ListView lvContacts;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allcontacts);

		// Init ListView
		lvContacts = (ListView) findViewById(R.id.lvContacts);

		// Initialize Content Resolver object to work with content Provider
		ContentResolver cr = getContentResolver();

		// Read Contacts
		Cursor c = cr.query(ContactsContract.Contacts.CONTENT_URI,
				new String[] { ContactsContract.Contacts._ID,
						ContactsContract.Contacts.DISPLAY_NAME }, null, null,
				null);
/*		 if (profile != null) {
	            // retrieve the contact photo as a Bitmap
	            Uri uri = ContentUris.withAppendedId(People.CONTENT_URI, id);
	            Bitmap bitmap = People.loadContactPhoto(this, uri, R.drawable.ic_launcher, null);
	 
	            // set it here in the ImageView
	            profile.setImageBitmap(bitmap);
	        }*/

		// Attached with cursor with Adapter
		adapter = new SimpleCursorAdapter(this, R.layout.row, c,
				new String[] { ContactsContract.Contacts.DISPLAY_NAME },
				new int[] { R.id.cname });

		// Display data in listview
		lvContacts.setAdapter(adapter);

		// On Click of each row of contact display next screen with contact
		// number
		lvContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v,
					int position, long id) {

				Cursor c = (Cursor) adapter.getItemAtPosition(position);

				String cid = c.getString(c
						.getColumnIndex(ContactsContract.Contacts._ID));

				ContentResolver cr = getContentResolver();
				 c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
						new String[] { cid }, null);
				c.moveToFirst();
				String number = c.getString(c
						.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				Toast.makeText(ContactsListActivity.this, "number "+number, 12).show();
				

			}
		});
	}
}