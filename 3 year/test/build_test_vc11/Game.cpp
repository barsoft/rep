#pragma once
#include "IwGx.h"
#include "sprite.h"
#include "button.h"
#include "game.h"
#include "SolidSprite.h"

#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include "Star.h"
#include "Player.h"
using namespace std;

using namespace std;



CGame::CGame()
{
	InitResources();
	InitMainMenu();
}

CGame::~CGame()
{
	DestroyScene();
	DestroyResources();
}

void CGame::InitResources()
{
	font = Iw2DCreateFontResource("Serif_10");

	string texturesFolder="/res/textures/";

	string path=texturesFolder;
	path.append("button.png");
	pBackgroundTex = Iw2DCreateImage(path.data());
	textures.push_back(pBackgroundTex);

	path=texturesFolder;
	path.append("button_pressed.png");
	pPressedBackgroundTex = Iw2DCreateImage(path.data());
	textures.push_back(pPressedBackgroundTex);

	path=texturesFolder;
	path.append("sun_red.png");
	pSunRedTex = Iw2DCreateImage(path.data());
	textures.push_back(pSunRedTex);

	path=texturesFolder;
	path.append("sun_yellow.png");
	pSunYellowTex = Iw2DCreateImage(path.data());
	textures.push_back(pSunYellowTex);

	path=texturesFolder;
	path.append("ground_forest.png");
	pGroundForestTex = Iw2DCreateImage(path.data());
	textures.push_back(pGroundForestTex);

	path=texturesFolder;
	path.append("grass.png");
	pGrass = Iw2DCreateImage(path.data());
	textures.push_back(pGrass);

	path=texturesFolder;
	path.append("tree_1.png");
	pTree1 = Iw2DCreateImage(path.data());
	textures.push_back(pTree1);

	path=texturesFolder;
	path.append("tree_2.png");
	pTree2 = Iw2DCreateImage(path.data());
	textures.push_back(pTree2);

	path=texturesFolder;
	path.append("tree_3.png");
	pTree3 = Iw2DCreateImage(path.data());
	textures.push_back(pTree3);

	path=texturesFolder;
	path.append("tree_4.png");
	pTree4 = Iw2DCreateImage(path.data());
	textures.push_back(pTree4);

	path=texturesFolder;
	path.append("lvl1.png");
	pLvl1 = Iw2DCreateImage(path.data());
	textures.push_back(pLvl1);

	path=texturesFolder;
	path.append("star.png");
	pStar = Iw2DCreateImage(path.data());
	textures.push_back(pStar);

	path=texturesFolder;
	path.append("kolobok.png");
	pPlayer = Iw2DCreateImage(path.data());
	textures.push_back(pPlayer);
}

void CGame::DestroyResources()
{
	delete font;

	for(int i = 0, cnt = textures.size(); i < cnt; ++i)
	{
		delete textures[i];
	}
	textures.clear();
}

void CGame::DestroyScene()
{

	for(int i = 0, cnt = controls.size(); i < cnt; ++i)
	{
		delete controls[i];
	}
	for(int i = 0, cnt = sprites.size(); i < cnt; ++i)
	{
		delete sprites[i];
	}
	controls.clear();
	sprites.clear();
}

