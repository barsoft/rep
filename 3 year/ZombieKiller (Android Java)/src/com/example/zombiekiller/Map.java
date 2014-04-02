package com.example.zombiekiller;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.Menu;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.cookie.SM;

public class Map 
{
	
	public int xNum;
    public int yNum;
    protected boolean reload = false;

    boolean pressed;
    
    protected boolean focused = false;
    protected boolean oldmoved = false;
    public boolean unaccecable;
    public boolean containerOpen;
    MainActivity a;
    
    public int width,height;
    
    PlayerCharacter p;
    ArrayList<Zombie> z;
	Camera cam;
	
	Joystick j;
	SoundPool sounds;
	
	public int soundWalk;
	HUD hud;
	int wave=1;
	int score;
	int currweapon;
	int c;
    MyRandom mr;
    int wavetime=0;
    
    float ex,ey;
    float ex2,ey2;
    float sx,sy;
    float sx2,sy2;
    
    ArrayList<Bitmap> textureBlood;
    
    public Bitmap textureDamage;
    
    
    public ZombieType DefaultZombie;
    public ZombieType Zak;
    public ZombieType Kost;
    public ZombieType Bieber;
    
    public GroundType asphalt;
    
    public BarrelType redBarrel;
    ArrayList<Base> asphaltarray;
    
    Barrel barrel;
    
    boolean add=false;
    Paint paint;
    Paint paintRec;
    boolean stop=false;
    int redcalc=0;
    boolean nextwave=false;
    int pausecalc;
    int zombiecalc;
    int bulletcalc;
    
    public  int soundAk47;
    public int bornsound;
    public int damagesound;
    public int diedsound;
    public int eatsound;
	private boolean singletouch;
	private float oldnum;
    
