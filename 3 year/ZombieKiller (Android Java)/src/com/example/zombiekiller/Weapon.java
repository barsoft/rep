package com.example.zombiekiller;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;

public class Weapon extends Base
{
	public int countbullets;
	ArrayList<Bullet> bullets;
	ArrayList<Flash> flashes;
	Bitmap bullettexture;

	Bitmap flashtexture;
	
	public Weapon(MainActivity _a)
	{
		super(_a);
		a=_a;
		bullets=new ArrayList<Bullet>();
		flashes=new ArrayList<Flash>();
		countbullets=500;
		position.x=0;
		position.y=0;
		width=70;
		height=70;
		//
	}
	public void LoadContent()
	{
		texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.ak47w);
		bullettexture=BitmapFactory.decodeResource(a.getResources(), R.drawable.bullet);
		flashtexture=BitmapFactory.decodeResource(a.getResources(), R.drawable.flash);
	}
	
	public void Update()
	{
		super.UpdatePosition();
		position.x=a.myview.map.p.position.x-10;
		position.y=a.myview.map.p.position.y-10;
		for (int i=0;i<bullets.size();i++)
			bullets.get(i).Update();
		
		for (int i=0;i<flashes.size();i++)
			flashes.get(i).Update();
		
		for (int i=0;i<bullets.size();i++)
			if (bullets.get(i).IsDestroy())
				bullets.remove(i);
		
		for (int i=0;i<flashes.size();i++)
			if (flashes.get(i).IsDestroy())
				flashes.remove(i);
		
		for (int i=0;i<a.myview.map.z.size();i++)
			for (int i1=0;i1<bullets.size();i1++)
			if (bullets.get(i1).Intersects(a.myview.map.z.get(i).position.x,a.myview.map.z.get(i).position.y
					,a.myview.map.z.get(i).position.x+a.myview.map.z.get(i).width,
					a.myview.map.z.get(i).position.y+a.myview.map.z.get(i).height))
			{
				bullets.remove(i1);
				a.myview.map.z.get(i).OnDamage();
			}
	}
	
	public void Draw(Canvas _canvas)
	{
		angle=a.myview.map.p.angle;
		super.Draw(_canvas);
		
		for (int i=0;i<bullets.size();i++)
			bullets.get(i).Draw(_canvas);
		for (int i=0;i<flashes.size();i++)
			flashes.get(i).Draw(_canvas);
	}
}
