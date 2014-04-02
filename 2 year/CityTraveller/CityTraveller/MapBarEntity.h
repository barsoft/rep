#pragma once
#include "StaticEntity.h"
#include "GameEntity.h"
#include "CharacterEntity.h"
#include "WorldEntity.h"
#include "BarEntity.h"
#include "PlayerIconEntity.h"

class MapBarEntity:public BarEntity
{
private:
	ButtonEntity MiniMap;
	PlayerIconEntity PlayerIcon;
public:
	void InitObject(int _x1,int _y1,int _x2,int _y2);
	void LoadTextures();
	void ReleaseTextures();
	void OnPaint(){}
	void OnPaint(AI);
};
void MapBarEntity::InitObject(int _x1,int _y1,int _x2,int _y2)
{
	Bar.InitObject(_x1+-110,_y1+-210,_x1+30,_y1+-115,"Data\\hud\\timerbar.tga");
	MiniMap.InitObject(_x1-41,_y1-164.5,"",MapStyle);
	PlayerIcon.InitObject(_x1-80,y1-160);
}
void MapBarEntity::LoadTextures()
{
	Bar.LoadTextures();
	MiniMap.LoadTextures();
	PlayerIcon.LoadTextures();
}
void MapBarEntity::ReleaseTextures()
{
	Bar.ReleaseTextures();
	MiniMap.ReleaseTextures();
	PlayerIcon.ReleaseTextures();
}
void MapBarEntity::OnPaint(AI Intellect)
{
	Bar.OnPaint();
	MiniMap.OnPaint();
	if (MiniMap.OnSelect()) gs=IsMap;
	PlayerIcon.OnPaint(Intellect);
}
