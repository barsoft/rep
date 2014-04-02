#pragma once
#include "GameEntity.h"
enum ButtonStyle
{
	StandardStyle=0,
	ArrowDownStyle=1,
	ArrowUpStyle=2,
	MapStyle=3
};
class ButtonEntity: public GameEntity
{
private:
	ButtonStyle bs;
	IDirect3DTexture9 *g_buttonTextureNo_Active;
	IDirect3DTexture9 *g_buttonTextureSelected;
	IDirect3DTexture9 *g_buttonTextureArrowDownNoActive;
	IDirect3DTexture9 *g_buttonTextureArrowDownSelected;
	IDirect3DTexture9 *g_buttonTextureArrowUpNoActive;
	IDirect3DTexture9 *g_buttonTextureArrowUpSelected;
	IDirect3DTexture9 *g_buttonTextureMapNoActive;
	IDirect3DTexture9 *g_buttonTextureMapNoSelected;
	IDirect3DVertexBuffer9 *g_button;
	VertPosTc m_v[4];
	BYTE *Ptr;
	string text;
	int x,y;
	bool Click;
	bool check;
	void DisplayText(string s);
	RECT rct;
public:
	ButtonEntity(int _x,int _y,const char *s,ButtonStyle _bs) 
	{		
		x=_x; y=_y; text=s; g_button = NULL; check=false; bs=_bs;
	}
	ButtonEntity() {}
	void ReleaseTextures();
	void LoadTextures();
	void InitObject(int _x,int _y,const char *s,ButtonStyle);
	void OnPaint( );
	bool OnFocus();
	bool OnSelect();
};

