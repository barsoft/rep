#include <iostream>
#include <Windows.h>
#include "Console.h"
#include <conio.h>
#include "UniversityEntity.h"
using namespace std;

UniversityEntity University;

void PaintUniversityStateWindow()
{
	char *s=new char[80];
	SetColor(Black,White);
	COORD c={cr.left-4,cr.top-2};
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<cr.bottom-3; i++)
	{
		for (int j=0;j<cr.right+2; j++)
		{
			cout<<' ';
		}
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
	}
	SetColor(Black,White);
	sprintf(s,"%s%s",University.GetUniversityName()," university:");
	c.X=23-strlen(s)/2;
	c.Y=7;
	SetConsoleCursorPosition(MyCon, c);
	cout<<s;
	c.X=cr.left;
	c.Y=cr.top;
	SetConsoleCursorPosition(MyCon, c);
	printf("%c",218);
	for (int i=0;i<cr.right-8; i++)
	{
		printf("%c",196);
	}
	printf("%c",191);
	for (int i=0;i<cr.bottom-8; i++)
	{
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
		printf("%c",179);
	}
	c.Y=cr.bottom;
	SetConsoleCursorPosition(MyCon, c);
	printf("%c",192);
	for (int i=0;i<cr.right-8; i++)
	{
		printf("%c",196);
	}
	printf("%c",217);
	c.X=cr.right;
	c.Y=cr.top;
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<cr.bottom-9; i++)
	{
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
		printf("%c",179);
	}
}
void PaintFacultyStateWindow(int curr)
{
	char *s=new char[80];
	SetColor(Black,White);
	COORD c={cr.left-4,cr.top-2};
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<cr.bottom-3; i++)
	{
		for (int j=0;j<cr.right+2; j++)
		{
			cout<<' ';
		}
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
	}
	SetColor(Black,White);
	sprintf(s,"%s%s",University.faculty.NODE[curr-1].name," faculty:");
	c.X=23-strlen(s)/2;
	c.Y=7;
	SetConsoleCursorPosition(MyCon, c);
	cout<<s;
	c.X=cr.left;
	c.Y=cr.top;
	SetConsoleCursorPosition(MyCon, c);
	printf("%c",218);
	for (int i=0;i<cr.right-8; i++)
	{
		printf("%c",196);
	}
	printf("%c",191);
	for (int i=0;i<cr.bottom-8; i++)
	{
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
		printf("%c",179);
	}
	c.Y=cr.bottom;
	SetConsoleCursorPosition(MyCon, c);
	printf("%c",192);
	for (int i=0;i<cr.right-8; i++)
	{
		printf("%c",196);
	}
	printf("%c",217);
	c.X=cr.right;
	c.Y=cr.top;
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<cr.bottom-9; i++)
	{
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
		printf("%c",179);
	}
}
void PaintCafedraStateWindow(int curr)
{
	char *s=new char[80];
	SetColor(Black,White);
	COORD c={cr.left-4,cr.top-2};
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<cr.bottom-3; i++)
	{
		for (int j=0;j<cr.right+2; j++)
		{
			cout<<' ';
		}
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
	}
	SetColor(Black,White);
	sprintf(s,"%s%s",University.cafedra.NODE[curr-1].name," chair:");
	c.X=23-strlen(s)/2;
	c.Y=7;
	SetConsoleCursorPosition(MyCon, c);
	cout<<s;
	c.X=cr.left;
	c.Y=cr.top;
	SetConsoleCursorPosition(MyCon, c);
	printf("%c",218);
	for (int i=0;i<cr.right-8; i++)
	{
		printf("%c",196);
	}
	printf("%c",191);
	for (int i=0;i<cr.bottom-8; i++)
	{
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
		printf("%c",179);
	}
	c.Y=cr.bottom;
	SetConsoleCursorPosition(MyCon, c);
	printf("%c",192);
	for (int i=0;i<cr.right-8; i++)
	{
		printf("%c",196);
	}
	printf("%c",217);
	c.X=cr.right;
	c.Y=cr.top;
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<cr.bottom-9; i++)
	{
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
		printf("%c",179);
	}
}
void ResetScreen()
{
	char * s=new char[80];
	char key;
	int cols=70;
	int lines=60;
	sprintf(s,"%s%d%s%d","mode con cols=",cols," lines=",lines);
	system(s);
	COORD c;
	SetColor(White,Red);
	system("cls");
	SetColor(White,DarkGray);
	for (int i=0;i<70;i++) cout<<char(176);
	s=" STUDENTS DATABASE";
	cout<<char(186)<<s; 
	for (int i=0;i<cols-strlen(s)-1;i++) cout<<' ';
	for (int i=0;i<70;i++) cout<<char(176);
	c.X=0;
	c.Y=58;
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<70;i++) cout<<char(176);
	c.X=0;
	c.Y=59;
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<70;i++) cout<<' ';
	c.X=0;
	c.Y=58;
	SetConsoleCursorPosition(MyCon, c);
	cout<<char(186)<<" 1-NEW";
	cout<<char(186)<<" 2-OPEN";
	if (gs==NULLSTATE) SetColor(LightGray,DarkGray);
	cout<<char(186)<<" 3-SAVE";
	SetColor(White,DarkGray);
	cout<<char(186)<<" ESC-EXIT";
}
void DrawUniversityStateScreen()
{

}
void main()
{
	char * s=new char[80];
	char key;
	int cols=70;
	int lines=60;
	sprintf(s,"%s%d%s%d","mode con cols=",cols," lines=",lines);
	system(s);
	COORD c;
	::cr.left=7,cr.right=62,cr.top=8,cr.bottom=28;
	int currFaculty=1;
	int currCafedra=1;
	int currStudent=1;
	int currpos=1;
	do
	{
		ResetScreen();
		switch(gs)
		{
		case NULLSTATE:
		{
			SetColor(White,DarkGray);
			c.X=0;
			c.Y=58;
			SetConsoleCursorPosition(MyCon, c);
			//for (int i=0;i<cols-strlen(s)-1;i++) cout<<' ';
			key=_getch();
			switch (key)
			{
			case '1':	
				PaintInputWindow("Input name of the university:");
				University.InputUniversityName();
				gs=UNIVERSITYSTATE;
				break;
			}
			break;
		}
		case UNIVERSITYSTATE:
		{
			PaintUniversityStateWindow();
			SetColor(White,Black);
			c.X=cr.left+1;
			c.Y=cr.top+1;
			SetConsoleCursorPosition(MyCon, c);
			string s1="ID |Name             |Decan FN";
			for (int i=0;i<cr.right-strlen(s1.data())+16;i++) s1+=' ';
			cout<<s1.data();
			SetColor(White,DarkGray);
			c.X=0;
			c.Y=55;
			SetConsoleCursorPosition(MyCon, c);
			for (int i=0;i<70;i++) cout<<char(176);
			for (int i=0;i<cols;i++) cout<<' ';
			c.X=0;
			c.Y=56;
			SetConsoleCursorPosition(MyCon, c);
			cout<<char(186)<<" A-ADD ";
			cout<<char(186)<<" DEL-DELETE ";
			cout<<char(186)<<" ENTER-SELECT ";
			cout<<char(186)<<" S-SORT ";
			cout<<char(186)<<" E-EDIT ";
			University.faculty.OnPaint();
			University.faculty.Select(currFaculty,ArrowNULL);
			bool end=false;
			while (!end)
			{
				key=_getch();
				switch (key)
				{
				case '1':	
					PaintInputWindow("Input name of the university:");
					University.InputUniversityName();
					gs=UNIVERSITYSTATE;
					end=true;
					break;
				case 'A':
				case 'a':
					University.faculty.InputFacultyToEnd();
					end=true;
					break;
				case 72:
					if (currFaculty>1) currFaculty--;

					University.faculty.Select(currFaculty,ArrowUp);
					break;
				case 80:
					if (currFaculty<University.faculty.GetFacultyCount()) currFaculty++;
					University.faculty.Select(currFaculty,ArrowDown);
					break;
				case 13:
					
					gs=FACULTYSTATE;
					
					end=true;
					break;
				}
			}
			break;
		}
		case FACULTYSTATE:
			{
				currpos=1;
				PaintFacultyStateWindow(currFaculty);
				SetColor(White,Black);
				c.X=cr.left+1;
				c.Y=cr.top+1;
				SetConsoleCursorPosition(MyCon, c);
				string s1="ID |Name             |Head of a chair FN";
				for (int i=0;i<cr.right-strlen(s1.data())+5;i++) s1+=' ';
				cout<<s1.data();
				SetColor(White,DarkGray);
				c.X=0;
				c.Y=55;
				SetConsoleCursorPosition(MyCon, c);
				for (int i=0;i<70;i++) cout<<char(176);
				for (int i=0;i<cols;i++) cout<<' ';
				c.X=0;
				c.Y=56;
				SetConsoleCursorPosition(MyCon, c);
				cout<<char(186)<<" A-ADD ";
				cout<<char(186)<<" DEL-DELETE ";
				cout<<char(186)<<" ENTER-SELECT ";
				cout<<char(186)<<" S-SORT ";
				cout<<char(186)<<" E-EDIT ";
				cout<<char(186)<<" <- BACK ";
				std::string s;

				University.cafedra.OnPaint(currFaculty);
				University.cafedra.Select(currCafedra,currpos,currFaculty);
				bool end=false;
				while (!end)
				{
					key=_getch();
					switch (key)
					{
					case '1':	
						PaintInputWindow("Input name of the university:");
						University.InputUniversityName();
						gs=UNIVERSITYSTATE;
						end=true;
						break;
					case 'A':
					case 'a':
						University.cafedra.InputCafedraToEnd();

						AddLinkFacultyCafedra(&University.faculty.NODE[currFaculty-1],&*(University.cafedra.NODE.end()-1));
						
						end=true;
						break;
					case 72:
						if (currpos>1) 
						{
							currpos--;
							currCafedra--;
							ResetScreen();
							PaintFacultyStateWindow(currFaculty);
							SetColor(White,Black);
							c.X=cr.left+1;
							c.Y=cr.top+1;
							SetConsoleCursorPosition(MyCon, c);
							string s1="ID |Name             |Head of a chair FN";
							for (int i=0;i<cr.right-strlen(s1.data())+5;i++) s1+=' ';
							cout<<s1.data();
							SetColor(White,DarkGray);
							c.X=0;
							c.Y=55;
							SetConsoleCursorPosition(MyCon, c);
							for (int i=0;i<70;i++) cout<<char(176);
							for (int i=0;i<cols;i++) cout<<' ';
							c.X=0;
							c.Y=56;
							SetConsoleCursorPosition(MyCon, c);
							cout<<char(186)<<" A-ADD ";
							cout<<char(186)<<" DEL-DELETE ";
							cout<<char(186)<<" ENTER-SELECT ";
							cout<<char(186)<<" S-SORT ";
							cout<<char(186)<<" E-EDIT ";
							cout<<char(186)<<" <- BACK ";
							std::string s;

							University.cafedra.OnPaint(currFaculty);
							University.cafedra.Select(currCafedra,currpos,currFaculty);
							
							
						}

						break;
					case 80:
						if (currpos) 
						{
							currpos++;
							currCafedra++;
							ResetScreen();
							PaintFacultyStateWindow(currFaculty);
							SetColor(White,Black);
							c.X=cr.left+1;
							c.Y=cr.top+1;
							SetConsoleCursorPosition(MyCon, c);
							string s1="ID |Name             |Head of a chair FN";
							for (int i=0;i<cr.right-strlen(s1.data())+5;i++) s1+=' ';
							cout<<s1.data();
							SetColor(White,DarkGray);
							c.X=0;
							c.Y=55;
							SetConsoleCursorPosition(MyCon, c);
							for (int i=0;i<70;i++) cout<<char(176);
							for (int i=0;i<cols;i++) cout<<' ';
							c.X=0;
							c.Y=56;
							SetConsoleCursorPosition(MyCon, c);
							cout<<char(186)<<" A-ADD ";
							cout<<char(186)<<" DEL-DELETE ";
							cout<<char(186)<<" ENTER-SELECT ";
							cout<<char(186)<<" S-SORT ";
							cout<<char(186)<<" E-EDIT ";
							cout<<char(186)<<" <- BACK ";
							std::string s;

							University.cafedra.OnPaint(currFaculty);
							University.cafedra.Select(currCafedra,currpos,currFaculty);
							
							
							
						}

						break;
					case 13:
						gs=CAFEDRASTATE;
						end=true;
						break;
					case 8:
						gs=UNIVERSITYSTATE;
						currpos=1;
						end=true;
						break;
					}
				}
				break;
			}
		case CAFEDRASTATE:
			{
				PaintCafedraStateWindow(currCafedra);
				SetColor(White,Black);
				c.X=cr.left+1;
				c.Y=cr.top+1;
				SetConsoleCursorPosition(MyCon, c);
				string s1="ID |Student FN";
				for (int i=0;i<cr.right-strlen(s1.data())+5;i++) s1+=' ';
				cout<<s1.data();
				SetColor(White,DarkGray);
				c.X=0;
				c.Y=55;
				SetConsoleCursorPosition(MyCon, c);
				for (int i=0;i<70;i++) cout<<char(176);
				for (int i=0;i<cols;i++) cout<<' ';
				c.X=0;
				c.Y=56;
				SetConsoleCursorPosition(MyCon, c);
				cout<<char(186)<<" A-ADD ";
				cout<<char(186)<<" DEL-DELETE ";
				cout<<char(186)<<" ENTER-SELECT ";
				cout<<char(186)<<" S-SORT ";
				cout<<char(186)<<" E-EDIT ";
				cout<<char(186)<<" <- BACK ";
				std::string s;

				University.student.OnPaint(currCafedra);
				University.student.Select(currStudent,currCafedra);
				bool end=false;
				while (!end)
				{
					key=_getch();
					switch (key)
					{
					case '1':	
						PaintInputWindow("Input name of the university:");
						University.InputUniversityName();
						gs=UNIVERSITYSTATE;
						end=true;
						break;
					case 'A':
					case 'a':
						University.student.InputStudentToEnd();
						AddLinkCafedraStudent(&University.cafedra.NODE[currCafedra-1],&University.student.NODE[currStudent-1]);
						end=true;
						break;
					case 72:
						if (currCafedra>1) currStudent--;
						end=true;
						break;
					case 80:
						if (currCafedra<University.cafedra.GetCafedraCount(currCafedra)) currStudent++;
						end=true;
						break;
					case 13:
						gs=CAFEDRASTATE;
						end=true;
						break;
					case 8:
						gs=FACULTYSTATE;
						end=true;
						break;
					}
				}
				break;
			}
		}
	}
	while (key!=27);
}