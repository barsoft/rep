#include "Main.h"
#include "MULTI SPISOK.h"
void MainMenu(MultiSpisok &); // OVERLOAD MAIN FUNCTION
void main()
{
	SetColor(Blue,White);
	MultiSpisok ListCS;
	MainMenu(ListCS);
}
void MainMenu(MultiSpisok & MultiListCS) // OVERLOAD MAIN FUNCTION
{
	char Key1;
	do
	{
		clrscr;
		setlocale(0,"Rus");
		cout<<"STUDENTS DATABASE | ���� ������ � ��������� ������������� �������\n";
		DrawLine();
		cout<<"1) ������� � ������ �������������\n";
		DrawLine();
		cout<<"2) ������ ���������� � ���������� (� ����������� �������� ������)\n";
		DrawLine();
		cout<<"3) �������� ������ �������������\n";
		DrawLine();
		cout<<"4) ������� ���� (� ����������� �������� ������ �������������)\n";
		DrawLine();
		cout<<"5) ��������� ������ �������������\n";
		DrawLine();
		cout<<"6) �������� ��� ������� �������������\n";
		DrawLine();
		cout<<"7) ������� ������� ����������\n";
		DrawLine();
		cout<<"8) �������� ������\n";
		DrawLine();
		cout<<"ESC - �����\n";
		Key1=_getch();
		switch(Key1)
		{
		case '1':MultiListCS.SelectUniversity();	break;
		case '2':MultiListCS.InputConsole();	WaitForPress();break;
		case '3':MultiListCS.OutputConsole();	WaitForPress();break;
		case '4':MultiListCS.InputFile();	WaitForPress();break;
		case '5':MultiListCS.OutputFile();	WaitForPress();break;
		case '6':
			clrscr;
			cout<<"1) ����� ��������\n";
			DrawLine();
			cout<<"2) �������� ����������� � �������\n";
			DrawLine();
			cout<<"ESC - �������\n";
			DrawLine();
			do
				Key1=_getch();
			while (Key1!='1' && Key1!='2'&& Key1!=27);
			switch (Key1)
			{			
			case '1':MultiListCS.Search();	WaitForPress();break;
			case '2':MultiListCS.DobavlElemVPosiziy();	WaitForPress();break;
			}
			break;
		case '7':
			clrscr;
			cout<<"1) ������� ���������� � ���������� ������������\n";
			DrawLine();
			cout<<"2) ������� ��� ���������� (������������ ������)\n";
			DrawLine();
			cout<<"ESC - �������\n";
			DrawLine();
			do
				Key1=_getch();
			while (Key1!='1' && Key1!='2'&& Key1!=27);
			switch (Key1)
			{
			case '1':MultiListCS.DeleteElem();	WaitForPress();break;
			case '2':MultiListCS.ResetAll();	WaitForPress();break;
			}
		case '8':MultiListCS.Sound();break;
		case 27:exit(0);
		}
	}
	while (1);
}