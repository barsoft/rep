#include "Main.h"
#include <conio.h>
#define clrscr system("cls")
void main()
{
	char key;
	setlocale(0,"Rus");
	SpisokCSExtended<char> s;
	SpisokCSExtended<char> t;
	SpisokCSExtended<char> u;
	SpisokCSExtended<char> Res;
	do
	{
		clrscr;
		cout <<"1) �������� ������������������ s\n";
		cout <<"2) �������� ������������������ t\n";
		cout <<"3) �������� ������������������ u\n";
		cout <<"4) ��������\n";
		cout<<"ESC-�����";
		for (int i=0;i<5;i++) cout<<arr[i]<<' ';
		do
			key=_getch();
		while (key!=27 && key!='1' && key!='2'&& key!='3' && key!='4');
		switch (key)
		{
		case '1': clrscr; MainMenu(s); break;
		case '2': clrscr; MainMenu(t); break;
		case '3': clrscr; MainMenu(u); break;
		case '4': clrscr; 
			cout<<"������������������ S:\n";
			s.PrintSpisok();
			cout<<endl;
			DrawLine();
			cout<<"������������������ T:\n";
			t.PrintSpisok();
			cout<<endl;
			DrawLine();
			cout<<"������������������ U:\n";
			u.PrintSpisok();
			cout<<endl;
			DrawLine();
			cout<<"�����������:\n";
			(s*t*u).PrintSpisok();
			WaitForPress(key); 
			break;
		case 27: exit(0);
		}
	}
	while (1);
	_getch();
}
void MainMenu(SpisokCSExtended<char>& ClassEX)
{
	clrscr;
	char key;
	bool stop;
	do
	{
		stop=false;
		clrscr;
		cout<<"1) ������� ������";
		DrawLine();
		cout<<"2) �������� ������ ->";
		DrawLine();
		cout<<"3) �������� ������ <-";
		DrawLine();
		cout<<"4) �������� ������� � ������";
		DrawLine();
		cout<<"5) ������� ������";
		DrawLine();
		cout<<"6) ������� �������";
		DrawLine();
		cout<<"7) ����� �������� � ������";
		DrawLine();
		cout<<"8) �������� �������� ��������";
		DrawLine();
		cout<<"9) �������� ������� ��������";
		DrawLine();
		cout<<"ESC - �����";
		DrawLine();
		do
		key=_getch();
		while (key!=27 && key!='1' && key!='2'&& key!='3' && key!='4'&& key!='5' && key!='6'&& key!='7'&& key!='8'&& key!='9');
		switch (key)
		{
		case '1': clrscr; ClassEX.CreateSpisok(); WaitForPress(key); break;
		case '2': clrscr; ClassEX.PrintSpisok(); WaitForPress(key); break;
		case '3': clrscr; ClassEX.PrintSpisokReverse(); WaitForPress(key); break;
		case '4': clrscr; ClassEX.AddElement(); WaitForPress(key); break;
		case '5': clrscr; ClassEX.DeleteSpisok(); WaitForPress(key); break;
		case '6': clrscr; ClassEX.DeleteElement(); WaitForPress(key); break;
		case '7': clrscr; ClassEX.Search(); WaitForPress(key); break;
		case '8': clrscr; ClassEX.ChangeVal(); WaitForPress(key); break;
		case '9': clrscr; ClassEX.ChangePos(); WaitForPress(key); break;
		case 27: stop=true;
		}
	}
	while (!stop);
}
void DrawLine()
{
	cout<<'\n';
	for (int i=0;i<80;i++)
	{
		cout<<'.';
	}
}
void WaitForPress(char key)
{
	DrawLine();
	cout<<"������� ESC ��� ��������";
	do
		key=_getch();
	while (key!=27);
}