package com.example.zombiekiller;

import android.graphics.RectF;

public class Static extends Base
{
	public Static(MainActivity _a)
	{
		super(_a);
	}
	@Override
	public void UpdatePosition()
	{
		rect=new RectF(position.x,position.y,position.x+width,position.y+height);
	}
}
