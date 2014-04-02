#pragma once
#include "GameEntity.h"
#include "WorldEntity.h"

class CharacterEntity: public GameEntity
{
friend class AI;
private:
	vector<IDirect3DTexture9*> SpriteBack;
	vector<IDirect3DTexture9*> SpriteFront;
	vector<IDirect3DTexture9*> SpriteLeft;
	vector<IDirect3DTexture9*> SpriteRight;
	int speed;
	int x;
	int y;
	WorldEntity map;
	VertPosTc m_v[4];
	IDirect3DVertexBuffer9 *g_pVB;
public:
	CharacterEntity() {speed=2; x=0;y=0;g_pVB = NULL;}
	void ReleaseTextures();
	void LoadTextures();
	void OnPaint();
	void SelectMap(WorldEntity);
	POINT GetPosition();
};

void CharacterEntity::ReleaseTextures()
{
	for (TextureIterator=SpriteBack.begin();TextureIterator!=SpriteBack.end();TextureIterator++)	(*TextureIterator)->Release();
	for (TextureIterator=SpriteFront.begin();TextureIterator!=SpriteFront.end();TextureIterator++)	(*TextureIterator)->Release();
	for (TextureIterator=SpriteLeft.begin();TextureIterator!=SpriteLeft.end();TextureIterator++)	(*TextureIterator)->Release();
	for (TextureIterator=SpriteRight.begin();TextureIterator!=SpriteRight.end();TextureIterator++)	(*TextureIterator)->Release();
}
void CharacterEntity::LoadTextures()
{
	IDirect3DTexture9 *buff=NULL;
	string s;
	char *s1=new char[80];
	for (int i=0; i<27;i++)
	{
		s="Data\\mandms\\back\\back (";
		sprintf(s1,"%d",i+1);
		s+=s1;
		s+=").tga";
		D3DXCreateTextureFromFile(g_pD3DDevice, s.data(), &buff);
		SpriteBack.push_back(buff);
	}
	for (int i=0; i<27;i++)
	{
		s="Data\\mandms\\front\\front (";
		sprintf(s1,"%d",i+1);
		s+=s1;
		s+=").tga";
		D3DXCreateTextureFromFile(g_pD3DDevice, s.data(), &buff);
		SpriteFront.push_back(buff);
	}
	for (int i=0; i<27;i++)
	{
		s="Data\\mandms\\left\\left (";
		sprintf(s1,"%d",i+1);
		s+=s1;
		s+=").tga";
		D3DXCreateTextureFromFile(g_pD3DDevice, s.data(), &buff);
		SpriteLeft.push_back(buff);
	}
	for (int i=0; i<27;i++)
	{
		s="Data\\mandms\\right\\right (";
		sprintf(s1,"%d",i+1);
		s+=s1;
		s+=").tga";
		D3DXCreateTextureFromFile(g_pD3DDevice, s.data(), &buff);
		SpriteRight.push_back(buff);
	}
}
void CharacterEntity::OnPaint()
{
	static int s=0;
	g_pD3DDevice->SetTexture(0, *(SpriteFront.begin()));
	if (GetAsyncKeyState(VK_LEFT)&32768) { TextureIterator=SpriteLeft.begin(); std::advance(TextureIterator,s); g_pD3DDevice->SetTexture(0, *(TextureIterator));  }
	if (GetAsyncKeyState(VK_RIGHT)&32768) {   TextureIterator=SpriteRight.begin(); std::advance(TextureIterator,s); g_pD3DDevice->SetTexture(0, *(TextureIterator));  }
	if (GetAsyncKeyState(VK_UP)&32768) {   TextureIterator=SpriteBack.begin(); std::advance(TextureIterator,s); g_pD3DDevice->SetTexture(0, *(TextureIterator));}
	if (GetAsyncKeyState(VK_DOWN)&32768) {    TextureIterator=SpriteFront.begin(); std::advance(TextureIterator,s); g_pD3DDevice->SetTexture(0, *(TextureIterator)); }
	s++; 
	if (s>26) s=0;
	m_v[0] = VertPosTc(D3DXVECTOR3(x, y+50, 0), D3DXVECTOR2(0,0));	
	m_v[1] = VertPosTc(D3DXVECTOR3(x+50, y+50, 0), D3DXVECTOR2(1,0));
	m_v[3] = VertPosTc(D3DXVECTOR3(x+50, y, 0), D3DXVECTOR2(1,1));
	m_v[2] = VertPosTc(D3DXVECTOR3(x, y, 0), D3DXVECTOR2(0,1));	

	g_pD3DDevice->SetRenderState(D3DRS_ALPHABLENDENABLE, true);
	g_pD3DDevice->SetRenderState(D3DRS_SRCBLEND,D3DBLEND_SRCALPHA);
	g_pD3DDevice->SetRenderState(D3DRS_DESTBLEND,D3DBLEND_INVSRCALPHA);

	g_pD3DDevice->SetStreamSource(0, g_pVB, 0, sizeof(VertPosTc));
	g_pD3DDevice->SetFVF(VERTEXFVF);
	g_pD3DDevice->DrawPrimitiveUP(D3DPT_TRIANGLESTRIP,2,m_v,sizeof(VertPosTc));
}
POINT CharacterEntity::GetPosition()
{
	POINT pos;
	pos.x=x;
	pos.y=y;
	return pos;
}
void CharacterEntity::SelectMap(WorldEntity _map)
{
	map=_map;
}