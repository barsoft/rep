#pragma once
#include "ButtonEntity.h"
#include "SlideshowEntity.h"
#include "MainBarEntity.h"
void TimerThread(void *dummy);
class PlayEntity:public GameEntity
{
private:
	MainBarEntity GameBar;
	CharacterEntity Mandms;
	WorldEntity World;
	AI Intellect;
	RECT rct;
	int x,y;
	
public:
	PlayEntity()
	{
		GameBar.InitObject(-0,-0,0,-0);
	}
	void LoadTextures();
	void OnPaint();
	void ReleaseTextures();
};

void PlayEntity::LoadTextures()
{
	GameBar.LoadTextures();
	Mandms.LoadTextures();
	World.LoadTextures();
}
void PlayEntity::OnPaint()
{	
	World.OnPaint();
	GameBar.OnPaint(Intellect);
	Mandms.OnPaint();
	Intellect.OnRoad(Mandms,World);
}
void PlayEntity::ReleaseTextures()
{
	GameBar.ReleaseTextures();
	World.ReleaseTextures();
	Mandms.ReleaseTextures();
}

void TimerThread(void *dummy)
{
	while(1)
	{
		Sleep(1000);
		if (gs==IsPlayGame && timer) timer--;
	}
	_endthread();
}
