package com.example.zombiekiller;

import android.graphics.Canvas;
import android.graphics.RectF;

public class Barrel extends Base 
{
	RectF influense;
	float radius;
	public Barrel(MainActivity _a,float x,float y,BarrelType b) 
	{
		super(_a);
		width=b.width;
		height=b.height;
		position.x=x;
		position.y=y;
		influense=new RectF(position.x-radius,position.y-radius,position.x+width+radius,position.y+height+radius);
		texture=b.texture;
	}
	
	public void Draw(Canvas _canvas)
	{
		super.Draw(_canvas);
		RectF r=new RectF(a.myview.map.p.position.x,a.myview.map.p.position.y,a.myview.map.p.position.x+a.myview.map.p.width,a.myview.map.p.position.y+a.myview.map.p.height);
		if (influense.intersect(r))
		{
			a.myview.map.p.health-=100;
		}
	}
}
