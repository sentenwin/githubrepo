package com.android.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MoreTabActivity extends Activity{
	ListView listTabs;
@Override
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablist);
		listTabs=(ListView)findViewById(R.id.list_tabs);
}
}
