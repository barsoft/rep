#pragma once
#include "WindowsManager.h"
#include "Departments.h"
#include "Table.h"
INT_PTR CALLBACK DepTablDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
INT CALLBACK  SortingDepProc(LPARAM lParam1, LPARAM lParam2, LPARAM lParamSort);
// faculties;
class DepTable:public Table
{
private:
	void CreateTable();
public:
	int currentString;
	Departments departments;
	void CreateDlg();
	void AddString(int _id,const char * _name,const char * _zav_kafediri_fio);
	void UpdateTable();
	void DeleteString(int _stringnumber);
	void SortTable(int _columnkeynumber);
	void ReadFromFile(LPCSTR _path);
	void EditString(int _num,const char * _name,const char * _zav_kafediri_fio);
};
void DepTable::SortTable(int _columnkeynumber)
{
	ListView_SortItems(hTable,SortingDepProc,LPARAM(_columnkeynumber));
}

void DepTable::CreateDlg()
{
	show=!show;
	if (show)
	{
		if (!hTablDialog)
		{
			hTablDialog=CreateDialog(hInstance,MAKEINTRESOURCE(IDD_DEPTABL),winMng.hMainWnd,(DLGPROC)DepTablDlgProc);
			if (!hTablDialog) MessageBox(0,0,0,0);
			ShowWindow(hTablDialog,SW_SHOW);
			UpdateWindow(hTablDialog);
			CreateTable();
			DWORD l_NewStyle = LVS_EX_FULLROWSELECT | LVS_EX_ONECLICKACTIVATE | ::SendMessage(hTable, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0);
			::SendMessage(hTable, LVM_SETEXTENDEDLISTVIEWSTYLE, 0, (LPARAM)l_NewStyle);
		}
		else
		{
			ShowWindow(hTablDialog,SW_SHOW);
			UpdateWindow(hTablDialog);
		}
	}
	else 
	{
		ShowWindow(hTablDialog,SW_HIDE);
	}
}

