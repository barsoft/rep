#pragma once
#ifndef SPRITE_H
#define SPRITE_H

#include "s3e.h"
#include "Iw2D.h"
#include <string>
#include <iostream>

using namespace std;

class CSprite
{
public:
	CSprite();
	~CSprite();

	// update will be called a fixed number of times per second
	// regardless of visual framerate
	virtual void Update();
	// render will be called as fast as possible (but not faster
	// than the update rate)
	virtual void Render();

	void SetX( int x);
	void SetY( int y);
	void SetWidth( int y);
	void SetHeight( int y);
	void SetId(int id);
	void SetBackgroundTexture(CIw2DImage* pTex);
	void SetAngle(float rangle);

	int GetX( );
	int GetY( );
	int GetWidth( );
	int GetHeight( );
	int GetId();
	float GetAngle();
	CIw2DImage* GetBackgroundTexture();

	void DrawSprite(CIw2DImage* image, int at_pos_x, int at_pos_y, int width,int height, int originx,int originy, float rangle, iwfixed scale);
protected:
	CIwSVec2 m_Position;
	CIwSVec2 m_Size;
	CIw2DImage* pTex;
	int id; 
	float rangle;
};

#endif