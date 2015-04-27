package com.screen.iooyrs;

import com.screen.iooyrs.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class People extends Fragment {
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View ios = inflater.inflate(R.layout.people_frag, container, false);
	        ((TextView)ios.findViewById(R.id.textView)).setText("People");
	        return ios;
}}
