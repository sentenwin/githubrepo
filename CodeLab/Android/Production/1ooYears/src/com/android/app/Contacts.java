package com.android.app;

import android.graphics.Bitmap;

public class Contacts {
private String name;
private String number;
private Bitmap photo;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public Bitmap getPhoto() {
	return photo;
}
public void setPhoto(Bitmap photo) {
	this.photo = photo;
}
}
