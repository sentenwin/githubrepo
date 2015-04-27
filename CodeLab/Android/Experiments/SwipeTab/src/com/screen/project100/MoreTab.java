package com.screen.project100;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MoreTab extends Fragment{
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View moretab = inflater.inflate(R.layout.moretab_frag, container, false);
	        ((TextView)moretab.findViewById(R.id.textView)).setText("MoreTab");
	        return moretab;
}}
