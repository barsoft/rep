#pragma once

#ifndef GAME_H
#define GAME_H

#include "s3e.h"
#include "Iw2D.h"
#include <vector>
#include <iostream>
#include "button.h"


using namespace std;

class CGame
{
public:
	CGame();
	~CGame();

	// update will be called a fixed number of times per second
	// regardless of visual framerate
	void Update();
	// render will be called as fast as possible (but not faster
	// than the update rate)
	void Render();

	void OnTouch();

	void InitMainMenu();

	void InitLevelsMenu();

	void InitGame();

	void InitLevel(int levelNum);

	void InitResources();

	void DestroyResources();

	void DestroyScene();

	std::vector<CSprite*> sprites;  
private:
	std::vector<CButton*> controls;  
	std::vector<CIw2DImage*> textures;  

	CIw2DImage* pBackgroundTex;
	CIw2DImage* pPressedBackgroundTex;
	CIw2DImage* pSunRedTex;
	CIw2DImage* pSunYellowTex;
	CIw2DImage* pGroundForestTex;
	CIw2DImage* pGrass;
	CIw2DImage* pTree1;
	CIw2DImage* pTree2;
	CIw2DImage* pTree3;
	CIw2DImage* pTree4;
	CIw2DImage* pLvl1;
	CIw2DImage* pStar;
	CIw2DImage* pPlayer;

	CIw2DFont* font;
};

#endif


#define  BUTTON_PLAY 1
#define  BUTTON_EXIT 2
#define  SUN_RED 3
#define  SUN_YELLOW 4
#define  GROUND_FOREST 5
#define  BUTTON_MENU 6
#define  BUTTON_LVL1 7
#define  KOLOBOK 8
#define  TREE 9
#define  STAR 10