package com.android.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.Contacts.Phones;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity 
{
	String number;
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Cursor names = getNamesAndPictures();
    names.moveToFirst();        
    ListAdapter adapter = new MySimpleCursorAdapter(this, R.layout.contacts,
                names, new String[] {Phones.NAME, Phones.NUMBER}, new int[] {
                R.id.tvName, R.id.tvNumber});
    setListAdapter(adapter);
    startManagingCursor(names);
//    Log.i(DEBUG_TAG, "Mi numero: " + miNumeroTelefono());   
    ListView lv=getListView();
    lv.setTextFilterEnabled(true);
    lv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Cursor c = (Cursor) parent.getItemAtPosition(position);

			String cid = c.getString(c
					.getColumnIndex(  Phones.PERSON_ID));
			ContentResolver cr = getContentResolver();
			 c = cr.query(Phones.CONTENT_URI,
					new String[] { Phones.NUMBER },
					Phones.PERSON_ID + "=?",
					new String[] { cid }, null);
			c.moveToFirst();
			
			 number = c.getString(c.getColumnIndex(Phones.NUMBER));
//			Toast.makeText(MainActivity.this, ""+number, 12).show();
			sendSMS(number);
		}
		private void sendSMS(final String number) {
			// TODO Auto-generated method stub
		
			/*	 final Dialog dialog = new Dialog(MainActivity.this);
			        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			        dialog.setContentView(R.layout.tabdialog);
			    ImageView imgOk = (ImageView)dialog.findViewById(R.id.img_ok);
			    ImageView imgCancel = (ImageView)dialog.findViewById(R.id.img_cancel);
			    imgOk.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try {
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(number, null, "###", null, null);
						Toast.makeText(getApplicationContext(), "Tabbed Successfully!",
									Toast.LENGTH_LONG).show();
						dialog.dismiss();
						  } catch (Exception e) {
								Toast.makeText(getApplicationContext(),
									"Sorry failed, please try again later!",
									Toast.LENGTH_LONG).show();
								e.printStackTrace();
							  }
					}
				});
			    imgCancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
			    dialog.show();*/
			AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
			alert.setTitle("Confirmation");
			alert.setMessage("Do you want to tab your friend?");
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					try {
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(number, null, "  ", null, null);
						Toast.makeText(getApplicationContext(), "Tabbed Successfully!",
									Toast.LENGTH_LONG).show();
						dialog.dismiss();
						  } catch (Exception e) {
								Toast.makeText(getApplicationContext(),
									"Sorry failed, please try again later!",
									Toast.LENGTH_LONG).show();
								e.printStackTrace();
							  }
					
				}
			});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		alert.show();
		}
	});

}

public Cursor getNamesAndPictures(){
     String[] projection = new String[] {
             Phones.NUMBER,
             Phones.PERSON_ID,
             People.NAME,
             People._ID
     };
     String selection = Phones.NAME + "!='null'";
     String sortOrder = Phones.NAME + " COLLATE LOCALIZED ASC";
     Cursor cursor = managedQuery(Phones.CONTENT_URI, projection, selection, null, sortOrder);
     return cursor;
}
}