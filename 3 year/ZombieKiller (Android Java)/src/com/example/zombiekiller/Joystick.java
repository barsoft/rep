package com.example.zombiekiller;

import android.graphics.Canvas;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;

public class Joystick extends Static 
{    
	JoystickControl jk;
    public Joystick(MainActivity _a,float _x,float _y)
    {
    	super(_a);
    	a=_a;

    	width=100;
    	height=100;    	

    	position=new PointF(_x,_y-height);
    	
    	jk=new JoystickControl(a,_x+width/2,_y-height/2);

        angle = 0f;
    }

    public void LoadContent()
    {
    	texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.joystick);
    	jk.LoadContent();
    	UpdatePosition();
    }


    public void Draw(Canvas _canvas)
    {

    	super.Draw(_canvas);
		jk.Draw(_canvas);
    }


    public void Update()
    {
    	
    }
}
