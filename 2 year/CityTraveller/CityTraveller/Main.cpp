// Includes
#include "MenuEntity.h"
#include "PlayEntity.h"
MenuEntity Menu;
PlayEntity PlayGame;
HINSTANCE     g_hInst;
static char   g_szClass[]   = "Draw3DClass";
static char   g_szCaption[] = "Draw3D Demo by Jim Adams";

long FAR PASCAL WindowProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam);
int time=0;
BOOL DoInit();
BOOL DoShutdown();
BOOL DoFrame(MSG Msg);
void CreateFont()
{
	D3DXCreateFont( g_pD3DDevice, 30, 0, FW_NORMAL, 0, FALSE, DEFAULT_CHARSET, OUT_DEFAULT_PRECIS, DEFAULT_QUALITY, DEFAULT_PITCH | FF_DONTCARE, TEXT("Berlin Sans FB"), &m_font );
	D3DXCreateFont( g_pD3DDevice, 150, 0, FW_NORMAL, 0, FALSE, DEFAULT_CHARSET, OUT_DEFAULT_PRECIS, DEFAULT_QUALITY, DEFAULT_PITCH | FF_DONTCARE, TEXT("Raavi"), &m_font2 );
	D3DXCreateFont( g_pD3DDevice, 70, 0, FW_NORMAL, 0, FALSE, DEFAULT_CHARSET, OUT_DEFAULT_PRECIS, DEFAULT_QUALITY, DEFAULT_PITCH | FF_DONTCARE, TEXT("Raavi"), &m_font3 );
}
int PASCAL WinMain(HINSTANCE hInst, HINSTANCE hPrev, LPSTR szCmdLine, int nCmdShow)
{
  WNDCLASSEX wcex;
  MSG        Msg;

  g_hInst = hInst;

  // Create the window class here and register it
  wcex.cbSize        = sizeof(wcex);
  wcex.style         = CS_CLASSDC;
  wcex.lpfnWndProc   = WindowProc;
  wcex.cbClsExtra    = 0;
  wcex.cbWndExtra    = 0;
  wcex.hInstance     = hInst;
  wcex.hIcon         = LoadIcon(NULL, IDI_APPLICATION);
  wcex.hCursor       = LoadCursor(NULL, IDC_ARROW);
  wcex.hbrBackground = NULL;
  wcex.lpszMenuName  = NULL;
  wcex.lpszClassName = g_szClass;
  wcex.hIconSm       = LoadIcon(NULL, IDI_APPLICATION);
  if(!RegisterClassEx(&wcex))
    return FALSE;

  // Create the Main Window
  g_hWnd = CreateWindow(g_szClass, g_szCaption,
        WS_CAPTION | WS_SYSMENU,
        0, 0, 1280, 800,
        NULL, NULL,
        hInst, NULL );
  if(!g_hWnd)
    return FALSE;
  ShowWindow(g_hWnd, SW_NORMAL);
  UpdateWindow(g_hWnd);

  // Run init function and return on error
  if(DoInit() == FALSE)
    return FALSE;

  // Start message pump, waiting for signal to quit
  ZeroMemory(&Msg, sizeof(MSG));
  while(Msg.message != WM_QUIT) 
  {
	if (PeekMessage(&Msg, NULL, 0, 0, PM_REMOVE)) 
	{
		TranslateMessage(&Msg);
		DispatchMessage(&Msg);
	}
	
	if (DoFrame(Msg) == FALSE) break;
  }

  // Run shutdown function
  DoShutdown();
  
  UnregisterClass(g_szClass, hInst);
  return Msg.wParam;
}

long FAR PASCAL WindowProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam)
{
	switch(uMsg) 
	{
	case WM_DESTROY:
		PostQuitMessage(0);
		return 0;
	}

  return DefWindowProc(hWnd, uMsg, wParam, lParam);
}

BOOL DoInit()
{
	D3DPRESENT_PARAMETERS d3dpp;
	D3DDISPLAYMODE        d3ddm;
	D3DXMATRIX matProj, matView;
	RECT rect;
	GetClientRect(g_hWnd,&rect);
	if((g_pD3D = Direct3DCreate9(D3D_SDK_VERSION)) == NULL)
	return FALSE;
	if(FAILED(g_pD3D->GetAdapterDisplayMode(D3DADAPTER_DEFAULT, &d3ddm)))
	return FALSE;
	ZeroMemory(&d3dpp, sizeof(d3dpp));
	d3dpp.Windowed = TRUE;
	d3dpp.SwapEffect = D3DSWAPEFFECT_DISCARD;
	d3dpp.BackBufferFormat = d3ddm.Format;
	d3dpp.EnableAutoDepthStencil = TRUE;
	d3dpp.AutoDepthStencilFormat = D3DFMT_D16;

	if(FAILED(g_pD3D->CreateDevice(D3DADAPTER_DEFAULT, D3DDEVTYPE_HAL, g_hWnd,D3DCREATE_SOFTWARE_VERTEXPROCESSING,&d3dpp, &g_pD3DDevice)))
	return FALSE;
	
	g_pD3DDevice->Clear(0, NULL, D3DCLEAR_TARGET | D3DCLEAR_ZBUFFER, D3DCOLOR_RGBA(0,0,0,0), 1.0f, 0);
	g_pD3DDevice->SetRenderState(D3DRS_LIGHTING, FALSE);
	g_pD3DDevice->SetRenderState(D3DRS_ZENABLE, TRUE);

	D3DXMatrixPerspectiveFovLH(&matProj, D3DX_PI/4.0f, 1.33333f, 1.0f, 1000.0f);
	g_pD3DDevice->SetTransform(D3DTS_PROJECTION, &matProj);

	D3DXMatrixLookAtLH(&matView, &D3DXVECTOR3(0.0f, 0.0f, -500.0f), &D3DXVECTOR3(0.0f, 0.0f, 0.0f), &D3DXVECTOR3(0.0f, 1.0f, 0.0f));
	g_pD3DDevice->SetTransform(D3DTS_VIEW, &matView);
	
	Menu.LoadTextures();
	PlayGame.LoadTextures();
	_beginthread(SlideshowThread,0,NULL);
	
	CreateFont();
	return TRUE;
}

BOOL DoShutdown()
{
	Menu.ReleaseTextures();
	PlayGame.ReleaseTextures();
	if(g_pD3DDevice != NULL)
	g_pD3DDevice->Release();
	if(g_pD3D != NULL)
	g_pD3D->Release();
	return TRUE;
}

BOOL DoFrame(MSG Msg)
{
	D3DXMATRIX matWorld;

	g_pD3DDevice->Clear(0, NULL, D3DCLEAR_TARGET | D3DCLEAR_ZBUFFER, D3DCOLOR_RGBA(0,0,0,0), 1.0f, 0);

	if(SUCCEEDED(g_pD3DDevice->BeginScene())) 
	{
		if (g_hWnd==GetForegroundWindow())
			if (gs==IsPlayGame)
			{
				static bool s=true;
				if (s)_beginthread(TimerThread,0,0);
				s=false;
				PlayGame.OnPaint();
				g_pD3DDevice->SetTexture(0, NULL);
			}
			else 
			{
				Menu.OnPaint();
			}
	}
	g_pD3DDevice->EndScene();
	g_pD3DDevice->Present(NULL, NULL, NULL, NULL);

  return TRUE;
}

void SlideshowThread(void *dummy)
{
	while(gs==IsMenuGame)
	{
		Sleep(1000);
		time++;
		if (time%10==0) 
		{
			Menu.NextSlide();
		}
	}
	_endthread();
}

