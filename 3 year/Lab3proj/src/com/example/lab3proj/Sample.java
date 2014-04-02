package com.example.lab3proj;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Sample extends Card {

	public Sample(String name,float x, float y, Bitmap signBitmap,
			MainActivity activity) {
		super(name,x, y, signBitmap, BitmapFactory.decodeResource(
				activity.getResources(), R.drawable.sample), activity);
		// TODO Auto-generated constructor stub
	}

}
