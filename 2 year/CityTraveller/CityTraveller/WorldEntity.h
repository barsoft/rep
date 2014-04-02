#pragma once
#include "GameEntity.h"

class WorldEntity:public GameEntity
{
friend class AI;
private:
	VertPosTc m_v[4];
	IDirect3DVertexBuffer9 *g_world;
	vector<IDirect3DTexture9*> g_worldTexture;
public:
	void ReleaseTextures();
	void LoadTextures();	
	void OnPaint();
};
void WorldEntity::LoadTextures()
{
	char *s1=new char[80];
	string s;
	IDirect3DTexture9* buff=NULL;
	for (int i=0;i<2;i++)
	{
		s="Data\\map\\map_";
		sprintf(s1,"%d",i+1);
		s+=s1;
		s+=".jpg";
		D3DXCreateTextureFromFile(g_pD3DDevice, s.data(), &buff);
		g_worldTexture.push_back(buff);
	}
	TextureIterator=g_worldTexture.begin();
}
void WorldEntity::OnPaint()
{
	m_v[0] = VertPosTc(D3DXVECTOR3(-280, 210, 0), D3DXVECTOR2(0,0));	
	m_v[1] = VertPosTc(D3DXVECTOR3(280, 210, 0), D3DXVECTOR2(1,0));
	m_v[3] = VertPosTc(D3DXVECTOR3(280, -120, 0), D3DXVECTOR2(1,1));
	m_v[2] = VertPosTc(D3DXVECTOR3(-280, -120, 0), D3DXVECTOR2(0,1));	
	g_pD3DDevice->SetStreamSource(0, g_world, 0, sizeof(VertPosTc));
	g_pD3DDevice->SetFVF(VERTEXFVF);
	g_pD3DDevice->SetTexture(0, *(TextureIterator));  
	g_pD3DDevice->DrawPrimitiveUP(D3DPT_TRIANGLESTRIP,2,m_v,sizeof(VertPosTc));
}
void WorldEntity::ReleaseTextures()
{
	for (TextureIterator=g_worldTexture.begin();TextureIterator!=g_worldTexture.end(); TextureIterator++)
		(*TextureIterator)->Release();
}
