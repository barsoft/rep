package com.example.zombiekiller;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
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

import java.util.ArrayList;

public class Zombie extends Base
{
    public float superspeed;
    public float normalspeed;
    public float  speed;
    /*private int bornsound;
    private int damagesound;
    private int diedsound;
    private int eatsound;*/
    int waypoint=1;
    SoundPool sounds;
    boolean born=true;
    
    int health;
    boolean damage=false;
    Bitmap oldtex;
    int calc=0;
    int atackCalc=0;
    ArrayList<Blood> b;
    int power;
    public Zombie(MainActivity _a,int x,int y,ZombieType _type, SoundPool _sounds)
    {
    	super(_a);
    	a=_a;
    	
    	width=50;
    	height=50;
    	
    	position=new PointF(x,y);
    	
        superspeed = 3;
        normalspeed = 0.5f;
        speed = normalspeed;
        
        b=new ArrayList<Blood>();

        angle = 0f;
        sounds=_sounds;
       /* sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        bornsound = sounds.load(a, R.raw.born, 1);
        damagesound= sounds.load(a, R.raw.damage, 1);
        diedsound=sounds.load(a, R.raw.died, 1);
        eatsound=sounds.load(a, R.raw.eat, 1);*/
        //sounds.play(sound, 1.0f, 1.0f, 0, 0, 1.5f);
        
        texture=_type.texture;
    	oldtex=texture;
    	health=_type.health;
    	power=_type.power;
    }

    public void LoadContent()
    {
    	
    }


    public void Draw(Canvas _canvas)
    {
    	if (damage )
    	{
    		texture=a.myview.map.textureDamage;
    		damage=false;
    		sounds.play(a.myview.map.damagesound, 1.0f, 1.0f, 0, 0, 1.5f);
    		calc=10;
    		b.add(new Blood(a,a.myview.map.textureBlood.get(a.myview.map.mr.generateRandom(3)),position.x,position.y));
    	}
    	if (!damage && calc==0)
    	{
    		texture=oldtex;
    		
    	}
    	if (health<=0)
    	{
			a.myview.map.score+=100;
			sounds.play(a.myview.map.diedsound, 1.0f, 1.0f, 0, 0, 1.5f);
    	}
    	if (born)
    	{
			sounds.play(a.myview.map.bornsound, 1.0f, 1.0f, 0, 0, 1.5f);
    		born=false;
    	}
    	float oldang=angle;
    	angle=(float) Math.toDegrees(angle)-180;
    	super.Draw(_canvas);
    	angle=oldang;
    	
    	for (int i=0;i<b.size();i++)
			b.get(i).Draw(_canvas);
    	
    	if (calc>0) calc--;
    }
    
    public void Update()
    {
    	UpdatePosition();  
        float x=-(a.myview.map.p.position.x - position.x);
        float y = (a.myview.map.p.position.y- position.y);

        double res=(x/y);

        if (y>=0)
            angle = (float)Math.atan(res)+3.14f;
        else 
            angle = (float)Math.atan(res);

        {
            if (Math.abs(a.myview.map.p.position.x - position.x) < 20 && Math.abs(a.myview.map.p.position.y- position.y) < 20) { waypoint++; }
            else
            {
                ColisionCheck(new PointF(position.x + (float)(speed * Math.cos(angle - 1.57)), position.y));
                ColisionCheck(new PointF(position.x, position.y + (float)(speed * Math.sin(angle - 1.57))));
            }
        }
        
        if (!a.myview.map.stop)
		{
        	RectF mypos=new RectF(a.myview.map.p.position.x-5,a.myview.map.p.position.y-5,a.myview.map.p.position.x+a.myview.map.p.width+5,a.myview.map.p.position.y+a.myview.map.p.health+5);
        	RectF zombiePos=new RectF(position.x,position.y,position.x+width,position.y+height);
        	if (mypos.intersect(zombiePos))
		    {
		    	if (calc==0) 
		    	{
		    		a.myview.map.p.health-=power;
		    		calc=20;
		    		sounds.play(a.myview.map.eatsound, 1.0f, 1.0f, 0, 0, 1.5f);
		    	}
		    	if (calc>0) calc--;
		    }
        }
        for (int i=0;i<b.size();i++)
			if (b.get(i).IsDestroy())
				b.remove(i);
        for (int i=0;i<b.size();i++)
			b.get(i).Update();
    }
    public void ColisionCheck(PointF vec)
    {
    	RectF mypos=new RectF(a.myview.map.p.position.x-5,a.myview.map.p.position.y-5,a.myview.map.p.position.x+a.myview.map.p.width+5,a.myview.map.p.position.y+a.myview.map.p.height+5);
    	RectF zombiePos=new RectF(position.x,position.y,position.x+width,position.y+height);
    	if (!mypos.intersect(zombiePos))
	    {
    		position.x=vec.x;      
        	position.y=vec.y;
	    }
    }
    
    public void OnDamage()
    {
    	health--;
    	damage=true;
    }
}