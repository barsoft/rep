#pragma once
#include "CharacterEntity.h"
#include "WorldEntity.h"

enum Direction
{
	D_right,
	D_left,
	D_up,
	D_down
};
struct RectangleColision
{
	POINT p1;
	POINT p2;
};
class AI
{
private:
	int Score;
	bool Colision(Direction dir, CharacterEntity& ch,WorldEntity& map);
	POINT CurrentCoords;
	RectangleColision RecCol;
public:
	AI()
	{
		CurrentCoords.x=0; CurrentCoords.y=0;
		Score=150000;
		RecCol.p1.x=-200;
		RecCol.p1.y=100;
		RecCol.p2.x=0;
		RecCol.p2.y=0;
	}
	void OnRoad(CharacterEntity &ch,WorldEntity& map);
	int GetScore();
	POINT GetCurrentCoors();
};

void AI::OnRoad(CharacterEntity &ch,WorldEntity &map)
{
	if (GetAsyncKeyState(VK_LEFT)&32768 && Colision(D_left,ch,map)) { ch.x-=ch.speed;  }
	if (GetAsyncKeyState(VK_RIGHT)&32768 && Colision(D_right,ch,map)) {ch. x+=ch.speed;    }
	if (GetAsyncKeyState(VK_UP)&32768 && Colision(D_up,ch,map)) { ch.y+=ch.speed;  }
	if (GetAsyncKeyState(VK_DOWN)&32768 && Colision(D_down,ch,map)) { ch.y-=ch.speed;    }
}
bool AI::Colision(Direction dir,CharacterEntity &ch,WorldEntity &map)
{
	bool lox=false;
	switch(dir)
	{
	case D_left:
		if (ch.x<-280 ) 
		{
			if (map.TextureIterator!=map.g_worldTexture.begin())
			{
				map.TextureIterator-- ; 
				CurrentCoords.x--;
				ch.x=220;
			}
			return false;
		}
		else 
		{
			for (int i=RecCol.p2.y;i<RecCol.p1.y;i++)
				if (ch.x==RecCol.p2.x && ch.y==i) 
					return false;
			break;
		}
	case D_right:
		if (ch.x>220) 
		{
			if (map.TextureIterator+1!=map.g_worldTexture.end())
			{
				map.TextureIterator++ ;
				CurrentCoords.x++;
				ch.x=-280;
			}
			return false;
		}
		else 
		{
			for (int i=RecCol.p2.y;i<RecCol.p1.y;i++)
				if (ch.x==RecCol.p1.x && ch.y==i) 
					return false;
			break;
		}
	case D_up:
		if (ch.y>150) 
		{
			return false;
		}
		else 
		{
			for (int i=RecCol.p1.x;i<0;i++)
				if (ch.x==i && ch.y==RecCol.p2.x) 
					return false;
			break;
		}
	case D_down:
		if (ch.y<-110) 
		{
			return false;
		}
		else 
		{
			for (int i=RecCol.p1.x;i<0;i++)
				if (ch.x==i && ch.y==RecCol.p1.y) 
					return false;
			break;
		}
	}
	return true;
}
int AI::GetScore()
{
	return Score;
}
POINT AI::GetCurrentCoors()
{
	return CurrentCoords;
}