void DepTable::CreateTable()
{
	LVCOLUMN column;
	RECT rc;
	GetWindowRect(hTablDialog,&rc);
	hTable=GetDlgItem(hTablDialog,IDC_LISTDEP);
	ListView_SetExtendedListViewStyle(hTable,LVS_EX_FULLROWSELECT);
	memset(&column, 0, sizeof(column)); 
	column.mask = LVCF_WIDTH | LVCF_TEXT  | LVCF_SUBITEM; 
	column.fmt = LVCFMT_LEFT; 
	column.iSubItem = 0; 
	column.pszText = "ID"; 
	column.cx=100;
	ListView_InsertColumn(hTable, 0, &column);
	column.cx=100;
	column.iSubItem = 1; 
	column.pszText = "Name"; 
	ListView_InsertColumn(hTable, 1, &column);
	column.cx=rc.right-rc.left-242;
	column.iSubItem = 2; 
	column.pszText = "Head full name"; 
	ListView_InsertColumn(hTable, 2, &column);
}
void DepTable::UpdateTable()
{
	ListView_DeleteAllItems(hTable);
	for (departments.vecIter=departments.vec.begin();departments.vecIter!=departments.vec.end();departments.vecIter++)
	{
		AddString(departments.vecIter->id,departments.vecIter->name,departments.vecIter->zav_kafediri_fio);
	}
}
void DepTable::AddString(int _id,const char * _name,const char * _zav_kafediri_fio)
{
	LVITEM Item;
	memset(&Item, 0, sizeof(Item));
	Item.mask =  LVIF_IMAGE | LVIF_TEXT | LVIF_PARAM ;
	char szText[255];
	sprintf(szText,"%d",_id);
	Item.pszText=szText;
	Item.lParam=SendMessage(hTable, LVM_GETITEMCOUNT, 0, 0);;
	Item.cchTextMax=255;
	Item.iItem=SendMessage(hTable, LVM_GETITEMCOUNT, 0, 0);
	Item.iSubItem=0;
	ListView_InsertItem(hTable,&Item);
	Item.mask = LVIF_TEXT;
	Item.iSubItem=1;       
	Item.pszText=(LPSTR)_name;
	ListView_SetItem(hTable,&Item);
	Item.mask = LVIF_TEXT;
	Item.iSubItem=2;       
	Item.pszText=(LPSTR)_zav_kafediri_fio;
	ListView_SetItem(hTable,&Item);
}
void DepTable::EditString(int _num,const char * _name,const char * _zav_kafediri_fio)
{
	LVITEM Item;
	memset(&Item, 0, sizeof(Item));
	Item.mask =  LVIF_IMAGE | LVIF_TEXT | LVIF_PARAM ;
	char szText[255];
	Item.lParam=_num;
	Item.cchTextMax=255;
	Item.iItem=_num;
	Item.mask = LVIF_TEXT;
	Item.iSubItem=1;       
	Item.pszText=(LPSTR)_name;
	ListView_SetItem(hTable,&Item);
	Item.mask = LVIF_TEXT;
	Item.iSubItem=2;       
	Item.pszText=(LPSTR)_zav_kafediri_fio;
	ListView_SetItem(hTable,&Item);
}
void DepTable::DeleteString(int _stringnumber)
{
	char * s=new char;
	departments.DeleteItem(_stringnumber);
	UpdateTable();
}
void DepTable::ReadFromFile(LPCSTR _path)
{
	fstream f;
	f.open(_path);
	char *s=new char[100];
	while (strcmp(s,"DEPARTMENTS:") )
	{
		f.getline(s,256);
		if (!f.good()) MessageBox(0,"Error reading file!","ERROR",MB_ICONWARNING);
	}
	s=new char[100];
	while (strcmp(s,"{") )
	{
		f.getline(s,256);
		if (!f.good()) MessageBox(0,"Error reading file!","ERROR",MB_ICONWARNING);
	}
	f.getline(s,256);
	while (strcmp(s,"}") )
	{		
		stringstream name;
		stringstream zav_kafedra_fio;
		int i=0;
		while (s[i]!=',')
		{
			name<<s[i];
			i++;
		}
		i++;
		while (s[i]!=';')
		{
			zav_kafedra_fio<<s[i];
			i++;
		}
		char *sName=new char[100];
		sprintf(sName,"%s",name.str().data());
		char *sZav_kafedra_fio=new char[100];
		sprintf(sZav_kafedra_fio,"%s",zav_kafedra_fio.str().data());
		departments.AddItem(sName,sZav_kafedra_fio);
		AddString(departments.vec.back().id,sName,sZav_kafedra_fio);
		f.getline(s,256);
		if (!f.good()) MessageBox(0,"Error reading file!","ERROR",MB_ICONWARNING);
	}
}
DepTable depTable;

