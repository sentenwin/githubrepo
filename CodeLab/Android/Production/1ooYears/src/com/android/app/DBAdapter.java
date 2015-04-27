package com.android.app;

import java.util.ArrayList;
import java.util.HashMap;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

	public class DBAdapter extends SQLiteOpenHelper {
		private static final String LOGCAT = null;

		public DBAdapter(Context applicationcontext) {
			super(applicationcontext, "100yrsapp.db", null, 1);
			Log.d(LOGCAT, "Created");
		}


		@Override
		public void onCreate(SQLiteDatabase database) {
			String query,query1;
			query = "CREATE TABLE friends (phonenumber TEXT primary key,name TEXT,tabcount integer,tabdate TEXT)";
		    
	        database.execSQL(query);
		}

		@Override
		public void onUpgrade(SQLiteDatabase database, int version_old,
				int current_version) {
			String query, query1;
			query = "DROP TABLE IF EXISTS friends";
			
			database.execSQL(query);
	     
	        onCreate(database);
		}
		public long addFriends(String phonenumber,String name,int tabcount,String date) {

			SQLiteDatabase database = this.getWritableDatabase();
			ContentValues cVal = new ContentValues();			
			cVal.put("phonenumber",phonenumber);
			
			cVal.put("name",name);
			cVal.put("tabcount", tabcount);
			cVal.put("tabdate",date);
		
			
			
		return database.insert("friends", null, cVal);
	}
		public boolean Update(String contactnumber,int tabcount,String date)
		{
			SQLiteDatabase db= this.getWritableDatabase();	 
			ContentValues values=new ContentValues();
			    values.put("tabcount", tabcount);
			    values.put("tabdate", date);
			 
			return db.update("friends", values, "phonenumber='"+contactnumber.trim()+"'",null)>0;		
		}
		public ArrayList<HashMap<String, String>> getAllFriendsTab() {
			ArrayList<HashMap<String, String>> tabList;
			tabList = new ArrayList<HashMap<String, String>>();
			String selectQuery = "SELECT  * FROM friends";
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		    if (cursor.moveToFirst()) {
		        do {
		        	HashMap<String, String> tabMap = new HashMap<String, String>();
		        	tabMap.put("phonenumber", cursor.getString(0));
		        	tabMap.put("name", cursor.getString(1));
		        	tabMap.put("tabcount", cursor.getString(2));
		        	tabMap.put("tabdate", cursor.getString(3));
				
		            tabList.add(tabMap);
		        } while (cursor.moveToNext());
		    }
		 
		    // return contact list
		    return tabList;
		}
	/*	public ArrayList<HashMap<String, String>> getMostTab(String date) {
			ArrayList<HashMap<String, String>> tabList;
			tabList = new ArrayList<HashMap<String, String>>();
			String selectQuery = "SELECT  * FROM friends group by "+date;
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		    if (cursor.moveToFirst()) {
		        do {
		        	HashMap<String, String> tabMap = new HashMap<String, String>();
		        	tabMap.put("phonenumber", cursor.getString(0));
		        	tabMap.put("name", cursor.getString(1));
		        	tabMap.put("tabcount", cursor.getString(2));
		        	tabMap.put("date", cursor.getString(3));
				
		            tabList.add(tabMap);
		        } while (cursor.moveToNext());
		    }
		 
		    // return contact list
		    return tabList;
		}*/
	
		public HashMap getMostTab(String date) {
		HashMap tabMap;
			tabMap = new HashMap();
			String selectQuery = "SELECT name,phonenumber,tabdate,MAX(tabcount) as tab FROM friends group by tabdate";
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		   if(cursor.moveToFirst())
		    {
		        	
		        	tabMap.put("phonenumber", cursor.getString(cursor.getColumnIndex("phonenumber")));
		        	tabMap.put("name", cursor.getString(cursor.getColumnIndex("name")));
			 
		        	tabMap.put("tabcount",cursor.getInt(cursor.getColumnIndex("tab")));
		        	tabMap.put("tabdate", cursor.getString(cursor.getColumnIndex("tabdate")));
		    }
		            
		   
		 
		    // return contact list
		    return tabMap;
		}
		public HashMap getWeekTab(String currentdate,String weekdate) {
			HashMap tabMap;
				tabMap = new HashMap();
				String selectQuery = "SELECT name,phonenumber,tabdate,MAX(tabcount) as tab FROM friends where tabdate BETWEEN '"+currentdate+"' AND '" + weekdate+"'";
			    SQLiteDatabase db = this.getWritableDatabase();
			    Cursor cursor = db.rawQuery(selectQuery, null);
			   if(cursor.moveToFirst())
			    {
			        	
			        	tabMap.put("phonenumber", cursor.getString(cursor.getColumnIndex("phonenumber")));
			        	tabMap.put("name", cursor.getString(cursor.getColumnIndex("name")));
				 
			        	tabMap.put("tabcount",cursor.getInt(cursor.getColumnIndex("tab")));
			        	tabMap.put("tabdate", cursor.getString(cursor.getColumnIndex("tabdate")));
			    }
			            
			   
			 
			    // return contact list
			    return tabMap;
			}
		public Cursor getAllInfo()
		{
			SQLiteDatabase database = this.getReadableDatabase();
			return database.query("friends", new String[] {  "phonenumber","name","tabcount","tabdate"}, null, null, null,
					null, null);
		}
		public int AvoidDuplicateRoomnumber(String roomnumber)
		{
		    try
		    {
		        int i = 0;
		        Cursor c = null;
		        SQLiteDatabase db = this.getReadableDatabase();
		        c = db.rawQuery("select * from Meeting where roomnumber =" + "\""+ roomnumber.trim()+"\"", null);
		        c.moveToFirst();
		        i = c.getCount(); 
		        
		        c.close(); 
		        return i;
		    }
		    catch(Exception e)
		    {
		        e.printStackTrace();
		    }
		    return 0;
		}
		public int AvoidDuplicateIP(String ip)
		{
		    try
		    {
		        int i = 0;
		        Cursor c = null;
		        SQLiteDatabase db = this.getReadableDatabase();
		        c = db.rawQuery("select * from Meeting where ip =" + "\""+ ip.trim()+"\"", null);
		        c.moveToFirst();
		        i = c.getCount(); 
		        
		        c.close(); 
		        return i;
		    }
		    catch(Exception e)
		    {
		        e.printStackTrace();
		    }
		    return 0;
		}
		public int FindContactNumber(String contactNumber)
		{
			int count = 0;
			  try
			    {
			       
				  SQLiteDatabase db = this.getReadableDatabase();
			      Cursor  c = db.rawQuery("select * from friends where phonenumber='" + contactNumber.trim()+"'", null);
			        c.moveToFirst();
			        
//			       String contact = c.getString(0);
			        System.out.println(c.getCount()+"//////////////////");
			        count=c.getCount();
			        c.close(); 
			        
			    }
			    catch(Exception e)
			    {
			        e.printStackTrace();
			    }
			  System.out.println(count);
			  return count;
		}
		public int FindTabCount(String contactNumber)
		{
			int count=0;
			  try
			    {
			       
				  SQLiteDatabase db = this.getReadableDatabase();
			      Cursor  c = db.rawQuery("select * from friends where phonenumber='" + contactNumber.trim()+"'", null);
			        c.moveToNext();
			        
			 count = c.getInt(2);
			        System.out.println(c.getCount()+"//////////////////");
			        c.close(); 
			        return count;
			    }
			    catch(Exception e)
			    {
			        e.printStackTrace();
			    }
			  return count;
		}
		public HashMap<String, String> getAll() {
			HashMap<String, String> sessionList = new HashMap<String, String>();
			SQLiteDatabase database = this.getReadableDatabase();
			String selectQuery = "SELECT * FROM Meeting ";
			Cursor cursor = database.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
//					 wordList.put("Password", cursor.getString(2)); 
					sessionList.put("meetingname", cursor.getString(1));
					sessionList.put("starttime", cursor.getString(2));
					sessionList.put("endtime", cursor.getString(3));
					sessionList.put("roomnumber", cursor.getString(4));
					sessionList.put("ip", cursor.getString(5));
					sessionList.put("flag", cursor.getString(6));
					
				} while (cursor.moveToNext());
			}
			return sessionList;
		}
		public ArrayList<HashMap<String, String>> getAllMeeting() {
			ArrayList<HashMap<String, String>> wordList;
			wordList = new ArrayList<HashMap<String, String>>();
			String selectQuery = "SELECT  * FROM Meeting";
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		    if (cursor.moveToFirst()) {
		        do {
		        	HashMap<String, String> sessionList = new HashMap<String, String>();
		        	sessionList.put("Id", cursor.getString(0));
		        	sessionList.put("meetingname", cursor.getString(1));
					sessionList.put("starttime", cursor.getString(2));
					sessionList.put("endtime", cursor.getString(3));
					sessionList.put("roomnumber", cursor.getString(4));
					sessionList.put("ip", cursor.getString(5));
					sessionList.put("flag", cursor.getString(6));
		            wordList.add(sessionList);
		        } while (cursor.moveToNext());
		    }
		 
		    // return contact list
		    return wordList;
		}
	
		
		public void deleteMeeting(long id) {
			Log.d(LOGCAT,"delete");
			SQLiteDatabase database = this.getWritableDatabase();	 
			String deleteQuery = "DELETE FROM  Meeting where Id='"+ id +"'";
			Log.d("query",deleteQuery);		
			database.execSQL(deleteQuery);
		}
		public void deleteall() {
			Log.d(LOGCAT,"deleteAll");
			SQLiteDatabase database = this.getWritableDatabase();
			database.execSQL("delete from "+ "Meeting");
			/*String deleteQuery = "DELETE FROM  employe where empId='"+ id +"'";
			Log.d("query",deleteQuery);	*/	
			/*database.execSQL(deleteQuery);*/
		}
		
		public int getTotalTabCount()
		{
			int count=0;
			  try
			    {
			       
				  SQLiteDatabase db = this.getReadableDatabase();
			      Cursor  c = db.rawQuery("select sum(tabcount) from friends", null);
			        c.moveToNext();
			        
			 count = c.getInt(0);
			        System.out.println(count+"//////////////////");
			        c.close(); 
			        return count;
			    }
			    catch(Exception e)
			    {
			        e.printStackTrace();
			    }
			  return count;
		}
	
	}
