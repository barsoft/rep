#pragma once
#include <iostream>
#include <Windows.h>
#include <conio.h>
using namespace std;
HANDLE MyCon=GetStdHandle(STD_OUTPUT_HANDLE);
enum GlobalState
{
	NULLSTATE,
	UNIVERSITYSTATE,
	FACULTYSTATE,
	CAFEDRASTATE
};
GlobalState gs=NULLSTATE;
enum _Color // 16 BIT COLOR TYPE DEFINITION
{
	Black         = 0,
	Blue          = 1,
	Green         = 2,
	Cyan          = 3,
	Red           = 4,
	Magenta       = 5,
	Brown         = 6,
	LightGray     = 7,
	DarkGray      = 8,
	LightBlue     = 9,
	LightGreen    = 10,
	LightCyan     = 11,
	LightRed      = 12,
	LightMagenta  = 13,
	Yellow        = 14,
	White         = 15
};
void SetColor(_Color text, _Color background)
{
	SetConsoleTextAttribute(MyCon,background<<4 | text);
}
void PaintInputWindow(char * message)
{
	SetColor(Black,White);
	COORD c={3,6};
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<25; i++)
	{
		for (int j=0;j<64; j++)
		{
			cout<<' ';
		}
		c.Y++;
		SetConsoleCursorPosition(MyCon, c);
	}
	c.X=8;
	c.Y=7;
	SetConsoleCursorPosition(MyCon, c);
	SetColor(Black,White);
	cout<<message;
	c.X=8;
	c.Y=8;
	SetColor(Black,LightGray);
	SetConsoleCursorPosition(MyCon, c);
	for (int i=0;i<29; i++)
	{
		cout<<' ';
	}
	c.X=8;
	c.Y=8;
	SetConsoleCursorPosition(MyCon, c);
}
char* ToString(int i)
{
	char *s=new char[80];
	sprintf(s,"%d",i);
	return s;
}
struct ConsoleRect
{
	int left,right,top,bottom;
};
ConsoleRect cr;
enum ArrowState
{
	ArrowNULL,
	ArrowUp,
	ArrowDown
};