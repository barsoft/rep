#pragma once
#include "GameEntity.h"
#include "StaticEntity.h"
class IconEntity:public GameEntity
{
protected:
	int x1,x2,y1,y2;
	StaticEntity Icon;
	virtual void ReleaseTextures()=0;
	virtual void LoadTextures()=0;	
	virtual void OnPaint()=0;
	virtual void InitObject(int _x1,int _y1)=0;
};