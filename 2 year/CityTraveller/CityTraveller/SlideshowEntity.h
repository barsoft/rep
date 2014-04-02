#pragma once
#include "GameEntity.h"

class SlideshowEntity:public GameEntity
{
private:
	int x1,y1,x2,y2;
	VertPosTc m_v[4];
	IDirect3DVertexBuffer9 *g_slideshow;
	vector<IDirect3DTexture9*> g_slideshowTexture;
public:
	SlideshowEntity(int _x1,int _y1,int _x2,int _y2) { x1=_x1; y1=_y1; x2=_x2; y2=_y2; g_slideshow = NULL;}
	SlideshowEntity(){}
	void ReleaseTextures();
	void LoadTextures();	
	void OnPaint();
	void NextSlide();
	void InitObject(int _x1,int _y1,int _x2,int _y2);
};
void SlideshowEntity::LoadTextures()
{
	char *s1=new char[80];
	string s;
	IDirect3DTexture9* buff=NULL;
	for (int i=0;i<8;i++)
	{
		s="Data\\slideshow\\DA_";
		sprintf(s1,"%d",i+1);
		s+=s1;
		s+=".jpg";
		D3DXCreateTextureFromFile(g_pD3DDevice, s.data(), &buff);
		g_slideshowTexture.push_back(buff);
	}
	TextureIterator=g_slideshowTexture.begin();
}
void SlideshowEntity::OnPaint()
{
	m_v[0] = VertPosTc(D3DXVECTOR3(x1, y2, 0), D3DXVECTOR2(0,0));	
	m_v[1] = VertPosTc(D3DXVECTOR3(x2, y2, 0), D3DXVECTOR2(1,0));
	m_v[3] = VertPosTc(D3DXVECTOR3(x2, y1, 0), D3DXVECTOR2(1,1));
	m_v[2] = VertPosTc(D3DXVECTOR3(x1, y1, 0), D3DXVECTOR2(0,1));
	
	g_pD3DDevice->SetRenderState(D3DRS_ALPHABLENDENABLE, true);
	g_pD3DDevice->SetRenderState(D3DRS_SRCBLEND,D3DBLEND_SRCALPHA);
	g_pD3DDevice->SetRenderState(D3DRS_DESTBLEND,D3DBLEND_INVSRCALPHA);

	g_pD3DDevice->SetStreamSource(0, g_slideshow, 0, sizeof(VertPosTc));
	g_pD3DDevice->SetFVF(VERTEXFVF);
	g_pD3DDevice->SetTexture(0, *(TextureIterator));  
	g_pD3DDevice->DrawPrimitiveUP(D3DPT_TRIANGLESTRIP,2,m_v,sizeof(VertPosTc));
}
void SlideshowEntity::ReleaseTextures()
{
	for (TextureIterator=g_slideshowTexture.begin();TextureIterator!=g_slideshowTexture.end(); TextureIterator++)
		(*TextureIterator)->Release();
}
void SlideshowEntity::NextSlide()
{
	TextureIterator++;
}
void SlideshowEntity::InitObject(int _x1,int _y1,int _x2,int _y2)
{
	x1=_x1; x2=_x2; y1=_y1; y2=_y2;
}