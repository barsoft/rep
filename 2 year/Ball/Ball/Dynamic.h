#pragma once
#include "Static.h"
#include "Engine.h"
class Dynamic:public Static
{
	float mass;
	float counter;
	float force;
	float ottalk;
public:
	void InitObject(char *_pathTexture,char *_pathMesh,float _x,float _y,float _z,float _mass)
	{
		SetTexture(_pathTexture);
		SetMesh(_pathMesh);
		SetCoords(_x,_y,_z);
		SetMass(_mass);
	}
	void OnCreate()
	{
		if (!xRot) xRot=1.57;
		if (!xScale) xScale=1;
		if (!yScale) yScale=1;
		if (!zScale) zScale=1;
		counter=1;
		force=-mass;
	}
	void OnUpdate()
	{
		int zet=200;
		if (zPos<=zet)
		{
			if (GetAsyncKeyState(VK_NUMPAD7)&32768) { ottalk=-3.05;  }
			else ottalk=force/1.15;
			force=-mass;
		}

		force*=1.001;
		ottalk*=0.999;
		zPos+=force-ottalk;
		
		

		D3DXMatrixRotationX(&rotateMatrixX, xRot);
		D3DXMatrixRotationY(&rotateMatrixY, yRot);
		D3DXMatrixRotationZ(&rotateMatrixZ, zRot);
		D3DXMatrixScaling(&scaleMatrix, xScale, yScale, zScale);
		D3DXMatrixTranslation(&translateMatrix,xPos,yPos,zPos);
		D3DXMatrixMultiply(&resultMatrix,&rotateMatrixX,&rotateMatrixY);
		D3DXMatrixMultiply(&resultMatrix,&resultMatrix,&rotateMatrixZ);
		D3DXMatrixMultiply(&resultMatrix,&resultMatrix,&scaleMatrix);
		D3DXMatrixMultiply(&resultMatrix,&resultMatrix,&translateMatrix);
		m_d3dDevice->SetTransform(D3DTS_WORLD,&resultMatrix);
		m_d3dDevice->SetTexture(0, texture);
		mesh->DrawSubset(0);

		RECT rec;
		rec.left=0;
		rec.right=300;
		rec.top=0;
		rec.bottom=20;
		stringstream s;

		s<<"FORCE:"<<force;
		m_font->DrawText(0,s.str().data(),-1,&rec,DT_LEFT,fontColor);

		rec.top=20;
		rec.bottom=40;
		s.str("");
		s<<"OTTALK:"<<ottalk;
		m_font->DrawText(0,s.str().data(),-1,&rec,DT_LEFT,fontColor);
	}
	void SetMass(float _mass)
	{
		mass=_mass;
	}
};