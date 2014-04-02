package com.example.zombiekiller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;

public class Blood extends Base 
{
	float calc;
	public Blood(MainActivity _a,Bitmap _texture,float x,float y)
	{
		super(_a);
		a=_a;
		angle=a.myview.map.p.angle;
		texture=_texture;
		position.x=x;
		position.y=y;
	}
	void Update()
	{
		super.UpdatePosition();
		width = 50;
		height = 50;
		calc += 0.5f;
	}
	public void Draw(Canvas _canvas)
    {	
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
        if (calc >= 10)
            return true;
        return false;
    }
}