void ButtonEntity::OnPaint()
{
	if (bs==0)
	{
		m_v[0] = VertPosTc(D3DXVECTOR3(x-51, y+11, 0), D3DXVECTOR2(0,0));	
		m_v[1] = VertPosTc(D3DXVECTOR3(x+51, y+11, 0), D3DXVECTOR2(1,0));
		m_v[3] = VertPosTc(D3DXVECTOR3(x+51, y-11, 0), D3DXVECTOR2(1,1));
		m_v[2] = VertPosTc(D3DXVECTOR3(x+-51, y-11, 0), D3DXVECTOR2(0,1));	
	}
	if (bs==3)
	{
		m_v[0] = VertPosTc(D3DXVECTOR3(x-54, y+31, 0), D3DXVECTOR2(0,0));	
		m_v[1] = VertPosTc(D3DXVECTOR3(x+54, y+31, 0), D3DXVECTOR2(1,0));
		m_v[3] = VertPosTc(D3DXVECTOR3(x+54, y-31, 0), D3DXVECTOR2(1,1));
		m_v[2] = VertPosTc(D3DXVECTOR3(x+-54, y-31, 0), D3DXVECTOR2(0,1));	
	}
	g_pD3DDevice->SetStreamSource(0, g_button, 0, sizeof(VertPosTc));
	g_pD3DDevice->SetRenderState(D3DRS_ALPHAREF, (DWORD)0x000000016);
	g_pD3DDevice->SetRenderState(D3DRS_ALPHATESTENABLE, TRUE); 
	g_pD3DDevice->SetRenderState(D3DRS_ALPHAFUNC, D3DCMP_GREATEREQUAL);
	g_pD3DDevice->SetFVF(VERTEXFVF);
	
	if (!this->OnFocus())
	{
		if (bs==0) g_pD3DDevice->SetTexture(0, g_buttonTextureNo_Active);
		if (bs==1) g_pD3DDevice->SetTexture(0, g_buttonTextureArrowDownNoActive);
		if (bs==2) g_pD3DDevice->SetTexture(0, g_buttonTextureArrowUpNoActive);
		if (bs==3) g_pD3DDevice->SetTexture(0, g_buttonTextureMapNoActive);
		Click=true;
	}
	else 
	{
		if (bs==0) g_pD3DDevice->SetTexture(0, g_buttonTextureSelected);
		if (bs==1) g_pD3DDevice->SetTexture(0, g_buttonTextureArrowDownSelected);
		if (bs==2) g_pD3DDevice->SetTexture(0, g_buttonTextureArrowUpSelected);
		if (bs==3) g_pD3DDevice->SetTexture(0, g_buttonTextureMapNoSelected);
		if (Click) {_beginthread(ClickThread,0,NULL); Click=false;}
	}
	g_pD3DDevice->DrawPrimitiveUP(D3DPT_TRIANGLESTRIP,2,m_v,sizeof(VertPosTc));
	DisplayText(text);
	
}
void ButtonEntity::LoadTextures()
{
	D3DXCreateTextureFromFile(g_pD3DDevice, "Data\\hud\\B_NoActive.bmp", &g_buttonTextureNo_Active);
	D3DXCreateTextureFromFile(g_pD3DDevice, "Data\\hud\\B_Selected.bmp", &g_buttonTextureSelected);
	D3DXCreateTextureFromFile(g_pD3DDevice, "Data\\hud\\B_ArrowDownNoActive.bmp", &g_buttonTextureArrowDownNoActive);
	D3DXCreateTextureFromFile(g_pD3DDevice, "Data\\hud\\B_ArrowDownSelected.bmp", &g_buttonTextureArrowDownSelected);
	D3DXCreateTextureFromFile(g_pD3DDevice, "Data\\hud\\B_ArrowUpNoActive.bmp", &g_buttonTextureArrowUpNoActive);
	D3DXCreateTextureFromFile(g_pD3DDevice, "Data\\hud\\B_ArrowUpSelected.bmp", &g_buttonTextureArrowUpSelected);
	D3DXCreateTextureFromFile(g_pD3DDevice, "Data\\hud\\B_MiniMapANoActive.jpg", &g_buttonTextureMapNoActive);
	D3DXCreateTextureFromFile(g_pD3DDevice, "Data\\hud\\B_MiniMapASelected.jpg", &g_buttonTextureMapNoSelected);
}
void ButtonEntity::ReleaseTextures()
{
	g_buttonTextureNo_Active->Release();
	g_buttonTextureSelected->Release();
	g_buttonTextureArrowDownNoActive->Release();
	g_buttonTextureArrowDownSelected->Release();
	g_buttonTextureArrowUpNoActive->Release();
	g_buttonTextureArrowUpSelected->Release();
	g_buttonTextureMapNoActive->Release();
	g_buttonTextureMapNoSelected->Release();
}
void ButtonEntity::DisplayText(string s)
{
	rct.left=x*2.15+570;
	rct.right=x*2.15+780;
	rct.top=370-y*1.86;
	rct.bottom=rct.top+30;
	m_font->DrawText(NULL, s.data(), -1, &rct, 0, fontColor );
}
bool ButtonEntity::OnFocus()
{
	POINT pt;
	RECT rect;
	GetCursorPos(&pt);
	GetWindowRect(g_hWnd,&rect);
	if (bs==0)
	{
		if (pt.x>(rect.left+x*2.14+490)*(rect.right-rect.left)/1280 && 
		pt.x<(rect.left+x*2.14+727)*(rect.right-rect.left)/1280 && 
		pt.y<(rect.top+430-y*1.86)*(rect.bottom-rect.top)/800 && 
		pt.y>(rect.top+390-y*1.86)*(rect.bottom-rect.top)/800)	return true;
		else return false;
	}
	if (bs==3)
	{
		if (pt.x>(rect.left+x*2.14+510)*(rect.right-rect.left)/1280 && 
		pt.x<(rect.left+x*2.14+760)*(rect.right-rect.left)/1280 && 
		pt.y<(rect.top+470-y*1.86)*(rect.bottom-rect.top)/800 && 
		pt.y>(rect.top+350-y*1.86)*(rect.bottom-rect.top)/800)	return true;
		else return false;
	}
}
bool ButtonEntity::OnSelect()
{
	if (this->OnFocus() && (GetAsyncKeyState(VK_LBUTTON)&32768) && c>=30) { c=0; return true;}
	c++;
	return false;
}
void ButtonEntity::InitObject(int _x,int _y,const char *s,ButtonStyle _bs)
{
	x=_x; y=_y; text=s; g_button = NULL; bs=_bs;
}
