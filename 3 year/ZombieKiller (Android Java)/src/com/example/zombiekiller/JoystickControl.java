package com.example.zombiekiller;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;

public class JoystickControl extends Base 
{
	float radialAngle;
    public JoystickControl(MainActivity _a,float _x,float _y)
    {
    	super(_a);
    	a=_a;
    	
    	width=25;
    	height=25;
    	
    	position=new PointF(_x-width/2,_y-height/2);
    
        angle = 0f;
    }

    public void LoadContent()
    {
    	texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.joystick_control);
    }


    @SuppressLint({ "FloatMath"})
	public void Draw(Canvas _canvas)
    {
    	Matrix msc=new Matrix();
    	Matrix mrot=new Matrix();
    	Matrix mtr=new Matrix();
    	Matrix m=new Matrix();

    	msc.setScale(width/texture.getWidth(), height/texture.getHeight());
    	
        float dispx=(float) (position.x+Math.cos(radialAngle+1.57)*30);
        float dispy=(float) (position.y+Math.sin(radialAngle+1.57)*30);
        origin=new PointF(position.x/width*texture.getWidth()+texture.getWidth()/2,position.y/height*texture.getHeight()+texture.getHeight()/2);
		mtr.setTranslate(dispx/width*texture.getWidth(), dispy/height*texture.getHeight());
		
		mrot.setRotate(angle,origin.x,origin.y);

		m.setConcat(msc, mrot);
		m.setConcat(m, mtr);
		_canvas.drawBitmap(texture,m, null);
    }


    public void Update()
    {
    	
    }
}
