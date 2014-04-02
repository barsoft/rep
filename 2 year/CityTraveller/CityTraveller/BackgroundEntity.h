#pragma once
#include "GameEntity.h"

class BackgroundEntity: public GameEntity
{
private:;
	IDirect3DTexture9 *g_backgroundTexture;
	IDirect3DVertexBuffer9 *g_background;
	VertPosTc m_v[4];
	BYTE *Ptr;
public:
	BackgroundEntity() { g_background = NULL;};
	void ReleaseTextures();
	void LoadTextures();
	void OnPaint();
};

void BackgroundEntity::OnPaint()
{
	m_v[0] = VertPosTc(D3DXVECTOR3(-280, 210, 0), D3DXVECTOR2(0,0));	
	m_v[1] = VertPosTc(D3DXVECTOR3(280,210, 0), D3DXVECTOR2(1,0));
	m_v[3] = VertPosTc(D3DXVECTOR3(280, -210, 0), D3DXVECTOR2(1,1));
	m_v[2] = VertPosTc(D3DXVECTOR3(-280, -210, 0), D3DXVECTOR2(0,1));	
	g_pD3DDevice->SetStreamSource(0, g_background, 0, sizeof(VertPosTc));
	g_pD3DDevice->SetFVF(VERTEXFVF);
	g_pD3DDevice->SetTexture(0, g_backgroundTexture);
	g_pD3DDevice->DrawPrimitiveUP(D3DPT_TRIANGLESTRIP,2,m_v,sizeof(VertPosTc));
}
void BackgroundEntity::LoadTextures()
{
	D3DXCreateTextureFromFile(g_pD3DDevice, "Data\\background\\map_1.jpg", &g_backgroundTexture);	
}
void BackgroundEntity::ReleaseTextures()
{
	g_backgroundTexture->Release();
}
