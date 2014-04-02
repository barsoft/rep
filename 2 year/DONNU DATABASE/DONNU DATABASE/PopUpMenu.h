#include "Buttons.h"
COORD global={0,0};
class PopUpMenu
{
	Console con;
	int x;
	_Button arr[15];
	int y;
	int length;
	int count;
public:
	PopUpMenu(Console _con,int _x,int _y,int _length,_Button _arr[] ,int _count)
	{
		con=_con; x=_x; y=_y;  length=_length; count=_count;
		int i=0;
		while (i<count)
		{
			arr[i]=_arr[i];
			i++;
		}
	}
	PopUpMenu()
	{
	}
	void Create();
	void OnPaint(bool);
	void Fill(Console _con,int _x,int _y,int _length,_Button _arr[] ,int _count);
};

void PopUpMenu::Create()
{	
	con.SetColor(Red,Green);
	int i=0;
	COORD c={x,y};
	while (i<count+1) 
	{
		SetConsoleCursorPosition(con.handle, c);
		int i1=0;
		while (i1<length ) {cout<<" "; i1++;}
		c.Y++;
		i++;
	}
	i=0;
	while (i<count-1) 
	{
		SetConsoleCursorPosition(con.handle, c);
		c.Y++;
		i++;
		arr[i].Create();
	}
}
void PopUpMenu::OnPaint(bool paint)
{
	con.SetColor(Red,Black);
	int i=0;
	COORD c={x,y};
	if (paint)
	{
		while (i<count+1) 
		{
			SetConsoleCursorPosition(con.handle, c);
			int i1=0;
			while (i1<length ) {cout<<"_"; i1++;}
			c.Y++;
			i++;
		}
		i=0;
		while (i<count) 
		{
			SetConsoleCursorPosition(con.handle, c);
			c.Y++;
			arr[i].OnPaint();
			i++;
		}
		c.X=global.X;
		c.Y=global.Y;
		SetConsoleCursorPosition(con.handle, c);
	}
	else
	{
		COORD c={x,y};
		con.SetColor(tc,bc);
		while (i<count+1) 
		{
			
			SetConsoleCursorPosition(con.handle, c);
			int i1=0;
			while (i1<length ) {cout<<" "; i1++;}
			c.Y++;
			i++;
		}
		c.X=global.X;
		c.Y=global.Y;
		SetConsoleCursorPosition(con.handle, c);
	}
}
void PopUpMenu::Fill(Console _con,int _x,int _y,int _length,_Button _arr[] ,int _count)
{
	con=_con; x=_x; y=_y;  length=_length; count=_count;
		int i=0;
		while (i<count)
		{
			arr[i]=_arr[i];
			i++;
		}
}