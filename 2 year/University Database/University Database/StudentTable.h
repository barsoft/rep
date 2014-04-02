#include "WindowsManager.h"
#include "Students.h"
#include "Table.h"
INT_PTR CALLBACK StudTablDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
INT CALLBACK  SortingStudentsProc(LPARAM lParam1, LPARAM lParam2, LPARAM lParamSort);
class StudentTable:public Table
{
private:
	void CreateTable();
public:
	int currentString;
	Students students;	
	void CreateDlg();
	void AddString(int _id,const char * _fio,const char * _speciality,int _averageGrade,int _course,const char *_photo);
	void EditString(int _num,const char * _fio,const char * _speciality,int _averageGrade,int _course,const char *_photo);
	void UpdateTable();
	void DeleteString(int _stringnumber);
	void SortTable(int _columnkeynumber);
	void ReadFromFile(LPCSTR _path);
};
void StudentTable::SortTable(int _columnkeynumber)
{
	ListView_SortItems(hTable,SortingStudentsProc,LPARAM(_columnkeynumber));
}

void StudentTable::CreateDlg()
{
	show=!show;
	if (show)
	{
		if (!hTablDialog)
		{
			hTablDialog=CreateDialog(hInstance,MAKEINTRESOURCE(IDD_STUDTABL),winMng.hMainWnd,(DLGPROC)StudTablDlgProc);
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

void StudentTable::CreateTable()
{
	LVCOLUMN column;
	RECT rc;
	GetWindowRect(hTablDialog,&rc);
	hTable=GetDlgItem(hTablDialog,IDC_LISTSTUD);
	ListView_SetExtendedListViewStyle(hTable,LVS_EX_FULLROWSELECT);
	memset(&column, 0, sizeof(column)); 
	column.mask = LVCF_WIDTH | LVCF_TEXT  | LVCF_SUBITEM; 
	column.fmt = LVCFMT_LEFT; 
	column.iSubItem = 0; 
	column.pszText = "ID"; 
	column.cx=25;
	ListView_InsertColumn(hTable, 0, &column);
	column.cx=150;
	column.iSubItem = 1; 
	column.pszText = "Full name"; 
	ListView_InsertColumn(hTable, 1, &column);
	column.cx=100;
	column.iSubItem = 2; 
	column.pszText = "Speciality"; 
	ListView_InsertColumn(hTable, 2, &column);
	column.cx=100;
	column.iSubItem = 3; 
	column.pszText = "Average grade"; 
	ListView_InsertColumn(hTable, 3, &column);
	column.cx=50;
	column.iSubItem = 4; 
	column.pszText = "Course"; 
	ListView_InsertColumn(hTable, 4, &column);
}
void StudentTable::UpdateTable()
{
	ListView_DeleteAllItems(hTable);
	for (students.vecIter=students.vec.begin();students.vecIter!=students.vec.end();students.vecIter++)
	{
		AddString(students.vecIter->id,students.vecIter->fio,students.vecIter->speciality,students.vecIter->average_grade,students.vecIter->course,students.vecIter->photo);
	}
}
void StudentTable::AddString(int _id,const char * _fio,const char * _speciality,int _averageGrade,int _course,const char *_photo)
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
	Item.pszText=(LPSTR)_fio;
	ListView_SetItem(hTable,&Item);
	Item.mask = LVIF_TEXT;
	Item.iSubItem=2;       
	Item.pszText=(LPSTR)_speciality;
	ListView_SetItem(hTable,&Item);
	Item.mask = LVIF_TEXT;
	Item.iSubItem=3;       
	char *s=new char[100];
	sprintf(s,"%d",_averageGrade);
	Item.pszText=(LPSTR)s;
	ListView_SetItem(hTable,&Item);
	Item.mask = LVIF_TEXT;
	Item.iSubItem=4;       
	sprintf(s,"%d",_course);
	Item.pszText=(LPSTR)s;
	ListView_SetItem(hTable,&Item);
}
void StudentTable::EditString(int _num,const char * _fio,const char * _speciality,int _averageGrade,int _course,const char *_photo)
{
	LVITEM Item;
	memset(&Item, 0, sizeof(Item));
	Item.mask =  LVIF_IMAGE | LVIF_TEXT | LVIF_PARAM ;
	char szText[255];
	Item.lParam=currentString;
	Item.cchTextMax=255;
	Item.iItem=currentString;
	Item.mask = LVIF_TEXT;
	Item.iSubItem=1;       
	Item.pszText=(LPSTR)_fio;
	ListView_SetItem(hTable,&Item);
	Item.mask = LVIF_TEXT;
	Item.iSubItem=2;       
	Item.pszText=(LPSTR)_speciality;
	ListView_SetItem(hTable,&Item);
	Item.mask = LVIF_TEXT;
	Item.iSubItem=3;       
	char *s=new char[100];
	sprintf(s,"%d",_averageGrade);
	Item.pszText=(LPSTR)s;
	ListView_SetItem(hTable,&Item);
	Item.mask = LVIF_TEXT;
	Item.iSubItem=4;       
	sprintf(s,"%d",_course);
	Item.pszText=(LPSTR)s;
	ListView_SetItem(hTable,&Item);
}
void StudentTable::DeleteString(int _stringnumber)
{
	char * s=new char;
	students.DeleteItem(_stringnumber);
	UpdateTable();
}
void StudentTable::ReadFromFile(LPCSTR _path)
{
	fstream f;
	//MessageBox(0,_path,0,0);
	f.open(_path);
	char *s=new char[100];
	while (strcmp(s,"STUDENTS:") )
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
		stringstream fio;
		stringstream speciality;
		stringstream average_grade;
		stringstream course;
		stringstream photo;
		int i=0;
		while (s[i]!=',')
		{
			fio<<s[i];
			i++;
		}
		i++;
		while (s[i]!=',')
		{
			speciality<<s[i];
			i++;
		}
		i++;
		while (s[i]!=',')
		{
			average_grade<<s[i];
			i++;
		}
		i++;
		while (s[i]!=',')
		{
			course<<s[i];
			i++;
		}
		i++;
		while (s[i]!=',')
		{
			photo<<s[i];
			i++;
		}
		char *sFio=new char[100];
		sprintf(sFio,"%s",fio.str().data());
		char *sSpeciality=new char[100];
		sprintf(sSpeciality,"%s",speciality.str().data());
		char *sAverage_grade=new char[100];
		sprintf(sAverage_grade,"%s",average_grade.str().data());
		char *sCourse=new char[100];
		sprintf(sCourse,"%s",course.str().data());
		char *sPhoto=new char[100];
		sprintf(sPhoto,"%s",photo.str().data());
		sPhoto[strlen(sPhoto)-1]='\0';
		students.AddItem(sFio,sSpeciality,atoi(sAverage_grade),atoi(sCourse),sPhoto);
		AddString(students.vec.back().id,sFio,sSpeciality,atoi(sAverage_grade),atoi(sCourse),sPhoto);
		f.getline(s,256);
	}
}
StudentTable studTable;

INT_PTR CALLBACK StudTablDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam)
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
			MoveWindow(hwndDlg,10+w,95,w,h,1);
			break;
		}
	case WM_NOTIFY:
		{
			NM_LISTVIEW *lpNm = (NM_LISTVIEW *)lParam;
			if ( ((LPNMHDR)lParam)->code == LVN_COLUMNCLICK ) 
			{ 
				studTable.SortTable(lpNm->iSubItem);
				break;
			} 
			if ( ((LPNMHDR)lParam)->code == NM_DBLCLK ) 
			{
				studTable.currentString=lpNm->iItem;
				if (lpNm->iItem>=0)DialogBox(hInstance,MAKEINTRESOURCE(IDD_ADDSTUDENT),winMng.hMainWnd,AddEditStudentDlgProc);
				break;
			}
			return 0; 
		}
		break;
	case WM_COMMAND:
		switch(LOWORD(wParam))
		{
		case IDC_STUDTABL_ADD:
			{
				studTable.currentString=-1;
				DialogBox(hInstance,MAKEINTRESOURCE(IDD_ADDSTUDENT),winMng.hMainWnd,AddEditStudentDlgProc);
				break;
			}
		case IDC_STUDTABL_DELETE:
			int sel=ListView_GetNextItem(studTable.hTable,-1, LVNI_FOCUSED | LVNI_SELECTED);
			studTable.DeleteString(sel);
			break;
		}
		break;
	case WM_CLOSE:
		EndDialog(hwndDlg,0);
		break;
	}
	return 0;
}
INT_PTR CALLBACK AddEditStudentDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam)
{
	static HWND hEditFio;
	static HWND hEditSpeciality;
	static HWND hEditAverage_grade;
	static HWND hEditCourse;
	static HWND hPic;
	static HWND hPicPath;
	static HDC hdc,image_dc;
	static HANDLE old_hbitmap,hBitmap;
	PAINTSTRUCT ps;
	static stringstream s;
	switch(uMsg)
	{
	case WM_INITDIALOG:
		{
			stringstream s;
			s<<studTable.currentString;
			//MessageBox(0,s.str().data(),0,0);
			if (!(studTable.currentString==-1))
			{
				SetWindowText(hwndDlg,"Edit student");
			}
			else 
			{
				SetWindowText(hwndDlg,"Add student");
			}
			RECT *rct=new RECT;
			RECT *rctDlg=new RECT;
			GetWindowRect(winMng.hMainWnd,rct);
			GetWindowRect(hwndDlg,rctDlg);
			int w=rctDlg->right-rctDlg->left;
			int h=rctDlg->bottom-rctDlg->top;
			MoveWindow(hwndDlg,rct->left+(rct->right-rct->left)/2-w/2,rct->top+(rct->bottom-rct->top)/2-h/2,w,h,1);
			hEditFio=GetDlgItem(hwndDlg,IDC_ADDSTUDENT_FIO);
			hEditSpeciality=GetDlgItem(hwndDlg,IDC_ADDSTUDENT_SPECIALITY);
			hEditAverage_grade=GetDlgItem(hwndDlg,IDC_ADDSTUDENT_AVERAGE_GRADE);
			hEditCourse=GetDlgItem(hwndDlg,IDC_ADDSTUDENT_COURSE);
			hPicPath=GetDlgItem(hwndDlg,IDC_PICTUREPATH);
			hPic= GetDlgItem(hwndDlg,IDC_PHOTO);
			const char *path=new char[100];
			if (!(studTable.currentString==-1))
			{
				int i=0;
				for (studTable.students.vecIter=studTable.students.vec.begin();studTable.students.vecIter!=studTable.students.vec.end()&& i<studTable.currentString;studTable.students.vecIter++,i++){}
				SendMessage(hEditFio,WM_SETTEXT,100,LPARAM(studTable.students.vecIter->fio));
				SendMessage(hEditSpeciality,WM_SETTEXT,100,LPARAM(studTable.students.vecIter->speciality));
				stringstream s;
				s<<studTable.students.vecIter->average_grade;
				SendMessage(hEditAverage_grade,WM_SETTEXT,100,LPARAM(s.str().data()));
				s.str("");
				s<<studTable.students.vecIter->course;
				SendMessage(hEditCourse,WM_SETTEXT,100,LPARAM(s.str().data()));
				path=studTable.students.vecIter->photo;
				SendMessage(hPicPath,WM_SETTEXT,100,LPARAM(studTable.students.vecIter->photo));
			}
			else 
			{
				path="photoempty.bmp";
			}
			hBitmap = LoadImage(NULL, path, IMAGE_BITMAP, 0, 0, LR_LOADFROMFILE);
			SendMessage(hPic, STM_SETIMAGE, (WPARAM)IMAGE_BITMAP, LPARAM(hBitmap));
			SetFocus(hEditFio);
			break;
		}
	case WM_COMMAND:
		switch(LOWORD(wParam))
		{
		case IDOK:
			{
				char *fio=new char[50];
				char *speciality=new char[50];
				char *average_grade=new char[50];
				char *course=new char[50];
				char *photo=new char[50];
				SendMessage(hEditFio,WM_GETTEXT,50,(LPARAM)fio);
				SendMessage(hEditSpeciality,WM_GETTEXT,50,(LPARAM)speciality);
				SendMessage(hEditAverage_grade,WM_GETTEXT,50,(LPARAM)average_grade);
				SendMessage(hEditCourse,WM_GETTEXT,50,(LPARAM)course);
				SendMessage(hPicPath,WM_GETTEXT,50,(LPARAM)photo);
				if (!(studTable.currentString==-1))
				{
					studTable.students.EditItem(studTable.currentString,fio,speciality,atoi(average_grade),atoi(course),photo);
					studTable.EditString(studTable.currentString,fio,speciality,atoi(average_grade),atoi(course),photo);
				}
				else
				{
					studTable.students.AddItem(fio,speciality,atoi(average_grade),atoi(course),photo);
					studTable.AddString(studTable.students.vec.back().id,fio,speciality,atoi(average_grade),atoi(course),photo);
				}
				EndDialog(hwndDlg,0);
				break;
			}
		case IDC_LOAD:
			{
				OPENFILENAME ofn ;
				char szFile[100] ;
				ZeroMemory( &ofn , sizeof( ofn));
				ofn.lStructSize = sizeof ( ofn );
				ofn.hwndOwner = NULL  ;
				ofn.lpstrFile = szFile ;
				ofn.lpstrFile[0] = '\0';
				ofn.nMaxFile = sizeof( szFile );
				ofn.lpstrFilter = "Images (*.bmp)\0*.BMP\0";
				ofn.nFilterIndex =1;
				ofn.lpstrFileTitle = NULL ;
				ofn.nMaxFileTitle = 0 ;
				ofn.lpstrInitialDir=NULL ;
				ofn.Flags = OFN_PATHMUSTEXIST|OFN_FILEMUSTEXIST ;

				// Display the Open dialog box. 
				if(GetOpenFileName(&ofn))
				{
					hBitmap = LoadImage(NULL, ofn.lpstrFile, IMAGE_BITMAP, 0, 0, LR_LOADFROMFILE);
					SendMessage(hPic, STM_SETIMAGE, (WPARAM)IMAGE_BITMAP, LPARAM(hBitmap));
					SendMessage(hPicPath,WM_SETTEXT,100,LPARAM(ofn.lpstrFile));
				}	
			}
			break;
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
INT CALLBACK  SortingStudentsProc(LPARAM lParam1, LPARAM lParam2, LPARAM lParamSort)
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
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			iResult = strcmp(lpStr1, lpStr2);
			break;
		default:
			iResult = 0;
			break;
		}
	}
	return(iResult);
}