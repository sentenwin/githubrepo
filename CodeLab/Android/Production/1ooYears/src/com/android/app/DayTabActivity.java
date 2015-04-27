package com.android.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DayTabActivity extends Activity{
	TextView tvTotalTabCount,tvMaxTabCount,tvName,tvWeekTabCount,tvWName;
	Button btnMore;
	DBAdapter db=new DBAdapter(this);
	boolean flag;
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.daytab);
	      btnMore=(Button)findViewById(R.id.btnMore);
	      int re=db.FindContactNumber("+919842549550");
	      System.out.println("RRRRRRRRRRRRRRRRRR"+re);
	      int r=db.FindTabCount("+919842549550");
	      System.out.println("RRRRRRRRRRRRRRRRRR"+r);
	    /*  boolean f=db.Update("+919842549550", 2, "2014-09-09");
	      System.out.println("FFFFFFFFFFFFFf"+f);*/
	   Cursor c=db.getAllInfo();
	   System.out.println("UUUUUUUUUUUUUUU"+c.getCount());
	  if(c.getCount()<=0)
	  {
		  return;
	  }
	      int n= db.getTotalTabCount();
	      tvTotalTabCount=(TextView)findViewById(R.id.tv_totaltabcount);    
	      tvMaxTabCount=(TextView)findViewById(R.id.maxtabcount);
	      tvName=(TextView)findViewById(R.id.tvmname);
	      tvWeekTabCount=(TextView)findViewById(R.id.weektabcount);
	      tvWName=(TextView)findViewById(R.id.tvwname);
	      tvTotalTabCount.setText(""+n);
	      SimpleDateFormat dateFormat = new SimpleDateFormat(
                  "yyyy-MM-dd", Locale.getDefault());
          Date date = new Date();
        String dateNow=dateFormat.format(date);
//          System.out.println("Now the date is :=>  " + dateNow);
        System.out.println("DDDDDDDDDDDDDDDDDDD"+dateNow);
	   HashMap map = db.getMostTab(dateNow);
	    System.out.println(map);
//	    HashMap<String, String> map1=map.get(0);
//	    System.out.println(map1);
	    tvMaxTabCount.setText(""+map.get("tabcount"));
	    tvName.setText(""+map.get("name"));
	   
	    map=db.getWeekTab("2014-09-01",dateNow);
	    System.out.println("jjjjjjjjjjjjj"+map);
	    tvWeekTabCount.setText(""+map.get("tabcount"));
	    tvWName.setText(""+map.get("name"));
	   btnMore.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			flag=true;
			switchTabInActivity(3);
			
		}
	}) ;
	  }
	  public void switchTabInActivity(int indexTabToSwitchTo){
//		  if(flag== trues)
		  
         TabBar parentActivity;
          parentActivity = (TabBar) this.getParent();
          parentActivity.switchTab(indexTabToSwitchTo);
}
}
