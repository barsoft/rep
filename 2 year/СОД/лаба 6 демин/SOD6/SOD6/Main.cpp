#include <iostream>
#include <conio.h>
#include "DataBase.h"
using namespace std;
HANDLE h=GetStdHandle(STD_OUTPUT_HANDLE);
COORD cp;
void main()
{
	setlocale(LC_ALL, "Russian");
	HelloWindow();
	MainMenu();
}
void MainMenu()
{
	do
	{
		SetColor(UT,UB);
		clrscr;
		SetTitle(UT,TB,"STUDENTS DATABASE | аюгю дюммшу н ярсдемрюу смхбепяхрернб днмежйю");
		SlidingHelp(UT,TB,"днаюбхрэ","сдюкхрэ","оепехлемнбюрэ",NULL);
		StaticHelpString(UT,HB,"яохянй смхбепяхрернб:",NULL);
		File DataBase;
		//int i=0; while (i++<300) {SetConsoleTextAttribute(h,i); cout<<i<<"x\n";}
		SetColor(UT,UB);
		DataBase.ShowFolderList();
		cp.X=0; cp.Y=3;
		SetConsoleCursorPosition(h,cp);
		DataBase.Selector(ST,SB,up,cp.Y); 
		cp.X=0; cp.Y=3;
		SetConsoleCursorPosition(h,cp);
		char* name=new char[80];
		char key;
		COORD coord;
		bool stop=false;
		do
		{
			do
			key=_getch();
			while (key!='1' && key!='2' && key!='3' && key!=80 && key!=72 && key!=13 && key!=27);
			switch (key)
			{
			case '1':
				MessageBox(UT,MBB,3,32,"ббедхре мюгбюмхе смхбепяхрерю:");
				cin>>name;
				DataBase.CreateFolder(name);
				stop=true;
				break;
			case '2':
				DataBase.RemoveFolder(cp.Y);
				stop=true;
				break;
			case '3':
				coord=cp;
				MessageBox(UT,MBB,3,37,"ббедхре мнбне мюгбюмхе смхбепяхрерю:");
				cin>>name;
				cout<<cp.Y;
				DataBase.RenameFolder(name,coord.Y);
				stop=true;
				break;
			/////////////////////////////////////// || //////////////////////////////
			/////////////////////////////////////// \/ //////////////////////////////
			case 80:
				if (cp.Y<DataBase.GetCountUniver() && DataBase.GetCountUniver())
				{
					cp.X=0; 
					SetConsoleCursorPosition(h,cp); 
					DataBase.Selector(ST,SB,down,cp.Y); 
					cp.Y++; cp.X=0;
					SetConsoleCursorPosition(h,cp); 
				}
				break;
			/////////////////////////////////////// /\ //////////////////////////////
			/////////////////////////////////////// || //////////////////////////////
			case 72:
				if (cp.Y>3 && DataBase.GetCountUniver())
				{
					cp.Y--; cp.X=0; 
					SetConsoleCursorPosition(h,cp); 
					DataBase.Selector(ST,SB,up,cp.Y); 
					cp.X=0;
					SetConsoleCursorPosition(h,cp); 
				}
				break;
			case 13: if (DataBase.GetCountUniver()-2) DataBase.ExtendedMenu(cp.Y-3);stop=true; break;
			case 27: exit(0);
			}
		}
		while (!stop);
	}
	while (1);
	_getch();
}
void DrawLine()
{
	int i=0;
	while (i++<80) cout<<'-';
}
void SetColor(ConsoleColor text, ConsoleColor background)
{
    SetConsoleTextAttribute(h, (WORD)((background << 4) | text));
}
void SetTitle(ConsoleColor text, ConsoleColor background,const char*s)
{
	SetColor(text,background);
	cp.X=0; cp.Y=0;
	SetConsoleCursorPosition(h,cp);
	SHORT len=strlen(s);
	cout<<s;
	while (len++<80) cout<<' ';
	DrawLine();
}
void SlidingHelp(ConsoleColor text, ConsoleColor background,const char*s,...)
{
	SetColor(text,background);
	cp.X=0; cp.Y=24;
	SetConsoleCursorPosition(h,cp);
	const char **p=&s;
	int count=0;
	SHORT len=0;
	while (*p) 
	{
		count++;
		len+=strlen(*p)+3;
		cout<<count<<'-'<<*p<<' ';
		p++;
		len=(len>80)?(len=0):len;
	}
	while (len++<80) cout<<' ';
	cp.X=0; cp.Y=0;
	SetConsoleCursorPosition(h,cp);
}
void StaticHelpString(ConsoleColor text, ConsoleColor background,const char*s,...)
{
	SetColor(text,background);
	cp.X=0; cp.Y=2;
	SetConsoleCursorPosition(h,cp);
	SHORT len=0;
	const char **p=&s;
	int count=0;
	while (*p) 
	{
		count++;
		len+=strlen(*p);
		cout<<*p;
		p++;
		len=(len>80)?(len=0):len;
	}
	while (len++<80) cout<<' ';
}
void MessageBox(ConsoleColor text, ConsoleColor background, SHORT height, SHORT width,const char*s)
{
	SetColor(text,background);
	cp.X=(80-width)/2; cp.Y=(25-height)/2;
	SetConsoleCursorPosition(h,cp);
	int i=cp.X;
	for (SHORT j=(25-height)/2;j<(25-(25-height)/2);j++)
	{
		cp.X=(80-width)/2;
		cp.Y=j;
		i=cp.X+1;
		SetConsoleCursorPosition(h,cp);
		cout<<'|';
		while (i++<(80-cp.X)-1) cout<<' ';
		cout<<'|';
	}
	cp.X=40-strlen(s)/2;
	cp.Y=(25-height)/2;
	SetConsoleCursorPosition(h,cp);
	cout<<s;
	cp.Y=(25-height)/2+1;
	SetConsoleCursorPosition(h,cp);
}
void HelloWindow()
{
	SetColor(ST,EB);
	clrscr;
	cp.X=40; cp.Y=13;
	SetConsoleCursorPosition(h,cp);
	SetColor(White,White);
	////// S ////////////
	int dx=7;
	int dy=-5;
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+12;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+13;
	for (cp.Y=dy+12;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+14;
	for (cp.X=dx+10;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// T ////////////
	dx+=5;
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+12;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// U ////////////
	dx+=6;
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+14;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+14;
	for (cp.X=dx+11;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+14;
	for (cp.Y=dy+10;cp.Y<dy+14;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// D ////////////
	dx+=6;
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+13;cp.Y=dy+11;SetConsoleCursorPosition(h,cp); cout<<'X';
	cp.X=dx+13;cp.Y=dy+12;SetConsoleCursorPosition(h,cp); cout<<'X';
	cp.X=dx+13;cp.Y=dy+13;SetConsoleCursorPosition(h,cp); cout<<'X';
	cp.Y=dy+14;
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// E ////////////
	dx+=5;
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+12;
	for (cp.X=dx+10;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+14;
	for (cp.X=dx+10;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// N ////////////
	dx+=6;
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+14;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+10;
	for (cp.X=dx+12;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// T ////////////
	dx+=6;
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+12;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// S ////////////
	dx+=6;
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+12;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+13;
	for (cp.Y=dy+12;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+14;
	for (cp.X=dx+10;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// DATABASE //////////////////
	dx=7;
	dy=3;
	////// D ////////////
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+13;cp.Y=dy+11;SetConsoleCursorPosition(h,cp); cout<<'X';
	cp.X=dx+13;cp.Y=dy+12;SetConsoleCursorPosition(h,cp); cout<<'X';
	cp.X=dx+13;cp.Y=dy+13;SetConsoleCursorPosition(h,cp); cout<<'X';
	cp.Y=dy+14;
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// A ////////////
	dx+=6;
	cp.Y=dy+10;
	for (cp.X=dx+11;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+10;
	for (cp.Y=dy+11;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+14;
	for (cp.Y=dy+11;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+12;
	for (cp.X=dx+11;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// T ////////////
	dx+=6;
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+12;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// A ////////////
	dx+=6;
	cp.Y=dy+10;
	for (cp.X=dx+11;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+10;
	for (cp.Y=dy+11;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+14;
	for (cp.Y=dy+11;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+12;
	for (cp.X=dx+11;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// B ////////////
	dx+=6;
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+13;cp.Y=dy+11;SetConsoleCursorPosition(h,cp); cout<<'X';
	cp.X=dx+13;cp.Y=dy+12;SetConsoleCursorPosition(h,cp); cout<<'X';
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+13;cp.Y=dy+13;SetConsoleCursorPosition(h,cp); cout<<'X';
	cp.Y=dy+14;
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// A ////////////
	dx+=5;
	cp.Y=dy+10;
	for (cp.X=dx+11;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+10;
	for (cp.Y=dy+11;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+14;
	for (cp.Y=dy+11;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+12;
	for (cp.X=dx+11;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// S ////////////
	dx+=6;
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+12;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	for (cp.X=dx+10;cp.X<dx+13;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.X=dx+13;
	for (cp.Y=dy+12;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+14;
	for (cp.X=dx+10;cp.X<dx+14;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// E ////////////
	dx+=5;
	cp.X=dx+10;
	for (cp.Y=dy+10;cp.Y<dy+15;cp.Y++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+10;
	for (cp.X=dx+10;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+12;
	for (cp.X=dx+10;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	cp.Y=dy+14;
	for (cp.X=dx+10;cp.X<dx+15;cp.X++) { SetConsoleCursorPosition(h,cp); cout<<'X';}
	////// SIGN ////////////
	SetColor(UT,EB);
	cp.X=37; cp.Y=24;
	SetConsoleCursorPosition(h,cp);
	cout<<"опхяъфмши л.а. 2011 Ц. STUDENTS DATABASE";
	printf("%c%c%c",'(',169,')');
	cp.X=0; cp.Y=0;
	SetConsoleCursorPosition(h,cp);
	Sleep(1000);
}