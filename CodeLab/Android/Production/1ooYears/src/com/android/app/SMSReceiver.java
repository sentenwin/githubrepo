package com.android.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver{

	  // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    DBAdapter db;
  static  int count;
  String date;
     
    public void onReceive(Context context, Intent intent) {
     
    	db=new DBAdapter(context);
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
 
        try {
             
            if (bundle != null) {
                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) {
                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    
                     
                    String senderNum = phoneNumber;
                  
                    
                    String message = currentMessage.getDisplayMessageBody();

                   /* Calendar currentDate = Calendar.getInstance(); //Get the current date
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
                    String dateNow = formatter.format(currentDate.getTime());*/
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd", Locale.getDefault());
                    Date date = new Date();
                  String dateNow=dateFormat.format(date);
//                    System.out.println("Now the date is :=>  " + dateNow);
                   
                    String name=getContactName(context, senderNum);
              	  System.out.println("Name : "+name);
                    if(message.equals("A"))
                    {
                    	 int existNumber=db.FindContactNumber(senderNum);
                    	 System.out.println("KKKKKK"+existNumber);
                    	 if(existNumber==0)
                    	 {
                    	  long ii=db.addFriends(senderNum, "name",count+1,dateNow);
                    	  if(ii>0)
                    	  {
                    		  System.out.println("Number added");
                    	  }
//                    	  deleteMessage(context,senderNum);
                    	  System.out.println("Not Exist");
                    	 }
                    	 else
                    	 {
                    		 System.out.println("Exist");
                    		 count=db.FindTabCount(senderNum);
                    		 System.out.println("Tab count : "+count);
                    		boolean s = db.Update(senderNum, count+1, dateNow); 
                    		System.out.println("flag : "+s);
                    	 }
                    }
                 
                  
                  /*  Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                     
 
                   // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                                 "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();*/
                     
                } // end for loop
              } // bundle is null
 
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
             
        }
    }
    private int deleteMessage(Context context,String senderNum) {
		// TODO Auto-generated method stub
    	 int result = 0;
    	try
    	{
    	result = context.getContentResolver().delete(Uri.parse("content://sms/"+senderNum),
                 null, null);
    	System.out.println("Delete Message Done "+result);
    	return result;
    	}catch(Exception e)
    	{
    		
    	}
		return result;
	}
	public static String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if(cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME));
        }

        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return contactName;
    }
   /* private int deleteMessage(Context context, SmsMessage msg) {
        Uri deleteUri = Uri.parse("content://sms");
        int count = 0;
        Cursor c = context.getContentResolver().query(deleteUri, null, null,
                null, null);
        while (c.moveToNext()) {
            try {
                // Delete the SMS
                String pid = c.getString(5); // Get id;
                String uri = "content://sms/" + pid;
                count = context.getContentResolver().delete(Uri.parse(uri),
                        null, null);
            } catch (Exception e) {
            }
        }
        return count;
    }*/

}
