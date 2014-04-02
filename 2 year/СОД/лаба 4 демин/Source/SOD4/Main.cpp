/////////////////////////////////////////////////////////////////////////////////////////////////////
// MAIN.CPP contains main functions implementation ////// ///////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
#include "SPISOK.h"
#include "Main.h"

void main()
{
	SetColor(Blue,White);
	Spisok StudentCSExemplar;	
	MainMenu(StudentCSExemplar);
	_getch();
}
// CALL POINT
void MainMenu(Spisok & ListCS)
{
	char Key;
	do
	{
		clrscr;
		setlocale(0,"Rus");
		cout<<"STUDENTS DATABASE | ���� ������ � ���������\n";
		DrawLine();
		cout<<"1) ������ ���������� � ���������� (� ����������� �������� ������)\n";
		DrawLine();
		cout<<"2) �������� ������� ����������\n";
		DrawLine();
		cout<<"3) ������� ���� (� ����������� �������� ������)\n";
		DrawLine();
		cout<<"4) ��������� ������� ���������� � ����\n";
		DrawLine();
		cout<<"5) �������� ��� �������\n";
		DrawLine();
		cout<<"6) ������� ������� ����������\n";
		DrawLine();
		cout<<"7) �������� �������������� ����\n";
		DrawLine();
		cout<<"8) �������� ������\n";
		DrawLine();
		cout<<"ESC - �����\n";
		DrawLine();
		Key=_getch();
		switch(Key)
		{
		case '1':ListCS.InputConsole();	WaitForPress();break;
		case '2':ListCS.OutputConsole();	WaitForPress();break;
		case '3':ListCS.InputFile();	WaitForPress();break;
		case '4':ListCS.OutputFile();	WaitForPress();break;
		case '5':
			clrscr;
			cout<<"1) ����������� ����������\n";
			DrawLine();
			cout<<"2) ����� ��������\n";
			DrawLine();
			cout<<"3) �������� ��������\n";
			DrawLine();
			cout<<"4) ������ ������ ���������\n";
			DrawLine();
			cout<<"5) �������� ������� ��������� � ������\n";
			DrawLine();
			cout<<"6) ���������� �������� � �������\n";
			DrawLine();
			cout<<"ESC - �������\n";
			DrawLine();
			do
				Key=_getch();
			while (Key!='1' && Key!='2' && Key!='3' && Key!='4' && Key!='5' && Key!='6' && Key!=27);
			switch(Key)
			{
			case '1':ListCS.Sort();WaitForPress();break;
			case '2':ListCS.Search();WaitForPress();break;
			case '3':	
				clrscr;
				cout<<"1) �������� � ����� ������\n";
				DrawLine();
				cout<<"2) �������� � ������ ������\n";
				DrawLine();
				cout<<"3) �������� � �������\n";
				DrawLine();						
				cout<<"ESC - �������\n";
				DrawLine();
				Key=_getch();
				switch(Key)
				{
				case '1':ListCS.DobavlElemVKonets();	WaitForPress();break;
				case '2':ListCS.DobavlElemVNach();	WaitForPress();break;
				case '3':ListCS.DobavlElemVPosiziy();	WaitForPress();break;
				}														
				break;
			case '4':ListCS.Reverse();WaitForPress();break;
			case '5':ListCS.Rakirovka();WaitForPress();break;
			case '6':ListCS.CopyTo();WaitForPress();break;
			}
			break;
		case '6':
			clrscr;
			cout<<"1) ������� ���������� � ���������� ��������\n";
			DrawLine();
			cout<<"2) ������� ���������� � ���� ����� ������� (CAR)\n";
			DrawLine();
			cout<<"3) ������� ���������� � ������ �������� (CDR)\n";
			DrawLine();
			cout<<"4) ������� ��� ���������� (CADR)\n";
			DrawLine();
			cout<<"5) ������� ��� ���������� (������������ ������)\n";
			DrawLine();				
			cout<<"ESC - �������\n";
			DrawLine();
			do
				Key=_getch();
			while (Key!='1' && Key!='2' && Key!='3' && Key!='4' &&Key!='5' && Key!=27);
			switch(Key)
			{
			case '1':ListCS.DeleteElem();	WaitForPress();break;
			case '2':ListCS.ViborFirst();	WaitForPress();break;
			case '3':ListCS.DeleteFirst();	WaitForPress();break;
			case '4':ListCS.ViborAndDeleteFirst();	WaitForPress();break;
			case '5':ListCS.ResetAll();	WaitForPress();break;
			case 27:break;
			}				
			break;
		case '7':
			clrscr;
			cout<<"1) �������� ��� ����\n";
			DrawLine();
			cout<<"2) �������� ���� ����\n";
			DrawLine();
			cout<<"ESC - �������\n";
			DrawLine();
			do
				Key=_getch();
			while (Key!='1' && Key!='2' && Key!=27);
			switch(Key)
			{
			case '1':ListCS.ChangeAllFields();WaitForPress();break;
			case '2':ListCS.ChangeVal();WaitForPress();break;
			case 27:break;
			}
			break;
		case '8':ListCS.Sound();break;
		case 27:exit(0);
		}
	}
	while (1);
}
// DRAWS LINE
void DrawLine()
{
	for(int i=0;i<80;i++)
		cout<<'_';
}
// WAITS FOR PRESS
void WaitForPress()
{
	cout<<"\n������� ESC ��� ��������...";
	do{}
	while (_getch()!=27);
}
// SETS TEXT AND BACKGROUND COLOR
void SetColor(ConsoleColor text, ConsoleColor background)
{
	HANDLE hConsoleOutput;
	hConsoleOutput = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsoleOutput, (WORD)((background << 4) | text));
}