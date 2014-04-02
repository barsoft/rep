#pragma once
#include "StaticEntity.h"
#include "GameEntity.h"
#include "CharacterEntity.h"
#include "WorldEntity.h"
#include "BarEntity.h"
#include "AI.h"
#include "ScoreBarEntity.h"
#include "TimerBarEntity.h"
#include "MapBarEntity.h"

class MainBarEntity:public BarEntity
{
private:
	TimerBarEntity TimerBar;
	ScoreBarEntity ScoreBar;
	MapBarEntity MapBar;
	ButtonEntity MenuButton;
	RECT rct;
public:
	void InitObject(int _x1,int _y1,int _x2,int _y2);
	void LoadTextures();
	void ReleaseTextures();
	void OnPaint(){}
	void OnPaint(AI);	
};
void MainBarEntity::InitObject(int _x1,int _y1,int _x2,int _y2)
{
	Bar.InitObject(_x1,_x2,_y1,_y2,"Data\\hud\\gamebar.tga");
	TimerBar.InitObject(_x1,_x2,_y1,_y2);
	ScoreBar.InitObject(_x1,_x2,_y1,_y2);
	MapBar.InitObject(_x1,_x2,_y1,_y2);
	MenuButton.InitObject(_x1-215,_y1-140,"Menu",StandardStyle);
}
void MainBarEntity::LoadTextures()
{
	TimerBar.LoadTextures();
	ScoreBar.LoadTextures();
	MapBar.LoadTextures();
	MenuButton.LoadTextures();
}
void MainBarEntity::ReleaseTextures()
{
	TimerBar.ReleaseTextures();
	ScoreBar.ReleaseTextures();
	MapBar.ReleaseTextures();
	MenuButton.ReleaseTextures();
}
void MainBarEntity::OnPaint(AI Intellect)
{
	TimerBar.OnPaint();
	ScoreBar.OnPaint(Intellect);
	MapBar.OnPaint(Intellect);
	MenuButton.OnPaint();
	if (MenuButton.OnSelect()) {gs=IsContinue;}	
}