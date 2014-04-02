package com.example.lab3proj;


import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.view.MotionEvent;

public class Instance extends Card {

	public boolean selected;

	public Instance(String name, Bitmap signBitmap, MainActivity activity) {
		super(name, 0, 0, signBitmap, BitmapFactory.decodeResource(
				activity.getResources(), R.drawable.instance), activity);
	}

	public float startCardTranslateX;
	public float startCardTranslateY;
	public float oldCardX;
	public float oldCardY;
	public MotionEvent oldEvent;
	public boolean dragInProcess;

	

}