    public Map(MainActivity _a,int _xNum,int _yNum)
    {
    	
    	a=_a;
		xNum = _xNum;
		yNum = _yNum;
		width=_xNum*100;
		height=_yNum*100;
		cam = new Camera(width,height );

		
		
		mr=new MyRandom();
		p=new PlayerCharacter(a);
		z=new ArrayList<Zombie>();
		
		//j=new Joystick(a,_a.displaymetrics.widthPixels/2-50,_a.displaymetrics.heightPixels/2+50);
		j=new Joystick(a,80/800.0f*a.displaymetrics.widthPixels,a.displaymetrics.heightPixels-(80/480.0f*a.displaymetrics.heightPixels));
		
		hud=new HUD(a);
		sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
		
		
		DefaultZombie=new ZombieType();
		Zak=new ZombieType();
		Kost=new ZombieType();
		Bieber=new ZombieType();
		
		redBarrel=new BarrelType();
		
		
		
    	DefaultZombie.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.zombie);
    	Zak.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.zak);
    	Kost.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.kost);
    	Bieber.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.bieber);
    	
    	redBarrel.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.barrel);
    	
    	DefaultZombie.health=10;
    	Zak.health=20;
    	Kost.health=20;
    	Bieber.health=3;
    	
    	
    	DefaultZombie.power=1;
    	Zak.power=2;
    	Kost.power=2;
    	Bieber.power=0;
    	
    	textureDamage=BitmapFactory.decodeResource(a.getResources(), R.drawable.zombiedied);
    	
    	textureBlood=new ArrayList<Bitmap>();
    	
    	textureBlood.add(BitmapFactory.decodeResource(a.getResources(), R.drawable.blood));
    	textureBlood.add(BitmapFactory.decodeResource(a.getResources(), R.drawable.blood2));
    	textureBlood.add(BitmapFactory.decodeResource(a.getResources(), R.drawable.blood3));
    	textureBlood.add(BitmapFactory.decodeResource(a.getResources(), R.drawable.blood4));
    	

		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextSize(80);
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Align.CENTER);
		
		paintRec = new Paint();
		paintRec.setStyle(Paint.Style.FILL);
		paintRec.setAntiAlias(true);
		paintRec.setColor(Color.RED);
		
		sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
		
		soundAk47 = sounds.load(a, R.raw.ak47s, 1);
        bornsound = sounds.load(a, R.raw.born, 1);
        damagesound= sounds.load(a, R.raw.damage, 1);
        diedsound=sounds.load(a, R.raw.died, 1);
        eatsound=sounds.load(a, R.raw.eat, 1);
        
        asphalt=new GroundType();
        
        asphalt.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.asphalt);
        asphalt.width=100;
        asphalt.height=100;
        
        redBarrel.width=100;
        redBarrel.height=100;
        
        asphaltarray=new ArrayList<Base>();
        for (int i=0;i<xNum;i++)
        	for (int j=0;j<yNum;j++)
        	{
		        asphaltarray.add(new Base(a,asphalt.texture,i*100,j*100,asphalt.width,asphalt.height));
        	}
        
        	barrel=new Barrel(a,100,100,redBarrel);
    }
    public void Draw(Canvas canvas)
    {
    	wavetime++;
    	Update();
    	
		
    	 
    	if (p.health<=0)
    	{
    		stop=true;
    		
    	}
    	
    	for (int i=0;i<asphaltarray.size();i++)
    		asphaltarray.get(i).Draw(canvas);
        p.Draw(canvas);
        j.Draw(canvas);
        for (int i=0;i<z.size();i++)
        z.get(i).Draw(canvas);
        hud.Draw(canvas);
        
        if (stop)
        {
        	paintRec.setAlpha(redcalc);
        	canvas.drawRect(new Rect(0,0,a.displaymetrics.widthPixels,a.displaymetrics.heightPixels),paintRec);
        	canvas.drawText("GAME OVER",a.displaymetrics.widthPixels/2, a.displaymetrics.heightPixels/2+40, paint);
        	
        	redcalc+=2;
        	if (redcalc>=255) redcalc=255;
        }
        
        if (z.isEmpty() && wavetime>200 && (pausecalc==0))
    	{
    		nextwave=true;
    		pausecalc=200;
    	}
        
    	if (nextwave && pausecalc==200)
    	{
    		wave++;
    		wavetime=0;
    	}
    	////////////////////////////////////////////////////////////////////////////////////////////
    	// WAVES ///////////////////////////////////////////////////////////////////////////////////
    	////////////////////////////////////////////////////////////////////////////////////////////
        if (wavetime>=0 && wavetime<200 && wave==1)
        {
        	if (z.isEmpty())
        	{
        		canvas.drawText("WAVE 1",a.displaymetrics.widthPixels/2, a.displaymetrics.heightPixels/2+40, paint);
        	}
        }
        
        if (wavetime==200 && wave==1) //(nextwave && pausecalc==0 && wave==2)
    	{
    		z.add(new Zombie(a,150,0,Zak, sounds));
    		z.add(new Zombie(a,250,0,Kost, sounds));
    		z.add(new Zombie(a,350,0,Bieber, sounds));
    		nextwave=false;
    	}
        
        if (wavetime>=0 && wavetime<200 && wave==2 && pausecalc>0)
        {
        	
        	if (z.isEmpty())
        	{
        		pausecalc--;
        		canvas.drawText("WAVE 2",a.displaymetrics.widthPixels/2, a.displaymetrics.heightPixels/2+40, paint);
        	}
        }
        
        if (wavetime==200 && wave==2 ) //(nextwave && pausecalc==0 && wave==2)
    	{
    //		z.add(new Zombie(a,150,0,Zak, sounds));
    //		z.add(new Zombie(a,250,0,Kost, sounds));
    	//	z.add(new Zombie(a,350,0,Bieber, sounds));
    		nextwave=false;
    	}
        
        
    	/*
    	 * 
    	
    	if (calc==200)
    	{
    		for (int i=0;i<3;i++)
    			z.add(new Zombie(a,z.size(),i*100,0,textureZombie));
    	}
    	
    	if (calc==500)
    	{
    		for (int i=0;i<4;i++)
    			z.add(new Zombie(a,z.size(),i*100,0,textureZombie));
    	}
    	
    	if (calc==700)
    	{
    		for (int i=0;i<5;i++)
    			z.add(new Zombie(a,z.size(),i*50,0,textureZombie));
    	}
    	
    	if (calc==1200)
    	{
    		for (int i=0;i<4;i++)
    			z.add(new Zombie(a,z.size(),i*50,0,textureZombie));
    		for (int i=0;i<4;i++)
    			z.add(new Zombie(a,z.size(),i*50,a.displaymetrics.heightPixels,textureZombie));
    	}
    	
    	
    	
    	if (pausecalc>0 && wave==2) 
    	{
    		pausecalc--;
    		canvas.drawText("WAVE 2",a.displaymetrics.widthPixels/2, a.displaymetrics.heightPixels/2+40, paint);
    	}
    	
    	if (nextwave && pausecalc==0 && wave==3)
    	{
    		z.add(new Zombie(a,150,0,Zombie, sounds));
    		
    		z.add(new Zombie(a,250,0,Zombie, sounds));
    		z.add(new Zombie(a,350,0,Zombie, sounds));
    		
    		nextwave=false;
    	}
    	if (pausecalc>0 && wave==3) 
    	{
    		pausecalc--;
    		canvas.drawText("WAVE 3",a.displaymetrics.widthPixels/2, a.displaymetrics.heightPixels/2+40, paint);
    	}
    	*/
    	barrel.Draw(canvas);
    	
    	
    	for (int i=0;i<z.size();i++)
        	if (z.get(i).health<=0)
        	{
        		z.remove(i);
        	}
    }

    public void LoadContent()
    {
    	p.LoadContent();
    	for (int i=0;i<z.size();i++)
            z.get(i).LoadContent();
    	j.LoadContent();
    	
		hud.LoadContent();
    }
    
    public boolean onTouchEvent(MotionEvent event)
	{
    	int co=event.getPointerCount();
    	if (co==2)
		{
			singletouch=false;
			
		}
		else
		{
			singletouch=true;
		}
    	
    	
    	
    	
    	if(event.getAction() == MotionEvent.ACTION_DOWN) 
		{
    		float csx;
    		float csy;
    		float csx2;
    		float csy2;
    		
    		csx=event.getX();
			csy=event.getY();
			csx2=event.getX(1);
			csy2=event.getY(1);
    		
			if (singletouch)
			{
				sx=csx;
				sy=csy;
			}
			
		}
    	if(event.getAction() == MotionEvent.ACTION_DOWN ||event.getAction() == MotionEvent.ACTION_MOVE) 
		{
    		float cex;
    		float cey;
    		float cey2;
    		float cex2;
    		
    		cex=event.getX();
    		cey=event.getY();
    		cex2=event.getX(1);
			cey2=event.getY(1);
			
			if (singletouch)
			{
				ex=cex;
				ey=cey;
			}
			else
			{
				if (cex>hud.fire.position.x && cex<hud.fire.position.x+hud.fire.width && cey>hud.fire.position.y && cey<hud.fire.position.y+hud.fire.height )
				{
					ex=cex;
					ey=cey;
					ex2=cex2;
					ey2=cey2;
				}
				
				if (cex2>hud.fire.position.x && cex2<hud.fire.position.x+hud.fire.width && cey2>hud.fire.position.y && cey2<hud.fire.position.y+hud.fire.height )
				{
					ex=cex2;
					ey=cey2;
					ex2=cex;
					ey2=cey;
				}
				
				
			}
			

			/*	AlertDialog alertDialog;
				alertDialog = new AlertDialog.Builder(a).create();
				alertDialog.setTitle("Packing List");
				alertDialog.setMessage("asd");
				alertDialog.show();*/
			
				sx2=cex;
				sy2=cey;
			
				
			pressed=true;
		}
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			pressed=false;
		}
		
		oldnum=co;
		
		return true;
	}
    public void Update()
    {
    	hud.Update();
    	for (int i=0;i<asphaltarray.size();i++)
    		asphaltarray.get(i).UpdatePosition();
    	for (int i=0;i<z.size();i++)
            z.get(i).Update();
    	if (pressed)
	    {
    		if (singletouch)
    		{
    			if (ex>hud.fire.position.x && ex<hud.fire.position.x+hud.fire.width && ey>hud.fire.position.y && ey<hud.fire.position.y+hud.fire.height )
				{
					if (c==0)
					{
						if (p.weapons.get(currweapon).countbullets>0)
						{
							p.weapons.get(currweapon).bullets.add(new Bullet(a, p.weapons.get(currweapon).bullettexture,p.position.x+(float)Math.cos(p.angle+2)*30+25,p.position.y+(float)Math.sin(p.angle+2)*30+25));
							p.weapons.get(currweapon).flashes.add(new Flash(a, p.weapons.get(currweapon).flashtexture));
							p.weapons.get(currweapon).countbullets--;
							//p.weapons.get(currweapon).PlaySound();
							sounds.play(soundAk47, 1.0f, 1.0f, 0, 0, 1.5f);
			
						}
					}
					c++;
					if (c==7) c=0;
				}
    			else
				if (ex<a.displaymetrics.widthPixels/2 )
    			{
					float dx=(ex-j.jk.position.x);
					float dy=(ey-j.jk.position.y);
					float newangle=(float)Math.atan(dx/dy);
					
					if (dy>=0)
					{
						j.jk.radialAngle=-newangle;
						p.angle=-newangle;
					}
					else 
					{
						j.jk.radialAngle=-newangle-3.14f;
						p.angle=-newangle-3.14f;
					}
    			}
    			if (ex<a.displaymetrics.widthPixels/2 )
    			if (sx>j.position.x && sx<j.position.x+j.width && sy>j.position.y && sy<j.position.y+j.height )
    			{
    				p.ColisionCheck(new PointF(p.position.x,p.position.y+(float)(p.speed*Math.sin(p.angle+1.57))));
    	    		p.ColisionCheck(new PointF(p.position.x+(float)(p.speed*Math.cos(p.angle+1.57)),p.position.y));
    			}
    		}
    		else
			{
    			if (ex>hud.fire.position.x && ex<hud.fire.position.x+hud.fire.width && ey>hud.fire.position.y && ey<hud.fire.position.y+hud.fire.height )
				{
					if (c==0)
					{
						if (p.weapons.get(currweapon).countbullets>0)
						{
							p.weapons.get(currweapon).bullets.add(new Bullet(a, p.weapons.get(currweapon).bullettexture,p.position.x+(float)Math.cos(p.angle+2)*30+25,p.position.y+(float)Math.sin(p.angle+2)*30+25));
							p.weapons.get(currweapon).flashes.add(new Flash(a, p.weapons.get(currweapon).flashtexture));
							p.weapons.get(currweapon).countbullets--;
							//p.weapons.get(currweapon).PlaySound();
							sounds.play(soundAk47, 1.0f, 1.0f, 0, 0, 1.5f);
			
						}
					}
					c++;
					if (c==7) c=0;
				}
				
    			if (ex2<a.displaymetrics.widthPixels/2 )
    			{
					float dx=(ex2-j.jk.position.x);
					float dy=(ey2-j.jk.position.y);
					float newangle=(float)Math.atan(dx/dy);
					
					if (dy>=0)
					{
						j.jk.radialAngle=-newangle;
						p.angle=-newangle;
					}
					else 
					{
						j.jk.radialAngle=-newangle-3.14f;
						p.angle=-newangle-3.14f;
					}
    			}
    			
    			if (ex2<a.displaymetrics.widthPixels/2 )
				if (ex2>j.position.x && ex2<j.position.x+j.width && ey2>j.position.y && ey2<j.position.y+j.height )
				{
					p.ColisionCheck(new PointF(p.position.x,p.position.y+(float)(p.speed*Math.sin(p.angle+1.57))));
		    		p.ColisionCheck(new PointF(p.position.x+(float)(p.speed*Math.cos(p.angle+1.57)),p.position.y));
				}
	    	}
	    		
			
    	}
    }
}