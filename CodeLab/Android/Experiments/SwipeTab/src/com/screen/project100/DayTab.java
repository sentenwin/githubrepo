package com.screen.project100;

import com.screen.project100.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DayTab extends Fragment {
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
		    View windows = inflater.inflate(R.layout.daytab_frag, container, false);
	        ((TextView)windows.findViewById(R.id.textView)).setText("DayTab");
	        return windows;
}}
