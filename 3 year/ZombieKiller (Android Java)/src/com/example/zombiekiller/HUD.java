package com.example.zombiekiller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;

public class HUD extends Base
{
	Static weaponBar;
	Static waveBar;
	Static[] weapon;
	Static score;
	Static health;
	Static fire;
	Bitmap bar;
	Paint paint;
	Paint paint1;
	//Static score;
	public HUD(MainActivity _a)
	{
		super(_a);
		a=_a;
		weaponBar=new Static(_a);
		waveBar=new Static(_a);
		score=new Static(_a);
		health=new Static(_a);
		weapon=new Static[1];
		fire=new Static(_a);
		//score=new Static();
	}
	
	public void LoadContent()
	{
		bar=BitmapFactory.decodeResource(a.getResources(), R.drawable.wpbar);
		weaponBar.texture=bar;
		weaponBar.width=50;
		weaponBar.height=50;
		weaponBar.position.x=a.displaymetrics.widthPixels-weaponBar.width;
		weaponBar.position.y=0;
		
		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextSize(20);
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Align.RIGHT);
		
		paint1 = new Paint();
		paint1.setStyle(Paint.Style.FILL);
        paint1.setAntiAlias(true);
        paint1.setTextSize(40);
		paint1.setColor(Color.WHITE);
		paint1.setTextAlign(Align.CENTER);
        
		weapon[0]=new Static(a);
		weapon[0].texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.ak47);;
		weapon[0].width=50;
		weapon[0].height=50;
		weapon[0].position.x=a.displaymetrics.widthPixels-weaponBar.width;
		weapon[0].position.y=0;
		
		
		waveBar.texture=bar;
		waveBar.width=50;
		waveBar.height=50;
		waveBar.position.x=0;
		waveBar.position.y=0;
		
		score.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.coin);
		score.width=20;
		score.height=20;
		score.position.x=a.displaymetrics.widthPixels-weaponBar.width-score.width-5;
		score.position.y=5;
		
		health.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.heart);
		health.width=20;
		health.height=20;
		health.position.x=a.displaymetrics.widthPixels-weaponBar.width-health.width-5;
		health.position.y=30;
		
		fire.texture=BitmapFactory.decodeResource(a.getResources(), R.drawable.fire);
		fire.width=50;
		fire.height=50;
		fire.position.x=a.displaymetrics.widthPixels-100;
		fire.position.y=a.displaymetrics.heightPixels-100;
		
	}
	
	public void Draw(Canvas _canvas)
	{
		waveBar.Draw(_canvas);
		weaponBar.Draw(_canvas);
		score.Draw(_canvas);
		health.Draw(_canvas);
		fire.Draw(_canvas);
		weapon[a.myview.map.currweapon].Draw(_canvas);
		_canvas.drawText(a.myview.map.score+"", a.displaymetrics.widthPixels-weaponBar.width-score.width-10, 20, paint);
		_canvas.drawText(a.myview.map.p.health+"", a.displaymetrics.widthPixels-weaponBar.width-health.width-10, 45, paint);
		_canvas.drawText(a.myview.map.wave+"", weaponBar.width/2, 40, paint1);
		_canvas.drawText(a.myview.map.p.weapons.get(a.myview.map.currweapon).countbullets +"",a.displaymetrics.widthPixels, 50+20, paint);
	}
	
	public void Update()
    {
		waveBar.UpdatePosition();
    	weaponBar.UpdatePosition();
    	score.UpdatePosition();
    	health.UpdatePosition();
    	fire.UpdatePosition();
    	weapon[a.myview.map.currweapon].UpdatePosition();
    }
}
