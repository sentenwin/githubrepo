package com.screen.social;

import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


public class MainActivity extends FragmentActivity {
	
	// A helper class to keep a list of the Fragments and titles.
	class MyPageAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragments;
		private List<String> titles;
		  
		public MyPageAdapter(FragmentManager fm) {
		       super(fm);
		       this.fragments = new ArrayList<Fragment>();
		       this.titles    = new ArrayList<String>();
		}
		  
		public void addItem(String url, String title) {
			Fragment myFragment = new MyWebView();
		    Bundle args = new Bundle();
		    args.putString("url", url);
		    myFragment.setArguments(args);
		    this.fragments.add(myFragment);
		    this.titles.add(title);
		    
		}
		  
		@Override 
		public Fragment getItem(int position) {
		       return this.fragments.get(position);
		}
		  
		public CharSequence getPageTitle(int position) {
		       return this.titles.get(position);
		}
		  
		@Override
		public int getCount() {
			return this.fragments.size();
		}
	}
	
	private MyPageAdapter pageAdapter  = null;
	private ViewPager pager            = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pageAdapter = new MyPageAdapter(getSupportFragmentManager());

		// Add any number of items to the list of your Fragment
		pageAdapter.addItem("https://m.facebook.com", "Facebook");
		pageAdapter.addItem("https://mobile.twitter.com", "Twitter");
		pageAdapter.addItem("http://plus.google.com", "GooglePlus");
		pageAdapter.addItem("http://touch.www.linkedin.com", "LinkedIn");
		pageAdapter.addItem("http://www.quora.com/", "Quora");
		pageAdapter.addItem("http://www.meetup.com/", "Meetup");
		pageAdapter.addItem("https://in.yahoo.com/", "Yahoo");
		pager = (ViewPager)findViewById(R.id.pager);

		// This gives the number of Fragments loaded outside the view. 
		// Here set to the number of Fragments minus one, i.e., all Fragments loaded.
		// This might not be a good idea if there are many Fragments.
		pager.setOffscreenPageLimit(pageAdapter.getCount() - 1);
		pager.setAdapter(pageAdapter);
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig){        
	    super.onConfigurationChanged(newConfig);
	}
	@Override
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	           .setMessage("Are you sure you want to exit?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   MainActivity.this.finish();
	               }
	           })
	           .setNegativeButton("No", null)
	           .show();
	}	
}