#include <windows.h>
#include "Render.h"

HWND hWnd;
Render *render=0;


LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam);
bool SetupDirect3D(HWND hWnd);
void LoadFile(HWND hWnd);

const int kViewPortWidth=800;
const int kViewPortHeight=600;

const float kCameraMovementSpeed=0.2f;
const float kCameraRotationSpeed=0.01f;


int APIENTRY WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow)
{
	WNDCLASSEX wcex;
	wcex.cbSize = sizeof(WNDCLASSEX); 
	wcex.style= 0;//CS_HREDRAW | CS_VREDRAW;
	wcex.lpfnWndProc= (WNDPROC)WndProc;
	wcex.cbClsExtra= 0;
	wcex.cbWndExtra= 0;
	wcex.hInstance= hInstance;
	wcex.hIcon= 0;
	wcex.hCursor= LoadCursor(NULL, IDC_ARROW);
	wcex.hbrBackground= (HBRUSH)(COLOR_WINDOW+1);
	wcex.lpszMenuName= 0;
	wcex.lpszClassName= "MainWindowClass";
	wcex.hIconSm= 0;
		
	RegisterClassEx(&wcex);

	RECT rc;
	SetRect( &rc, 0, 0, kViewPortWidth,kViewPortHeight );  
	AdjustWindowRectEx( &rc, WS_OVERLAPPEDWINDOW,false,0);  

	hWnd = CreateWindow("MainWindowClass", "X File Animation Demo, Keith Ditchburn 2008", WS_OVERLAPPEDWINDOW,
		CW_USEDEFAULT, CW_USEDEFAULT, rc.right-rc.left, rc.bottom-rc.top, NULL, NULL, hInstance, NULL);
	if (hWnd==0)
		return 0;

	render=new Render();
	if (!render->OnCreate(hWnd))
	{
		delete render;
		return 0;
	}

	D3DXCreateFont(m_d3dDevice,20,10,1,0,0,0,30,0,0,"Arial",&m_font);

	ShowWindow(hWnd, nCmdShow);
	UpdateWindow(hWnd);

	MSG msg;
	ZeroMemory( &msg, sizeof(msg) );
	while( msg.message!=WM_QUIT )
	{
	   if( PeekMessage( &msg, NULL, 0U, 0U, PM_REMOVE ) )
	   {
		  TranslateMessage( &msg );
		  DispatchMessage( &msg );
	   }
	   else
	   {
		   render->OnUpdate();
	   }  
	}

	delete render;
	return (int)msg.wParam;
}


LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	switch (message) 
	{
        case WM_DESTROY:
             PostQuitMessage(0);
        break;
        default:
            return DefWindowProc(hWnd, message, wParam, lParam);
	}
	return 0;
}