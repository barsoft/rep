#pragma once
#include "Engine.h"
class Static
{
	friend class Dynamic;
protected:
	D3DXMATRIX rotateMatrixX;
	D3DXMATRIX rotateMatrixY;
	D3DXMATRIX rotateMatrixZ;
	D3DXMATRIX translateMatrix;
	D3DXMATRIX scaleMatrix;
	D3DXMATRIX resultMatrix;
	char *pathTexture;
	char *pathMesh;
	float xPos,yPos,zPos;
	float xRot,yRot,zRot;
	float xScale,yScale,zScale;
	LPDIRECT3DTEXTURE9 texture;
	ID3DXMesh* mesh;
public:
	//////////////////////////////// INITIALIZE
	///////////////////////////////////////////
	//////////////////////////////// COMMON
	void SetTexture(char *_pathTexture)
	{
		pathTexture=_pathTexture;
	}
	void SetTexture(LPDIRECT3DTEXTURE9 _texture)
	{
		texture=_texture;
	}
	void SetMesh(char *_pathMesh)
	{
		pathMesh=_pathMesh;
	}
	//////////////////////////////// TRANSLATE
	void SetX(float _xPos)
	{
		xPos=_xPos;
	}
	void SetY(float _yPos)
	{
		yPos=_yPos;
	}
	void SetZ(float _zPos)
	{
		zPos=_zPos;
	}
	void SetCoords(float _xPos,float _yPos,float _zPos)
	{
		xPos=_xPos; yPos=_yPos; zPos=_zPos;
	}
	//////////////////////////////// ROTATE
	void SetXRotationAngle(float _xRot)
	{
		xRot=_xRot;
	}
	void SetYRotationAngle(float _yRot)
	{
		yRot=_yRot;
	}
	void SetZRotationAngle(float _zRot)
	{
		zRot=_zRot;
	}
	void SetRotation(float _xRot,float _yRot,float _zRot)
	{
		SetXRotationAngle(_xRot);
		SetYRotationAngle(_yRot);
		SetZRotationAngle(_zRot);
	}
	//////////////////////////////// SCALE
	void SetXScale(float _xScale)
	{
		xScale=_xScale;
	}
	void SetYScale(float _yScale)
	{
		yScale=_yScale;
	}
	void SetZScale(float _zScale)
	{
		zScale=_zScale;
	}
	void SetScale(float _xScale,float _yScale,float _zScale)
	{
		SetXScale(_xScale);
		SetYScale(_yScale);
		SetZScale(_zScale);
	}
	//////////////////////////////// INIT
	void InitObject(char *_pathTexture,char *_pathMesh,float _x,float _y,float _z)
	{
		SetTexture(_pathTexture);
		SetMesh(_pathMesh);
		SetCoords(_x,_y,_z);
	}
	//////////////////////////////// LOAD
	/////////////////////////////////////
	void LoadObject()
	{
		D3DXCreateTextureFromFile(m_d3dDevice, pathTexture, &texture);
		D3DXLoadMeshFromX(pathMesh,0,m_d3dDevice,NULL,NULL,NULL,NULL,&mesh);
	}
	void OnCreate()
	{
		if (!xRot) xRot=1.57;
		if (!xScale) xScale=1;
		if (!yScale) yScale=1;
		if (!zScale) zScale=1;
	}
	//////////////////////////////// PAINT
	//////////////////////////////////////
	virtual void OnUpdate()
	{
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
	}
	//////////////////////////////// DESTROY
	////////////////////////////////////////
	void OnDestroy()
	{
		texture->Release();
		mesh->Release();
	}
};