#include <iostream>
#include <vector>
#include "Entity.h"
using namespace std;
struct Faculty
{
	int id;
	const char * decan_fio;
	const char * name;
};
typedef vector<Faculty>
	FacultyVec;
typedef vector<Faculty>::iterator
	FacultyVecIterator;
class Faculties:public Entity
{
	friend class FacultyTable;
	friend INT_PTR CALLBACK AddEditFacultyDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
	friend LRESULT CALLBACK MainWndProc( __in  HWND hwnd,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
private:
	FacultyVec vec;	
	FacultyVecIterator vecIter;
public:
	void AddItem(const char * _name,const char * _decanFIO);
	void EditItem(int _num,const char * _name,const char * _decanFIO);
	int GetItemCount();
	void DeleteItem(int _i);
};
void Faculties::AddItem(const char * _name,const char * _decanFIO)
{
	Faculty buff;
	buff.id=IDcounter;
	buff.name=_name;
	buff.decan_fio=_decanFIO;
	vec.push_back(buff);
	IDcounter++;
}
void Faculties::EditItem(int _num,const char * _name,const char * _decanFIO)
{
	int i=0;
	for (vecIter=vec.begin();vecIter!=vec.end() && i<_num ;vecIter++,i++){}
	vecIter->name=_name;
	vecIter->decan_fio=_decanFIO;
}
void Faculties::DeleteItem(int _i)
{
	int i=0;
	for (vecIter=vec.begin();vecIter!=vec.end() && i<_i ;vecIter++,i++){}
	vec.erase(vecIter);
}
int Faculties::GetItemCount()
{
	return vec.size();
}