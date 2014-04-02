package com.example.lab3proj;

import javax.microedition.khronos.opengles.GL10;


import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;

public class GoDownButton {
	public Base goDownButton;
	public TextControl goDownText;
	public MainActivity activity;
	
	public GoDownButton(MainActivity activity,float zoomX,float zoomY)
	{
		goDownButton = new Base(0, zoomY - zoomY / 7, zoomX, zoomY / 7,
				BitmapFactory.decodeResource(activity.getResources(),
						R.drawable.go_down), activity);

		goDownText = new TextControl("Go Down",0, 0, 120, 100, activity);
		this.activity=activity;
	}
	public void loadFromAsset(GL10 gl,Context activity)
	{
		goDownButton.loadFromAsset(gl, activity);
		goDownText.loadFromAsset(gl, activity);
	}
	
	public void draw(GL10 gl)
	{
		goDownButton.draw(gl);
		update();
		goDownText.draw(gl);
	}
	
	public void update()
	{
		goDownText.x=activity.renderer.zoomX/2-goDownText.width/2;
		goDownText.y=goDownButton.y-goDownButton.height/3;
	}
}
