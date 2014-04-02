#pragma once

#include "Base.h"
class StudentEntity
{
	Student buff;
	int count;
	
	vector<Student>::iterator i;
public:
	vector<Student> NODE;
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
		static int idBuff=1;
		buff.id=idBuff;

		COORD c;
		c.X=8;
		c.Y=9;
		buff.first_name=new char [80];
		buff.second_name=new char [80];
		buff.third_name=new char [80];
		buff.speciality=new char [80];
		SetConsoleCursorPosition(MyCon, c);
		PaintInputWindow("Input student first name:");
		SetColor(Black,LightGray);
		cin>>buff.first_name;
		PaintInputWindow("Input student second name:");
		SetColor(Black,LightGray);
		cin>>buff.second_name;
		PaintInputWindow("Input student third name:");
		SetColor(Black,LightGray);
		cin>>buff.third_name;
		PaintInputWindow("Input student average grade:");
		SetColor(Black,LightGray);
		cin>>buff.average_grade;
		PaintInputWindow("Input student course:");
		SetColor(Black,LightGray);
		cin>>buff.course;
		PaintInputWindow("Input student speciality:");
		SetColor(Black,LightGray);
		cin>>buff.speciality;
		char *s=new char[80];
		sprintf(s,"%s %s %s %d %d %s",buff.first_name,buff.second_name,buff.third_name,buff.average_grade,buff.course,buff.speciality);
		NODE.push_back(buff);
		y++;
		x+=1;
	}
	void OnPaint(int currCaff)
	{
		std::string s;
		COORD c;
		c.Y=cr.top+1;
			
		int count=0;
		for (LinkCafedraStudentIterator=LinkCafedraStudent.begin();LinkCafedraStudentIterator!=LinkCafedraStudent.end();LinkCafedraStudentIterator++)
		{
			if ((*LinkCafedraStudentIterator).cafedra->id==currCaff)
			{
			c.X=cr.left+1;
			c.Y++;
			SetConsoleCursorPosition(MyCon, c);
			s=ToString((*LinkCafedraStudentIterator).student->id);
			int j=0;
			while (j<4-strlen(s.data())) {s+=' '; j++;}
			s+="|";
			s+=(*LinkCafedraStudentIterator).student->first_name;
			j=0;
			while (j<10-strlen((*LinkCafedraStudentIterator).student->first_name)) {s+=' '; j++;}
			s+="|";
			s+=(*LinkCafedraStudentIterator).student->first_name;
			j=0;
			while (j<10-strlen((*LinkCafedraStudentIterator).student->second_name)) {s+=' '; j++;}
			s+="|";
			s+=(*LinkCafedraStudentIterator).student->first_name;
			j=0;
			while (j<10-strlen((*LinkCafedraStudentIterator).student->third_name)) {s+=' '; j++;}
			j=0;
			s+='|';
			s+=(*LinkCafedraStudentIterator).cafedra->zavkafedroy_fio;
			int l=strlen(s.data());
			for (int i=0;i<cr.right-l-8;i++) s+=' ';
			cout<<s.data();
			count++;
			}
		}
				
		if (!count) 
		{
			SetColor(Black,White);
			c.X=cr.left+1;
			c.Y++;
			SetConsoleCursorPosition(MyCon, c);
			cout<<"There is no chair yet...";
		}
	}
	void Select(int id,int currCaff)
	{
		SetColor(Yellow,DarkGray);
		std::string s;
		COORD c;
		c.Y=cr.top+id;

		for (LinkCafedraStudentIterator=LinkCafedraStudent.begin();LinkCafedraStudentIterator!=LinkCafedraStudent.end();LinkCafedraStudentIterator++)
		{
			if ((*LinkCafedraStudentIterator).cafedra->id==currCaff)
			{
				if ((*LinkCafedraStudentIterator).student->id==id)
				{
					SetColor(Yellow,DarkGray);
					c.X=cr.left+1;
					c.Y=cr.top+id+1;
					SetConsoleCursorPosition(MyCon, c);
					s=ToString((*LinkCafedraStudentIterator).student->id);
					int j=0;
					while (j<4-strlen(s.data())) {s+=' '; j++;}
					s+="|";
					s+=(*LinkCafedraStudentIterator).student->first_name;
					j=0;
					while (j<10-strlen((*LinkCafedraStudentIterator).student->first_name)) {s+=' '; j++;}
					s+="|";
					s+=(*LinkCafedraStudentIterator).student->second_name;
					j=0;
					while (j<10-strlen((*LinkCafedraStudentIterator).student->second_name)) {s+=' '; j++;}
					s+="|";
					s+=(*LinkCafedraStudentIterator).student->third_name;
					int l=strlen(s.data());
					for (int i=0;i<cr.right-l-8;i++) s+=' ';
					cout<<s.data();
				}
			}
		}
		
	}
};