#include "FacultyTable.h"
#include "DepTable.h"
INT_PTR CALLBACK LinkFacultyDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam)
{
	static HWND hCombo;
	switch(uMsg)
	{
	case WM_INITDIALOG:
		{
			hCombo=GetDlgItem(hwndDlg,IDC_COMBOFAC);
			if (!hCombo) MessageBox(0,0,0,0);
			for (depTable.departments.vecIter=depTable.departments.vec.begin();depTable.departments.vecIter!=depTable.departments.vec.end();depTable.departments.vecIter++)
			{
				SendMessage(hCombo,CB_ADDSTRING,0, (LPARAM)"text");
				//reinterpret_cast<LPARAM>(depTable.departments.vecIter->name)
				//MessageBox(0,depTable.departments.vecIter->name,0,0);
			}
			break;
		}
	case WM_COMMAND:
		switch(LOWORD(wParam))
		{
		case IDOK:
			{

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