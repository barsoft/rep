#include "StudentEntity.h"
struct Faculty
{
	char * decanfirst_name;
	char * decansecond_name;
	char * decanthird_name;
	char * name;
	_Button but;
};
class FacultyEntity
{
	Faculty buff;
	int count;
	vector<Faculty> NODE;
	vector<Faculty>::iterator i;
public:
	FacultyEntity()
	{
		count=0;
		
		buff.decanfirst_name=new char [80];
		buff.decansecond_name=new char [80];
		buff.decanthird_name=new char [80];
		buff.name=new char [80];
	}
	void InputFacultyToEnd()
	{
		static int x=0;
		static int y=11;
		global.X=8;
		global.Y=9;
		buff.decanfirst_name=new char [80];
		buff.decansecond_name=new char [80];
		buff.decanthird_name=new char [80];
		buff.name=new char [80];
		SetConsoleCursorPosition(console.handle, global);
		wb.PaintInputWindow("Input student name:",_inputStudentSecondName);
		console.SetColor(Black,LightGray);
		cin>>buff.name;
		wb.PaintInputWindow("Input faculty decan first name:",_inputStudentFirstName);
		console.SetColor(Black,LightGray);
		cin>>buff.decanfirst_name;
		wb.PaintInputWindow("Input Faculty decan second name:",_inputStudentSecondName);
		console.SetColor(Black,LightGray);
		cin>>buff.decansecond_name;
		wb.PaintInputWindow("Input Faculty decan third name:",_inputStudentSecondName);
		console.SetColor(Black,LightGray);
		cin>>buff.decanthird_name;
		std::string s;
		s=buff.name;
		int i=0;
		while (i<18-strlen(buff.name)) {s+=' '; i++;}
		i=0;
		s+=buff.decanfirst_name;
		while (i<18-strlen(buff.decanfirst_name)) {s+=' '; i++;}
		i=0;
		s+=buff.decansecond_name;
		while (i<18-strlen(buff.decansecond_name)) {s+=' '; i++;}
		i=0;
		s+=buff.decanthird_name;
		buff.but.Create(console,9,10+x,s.length(),LightGray,Black,s);
		NODE.push_back(buff);
		wb.AddFaculty.Create(console,9,y,15,LightGray,Black,"add faculty...");
		y++;
		wb.AddFaculty.OnPaint();
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