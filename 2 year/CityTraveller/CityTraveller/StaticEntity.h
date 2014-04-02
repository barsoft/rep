#pragma once
#include "GameEntity.h"

class StaticEntity: public GameEntity
{
friend class PlayerIconEntity;
private:;
	IDirect3DTexture9 *g_staticTexture;
	IDirect3DVertexBuffer9 *g_static;
	VertPosTc m_v[4];
	int x1,y1,x2,y2;
	string s;
public:
	StaticEntity(int _x1,int _y1,int _x2,int _y2,const char *_s) { x1=_x1; y1=_y1; x2=_x2; y2=_y2; s=_s; g_static = NULL;}
	StaticEntity() {}
	void ReleaseTextures();
	void LoadTextures();
	void OnPaint();
	void InitObject(int _x1,int _y1,int _x2,int _y2,const char *_s);
};

void StaticEntity::OnPaint()
{
	m_v[0] = VertPosTc(D3DXVECTOR3(x1, y2, 0), D3DXVECTOR2(0,0));	
	m_v[1] = VertPosTc(D3DXVECTOR3(x2, y2, 0), D3DXVECTOR2(1,0));
	m_v[3] = VertPosTc(D3DXVECTOR3(x2, y1, 0), D3DXVECTOR2(1,1));
	m_v[2] = VertPosTc(D3DXVECTOR3(x1, y1, 0), D3DXVECTOR2(0,1));	

	g_pD3DDevice->SetRenderState(D3DRS_ALPHABLENDENABLE, true);
	g_pD3DDevice->SetRenderState(D3DRS_SRCBLEND,D3DBLEND_SRCALPHA);
	g_pD3DDevice->SetRenderState(D3DRS_DESTBLEND,D3DBLEND_INVSRCALPHA);


	g_pD3DDevice->SetStreamSource(0, g_static, 0, sizeof(VertPosTc));
	g_pD3DDevice->SetFVF(VERTEXFVF);
	g_pD3DDevice->SetTexture(0, g_staticTexture);
	g_pD3DDevice->DrawPrimitiveUP(D3DPT_TRIANGLESTRIP,2,m_v,sizeof(VertPosTc));
}
void StaticEntity::LoadTextures()
{
	D3DXCreateTextureFromFile(g_pD3DDevice, s.data() , &g_staticTexture);	
}
void StaticEntity::ReleaseTextures()
{
	g_staticTexture->Release();
}
void StaticEntity::InitObject(int _x1,int _y1,int _x2,int _y2,const char *_s)
{
	x1=_x1; y1=_y1; x2=_x2; y2=_y2; s=_s;
}
