#pragma once
LRESULT CALLBACK MainWndProc( __in  HWND hwnd,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);

HINSTANCE hInstance;

class WindowsManager
{
public:
	HWND hMainWnd;
	HMENU hMainMenu;
	char * str;
	WNDCLASSEX wndClassEx;
	int CreateMainWindow();
};
int WindowsManager::CreateMainWindow()
{
	str="University Database";
	wndClassEx.hIcon=LoadIcon(NULL,IDI_APPLICATION);
	wndClassEx.hIconSm= LoadIcon(NULL, IDI_APPLICATION);
	wndClassEx.hCursor=LoadCursor(NULL,IDC_ARROW);
	wndClassEx.cbSize=sizeof(WNDCLASSEX);
	wndClassEx.hbrBackground=(HBRUSH)GetStockObject(GRAY_BRUSH);
	wndClassEx.style=CS_HREDRAW |CS_VREDRAW;
	wndClassEx.lpfnWndProc=MainWndProc;
	wndClassEx.lpszMenuName  = NULL;
	wndClassEx.cbClsExtra= 0;
	wndClassEx.cbWndExtra= 0;
	wndClassEx.lpszClassName =str;
	wndClassEx.hInstance     = hInstance;
	if(!RegisterClassEx(&wndClassEx))
	{
		MessageBox(NULL, "Window Registration Failed!", "Error!",
			MB_ICONEXCLAMATION | MB_OK);
		return 0;
	}
	hMainMenu=(HMENU)LoadMenu(hInstance,MAKEINTRESOURCE(IDR_MAINMENU));
	RECT rc;
	GetWindowRect(GetDesktopWindow(), &rc);
	int w=1050;
	int h=820;
	if (!(hMainWnd=CreateWindowEx( WS_EX_CLIENTEDGE,str,"University Database",WS_OVERLAPPEDWINDOW,rc.right/2-w/2,rc.bottom/2-h/2,w,h,NULL,hMainMenu,hInstance,0)))
	{
		MessageBox(NULL, "Cannot create window!", "Error!",
			MB_ICONEXCLAMATION | MB_OK);
		return 0;
	}
	ShowWindow(hMainWnd,SW_SHOW);
	UpdateWindow(hMainWnd);
}

WindowsManager winMng;