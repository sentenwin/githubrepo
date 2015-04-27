package com.android.app;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.Contacts.People;
import android.provider.Contacts.Phones;

import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ImageView;

public class MySimpleCursorAdapter extends SimpleCursorAdapter {

	 
	  public MySimpleCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
	}

	@Override
	  public void bindView(View view, Context context, Cursor cursor) {
	      ImageView imageView = (ImageView) view.findViewById(R.id.photo);

	      int id = cursor.getColumnIndex(Phones.PERSON_ID);
	      Uri uri = ContentUris.withAppendedId(Phones.CONTENT_URI, cursor.getLong(id));
	      String uriPhoto = uri.toString();
	      String uriPeople = uriPhoto.replace("phones", "people");

	      Uri uriFinal = Uri.parse(uriPeople);

	      Bitmap bitmap = People.loadContactPhoto(context, uriFinal, R.drawable.contact, null);
	      imageView.setImageBitmap(bitmap);
	      super.bindView(view, context, cursor);
	      
	  }
	}
