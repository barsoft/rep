#pragma once

#define SCREEN_WIDTH 1920
#define SCREEN_HEIGHT 1080
#define CUSTOMFVF (D3DFVF_XYZ | D3DFVF_DIFFUSE)

#include <d3d9.h>
#include <d3dx9.h>
#include <windows.h>
#include <string>
#include <sstream>

#pragma comment(lib,"winmm.lib")
#pragma comment (lib, "d3d9.lib")
#pragma comment (lib, "d3dx9.lib")

LPDIRECT3D9 m_d3dObject;
LPDIRECT3DDEVICE9 m_d3dDevice;
LPDIRECT3DVERTEXBUFFER9 v_buffer = NULL; 
LPD3DXFONT m_font;
bool windowed=true;

using namespace std;

D3DCOLOR fontColor = D3DCOLOR_ARGB(255,255,255,255);