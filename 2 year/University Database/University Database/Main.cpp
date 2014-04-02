#include <Windows.h>
#include "resource.h"
#include "WindowsManager.h"
#include "University.h"
#include "GdiInit.h"
#include "ButtonsManager.h"
#include <stdio.h>
#include "Faculties.h"
#include "FacultyTable.h"
#include "StudentTable.h"
#include "DepTable.h"
#include "LinkProcs.h"
ButtonsManager buttonsManager;
CGdiPlusInit gdi;
bool buttonNewState=true;
bool buttonSaveState=false;
bool buttonOpenState=true;
bool tableButtonsState=false;

int WINAPI WinMain(HINSTANCE hInst,__in  HINSTANCE hPrevInstance,__in  LPSTR lpCmdLine,__in  int nCmdShow)
{
	MSG msg;
	HACCEL haccel;
	if (!winMng.CreateMainWindow())
		PostQuitMessage(0);
	buttonsManager.CreateUnivNameButton();
	hInstance=hInst;
	BOOL bRet;
	haccel=LoadAccelerators(hInst,MAKEINTRESOURCE(IDR_ACCELERATOR1));
	while ((bRet = GetMessage(&msg, NULL, 0, 0)) != 0) 
	{ 
		if (bRet == -1)
		{
			// Handle the error and possibly exit
		}

		else 
			if (!IsWindow(facTable.hTablDialog) || !IsDialogMessage(facTable.hTablDialog, &msg) || 
			!(IsWindow(studTable.hTablDialog)|| !IsDialogMessage(studTable.hTablDialog, &msg)) ||
			!(IsWindow(depTable.hTablDialog)|| !IsDialogMessage(depTable.hTablDialog, &msg))) 
		{ 
			TranslateAccelerator(winMng.hMainWnd, haccel, &msg);
			TranslateMessage(&msg); 
			DispatchMessage(&msg); 
		} 
	} 
	return msg.wParam;
}

