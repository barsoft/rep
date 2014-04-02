#include "WindowsManager.h"
class ButtonsManager
{
public:
	HWND univNameButton;
	HWND facTablButton;
	HWND studTablButton;
	HWND depTablButton;
	HWND newButton;
	HWND openButton;
	HWND saveButton;
	void CreateUnivNameButton();
};
void ButtonsManager::CreateUnivNameButton()
{
	newButton=CreateWindow("BUTTON",0, BS_OWNERDRAW | WS_CHILD | WS_VISIBLE, 10, 10, 100,50 ,winMng.hMainWnd, (HMENU)IDB_NEW, hInstance, NULL);
	openButton=CreateWindow("BUTTON",0, BS_OWNERDRAW | WS_CHILD | WS_VISIBLE, 120, 10, 100,50 ,winMng.hMainWnd, (HMENU)IDB_OPEN, hInstance, NULL);
	saveButton=CreateWindow("BUTTON",0, BS_OWNERDRAW | WS_CHILD | WS_VISIBLE, 230, 10, 100,50 ,winMng.hMainWnd, (HMENU)IDB_SAVE, hInstance, NULL);
	univNameButton=CreateWindow("BUTTON",0, BS_OWNERDRAW | WS_CHILD | WS_VISIBLE, 340, 10, 160,50 ,winMng.hMainWnd, (HMENU)IDB_UNIVNAME, hInstance, NULL);
	facTablButton=CreateWindow("BUTTON",0, BS_OWNERDRAW | WS_CHILD | WS_VISIBLE, 510, 10, 160,50 ,winMng.hMainWnd, (HMENU)IDB_FACTABL, hInstance, NULL);
	depTablButton=CreateWindow("BUTTON",0, BS_OWNERDRAW | WS_CHILD | WS_VISIBLE,680 , 10, 160,50 ,winMng.hMainWnd, (HMENU)IDB_DEPTABL, hInstance, NULL);
	studTablButton=CreateWindow("BUTTON",0, BS_OWNERDRAW | WS_CHILD | WS_VISIBLE,850,10 , 160,50 ,winMng.hMainWnd, (HMENU)IDB_STUDTABL, hInstance, NULL);
}
