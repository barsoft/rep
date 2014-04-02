#pragma once
#include <fstream>
using namespace std;
INT_PTR CALLBACK UnivNameDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
class University
{
	char *name;
public:
	University()
	{
		name=NULL;
	}
	char * GetName();
	void SetName(char *_s);
	void ReadUniversityName(char *_path);
};
void University::ReadUniversityName(char *_path)
{
	fstream f;
	f.open(_path);
	char *s=new char[100];
	f.getline(s,256);
	if (!f.good()) MessageBox(0,"Error reading file!","ERROR",MB_ICONWARNING);
	name=s;
}
void University::SetName(char *_s)
{
	name=new char[100];
	name=_s;
}
char * University::GetName()
{
	return name?name:NULL;
}
University university;
struct Cafedra
{
	int id;
	char * zav_cafedroy;
	char * name;
};
INT_PTR CALLBACK UnivNameDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam)
{
	static HWND hEdit;
	switch(uMsg)
	{
	case WM_INITDIALOG:
		{
			RECT *rct=new RECT;
			RECT *rctDlg=new RECT;
			GetWindowRect(winMng.hMainWnd,rct);
			GetWindowRect(hwndDlg,rctDlg);
			int w=rctDlg->right-rctDlg->left;
			int h=rctDlg->bottom-rctDlg->top;
			MoveWindow(hwndDlg,rct->left+(rct->right-rct->left)/2-w/2,rct->top+(rct->bottom-rct->top)/2-h/2,w,h,1);
			hEdit=GetDlgItem(hwndDlg,IDC_EDITUNIVNAME);
			SetFocus(hEdit);
			char *s=university.GetName()?university.GetName():"";
			SendMessage(hEdit,WM_SETTEXT,100,LPARAM(s));
			break;
		}
	case WM_COMMAND:
		switch(wParam)
		{
		case IDC_OK:
			{
				char *s=new char[100];
				int length=SendMessage(hEdit,WM_GETTEXT,100,LPARAM(s));
				s[length]='\0';
				university.SetName(s);
				EndDialog(hwndDlg,1);

				return true;
			}
		case IDC_CANCEL:
			EndDialog(hwndDlg,0);
			return false;
		}
		break;
	case WM_CLOSE:
		EndDialog(hwndDlg,0);
		return false;
	}
	return 0;
}