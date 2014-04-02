#pragma once
#ifndef BUTTON_H
#define BUTTON_H

#include "s3e.h"
#include "Iw2D.h"
#include <string>
#include <iostream>
#include "IwGxFontContainer.h"
#include "sprite.h"
using namespace std;

class CButton: public CSprite
{
public:
	CButton();
	~CButton();

	bool IsTouched();
	bool IsReleased();
	bool IsDown();

	void Update();

	void Render();

	void SetPressedBackgroundTexture(CIw2DImage* pTex);
	void SetText(string s);
	void SetTextFont(CIw2DFont* pFont);

	CIw2DImage* GetPressedBackgroundTexture();
	string GetText();
	CIw2DFont* GetTextFont();
private:
	bool touched;
	bool released;
	bool  oldState;
	bool down;

	CIwColour colour;
	CIw2DImage* pPressedTex;
	CIw2DFont* pFont;
	string text;
	int id;
};

#endif