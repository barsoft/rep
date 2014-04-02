#pragma once
#include <iostream>
#include <vector>
#include "Entity.h"
using namespace std;
struct Department
{
	int id;
	const char * zav_kafediri_fio;
	const char * name;
};
typedef vector<Department>
	DepartmentVec;
typedef vector<Department>::iterator
	DepartmentVecIterator;
class Departments:public Entity
{
	friend class DepTable;
	friend INT_PTR CALLBACK AddEditDepDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
	friend LRESULT CALLBACK MainWndProc( __in  HWND hwnd,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
	friend INT_PTR CALLBACK LinkFacultyDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
private:
	DepartmentVec vec;	
	DepartmentVecIterator vecIter;
public:
	void AddItem(const char * _name,const char * _zav_kafediri_fio);
	void EditItem(int _num,const char * _name,const char * _zav_kafediri_fio);
	int GetItemCount();
	void DeleteItem(int _i);
};
void Departments::AddItem(const char * _name,const char * _zav_kafediri_fio)
{
	Department buff;
	buff.id=IDcounter;
	buff.name=_name;
	buff.zav_kafediri_fio=_zav_kafediri_fio;
	vec.push_back(buff);
	IDcounter++;
}
void Departments::EditItem(int _num,const char * _name,const char * _zav_kafediri_fio)
{
	int i=0;
	for (vecIter=vec.begin();vecIter!=vec.end() && i<_num ;vecIter++,i++){}
	vecIter->name=_name;
	vecIter->zav_kafediri_fio=_zav_kafediri_fio;
}
void Departments::DeleteItem(int _i)
{
	int i=0;
	for (vecIter=vec.begin();vecIter!=vec.end() && i<_i ;vecIter++,i++){}
	vec.erase(vecIter);
}
int Departments::GetItemCount()
{
	return vec.size();
}