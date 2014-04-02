void FacultyTable::CreateDlg()
{
	show=!show;
	if (show)
	{
		if (!hTablDialog)
		{
			hTablDialog=CreateDialog(hInstance,MAKEINTRESOURCE(IDD_FACTABL),winMng.hMainWnd,(DLGPROC)FacTablDlgProc);
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

void FacultyTable::CreateTable()
{
	LVCOLUMN column;
	RECT rc;
	GetWindowRect(hTablDialog,&rc);
	hTable=GetDlgItem(hTablDialog,IDC_LIST2);
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
	column.pszText = "DecanFIO"; 
	ListView_InsertColumn(hTable, 2, &column);
	//UpdateTable();
}
void FacultyTable::UpdateTable()
{
	ListView_DeleteAllItems(hTable);
	for (faculties.vecIter=faculties.vec.begin();faculties.vecIter!=faculties.vec.end();faculties.vecIter++)
	{
		AddString(faculties.vecIter->id,faculties.vecIter->name,faculties.vecIter->decan_fio);
	}
}
void FacultyTable::AddString(int _id,const char * _name,const char * _decanFIO)
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
	Item.pszText=(LPSTR)_decanFIO;
	ListView_SetItem(hTable,&Item);
}
void FacultyTable::DeleteString(int _stringnumber)
{
	char * s=new char;
	ListView_GetItemText(hTable,_stringnumber,0,s,256);
	faculties.DeleteFaculty(_stringnumber);
	UpdateTable();
}
void FacultyTable::ReadFromFile(char *_path)
{
	fstream f;
	f.open(_path);
	//MessageBox(0,"Error reading file!","ERROR",MB_ICONWARNING);
	char *s=new char[100];
	while (strcmp(s,"FACULTIES:") )
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
		stringstream decanFIO;
		int i=0;
		while (s[i]!=',')
		{
			name<<s[i];
			i++;
		}
		i++;
		while (s[i]!=';')
		{
			decanFIO<<s[i];
			i++;
		}
		faculties.AddFaculty(name.str().data(),decanFIO.str().data());
		AddString(faculties.vec.back().id,name.str().data(),decanFIO.str().data());
		f.getline(s,256);
		if (!f.good()) MessageBox(0,"Error reading file!","ERROR",MB_ICONWARNING);
	}
}
FacultyTable facTable;

INT_PTR CALLBACK FacTablDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam)
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
			MoveWindow(hwndDlg,5,95,w,h,1);
			break;
		}
	case WM_NOTIFY:
		{
			NM_LISTVIEW *lpNm = (NM_LISTVIEW *)lParam;
			if ( ((LPNMHDR)lParam)->code == LVN_COLUMNCLICK ) 
			{ 
				facTable.SortTable(lpNm->iSubItem);
			} 
			if ( ((LPNMHDR)lParam)->code == NM_DBLCLK ) 
			{

			}
			return 0; 
		}
		break;
	case WM_COMMAND:
		switch(LOWORD(wParam))
		{
		case IDC_FACTABL_ADD:
			{
				DialogBox(hInstance,MAKEINTRESOURCE(IDD_ADDFACULTY),winMng.hMainWnd,AddFacultyDlgProc);
				break;
			}
		case IDC_FACTABL_DELETE:
			int sel=ListView_GetNextItem(facTable.hTable,-1, LVNI_FOCUSED | LVNI_SELECTED);
			facTable.DeleteString(sel);
			break;
		}
		break;
	case WM_CLOSE:
		DestroyWindow(hwndDlg);
		hwndDlg=0;
		EndDialog(hwndDlg,0);
		break;
	}
	return 0;
}
INT_PTR CALLBACK AddFacultyDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam)
{
	static HWND hEditName;
	static HWND hEditDecanFIO;
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
			hEditName=GetDlgItem(hwndDlg,IDC_ADDFACULTY_NAMEEDIT);
			hEditDecanFIO=GetDlgItem(hwndDlg,IDC_ADDFACULTY_DECANFIOEDIT);
			SetFocus(hEditName);
			break;
		}
	case WM_COMMAND:
		switch(LOWORD(wParam))
		{
		case IDOK:
			{
				char *name=new char[50];
				char *decanFIO=new char[50];
				if(!hEditName) MessageBox(0,0,0,0);
				SendMessage(hEditName,WM_GETTEXT,50,(LPARAM)name);
				SendMessage(hEditDecanFIO,WM_GETTEXT,50,(LPARAM)decanFIO);
				facTable.faculties.AddFaculty(name,decanFIO);
				facTable.AddString(facTable.faculties.vec.back().id,name,decanFIO);
				EndDialog(hwndDlg,0);
				break;
			}
		case IDCANCEL:
			EndDialog(hwndDlg,0);
			break;
		}
		break;
	case WM_CLOSE:
		DestroyWindow(hwndDlg);
		hwndDlg=0;
		EndDialog(hwndDlg,0);
		break;
	}
	return 0;
}
INT CALLBACK  SortingProc(LPARAM lParam1, LPARAM lParam2, LPARAM lParamSort)
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