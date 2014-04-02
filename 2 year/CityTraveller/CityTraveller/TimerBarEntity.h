#pragma once
#include "StaticEntity.h"
#include "GameEntity.h"
#include "CharacterEntity.h"
#include "WorldEntity.h"
#include "BarEntity.h"
#include "AI.h"
class TimerBarEntity:public BarEntity
{
private:
	void DisplayTimerText();
	int x,y;
	RECT rct;
public:
	void InitObject(int _x1,int _y1,int _x2,int _y2);
	void LoadTextures();
	void ReleaseTextures();
	void OnPaint();
};
void TimerBarEntity::InitObject(int _x1,int _y1,int _x2,int _y2)
{
	Bar.InitObject(_x1+140,_y1+-210,_x1+280,_y1+-115,"Data\\hud\\timerbar.tga");
}
void TimerBarEntity::LoadTextures()
{
	Bar.LoadTextures();
}
void TimerBarEntity::ReleaseTextures()
{
	Bar.ReleaseTextures();
}
void TimerBarEntity::OnPaint()
{
	Bar.OnPaint();
	DisplayTimerText();
}
void TimerBarEntity::DisplayTimerText()
{
	x=215;
	y=-137;
	rct.left=x*2.15+570;
	rct.right=x*2.15+900;
	rct.top=370-y*1.86;
	rct.bottom=rct.top+150;
	char *s=new char[80];
	if (timer-(timer/60)*60<10)
		sprintf(s,"%d:0%d",timer/60,timer-(timer/60)*60);
	else
		sprintf(s,"%d:%d",timer/60,timer-(timer/60)*60);
	m_font2->DrawText(NULL, s, -1, &rct, 0, fontColor2 );
}