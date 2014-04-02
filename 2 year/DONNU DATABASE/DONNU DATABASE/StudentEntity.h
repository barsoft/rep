#include "WorkBench.h"
#include <vector>
WorkBench wb;
_Menu menu(console);
struct Student
{
	char * first_name;
	char * second_name;
	char * third_name;
	int average_grade;
	int course;
	char * speciality;
	_Button but;
};
class StudentEntity
{
	Student buff;
	int count;
	vector<Student> NODE;
	vector<Student>::iterator i;
public:
	StudentEntity()
	{
		count=0;
		
		buff.first_name=new char [80];
		buff.second_name=new char [80];
		buff.third_name=new char [80];
		buff.speciality=new char [80];
	}
	void InputStudentToEnd()
	{
		static int x=0;
		static int y=11;
		global.X=8;
		global.Y=9;
		buff.first_name=new char [80];
		buff.second_name=new char [80];
		buff.third_name=new char [80];
		buff.speciality=new char [80];
		SetConsoleCursorPosition(console.handle, global);
		wb.PaintInputWindow("Input student first name:",_inputStudentFirstName);
		console.SetColor(Black,LightGray);
		cin>>buff.first_name;
		wb.PaintInputWindow("Input student second name:",_inputStudentSecondName);
		console.SetColor(Black,LightGray);
		cin>>buff.second_name;
		wb.PaintInputWindow("Input student third name:",_inputStudentSecondName);
		console.SetColor(Black,LightGray);
		cin>>buff.third_name;
		wb.PaintInputWindow("Input student average grade:",_inputStudentSecondName);
		console.SetColor(Black,LightGray);
		cin>>buff.average_grade;
		wb.PaintInputWindow("Input student course:",_inputStudentSecondName);
		console.SetColor(Black,LightGray);
		cin>>buff.course;
		wb.PaintInputWindow("Input student speciality:",_inputStudentSecondName);
		console.SetColor(Black,LightGray);
		cin>>buff.speciality;
		char *s=new char[80];
		sprintf(s,"%s %s %s %d %d %s",buff.first_name,buff.second_name,buff.third_name,buff.average_grade,buff.course,buff.speciality);
		buff.but.Create(console,9,10+x,strlen(s),LightGray,Black,s);
		NODE.push_back(buff);
		wb.AddStudent.Create(console,9,y,15,LightGray,Black,"add student...");
		y++;
		wb.AddStudent.OnPaint();
		x+=1;
	}
	void OnPaint()
	{
		for (i=NODE.begin();i!=NODE.end();i++)
		{
			(*i).but.OnPaint();
		}
	}
};