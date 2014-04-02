#include <windows.h>
#include <iostream>
#include <conio.h>
using namespace std;
RECT r;
POINT p;
#define xCoord (p.x-r.left-9)/8.5
#define yCoord (p.y-r.top-30)/15
#define tc Blue
#define bc White
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
struct _ButtonAttributes
{
	int x;
	int y;
	int length;
	_Color Bkc;
	_Color Textc;
	char * text;
};

class _Button;

class Console
{
private:
public:
	HANDLE handle;
	Console(HANDLE h)
	{
		handle=h;
	}
	Console()
	{
	}
	HWND GetConsoleHwnd(void);
	void SetColor(_Color, _Color);
	_Color _GetBkColor();
	_Color _GetTextColor();
};

class _Button
{
private:

	int length;
	char *s;
	Console con;
	POINT p;
public:
	_ButtonAttributes ba;
	_Button(Console _con,int _x,int _y,int _length,_Color _Bkcolor,_Color _Textcolor,char *_text)
	{
		s=new char[80];
		ba.x=_x; ba.y=_y; ba.Bkc=_Bkcolor; ba.Textc=_Textcolor; ba.text=_text; length=_length; con=_con;
		con.SetColor(ba.Textc,ba.Bkc);
		COORD c={ba.x,ba.y};
		SetConsoleCursorPosition(con.handle, c);
		int i=0;
		while (i<length ) {cout<<" "; i++;}
		SetConsoleCursorPosition(con.handle, c);
		cout<<ba.text;
		con.SetColor(tc,bc);
	}
	bool OnFocus();
	bool OnPaint();
};

void Console::SetColor(_Color text, _Color background)
{
	SetConsoleTextAttribute(handle,background<<4 | text);
}


HWND Console::GetConsoleHwnd(void)
{
    HWND hwndFound;        
    char * pszNewWindowTitle="DONNU DATABASE";
    SetConsoleTitle(pszNewWindowTitle);
    Sleep(1);
    hwndFound=FindWindow(NULL, pszNewWindowTitle);
    return(hwndFound);
} 
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
		con.SetColor(Yellow,ba.Bkc);
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
	cout<<ba.text;
	con.SetColor(tc,bc);
	return true;
}