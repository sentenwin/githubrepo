package com.android.app;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.Contacts.Phones;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class ContactsChooser extends Activity {
	GridView gridview;
	MySimpleCursorAdapter adapter;
	String number;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactslist);
		gridview = (GridView)findViewById(R.id.gridview);

		
//		ContentResolver cr = getContentResolver();
		 Cursor c = getNamesAndPictures();
		    c.moveToFirst();   
		
	/*	Cursor c = cr.query(ContactsContract.Contacts.CONTENT_URI,
				new String[] { ContactsContract.Contacts._ID,
						ContactsContract.Contacts.DISPLAY_NAME }, "starred=?",new String[]{"1"},
				null);*/

		
		adapter = new MySimpleCursorAdapter(this, R.layout.row_item, c,
				new String[] { Phones.NAME },
				new int[] { R.id.name});

		// Display data in gridview
		gridview.setAdapter(adapter);
        startManagingCursor(c);
		
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v,
					int position, long id) {

				Cursor c = (Cursor) adapter.getItemAtPosition(position);

				String cid = c.getString(c
						.getColumnIndex(  Phones.PERSON_ID));
				ContentResolver cr = getContentResolver();
				 c = cr.query(Phones.CONTENT_URI,
						new String[] { Phones.NUMBER },
						Phones.PERSON_ID + "=?",
						new String[] { cid }, null);
				c.moveToFirst();
				
				 number = c.getString(c.getColumnIndex(Phones.NUMBER));
//				Toast.makeText(ContactsChooser.this, ""+number, 12).show();
			

			
		/*		Intent iCInfo = new Intent(getApplicationContext(), CInfo.class);
				iCInfo.putExtra("cid", cid);
				startActivity(iCInfo);*/
sendSMS(number);
			}

			private void sendSMS(final String number) {
				// TODO Auto-generated method stub
			
				/*	 final Dialog dialog = new Dialog(ContactsChooser.this);
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
							Toast.makeText(getApplicationContext(), "Tabbed Successfully! ",
										Toast.LENGTH_LONG).show();
							dialog.dismiss();
							  } catch (Exception e) {
									Toast.makeText(getApplicationContext(),
										"SMS faild, please try again later!",
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
				    dialog.show();
				*/
				AlertDialog.Builder alert=new AlertDialog.Builder(ContactsChooser.this);
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
	     Cursor cursor = managedQuery(Phones.CONTENT_URI, projection, "starred=?", new String[]{"1"}, sortOrder);
	     return cursor;
	}
}
