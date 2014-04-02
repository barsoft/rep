#pragma once
#include "WindowsManager.h"
#include <sstream>
#include <CommCtrl.h>
#include <fstream>
using namespace std;
INT CALLBACK  SortingProc(LPARAM lParam1, LPARAM lParam2, LPARAM lParamSort);
class Table
{	
	protected:
		bool show;
		// ABSTRACT METHODS
		virtual void CreateTable()=0; // CREATES TABLE
	public:
		Table()
		{
			show=false;
		}
		HWND hTable; // TABLE DESCRIPTOR
		HWND hTablDialog; // TABLE DIALOG DESCRIPTOR
		HWND hAddDialog; // ADD DIALOG DESCRIPTOR
		// ABSTRACT METHODS
		virtual void SortTable(int _columnkeynumber)=0;
		virtual void CreateDlg()=0; // CREATES DIALOG
		virtual void UpdateTable()=0; // UPDATES TABLE
		virtual void DeleteString(int _stringnumber)=0; // DELETES STRING FROM TABLE
		virtual void ReadFromFile(LPCSTR _path)=0; // READS DATA FROM FILE
};
