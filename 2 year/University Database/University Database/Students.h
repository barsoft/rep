#include <iostream>
#include "Entity.h"
struct Student
{
	int id;
	const char * fio;
	int average_grade;
	int course;
	const char * speciality;
	const char * photo;
};
typedef vector<Student>
	StudentVec;
typedef vector<Student>::iterator
	StudentVecIterator;
class Students:public Entity
{
	friend class StudentTable;
	friend INT_PTR CALLBACK AddEditStudentDlgProc( __in  HWND hwndDlg,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
	friend LRESULT CALLBACK MainWndProc( __in  HWND hwnd,__in  UINT uMsg,__in  WPARAM wParam,__in  LPARAM lParam);
private:
	StudentVec::iterator vecIter;
	StudentVec vec;
public:
	void AddItem(const char * _fio,const char * _speciality,int _average_grade,int _course,const char *_photo);
	void EditItem(int _num,const char * _fio,const char * _speciality,int _average_grade,int _course,const char *_photo);
	int GetItemCount();
	void DeleteItem(int _i);
};
void Students::AddItem(const char * _fio,const char * _speciality,int _average_grade,int _course,const char *_photo)
{
	Student buff;
	buff.id=IDcounter;
	buff.fio=_fio;
	buff.speciality=_speciality;
	buff.average_grade=_average_grade;
	buff.course=_course;
	buff.photo=_photo;
	vec.push_back(buff);
	IDcounter++;
}
void Students::DeleteItem(int _i)
{
	int i=0;
	for (vecIter=vec.begin();vecIter!=vec.end() && i<_i ;vecIter++,i++){}
	vec.erase(vecIter);
}
int Students::GetItemCount()
{
	return vec.size();
}
void Students::EditItem(int _num,const char * _fio,const char * _speciality,int _average_grade,int _course,const char *_photo)
{
	int i=0;
	for (vecIter=vec.begin();vecIter!=vec.end() && i<_num ;vecIter++,i++){}
	vecIter->fio=_fio;
	vecIter->speciality=_speciality;
	vecIter->average_grade=_average_grade;
	vecIter->course=_course;
	vecIter->photo=_photo;
}