package com.example.zombiekiller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;

public class Base 
{
	public PointF position;
    public float height, width;
    public float angle;
    public PointF origin;
    Bitmap texture;
    protected String name;
    RectF rect;
    MainActivity a;
    public Base(MainActivity _a)
    {
    	a=_a;
    	position=new PointF();
    	rect=new RectF();
    }
    
    public Base(MainActivity _a,Bitmap _texture,float _x,float _y,float _width,float _height)
    {
    	a=_a;
    	texture=_texture;
    	position=new PointF(_x,_y);
    	width=_width;
    	height=_height;
    	rect=new RectF();
    }
    
    public void Draw(Canvas _canvas)
    {
    	Matrix msc=new Matrix();
    	Matrix mrot=new Matrix();
    	Matrix mtr=new Matrix();
    	Matrix m=new Matrix();
    	
    	msc.setScale(rect.width()/texture.getWidth(), rect.height()/texture.getHeight());
    	origin=new PointF(rect.left/rect.width()*texture.getWidth()+texture.getWidth()/2,rect.top/rect.height()*texture.getHeight()+texture.getHeight()/2);
		mtr.setTranslate(rect.left/rect.width()*texture.getWidth(), rect.top/rect.height()*texture.getHeight());
		mrot.setRotate(angle,origin.x,origin.y);

		m.setConcat(msc, mrot);
		m.setConcat(m, mtr);
		_canvas.drawBitmap(texture,m, null);
    }
    
    public void UpdatePosition()
    {
    	float x=(float) (position.x / 100.0 * a.myview.map.cam.scale - a.myview.map.cam.world.x);
    	float y=(float) (position.y / 100.0 * a.myview.map.cam.scale - a.myview.map.cam.world.y);
        rect = new RectF(x,y ,x+(int)(width / 100.0 * a.myview.map.cam.scale),y+(int)( height / 100.0 * a.myview.map.cam.scale));
    }
}
