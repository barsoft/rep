#pragma once
#include "sprite.h"
#include "IwTexture.h"
#include "IwGx.h"
#include "IwGxFont.h"
#include "Iw2D.h"

#define DEG2RAD(DEG) ((DEG)*((PI)/(180.0)))

CSprite::CSprite()
	: m_Position(0,0)
	, m_Size(Iw2DGetSurfaceHeight() / 10, Iw2DGetSurfaceHeight() / 10)
	, rangle(0.0f)
{

}


CSprite::~CSprite()
{
}


void CSprite::Update()
{

}


void CSprite::DrawSprite(CIw2DImage* image, int at_pos_x, int at_pos_y, int width,int height, int originx,int originy, float rangle, iwfixed scale)
{
	glEnable(GL_BLEND);
	
	glBlendFunc(GL_ONE,GL_ONE_MINUS_SRC_ALPHA);

	Iw2DSetColour(0xFFFFFFFF);

	CIwFMat2D rot;
	CIwFVec2 center=CIwFVec2(originx,originy);
	rot.SetRot(rangle,center);

	Iw2DSetTransformMatrix(rot);
	
	Iw2DDrawImage(image, CIwFVec2(at_pos_x,at_pos_y), CIwFVec2(width, height));

	Iw2DSetTransformMatrix(CIwFMat2D::g_Identity);
}

void CSprite::Render()
{	CIwFVec2 origin=CIwFVec2(GetX()+GetWidth()/2,GetY()+GetHeight()/2);
	DrawSprite(pTex, GetX(), GetY(),GetWidth(),GetHeight(), origin.x,origin.y,rangle, IW_GEOM_ONE);
}

void CSprite::SetX(int x)
{
	this->m_Position.x=x;
}

void CSprite::SetY(int y)
{
	this->m_Position.y=y;
}

void CSprite::SetWidth(int width)
{
	this->m_Size.x=width;
}

void CSprite::SetHeight(int height)
{
	this->m_Size.y=height;
}

void CSprite::SetId(int id)
{
	this->id=id;
}

void CSprite::SetAngle( float rangle )
{
	this->rangle=rangle;
}

void CSprite::SetBackgroundTexture(CIw2DImage* pTex)
{
	this->pTex=pTex;
}

int CSprite::GetX()
{
	return this->m_Position.x;
}

int CSprite::GetY()
{
	return this->m_Position.y;
}

int CSprite::GetWidth()
{
	return this->m_Size.x;
}

int CSprite::GetHeight()
{
	return this->m_Size.y;
}

int CSprite::GetId()
{
	return this->id;
}

CIw2DImage* CSprite::GetBackgroundTexture()
{
	return this->pTex;
}


float CSprite::GetAngle()
{
	return this->rangle;
}