INT_PTR CALLBACK DepTablDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam)
{
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
			MoveWindow(hwndDlg,5+w/2+5,95+h+5,w,h,1);
			break;
		}
	case WM_NOTIFY:
		{
			NM_LISTVIEW *lpNm = (NM_LISTVIEW *)lParam;
			if ( ((LPNMHDR)lParam)->code == LVN_COLUMNCLICK ) 
			{ 
				depTable.SortTable(lpNm->iSubItem);
				break;
			} 
			if ( ((LPNMHDR)lParam)->code == NM_DBLCLK ) 
			{
				depTable.currentString=lpNm->iItem;
				if (lpNm->iItem>=0)DialogBox(hInstance,MAKEINTRESOURCE(IDD_ADDDEPARTMENT),winMng.hMainWnd,AddEditDepDlgProc);
				break;
			}
			return 0; 
		}
		break;
	case WM_COMMAND:
		switch(LOWORD(wParam))
		{
		case IDC_DEPTABL_ADD:
			{
				depTable.currentString=-1;
				DialogBox(hInstance,MAKEINTRESOURCE(IDD_ADDDEPARTMENT),winMng.hMainWnd,AddEditDepDlgProc);
				break;
			}
		case IDC_DEPTABL_DELETE:
			int sel=ListView_GetNextItem(depTable.hTable,-1, LVNI_FOCUSED | LVNI_SELECTED);
			depTable.DeleteString(sel);
			break;
		}
		break;
	case WM_CLOSE:
		EndDialog(hwndDlg,0);
		break;
	}
	return 0;
}
INT_PTR CALLBACK AddEditDepDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam)
{
	static HWND hEditName;
	static HWND hEditZavKafedraFIO;
	switch(uMsg)
	{
	case WM_INITDIALOG:
		{
			if (facTable.currentString!=-1)
			{
				SetWindowText(hwndDlg,"Edit department");
			}
			else 
			{
				SetWindowText(hwndDlg,"Add department");
			}
			RECT *rct=new RECT;
			RECT *rctDlg=new RECT;
			GetWindowRect(winMng.hMainWnd,rct);
			GetWindowRect(hwndDlg,rctDlg);
			int w=rctDlg->right-rctDlg->left;
			int h=rctDlg->bottom-rctDlg->top;
			MoveWindow(hwndDlg,rct->left+(rct->right-rct->left)/2-w/2,rct->top+(rct->bottom-rct->top)/2-h/2,w,h,1);
			hEditName=GetDlgItem(hwndDlg,IDC_ADDDEP_NAMEEDIT);
			hEditZavKafedraFIO=GetDlgItem(hwndDlg,IDC_ADDDEP_HEADEDIT);
			int i=0;
			if (!(depTable.currentString==-1))
			{
				for (depTable.departments.vecIter=depTable.departments.vec.begin();depTable.departments.vecIter!=depTable.departments.vec.end()&& i<depTable.currentString;depTable.departments.vecIter++,i++){}
				SendMessage(hEditName,WM_SETTEXT,100,LPARAM(depTable.departments.vecIter->name));
				SendMessage(hEditZavKafedraFIO,WM_SETTEXT,100,LPARAM(depTable.departments.vecIter->zav_kafediri_fio));
			}
			SetFocus(hEditName);
			break;
		}
	case WM_COMMAND:
		switch(LOWORD(wParam))
		{
		case IDOK:
			{
				char *name=new char[50];
				char *zav_kafedri_fio=new char[50];
				if(!hEditName) MessageBox(0,0,0,0); 
				SendMessage(hEditName,WM_GETTEXT,50,(LPARAM)name);
				SendMessage(hEditZavKafedraFIO,WM_GETTEXT,50,(LPARAM)zav_kafedri_fio);
				if (!(depTable.currentString==-1))
				{
					depTable.EditString(depTable.currentString,name,zav_kafedri_fio);
					depTable.departments.EditItem(depTable.currentString,name,zav_kafedri_fio);
				}
				else
				{
					depTable.departments.AddItem(name,zav_kafedri_fio);
					depTable.AddString(depTable.departments.vec.back().id,name,zav_kafedri_fio);
				}
				EndDialog(hwndDlg,0);
				break;
			}
		case IDCANCEL:
			EndDialog(hwndDlg,0);
			break;
		}
		break;
	case WM_CLOSE:
		EndDialog(hwndDlg,0);
		break;
	}
	return 0;
}
INT CALLBACK  SortingDepProc(LPARAM lParam1, LPARAM lParam2, LPARAM lParamSort)
{
	CHAR Buff1[1024]={0};
	CHAR Buff2[1024]={0};
	LPSTR lpStr1, lpStr2;
	INT iNum1 = 0, iNum2 = 0;
	INT iResult, iIndex = 0;

	LVFINDINFO ItemInfo;

	ItemInfo.flags = LVFI_PARAM;

	ItemInfo.lParam = lParam1;
	iIndex = ListView_FindItem(facTable.hTable, -1, &ItemInfo);
	ListView_GetItemText(facTable.hTable, iIndex, (int)lParamSort, Buff1, sizeof(Buff1));
	lpStr1 = Buff1;

	ItemInfo.lParam = lParam2;
	iIndex = ListView_FindItem(facTable.hTable, -1, &ItemInfo);
	ListView_GetItemText(facTable.hTable, iIndex, (int)lParamSort, Buff2, sizeof(Buff2));
	lpStr2 = Buff2;
	if(lpStr1 && lpStr2)
	{
		switch(lParamSort)
		{
		case 0:
			iResult = strcmp(lpStr1, lpStr2);
			break;
		case 1:
			iResult = strcmp(lpStr1, lpStr2);
			break;
		case 2:
			iResult = strcmp(lpStr1, lpStr2);
			break;
		default:
			iResult = 0;
			break;
		}
	}
	return(iResult);
}