LRESULT CALLBACK MainWndProc( __in  HWND hwnd,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam)
{
	static PAINTSTRUCT ps;
	static Bitmap* menuBackground;
	static Bitmap* univNameButtonPic;
	static Bitmap* facTablButtonPic;
	static Bitmap* depTablButtonPic;
	static Bitmap* studTablButtonPic;
	static Bitmap* selectedPic;
	static Bitmap* selectedSmallPic;
	static Bitmap* noActvePic;
	static Bitmap* noActveSmallPic;
	static Bitmap* newButtonPic;
	static Bitmap* openButtonPic;
	static Bitmap* saveButtonPic;
	static LPDRAWITEMSTRUCT pdis;
	RECT rect;
	HDC hDC;
	switch(uMsg)
	{
	case WM_CREATE:
		{
			if (!gdi.Good())
			{
				MessageBox(NULL, "GDI Init error!", "Error!",
					MB_ICONEXCLAMATION | MB_OK);
				return 0;
			}
			menuBackground=Bitmap::FromFile(L"Menu.png",0);
			univNameButtonPic=Bitmap::FromFile(L"univName.png",0);
			facTablButtonPic=Bitmap::FromFile(L"facTabl.png",0);
			depTablButtonPic=Bitmap::FromFile(L"depTabl.png",0);
			studTablButtonPic=Bitmap::FromFile(L"studTabl.png",0);
			selectedPic=Bitmap::FromFile(L"selected.png",0);
			selectedSmallPic=Bitmap::FromFile(L"selectedsmall.png",0);
			noActveSmallPic=Bitmap::FromFile(L"noactivesmall.png",0);
			noActvePic=Bitmap::FromFile(L"noactive.png",0);
			newButtonPic=Bitmap::FromFile(L"new.png",0);
			openButtonPic=Bitmap::FromFile(L"open.png",0);
			saveButtonPic=Bitmap::FromFile(L"save.png",0);
			break;
		}
	case WM_DRAWITEM:
		{
			pdis = (LPDRAWITEMSTRUCT) lParam;
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			// CHECK BUTTON CONDITION////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////	
			/////////////////////////////////////////////////////////////////////////////////////////////////////	
			bool onFocus=false;
			switch (pdis->itemAction)
			{
				case ODA_SELECT:
					if (pdis->itemState & ODS_SELECTED) onFocus=true;
					break;
				default:
					onFocus=false;
					break;
			}
			Graphics g(pdis->hDC);
			if (pdis->hwndItem==buttonsManager.univNameButton)
			{
				g.DrawImage(univNameButtonPic,0,0,160,50);
			}
			else if (pdis->hwndItem==buttonsManager.facTablButton)
			{
				g.DrawImage(facTablButtonPic,0,0,160,50);
			}
			else if (pdis->hwndItem==buttonsManager.depTablButton)
			{
				g.DrawImage(depTablButtonPic,0,0,160,50);
			}
			else if (pdis->hwndItem==buttonsManager.studTablButton)
			{
				g.DrawImage(studTablButtonPic,0,0,160,50);
			}
			else if (pdis->hwndItem==buttonsManager.newButton)
			{
				g.DrawImage(newButtonPic,0,0,100,50);
				if (onFocus) g.DrawImage(selectedSmallPic,0,0,100,50);
				if (!buttonNewState) g.DrawImage(noActveSmallPic,0,0,100,50);
			}
			else if (pdis->hwndItem==buttonsManager.openButton)
			{
				g.DrawImage(openButtonPic,0,0,100,50);
				if (onFocus) g.DrawImage(selectedSmallPic,0,0,100,50);
				if (!buttonOpenState) g.DrawImage(noActveSmallPic,0,0,100,50);
			}
			else if (pdis->hwndItem==buttonsManager.saveButton)
			{
				g.DrawImage(saveButtonPic,0,0,100,50);
				if (onFocus) g.DrawImage(selectedSmallPic,0,0,100,50);
				if (!buttonSaveState) g.DrawImage(noActveSmallPic,0,0,100,50);
			}
			if (pdis->hwndItem==buttonsManager.univNameButton ||pdis->hwndItem==buttonsManager.facTablButton  ||
				pdis->hwndItem==buttonsManager.depTablButton ||pdis->hwndItem==buttonsManager.studTablButton) 
			{
				if (onFocus) g.DrawImage(selectedPic,0,0,160,50);
				if (!tableButtonsState) g.DrawImage(noActvePic,0,0,160,50);
			}
			break;
		}
	case WM_COMMAND:
		{
			switch(LOWORD(wParam))
			{
			case IDM_FILE_EXIT:
				{
					exit(0);
				}
			case IDB_UNIVNAME:
				{
					if (tableButtonsState)
					DialogBox(NULL,MAKEINTRESOURCE(IDD_UNIVNAME),winMng.hMainWnd,UnivNameDlgProc);
					break;
				}
			case IDB_FACTABL:
				{
					if (tableButtonsState)
					facTable.CreateDlg();
					break;
				}
			case IDB_DEPTABL:
				{
					if (tableButtonsState)
					depTable.CreateDlg();
					break;
				}
			case IDB_STUDTABL:
				{
					if (tableButtonsState)
					studTable.CreateDlg();
					break;
				}
			case IDB_NEW:
			case IDM_FILE_NEW:
				{
					int c=DialogBox(NULL,MAKEINTRESOURCE(IDD_UNIVNAME),winMng.hMainWnd,UnivNameDlgProc);
					if (c==1)
					{
						tableButtonsState=true;
						InvalidateRect(winMng.hMainWnd,0,1);
						facTable.CreateDlg();
						studTable.CreateDlg();
						depTable.CreateDlg();
					}
					break;
				}
			case IDB_OPEN:
			case  IDM_FILE_OPEN:
				{
					HANDLE hf;
					OPENFILENAME ofn ;
					char szFile[100] ;
					ZeroMemory( &ofn , sizeof( ofn));
					ofn.lStructSize = sizeof ( ofn );
					ofn.hwndOwner = NULL  ;
					ofn.lpstrFile = szFile ;
					ofn.lpstrFile[0] = '\0';
					ofn.nMaxFile = sizeof( szFile );
					ofn.lpstrFilter = "University database (*.udt)\0*.UDT\0";
					ofn.nFilterIndex =1;
					ofn.lpstrFileTitle = NULL ;
					ofn.nMaxFileTitle = 0 ;
					ofn.lpstrInitialDir=NULL ;
					ofn.Flags = OFN_PATHMUSTEXIST|OFN_FILEMUSTEXIST ;

					// Display the Open dialog box. 
					if(GetOpenFileName(&ofn))
					{
						facTable.CreateDlg();
						studTable.CreateDlg();
						depTable.CreateDlg();
						university.ReadUniversityName(ofn.lpstrFile);
						facTable.ReadFromFile(ofn.lpstrFile);
						studTable.ReadFromFile(ofn.lpstrFile);
						depTable.ReadFromFile(ofn.lpstrFile);
						buttonNewState=true;
						buttonSaveState=true;
						buttonOpenState=true;
						tableButtonsState=true;
						InvalidateRect(hwnd,0,1);
					}
					break;
				}
			case IDB_SAVE:
			case IDM_FILE_SAVE:
				{
					HANDLE hf;
					OPENFILENAME ofn ;
					char szFile[100] ;
					ZeroMemory( &ofn , sizeof( ofn));
					ofn.lStructSize = sizeof ( ofn );
					ofn.hwndOwner = NULL  ;
					ofn.lpstrFile = szFile ;
					ofn.lpstrFile[0] = '\0';
					ofn.nMaxFile = sizeof( szFile );
					ofn.lpstrFilter = "University database (*.udt)\0*.UDT\0";
					ofn.nFilterIndex =1;
					ofn.lpstrFileTitle = NULL ;
					ofn.nMaxFileTitle = 0 ;
					ofn.lpstrInitialDir=NULL ;
					ofn.Flags = OFN_PATHMUSTEXIST|OFN_FILEMUSTEXIST ;
					if(GetSaveFileName(&ofn))
					{
						char *path=new char[100];
						sprintf(path,"%s%s",ofn.lpstrFile,".udt");
						MessageBox(0,path,0,0);
						ofstream file(path, ios::out);
						/*file.write(university.GetName(),strlen(university.GetName()));
						file.write("",12);
						file.write("",2);
						file.write("",2);*/
						file<<university.GetName();
						
						file<<"\nFACULTIES:\n"<<"{\n";
						for (facTable.faculties.vecIter=facTable.faculties.vec.begin();facTable.faculties.vecIter!=facTable.faculties.vec.end();facTable.faculties.vecIter++)
						{
							file<<facTable.faculties.vecIter->name<<","<<facTable.faculties.vecIter->decan_fio<<";\n";
						}
						file<<"}\n";
						file<<"DEPARTMENTS:\n"<<"{\n";
						for (depTable.departments.vecIter=depTable.departments.vec.begin();depTable.departments.vecIter!=depTable.departments.vec.end();depTable.departments.vecIter++)
						{
							file<<depTable.departments.vecIter->name<<","<<depTable.departments.vecIter->zav_kafediri_fio<<";\n";
						}
						file<<"}\n";
						file<<"STUDENTS:\n"<<"{\n";
						for (studTable.students.vecIter=studTable.students.vec.begin();studTable.students.vecIter!=studTable.students.vec.end();studTable.students.vecIter++)
						{
							file<<studTable.students.vecIter->fio<<","<<studTable.students.vecIter->speciality<<","<<studTable.students.vecIter->average_grade<<",";
							file<<studTable.students.vecIter->course<<","<<studTable.students.vecIter->photo<<";\n";
						}
						file<<"}\n";
						file.close();
					}
					break;
				}
			}
	
			return 0;
		}
	case WM_PAINT:
		{
			hDC=BeginPaint(hwnd,&ps);
			Graphics g(hDC);
			RECT *rct=new RECT;
			GetWindowRect(winMng.hMainWnd,rct);
			g.DrawImage(menuBackground,0,0,rct->right-rct->left,100);
			EndPaint(hwnd,&ps);
			return 0;
		}
	case WM_CLOSE:
		exit(0);
	default:
		return DefWindowProc(hwnd, uMsg, wParam, lParam);
	}
	return 0;
}



