#pragma once

#include "Base.h"

class CafedraEntity
{
	Cafedra buff;
	int count;
	
	vector<Cafedra>::iterator i;
public:
	vector<Cafedra> NODE;
	CafedraEntity()
	{
		count=0;
		
		
		buff.zavkafedroy_fio=new char [80];
		buff.name=new char [80];
	}
	void InputCafedraToEnd()
	{
		static int x=0;
		static int y=11;
		COORD c;
		c.X=8;
		c.Y=9;
		static int idBuff=1;
		buff.id=idBuff;
		buff.zavkafedroy_fio=new char [80];
		buff.name=new char [80];
		SetConsoleCursorPosition(MyCon, c);
		PaintInputWindow("Input chair name:");
		SetColor(Black,LightGray);
		cin>>buff.name;
		PaintInputWindow("Input Head of chair FN:");
		SetColor(Black,LightGray);
		cin>>buff.zavkafedroy_fio;
		NODE.push_back(buff);
		y++;
		x+=1;
		idBuff++;
		count++;
	}
	void OnPaint(int currFaculty)
	{
		 std::string s;
		COORD c;
		c.Y=cr.top+1;
			int z=0;
		int countCaff=0;
		for (LinkFacultyCafedraIterator=LinkFacultyCafedra.begin();LinkFacultyCafedraIterator!=LinkFacultyCafedra.end();LinkFacultyCafedraIterator++)
		{
			if ((*(LinkFacultyCafedraIterator)).faculty->id==currFaculty)
			{
			c.X=cr.left+1;
			c.Y++;
			SetConsoleCursorPosition(MyCon, c);
			s=ToString((*LinkFacultyCafedraIterator).cafedra->id);
			int j=0;
			while (j<4-strlen(s.data())) {s+=' '; j++;}
			s+="|";
			s+=(*LinkFacultyCafedraIterator).cafedra->name;
			j=0;
			while (j<17-strlen((*LinkFacultyCafedraIterator).cafedra->name)) {s+=' '; j++;}
			j=0;
			s+='|';
			s+=(*LinkFacultyCafedraIterator).cafedra->zavkafedroy_fio;
			int l=strlen(s.data());
			for (int i=0;i<cr.right-l-8;i++) s+=' ';
			
			cout<<s.data()<<z;
			z++;
			countCaff++;
			}
		}
				
		if (!countCaff) 
		{
			SetColor(Black,White);
			c.X=cr.left+1;
			c.Y++;
			SetConsoleCursorPosition(MyCon, c);
			cout<<"There is no chair yet...";
		}
		
	}
	void Select(int id,int pos,int currFaculty)
	{
		SetColor(Yellow,DarkGray);
		std::string s;
		COORD c;
		c.Y=cr.top+pos;

		for (LinkFacultyCafedraIterator=LinkFacultyCafedra.begin();LinkFacultyCafedraIterator!=LinkFacultyCafedra.end();LinkFacultyCafedraIterator++)
		{
			if ((*LinkFacultyCafedraIterator).faculty->id==currFaculty)
			{
				if ((*LinkFacultyCafedraIterator).cafedra->id==id)
				{
					SetColor(Yellow,DarkGray);
					c.X=cr.left+1;
					c.Y=cr.top+pos+1;
					SetConsoleCursorPosition(MyCon, c);
					s=ToString(((*LinkFacultyCafedraIterator).cafedra)->id);
					int j=0;
					while (j<4-strlen(s.data())) {s+=' '; j++;}
					s+="|";
					s+=((*LinkFacultyCafedraIterator).cafedra)->name;
					j=0;
					while (j<17-strlen(((*LinkFacultyCafedraIterator).cafedra)->name)) {s+=' '; j++;}
					j=0;
					s+='|';
					s+=((*LinkFacultyCafedraIterator).cafedra)->zavkafedroy_fio;
					int l=strlen(s.data());
					for (int i=0;i<cr.right-l-8;i++) s+=' ';
					cout<<s.data();
				}		
			}
		}

		/*SetColor(Yellow,DarkGray);
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
				s+=(*i).zavkafedroy_fio;
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
				s+=(*i).zavkafedroy_fio;
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
				s+=(*i).zavkafedroy_fio;
				int l=strlen(s.data());
				for (int i=0;i<cr.right-l-8;i++) s+=' ';
				cout<<s.data();
			}
		}*/
	}
	int GetCafedraCount(int currFaculty)
	{
		for (LinkFacultyCafedraIterator=LinkFacultyCafedra.begin();LinkFacultyCafedraIterator!=LinkFacultyCafedra.end();LinkFacultyCafedraIterator++)
		{
			if ((*LinkFacultyCafedraIterator).faculty->id==currFaculty)
			{
				count++;	
			}
		}
		return count;
	}
};