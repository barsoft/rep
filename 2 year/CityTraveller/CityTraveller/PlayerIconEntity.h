#pragma once
#include "IconEntity.h"

class PlayerIconEntity:public IconEntity
{
public:
	void ReleaseTextures();
	void LoadTextures();	
	void OnPaint(AI);
	void OnPaint(){}
	void InitObject(int _x1,int _y1);
};
void PlayerIconEntity::LoadTextures()
{
	Icon.LoadTextures();
}
void PlayerIconEntity::OnPaint(AI intellect)
{
	Icon.x1=x1+intellect.GetCurrentCoors().x;
	Icon.x2=x1+5+intellect.GetCurrentCoors().x;
	Icon.OnPaint();
}
void PlayerIconEntity::ReleaseTextures()
{
	Icon.ReleaseTextures();
}
void PlayerIconEntity::InitObject(int _x1,int _y1)
{
	Icon.InitObject(_x1,_y1,_x1+5,_y1+5,"Data\\hud\\playericon.tga");
	x1=_x1;
}