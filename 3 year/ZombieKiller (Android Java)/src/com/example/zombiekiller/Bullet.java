package com.example.zombiekiller;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

public class Bullet extends Base {
	float calc;

	public Bullet(MainActivity _a,Bitmap _texture,float x,float y)
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
		width = 3;
		height = 10;
		calc += 0.5f;
		
		int x=a.myview.map.mr.generateRandom(10);
		
		position.x += (float)(Math.cos(angle+1.57))*(20+x);
		position.y += (float)(Math.sin(angle + 1.57)) * (20+x);
		
		
	}
	public void Draw(Canvas _canvas)
    {
		float oldang=angle;
		angle=(float) Math.toDegrees(angle)-180;
    	super.Draw(_canvas);
    	angle=oldang;
    	
    }
	public boolean IsDestroy()
    {
        if (calc >= 80)
            return true;
        return false;
    }
	/*public boolean Intersects(PointF a1,PointF a2, PointF b1, PointF b2) 
	{
	    float d = (a1.x-a2.x)*(b1.y-b2.y) - (a1.y-a2.y)*(b1.x-b2.x);
	    if (d == 0) return false;
	    else
	    return true;
	}
	public boolean Intersects(PointF positionA,PointF positionB,float x,float y,float x2,float y2)
	{
		RectF rec=new RectF();
		rec.left=x;
		rec.top=y;
		rec.right=x2;
		rec.bottom=y2;
		if (Intersects(new PointF((float)positionA.x, (float)positionA.y), new PointF((float)positionB.x, (float)positionB.y), new PointF((float)rec.left, (float)rec.top), new PointF((float)(rec.right), (float)rec.top)) ||
                Intersects(new PointF((float)positionA.x, (float)positionA.y), new PointF((float)positionB.x, (float)positionB.y), new PointF((float)(rec.right), (float)rec.top), new PointF((float)(rec.right), (float)rec.bottom)) ||
                Intersects(new PointF((float)positionA.x, (float)positionA.y), new PointF((float)positionB.x, (float)positionB.y), new PointF((float)(rec.right), (float)rec.bottom), new PointF((float)(rec.left), (float)rec.bottom)) ||
                Intersects(new PointF((float)positionA.x, (float)positionA.y), new PointF((float)positionB.x, (float)positionB.y), new PointF((float)(rec.left), (float)rec.bottom), new PointF((float)rec.left, (float)rec.top)))
            {
                return true;
            }
		return false;
	}*/
	public boolean Intersects(float x,float y,float x2,float y2)
	{
		/*PointF A=position;
		PointF B=new PointF();
		B.x=position.x+width;
		B.y=position.y;
		PointF C=new PointF();;
		B.x=position.x+width;
		B.y=position.y+height;
		PointF D=new PointF();;
		B.x=position.x;
		B.y=position.y+height;
		if (Intersects(A,B,x,y,x2,y2) || Intersects(B,C,x,y,x2,y2) || Intersects(C,D,x,y,x2,y2) ||Intersects(D,A,x,y,x2,y2))
		{
			return true;
		}
		return false;*/
		RectF rec1=new RectF(position.x,position.y,position.x+width,position.y+height);
		RectF rec2=new RectF(x,y,x2,y2);
		if (rec1.intersect(rec2))
			return true;
		return false;
	}
}
