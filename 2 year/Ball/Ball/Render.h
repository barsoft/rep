#pragma once
#include "Static.h"
#include "Dynamic.h"

Dynamic	ball;
Static	ground;
D3DXMATRIX cameraMatrix;
D3DLIGHT9 Light;
D3DMATERIAL9 mat;
D3DXVECTOR3 VectorDir;
class Render
{
private:
	RECT rec;
	RECT rec2;
public:
	bool OnCreate(HWND hWnd);
	void OnUpdate();
	void LoadObject()
	{
		ball.InitObject("data\\ball.jpg","data\\ball.x",0,0,1500,0.1);
		ball.LoadObject();
		ball.OnCreate();
		ground.InitObject("data\\ground.jpg","data\\ground.x",0,0,0);
		ground.LoadObject();
		ground.OnCreate();
	}
};
const int kFontSize=14;

bool Render::OnCreate(HWND hWnd)
{
	m_d3dObject=Direct3DCreate9(D3D_SDK_VERSION);

	D3DPRESENT_PARAMETERS presParams;
	memset(&presParams,0,sizeof(presParams));
	
	if (windowed)
	{
		presParams.Windowed=true;
		presParams.SwapEffect=D3DSWAPEFFECT_DISCARD;
		presParams.BackBufferFormat=D3DFMT_UNKNOWN;
		presParams.BackBufferCount=1;
		presParams.PresentationInterval=D3DPRESENT_INTERVAL_IMMEDIATE;
		presParams.EnableAutoDepthStencil = TRUE;
		presParams.AutoDepthStencilFormat = D3DFMT_D16;
	}
	else
	{
		ZeroMemory(&presParams, sizeof(presParams));
		presParams.Windowed = FALSE;
		presParams.SwapEffect = D3DSWAPEFFECT_DISCARD;
		presParams.hDeviceWindow = hWnd;
		presParams.BackBufferFormat = D3DFMT_X8R8G8B8;
		presParams.BackBufferWidth = SCREEN_WIDTH;
		presParams.BackBufferHeight = SCREEN_HEIGHT;
		presParams.EnableAutoDepthStencil = TRUE;
		presParams.AutoDepthStencilFormat = D3DFMT_D16;
	}

	HRESULT hr=m_d3dObject->CreateDevice(D3DADAPTER_DEFAULT,D3DDEVTYPE_HAL,hWnd,D3DCREATE_HARDWARE_VERTEXPROCESSING, &presParams, &m_d3dDevice);

	m_d3dDevice->SetRenderState( D3DRS_ZENABLE, D3DZB_TRUE );
	m_d3dDevice->SetRenderState( D3DRS_LIGHTING,         TRUE );
	m_d3dDevice->SetRenderState( D3DRS_DITHERENABLE,     TRUE );
	m_d3dDevice->SetRenderState( D3DRS_SPECULARENABLE,	  FALSE );
	m_d3dDevice->SetRenderState( D3DRS_ZENABLE,          TRUE );
	m_d3dDevice->SetRenderState( D3DRS_CULLMODE,         D3DCULL_CCW );
	m_d3dDevice->SetRenderState( D3DRS_AMBIENT,          0 );
	m_d3dDevice->SetRenderState( D3DRS_NORMALIZENORMALS, TRUE );
	m_d3dDevice->SetRenderState(D3DRS_ALPHABLENDENABLE, TRUE);
	m_d3dDevice->SetRenderState(D3DRS_SRCBLEND,D3DBLEND_SRCALPHA);
	m_d3dDevice->SetRenderState(D3DRS_DESTBLEND,D3DBLEND_INVSRCALPHA);

	ZeroMemory( &mat, sizeof(mat) );
	mat.Diffuse.r = mat.Ambient.r = 1.0f;
	mat.Diffuse.g = mat.Ambient.g = 1.0f;
	mat.Diffuse.b = mat.Ambient.b = 1.0f;
	//mat.Diffuse.a = mat.Ambient.a = 1.0f;

	ZeroMemory(&Light, sizeof(D3DLIGHT9));

	Light.Type = D3DLIGHT_DIRECTIONAL;

	Light.Diffuse.r = 1.0f;
	Light.Diffuse.g = 1.0f;
	Light.Diffuse.b = 1.0f;

	Light.Range = 1000.0f;

	VectorDir = D3DXVECTOR3(0.0f, 40.0f, -10.0f);

	D3DXVec3Normalize((D3DXVECTOR3*)&Light.Direction, &VectorDir);

	m_d3dDevice->SetLight(0, &Light);

	m_d3dDevice->LightEnable(0, TRUE);

	D3DXMATRIX matProj;	
	D3DXMatrixPerspectiveFovLH( &matProj, D3DX_PI/4, 800.0f/600.0f, 1.0f, 20000.0f );
	m_d3dDevice->SetTransform( D3DTS_PROJECTION, &matProj );

	D3DXMatrixLookAtLH(&cameraMatrix,
		&D3DXVECTOR3 (0, -1000, 3000),	// LOOK FROM
		&D3DXVECTOR3 (0, 0, 0),	// LOOK TO
		&D3DXVECTOR3 (0.0f, 1.0f, 0.0f));
	m_d3dDevice->SetTransform(D3DTS_VIEW, &cameraMatrix);
	LoadObject();

	m_d3dDevice->SetRenderState( D3DRS_AMBIENT, D3DCOLOR_XRGB(80,80,80));
	
	D3DXCreateFont( m_d3dDevice, kFontSize, 0, FW_BOLD, 0, FALSE, DEFAULT_CHARSET, OUT_DEFAULT_PRECIS, DEFAULT_QUALITY, DEFAULT_PITCH | FF_DONTCARE, TEXT("Arial"), &m_font );

	return true;
}

void Render::OnUpdate()
{
	HRESULT hr=m_d3dDevice->Clear(0, NULL, D3DCLEAR_TARGET | D3DCLEAR_ZBUFFER, D3DCOLOR_XRGB(200,200,200), 1.0f, 0);

	if(SUCCEEDED(m_d3dDevice->BeginScene()))
	{
		m_d3dDevice->SetMaterial(&mat);
		ball.OnUpdate();
		
		ground.OnUpdate();
		m_d3dDevice->EndScene();
		m_d3dDevice->Present( NULL, NULL, NULL, NULL ); 
	}
}