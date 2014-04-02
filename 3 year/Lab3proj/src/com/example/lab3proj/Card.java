package com.example.lab3proj;

import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Card extends Base {

	Base sign;
	public String name;
	public Card(String name,float x, float y, Bitmap signBitmap, Bitmap cardBitmap, MainActivity activity) {
		super(x, y, 70, 100, cardBitmap,activity);
		this.name=name;
		sign = new Base(x + 20, y + 35, 30, 30, signBitmap,activity);
	}

	@Override
	public void loadFromAsset(GL10 gl, Context context) {
		super.loadFromAsset(gl, context);
		sign.loadFromAsset(gl, context);
	}

	@Override
	public void draw(GL10 gl) {
		super.draw(gl);
		
		update();
		sign.draw(gl);
	}
	
	public void update()
	{
		sign.x=this.x+20;
		sign.y=this.y+35;
	}

}
