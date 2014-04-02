package com.example.zombiekiller;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Point;
import android.graphics.PointF;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.Menu;

public class PlayerCharacter extends Base
{
    public int superspeed;
    public int normalspeed;
    public int speed;
    Base hands;
    public int health;
    ArrayList<Weapon> weapons;
    
    public PlayerCharacter(MainActivity _a )
    {
    	super(_a);
    	a=_a;
    	
    	hands=new Base(_a);
    	
    	weapons=new ArrayList<Weapon>();
    	
    	health=100;
    	
    	width=50;
    	height=50;
    	
    	position=new PointF(_a.displaymetrics.widthPixels/2-width/2,_a.displaymetrics.heightPixels/2-height/2);
    	weapons.add(new Weapon(_a));
    	
    	
    	hands.width=60;
    	hands.height=60;
    	
    	hands.position=new PointF(position.x-5,position.y-5);
        name = "mandms";

        superspeed = 15;
        normalspeed = 5;
        speed = normalspeed;

        angle = 0f;
    }

    public void LoadContent()
    {
    	texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.head);
    	hands.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.hands);
    	for (int i=0;i<weapons.size();i++)
    	{
    		weapons.get(i).LoadContent();
    	}
 
    }


    public void Draw(Canvas _canvas)
    {
    	Update();
    	
    	float oldang=angle;
    	angle=(float) Math.toDegrees(angle)-180;
    	hands.angle=angle;
    	hands.Draw(_canvas);
    	weapons.get(a.myview.map.currweapon).Draw(_canvas);
    	super.Draw(_canvas);
    	angle=oldang;
    	hands.angle=angle;
    }
    
    public void Update()
    {
    	hands.position=new PointF(position.x-5,position.y-5);
    	UpdatePosition();
    	hands.UpdatePosition();
    	weapons.get(a.myview.map.currweapon).Update();
    	
    	
    	
    	float newx = (position.x - a.displaymetrics.widthPixels / 2 * 100 / a.myview.map.cam.scale) / 100 * a.myview.map.cam.scale;
        float newy = (position.y - a.displaymetrics.heightPixels / 2 * 100 / a.myview.map.cam.scale) / 100 * a.myview.map.cam.scale;

        if (newx > 0)
        	a.myview.map.cam.world.x = newx;

        if (newy > 0)
        	a.myview.map.cam.world.y = newy;

        if (newx > a.myview.map.width - a.displaymetrics.widthPixels)
        	a.myview.map.cam.world.x = a.myview.map.width - a.displaymetrics.widthPixels;

        if (newy > a.myview.map.height - a.displaymetrics.heightPixels)
        	a.myview.map.cam.world.y = a.myview.map.height - a.displaymetrics.heightPixels;
    }
    
    public void ColisionCheck(PointF vec)
    {
    	RectF newpos=new RectF(vec.x,vec.y,vec.x+width,vec.y+height);
    	RectF leftMap=new RectF(0,0,1,a.myview.map.height);
    	RectF rightMap=new RectF(a.myview.map.width,0,a.myview.map.width+1,a.myview.map.height);
    	RectF topMap=new RectF(0,0,a.myview.map.width,1);
    	RectF bottomMap=new RectF(0,a.myview.map.height,a.myview.map.width,a.myview.map.height+1);
    	RectF bottomMaps=new RectF(0,0,1,1);
    	//if ()
    	if (!newpos.intersect(leftMap) && !newpos.intersect(rightMap) && !newpos.intersect(topMap) && !newpos.intersect(bottomMap))
	    {
    		position.x=vec.x;      
        	position.y=vec.y;
	    }
    }
}