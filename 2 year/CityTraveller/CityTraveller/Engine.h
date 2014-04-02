#pragma once
#pragma comment ( lib, "d3d9.lib" )
#pragma comment ( lib, "d3dx9.lib" )
#include <d3d9.h>
#include <d3dx9.h>
#include <windows.h>
#include <process.h>
#include <MMSystem.h>
#pragma comment(lib,"Winmm.lib")
#define VERTEXFVF (D3DFVF_XYZ | D3DFVF_TEX1)
HWND          g_hWnd;
int c=30;
void	MusicThread( void *dummy );
void	ClickThread( void *dummy );
void	SlideshowThread(void *dummy);
LPD3DXFONT m_font, m_font2,m_font3;
D3DCOLOR fontColor = D3DCOLOR_ARGB(255,255,255,255);
D3DCOLOR fontColor2 = D3DCOLOR_ARGB(255,255,255,255);
IDirect3D9       *g_pD3D       = NULL;
IDirect3DDevice9 *g_pD3DDevice = NULL;
int timer=120;
enum GameState
{
	IsPlayGame,
	IsMenuGame,
	IsContinue,
	IsMap,
	IsNewGame
};
GameState gs;
struct VertPosTc 
{
	D3DXVECTOR3 m_pos;
	D3DXVECTOR2 m_tc;
	VertPosTc() {}
	VertPosTc(D3DXVECTOR3 pos, D3DXVECTOR2 tc)
		: m_pos(pos), m_tc(tc)
	{}
};
void MusicThread( void *dummy )
{
	PlaySound("Data\\music\\Butyrka - Attestat.wav",GetModuleHandle(NULL),SND_FILENAME | SND_ASYNC);
	_endthread();
}
void ClickThread( void *dummy )
{
	mciSendString("open Data\\sound\\click.wav type waveaudio alias myfile",NULL,0,NULL);
	DWORD result = mciSendString("play myfile ", NULL, 0, NULL);
	_endthread();
}
