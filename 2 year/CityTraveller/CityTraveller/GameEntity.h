#pragma once
#include <iostream>
using namespace std;
#include <vector>
#include "Engine.h"

class GameEntity
{
public:
	vector<IDirect3DTexture9*>::iterator TextureIterator;
protected:
	virtual void ReleaseTextures()=0;
	virtual void LoadTextures()=0;	
	virtual void OnPaint()=0;
};