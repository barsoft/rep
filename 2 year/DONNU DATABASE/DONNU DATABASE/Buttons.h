#include <windows.h>
#include <iostream>
#include <conio.h>
#include "Console.h"
using namespace std;
RECT r;
POINT p;
#define xCoord (p.x-r.left-9)/8
#define yCoord (p.y-r.top-30)/12

struct _ButtonAttributes
{
	int x;
	int y;
	int length;
	_Color Bkc;
	_Color Textc;
	string text;
};
class _Button
{
private:
	friend class PopUpMenu;
	int length;
	char *s;
	Console con;
	POINT p;
public:
	_ButtonAttributes ba;
	_Button(Console _con,int _x,int _y,int _length,_Color _Bkcolor,_Color _Textcolor,string _text)
	{
		ba.x=_x; ba.y=_y; ba.Bkc=_Bkcolor; ba.Textc=_Textcolor; ba.text=_text; length=_length; con=_con;
	}
	_Button(){}
	void Create(Console _con,int _x,int _y,int _length,_Color _Bkcolor,_Color _Textcolor,string _text);
	void Fill(Console _con,int _x,int _y,int _length,_Color _Bkcolor,_Color _Textcolor,string _text);
	void Create();
	bool OnFocus();
	bool OnPaint();
};
bool _Button::OnFocus()
{
	GetCursorPos(&p);
	if (xCoord<length+ba.x  && yCoord==ba.y && xCoord>ba.x) return true;
	else return false;
}
bool _Button::OnPaint()
{
	if (this->OnFocus() ) 
	{
		con.SetColor(Yellow,DarkGray);
	}
	else 
	if (!(this->OnFocus()) ) 
	{
		con.SetColor(ba.Textc,ba.Bkc);
	}
	COORD c={ba.x,ba.y};
	SetConsoleCursorPosition(con.handle, c);
	int i=0;
	while (i<length ) {cout<<" "; i++;}
	SetConsoleCursorPosition(con.handle, c);
	cout<<ba.text.data();
	con.SetColor(tc,bc);
	return true;
}
void _Button::Create(Console _con,int _x,int _y,int _length,_Color _Bkcolor,_Color _Textcolor,string _text)
{
	s=new char[80];
	ba.x=_x; ba.y=_y; ba.Bkc=_Bkcolor; ba.Textc=_Textcolor; ba.text=_text; length=_length; con=_con;
	con.SetColor(ba.Textc,ba.Bkc);
	COORD c={ba.x,ba.y};
	SetConsoleCursorPosition(con.handle, c);
	int i=0;
	while (i<length ) {cout<<" "; i++;}
	SetConsoleCursorPosition(con.handle, c);
	cout<<ba.text.data();
	con.SetColor(tc,bc);
}
void _Button::Create()
{
	con.SetColor(ba.Textc,ba.Bkc);
	COORD c={ba.x,ba.y};
	SetConsoleCursorPosition(con.handle, c);
	int i=0;
	while (i<length ) {cout<<" "; i++;}
	SetConsoleCursorPosition(con.handle, c);
	cout<<ba.text.data();
	con.SetColor(tc,bc);
}
void _Button::Fill(Console _con,int _x,int _y,int _length,_Color _Bkcolor,_Color _Textcolor,string _text)
{
	ba.x=_x; ba.y=_y; ba.Bkc=_Bkcolor; ba.Textc=_Textcolor; ba.text=_text; length=_length; con=_con;
}