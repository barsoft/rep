#pragma once

#include "Base.h"

class FacultyEntity
{
	Faculty buff;
	int count;
	
	vector<Faculty>::iterator i;
public:
	vector<Faculty> NODE;
	FacultyEntity()
	{
		count=0;
		buff.decan_fio=new char [80];
		buff.name=new char [80];
	}
	void InputFacultyToEnd()
	{
		static int x=0;
		static int y=11;
		COORD c;
		c.X=8;
		c.Y=9;
		static int idBuff=1;
		buff.id=idBuff;
		buff.decan_fio=new char [80];
		buff.name=new char [80];
		SetConsoleCursorPosition(MyCon, c);
		PaintInputWindow("Input faculty name:");
		
		SetColor(Black,LightGray);
		cin>>buff.name;
		PaintInputWindow("Input faculty decan FN:");
		
		SetColor(Black,LightGray);
		cin>>buff.decan_fio;
		NODE.push_back(buff);
		y++;
		x+=1;
		idBuff++;
		count++;
	}
	void OnPaint()
	{
		std::string s;
		COORD c;
		c.Y=cr.top+1;
		if (!count) 
		{
			SetColor(Black,White);
			c.X=cr.left+1;
			c.Y++;
			SetConsoleCursorPosition(MyCon, c);
			cout<<"There is no faculty yet...";
		}
		for (i=NODE.begin();i!=NODE.end();i++)
		{
			c.X=cr.left+1;
			c.Y++;
			SetConsoleCursorPosition(MyCon, c);
			s=ToString((*i).id);
			int j=0;
			while (j<4-strlen(s.data())) {s+=' '; j++;}
			s+="|";
			s+=(*i).name;
			j=0;
			while (j<17-strlen((*i).name)) {s+=' '; j++;}
			j=0;
			s+='|';
			s+=(*i).decan_fio;
			int l=strlen(s.data());
			for (int i=0;i<cr.right-l-8;i++) s+=' ';
			cout<<s.data();
		}
	}
	void Select(int id,ArrowState as)
	{
		SetColor(Yellow,DarkGray);
		std::string s;
		COORD c;
		c.Y=cr.top+id;
		for (i=NODE.begin();i!=NODE.end();i++)
		{
			if ((*i).id==id+1 && as==ArrowUp)
			{
				SetColor(White,DarkGray);
				c.X=cr.left+1;
				c.Y=cr.top+id+2;
				SetConsoleCursorPosition(MyCon, c);
				s=ToString((*i).id);
				int j=0;
				while (j<4-strlen(s.data())) {s+=' '; j++;}
				s+="|";
				s+=(*i).name;
				j=0;
				while (j<17-strlen((*i).name)) {s+=' '; j++;}
				j=0;
				s+='|';
				s+=(*i).decan_fio;
				int l=strlen(s.data());
				for (int i=0;i<cr.right-l-8;i++) s+=' ';
				cout<<s.data();
			}

		}
		
		for (i=NODE.begin();i!=NODE.end();i++)
		{
			if ((*i).id==id)
			{
				SetColor(Yellow,DarkGray);
				c.X=cr.left+1;
				c.Y=cr.top+id+1;
				SetConsoleCursorPosition(MyCon, c);
				s=ToString((*i).id);
				int j=0;
				while (j<4-strlen(s.data())) {s+=' '; j++;}
				s+="|";
				s+=(*i).name;
				j=0;
				while (j<17-strlen((*i).name)) {s+=' '; j++;}
				j=0;
				s+='|';
				s+=(*i).decan_fio;
				int l=strlen(s.data());
				for (int i=0;i<cr.right-l-8;i++) s+=' ';
				cout<<s.data();
			}

		}
		for (i=NODE.begin();i!=NODE.end();i++)
		{
			if ((*i).id==id-1 && as==ArrowDown)
			{
				SetColor(White,DarkGray);
				c.X=cr.left+1;
				c.Y=cr.top+id;
				SetConsoleCursorPosition(MyCon, c);
				s=ToString((*i).id);
				int j=0;
				while (j<4-strlen(s.data())) {s+=' '; j++;}
				s+="|";
				s+=(*i).name;
				j=0;
				while (j<17-strlen((*i).name)) {s+=' '; j++;}
				j=0;
				s+='|';
				s+=(*i).decan_fio;
				int l=strlen(s.data());
				for (int i=0;i<cr.right-l-8;i++) s+=' ';
				cout<<s.data();
			}

		}
	}
	int GetFacultyCount()
	{
		return count;
	}
};