void CGame::InitMainMenu()
{
	DestroyScene();

	int width=200;
	int height=80;
	int y=Iw2DGetSurfaceHeight() / 2-height/2;
	int x=Iw2DGetSurfaceWidth() / 2-width/2;
	int interval=10;

	CButton* buttonPlay=new CButton();
	buttonPlay->SetText("Play");
	buttonPlay->SetId(BUTTON_PLAY);
	controls.push_back(buttonPlay);

	CButton* buttonExit=new CButton();
	buttonExit->SetText("Exit");
	buttonExit->SetId(BUTTON_EXIT);
	controls.push_back(buttonExit);

	for(int i = 0, cnt = controls.size(); i < cnt; ++i)
	{
		controls[i]->SetBackgroundTexture(pBackgroundTex);
		controls[i]->SetPressedBackgroundTexture(pPressedBackgroundTex);
		controls[i]->SetTextFont(font);
		controls[i]->SetWidth(width);
		controls[i]->SetHeight(height);
		controls[i]->SetX(x);
		controls[i]->SetY(y);
		y+=controls[i]->GetHeight()+interval;
	}

	CSprite* sunRed=new CSprite();
	sunRed->SetBackgroundTexture(pSunRedTex);
	sunRed->SetWidth(250);
	sunRed->SetHeight(250);
	sunRed->SetX(Iw2DGetSurfaceWidth() / 2-sunRed->GetWidth()/2);
	sunRed->SetY(Iw2DGetSurfaceHeight()/2 - sunRed->GetWidth()/2);
	sunRed->SetId(SUN_RED);
	sprites.push_back(sunRed);


	CSprite* sunYellow=new CSprite();
	sunYellow->SetBackgroundTexture(pSunYellowTex);
	sunYellow->SetWidth(250);
	sunYellow->SetHeight(250);
	sunYellow->SetX(Iw2DGetSurfaceWidth() / 2-sunYellow->GetWidth()/2);
	sunYellow->SetY(Iw2DGetSurfaceHeight()/2 - sunYellow->GetWidth()/2);
	sunYellow->SetId(SUN_YELLOW);
	sprites.push_back(sunYellow);


	CSprite* groundForest=new CSprite();
	groundForest->SetBackgroundTexture(pGroundForestTex);
	groundForest->SetWidth(Iw2DGetSurfaceWidth());
	groundForest->SetHeight(Iw2DGetSurfaceHeight());
	groundForest->SetX(0);
	groundForest->SetY(0);
	groundForest->SetId(GROUND_FOREST);
	sprites.push_back(groundForest);
}



void CGame::Update()
{
	for(int i = 0, cnt = sprites.size(); i < cnt; ++i)
	{
		if (sprites[i]->GetId()==SUN_RED)
		{
			sprites[i]->SetAngle(sprites[i]->GetAngle()+0.001f);
		}
		if (sprites[i]->GetId()==SUN_YELLOW)
		{
			sprites[i]->SetAngle(sprites[i]->GetAngle()-0.001f);
		}

		sprites[i]->Update();
	}

	for each (CButton* var in controls)
	{
		var->Update();
	}

	OnTouch();

}

void CGame::Render()
{
	IwGxSetColClear(0x40, 0x40, 0x40, 0);
	IwGxClear(IW_GX_COLOUR_BUFFER_F | IW_GX_DEPTH_BUFFER_F);

	IwGxLightingOff();

	for each (CSprite* var in sprites)
	{
		var->Render();
	}

	for each (CButton* var in controls)
	{
		var->Render();
	}


	Iw2DSurfaceShow();

	IwGxFlush();
	IwGxSwapBuffers();
}

