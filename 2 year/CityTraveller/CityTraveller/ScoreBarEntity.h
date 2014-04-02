#pragma once
#include "StaticEntity.h"
#include "GameEntity.h"
#include "CharacterEntity.h"
#include "WorldEntity.h"
#include "BarEntity.h"
#include "AI.h"
class ScoreBarEntity:public BarEntity
{
private:
	void DisplayScoreText(AI);
	int x,y;
	RECT rct;
public:
	void InitObject(int _x1,int _y1,int _x2,int _y2);
	void LoadTextures();
	void ReleaseTextures();
	void OnPaint(AI);
	void OnPaint(){}
};
void ScoreBarEntity::InitObject(int _x1,int _y1,int _x2,int _y2)
{
	Bar.InitObject(_x1+15,_y1+-210,_x1+155,_y1+-115,"Data\\hud\\timerbar.tga");
}
void ScoreBarEntity::LoadTextures()
{
	Bar.LoadTextures();
}
void ScoreBarEntity::ReleaseTextures()
{
	Bar.ReleaseTextures();
}
void ScoreBarEntity::OnPaint(AI Intellect)
{
	Bar.OnPaint();
	DisplayScoreText(Intellect);
}
void ScoreBarEntity::DisplayScoreText(AI Intellect)
{
	x=90;
	y=-147;
	rct.left=x*2.15+570;
	rct.right=x*2.15+900;
	rct.top=370-y*1.86;
	rct.bottom=rct.top+150;
	char *s=new char[80];
	m_font3->DrawText(NULL, "SCORE:", -1, &rct, 0, fontColor2 );
	x+=25;
	sprintf(s,"%d",Intellect.GetScore());
	x-=strlen(s)>1?(strlen(s)*10/2):0;
	y-=20;
	rct.left=x*2.15+570;
	rct.right=x*2.15+900;
	rct.top=370-y*1.86;
	rct.bottom=rct.top+150;
	
	m_font3->DrawText(NULL, s, -1, &rct, 0, fontColor2 );
}