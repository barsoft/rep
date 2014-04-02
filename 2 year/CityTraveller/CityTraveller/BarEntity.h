#pragma once
#include "GameEntity.h"
#include "StaticEntity.h"
class BarEntity:public GameEntity
{
protected:
	int x1,x2,y1,y2;
	StaticEntity Bar;
	virtual void ReleaseTextures()=0;
	virtual void LoadTextures()=0;	
	virtual void OnPaint()=0;
	virtual void InitObject(int _x1,int _y1,int _x2,int _y2)=0;
};