void CGame::OnTouch()
{
	CIwFVec2 mousePosition((float)s3ePointerGetX(), (float)s3ePointerGetY());

	for each (CButton* var in controls)
	{
		if (var->IsReleased())
		{
			if (var->GetId()==BUTTON_PLAY)
			{
				InitLevelsMenu();
				break;
			}
			if (var->GetId()==BUTTON_MENU)
			{
				InitMainMenu();
				break;
			}
			if (var->GetId()==BUTTON_LVL1)
			{
				InitLevel(1);
				break;
			}
		}
	}

	for each (CPlayer* var in sprites)
	{
		if (var->GetId()==KOLOBOK)
		{
			if (var->IsSwipedLeft())
			{
				var->MoveLeft();
			}
			else
			if (var->IsSwipedRight())
			{
				var->MoveRight();
			}
			else
			if (var->IsSwipedTop())
			{
				var->MoveTop();
			}
			else
			if (var->IsSwipedBottom())
			{
				var->MoveBottom();
			}
		}
	}
}
void CGame::InitLevelsMenu()
{
	DestroyScene();

	CButton* buttonMenu=new CButton();
	buttonMenu->SetBackgroundTexture(pBackgroundTex);
	buttonMenu->SetPressedBackgroundTexture(pPressedBackgroundTex);
	buttonMenu->SetTextFont(font);
	buttonMenu->SetWidth(80);
	buttonMenu->SetHeight(40);
	buttonMenu->SetX(0);
	buttonMenu->SetY(0);
	buttonMenu->SetText("Menu");
	buttonMenu->SetId(BUTTON_MENU);
	controls.push_back(buttonMenu);

	CButton* buttonLvl1=new CButton();
	buttonLvl1->SetBackgroundTexture(pLvl1);
	buttonLvl1->SetPressedBackgroundTexture(pLvl1);
	buttonLvl1->SetTextFont(font);
	buttonLvl1->SetWidth(150);
	buttonLvl1->SetHeight(80);
	buttonLvl1->SetX(Iw2DGetSurfaceWidth()/3-buttonLvl1->GetWidth()/2);
	buttonLvl1->SetY(Iw2DGetSurfaceHeight()/2-buttonLvl1->GetHeight()/2);
	buttonLvl1->SetText("");
	buttonLvl1->SetId(BUTTON_LVL1);
	controls.push_back(buttonLvl1);

	CSprite* sunRed=new CSprite();
	sunRed->SetBackgroundTexture(pSunRedTex);
	sunRed->SetWidth(250);
	sunRed->SetHeight(250);
	sunRed->SetX(Iw2DGetSurfaceWidth() / 2-sunRed->GetWidth()/2);
	sunRed->SetY(Iw2DGetSurfaceHeight()/2 - sunRed->GetWidth()/2);
	sunRed->SetId(SUN_RED);
	sprites.push_back(sunRed);


	CSprite* sunYellow=new CSprite();
	sunYellow->SetBackgroundTexture(pSunYellowTex);
	sunYellow->SetWidth(250);
	sunYellow->SetHeight(250);
	sunYellow->SetX(Iw2DGetSurfaceWidth() / 2-sunYellow->GetWidth()/2);
	sunYellow->SetY(Iw2DGetSurfaceHeight()/2 - sunYellow->GetWidth()/2);
	sunYellow->SetId(SUN_YELLOW);
	sprites.push_back(sunYellow);


	CSprite* groundForest=new CSprite();
	groundForest->SetBackgroundTexture(pGroundForestTex);
	groundForest->SetWidth(Iw2DGetSurfaceWidth());
	groundForest->SetHeight(Iw2DGetSurfaceHeight());
	groundForest->SetX(0);
	groundForest->SetY(0);
	groundForest->SetId(GROUND_FOREST);
	sprites.push_back(groundForest);
}

