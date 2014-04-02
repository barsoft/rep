#pragma once
#include "button.h"
#include "IwTexture.h"
#include "IwGx.h"
#include "IwGxFont.h"
#include "Iw2D.h"


CButton::CButton()
	:text("text")
{
	colour=0xDDFFFFFF;
	oldState=false;
}


CButton::~CButton()
{
}



void CButton::Update()
{
	CIwFVec2 mousePosition((float)s3ePointerGetX(), (float)s3ePointerGetY());
	CIwRect rect=CIwRect(m_Position.x,m_Position.y,m_Size.x,m_Size.y);
	bool bPointInRect = mousePosition.x > rect.x && mousePosition.x < rect.x+rect.w && mousePosition.y > rect.y && mousePosition.y<rect.y+rect.h;

	bool newState=s3ePointerGetState(S3E_POINTER_BUTTON_SELECT) & S3E_POINTER_STATE_DOWN;

	touched=false;

	released=false;

	down=false;

	if (bPointInRect)
	{
		if ( newState )
		{
			down=true;
			if (!oldState)
				touched=true;
		}
		else if (oldState){
			released=true;
		}
	}

	oldState=newState;
}


void CButton::Render()
{
	if (down)
	{
		DrawSprite(pPressedTex, GetX(), GetY(),GetWidth(),GetHeight(),0,0, 0, IW_GEOM_ONE);
	}
	else
	{
		DrawSprite(pTex, GetX(), GetY(),GetWidth(),GetHeight(),0,0, 0, IW_GEOM_ONE);
	}

	Iw2DSetColour(colour);

	Iw2DSetFont(pFont);

	Iw2DDrawString(text.data(),CIwFVec2(m_Position.x,m_Position.y),  CIwFVec2(m_Size.x,m_Size.y),IW_2D_FONT_ALIGN_CENTRE,IW_2D_FONT_ALIGN_CENTRE);
}


void CButton::SetText(string text)
{
	this->text=text;
}

void CButton::SetPressedBackgroundTexture(CIw2DImage* pPressedTex)
{
	this->pPressedTex=pPressedTex;
}

void CButton::SetTextFont(CIw2DFont* pFont)
{
	this->pFont=pFont;
}


string CButton::GetText()
{
	return this->text;
}

CIw2DImage* CButton::GetPressedBackgroundTexture()
{
	return this->pPressedTex;
}

CIw2DFont* CButton::GetTextFont()
{
	return this->pFont;
}

bool CButton::IsTouched()
{
	return touched;
}

bool CButton::IsReleased()
{
	return released;
}

bool CButton::IsDown()
{
	return down;
}
