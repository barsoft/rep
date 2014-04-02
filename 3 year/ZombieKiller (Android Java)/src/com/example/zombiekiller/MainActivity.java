package com.example.zombiekiller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity 
{	
	GraphicsView myview;
	DisplayMetrics displaymetrics;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
 
		 
		displaymetrics = getResources().getDisplayMetrics();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		myview=new GraphicsView(this,this); // создаем объект myview класса GraphicsView
		setContentView(myview); // отображаем его в Activity
	}
	
	
	
	public class GraphicsView extends View
	{
		Context c;
		Map map;
		public GraphicsView(Context context,MainActivity a) 
		{ 
			super(context);
			c=context;
			map=new Map(a, 10, 10);
			map.LoadContent();
		}
		
		@Override
		protected void onDraw(Canvas canvas)
		{
			map.Draw(canvas);
			invalidate();
		}	
	
		public boolean onTouchEvent(MotionEvent event)
		{
			return map.onTouchEvent(event); 
		}
		
		
	}
}