void CGame::InitLevel( int levelNum )
{
	DestroyScene();

	CButton* buttonMenu=new CButton();
	buttonMenu->SetBackgroundTexture(pBackgroundTex);
	buttonMenu->SetPressedBackgroundTexture(pPressedBackgroundTex);
	buttonMenu->SetTextFont(font);
	buttonMenu->SetWidth(80);
	buttonMenu->SetHeight(40);
	buttonMenu->SetX(0);
	buttonMenu->SetY(0);
	buttonMenu->SetText("Menu");
	buttonMenu->SetId(BUTTON_MENU);
	controls.push_back(buttonMenu);

	for (int i=0; i<12;i++)
	{
		for (int j=0; j<8;j++)
		{
			CSprite* grass=new CSprite();
			grass->SetBackgroundTexture(pGrass);
			grass->SetWidth(40);
			grass->SetHeight(40);
			grass->SetX(i*grass->GetWidth());
			grass->SetY(j*grass->GetHeight());
			sprites.push_back(grass);
		}
	}

	string s;
	ifstream myfile;
	int i=0;
	string path;

	stringstream strstr;
	strstr<<"/res/levels/lvl"<<levelNum<<".lvl";
	strstr>>path;
	myfile.open ( path.data());



	while(true) 
	{
		getline(myfile,s);
		if (s.find("Trees")==0) break;
	}
	while(true) 
	{
		getline(myfile,s);
		if (s.find("{")==0) break;
	}

	while(true) 
	{
		int rand1;

		i=0;
		getline(myfile,s);
		if (s.find("}")==0) break;
		string s1;

		while (s[i]!=',')
		{
			s1+=s[i];
			i++;
		}

		int x =atoi(s1.data());

		s1.clear();
		i++;


		while (s[i]!=';')
		{
			s1+=s[i];
			i++;
		}

		int y =atoi(s1.data());

		rand1=rand() % 3+1;

		CSolidSprite* tree=new CSolidSprite();

		if (rand1==1)
		{
			tree->SetBackgroundTexture(pTree1);
		}
		if (rand1==2)
		{
			tree->SetBackgroundTexture(pTree2);
		}
		if (rand1==3)
		{
			tree->SetBackgroundTexture(pTree3);
		}
		if (rand1==4)
		{
			tree->SetBackgroundTexture(pTree4);
		}

		tree->SetWidth(40);
		tree->SetHeight(40);
		tree->SetId(TREE);

		int resx=x*40;
		int resy=y*40;

		tree->SetX(resx);
		tree->SetY(resy);
		sprites.push_back(tree);
	}


	while(true) 
	{
		getline(myfile,s);
		if (s.find("Stars")==0) break;
	}
	while(true) 
	{
		getline(myfile,s);
		if (s.find("{")==0) break;
	}
	while(true) 
	{
		i=0;
		getline(myfile,s);
		if (s.find("}")==0) break;
		string s1;

		while (s[i]!=',')
		{
			s1+=s[i];
			i++;
		}

		int x =atoi(s1.data());

		s1.clear();
		i++;


		while (s[i]!=';')
		{
			s1+=s[i];
			i++;
		}

		int y =atoi(s1.data());

		CStar* star=new CStar();


		star->SetBackgroundTexture(pStar);

		star->SetWidth(30);
		star->SetHeight(30);
		star->SetId(STAR);

		int resx=x*40+5;
		int resy=y*40+5;

		star->SetX(resx);
		star->SetY(resy);
		sprites.push_back(star);
	}



	while(true) 
	{
		getline(myfile,s);
		if (s.find("Kolobok")==0) break;
	}
	while(true) 
	{
		getline(myfile,s);
		if (s.find("{")==0) break;
	}
	while(true) 
	{
		i=0;
		getline(myfile,s);
		if (s.find("}")==0) break;
		string s1;

		while (s[i]!=',')
		{
			s1+=s[i];
			i++;
		}

		int x =atoi(s1.data());

		s1.clear();
		i++;


		while (s[i]!=';')
		{
			s1+=s[i];
			i++;
		}

		int y =atoi(s1.data());

		CPlayer* player=new CPlayer(this);


		player->SetBackgroundTexture(pPlayer);

		player->SetWidth(30);
		player->SetHeight(30);
		player->SetId(KOLOBOK);

		int resx=x*40+5;
		int resy=y*40+5;

		player->SetX(resx);
		player->SetY(resy);
		sprites.push_back(player);
	}
	myfile.close();
}



/*if (rand1==1)
{
CSolidSprite* tree=new CSolidSprite();
tree->SetBackgroundTexture(pTree1);
tree->SetWidth(40);
tree->SetHeight(40);
tree->SetX(i*tree->GetWidth());
tree->SetY(j*tree->GetHeight());
sprites.push_back(tree);
}
if (rand1==2)
{
CSolidSprite* tree=new CSolidSprite();
tree->SetBackgroundTexture(pTree2);
tree->SetWidth(40);
tree->SetHeight(40);
tree->SetX(i*tree->GetWidth());
tree->SetY(j*tree->GetHeight());
sprites.push_back(tree);
}
if (rand1==3)
{
CSolidSprite* tree=new CSolidSprite();
tree->SetBackgroundTexture(pTree3);
tree->SetWidth(40);
tree->SetHeight(40);
tree->SetX(i*tree->GetWidth());
tree->SetY(j*tree->GetHeight());
sprites.push_back(tree);
}
if (rand1==4)
{
CSolidSprite* tree=new CSolidSprite();
tree->SetBackgroundTexture(pTree4);
tree->SetWidth(40);
tree->SetHeight(40);
tree->SetX(i*tree->GetWidth());
tree->SetY(j*tree->GetHeight());
sprites.push_back(tree);
}*/