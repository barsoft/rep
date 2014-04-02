package com.example.zombiekiller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;

public class Flash extends Base 
{
	float calc;
	public Flash(MainActivity _a,Bitmap _texture)
	{
		super(_a);
		a=_a;
		angle=a.myview.map.p.angle;
		texture=_texture;
		
	}
	void Update()
	{
		UpdatePosition();
		width = 60;
		height = 60;
		calc += 0.5f;
	}
	public void Draw(Canvas _canvas)
    {
		position.x=a.myview.map.p.position.x-5;
		position.y=a.myview.map.p.position.y-5;
		
		/*Matrix msc=new Matrix();
    	Matrix mrot=new Matrix();
    	Matrix mtr=new Matrix();
    	Matrix m=new Matrix();

    	msc.setScale(width/texture.getWidth(), height/texture.getHeight());
    	
        float dispx=(float) (position.x+Math.cos(angle+2)*30);
        float dispy=(float) (position.y+Math.sin(angle+2)*30);
        origin=new PointF(position.x/width*texture.getWidth()+texture.getWidth()/2,position.y/height*texture.getHeight()+texture.getHeight()/2);
		mtr.setTranslate(dispx/width*texture.getWidth(), dispy/height*texture.getHeight());
		
		mrot.setRotate(angle,origin.x,origin.y);

		m.setConcat(msc, mrot);
		m.setConcat(m, mtr);
		_canvas.drawBitmap(texture,m, null);
		*/
		float oldang=angle;
		angle=(float) Math.toDegrees(angle)-180;
    	super.Draw(_canvas);
    	angle=oldang;
    }
	public boolean IsDestroy()
    {
        if (calc >= 3)
            return true;
        return false;
    }
}
