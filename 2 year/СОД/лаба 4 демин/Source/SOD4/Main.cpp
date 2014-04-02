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
		cout<<"STUDENTS DATABASE | ÁÀÇÀ ÄÀÍÍÛÕ Î ÑÒÓÄÅÍÒÀÕ\n";
		DrawLine();
		cout<<"1) ÂÂÅÑÒÈ ÈÍÔÎÐÌÀÖÈÞ Ñ ÊËÀÂÈÀÒÓÐÛ (Ñ ÏÅÐÅÇÀÏÈÑÜÞ ÒÅÊÓÙÅÃÎ ÑÏÈÑÊÀ)\n";
		DrawLine();
		cout<<"2) ÏÎÊÀÇÀÒÜ ÒÅÊÓÙÓÞ ÈÍÔÎÐÌÀÖÈÞ\n";
		DrawLine();
		cout<<"3) ÎÒÊÐÛÒÜ ÔÀÉË (Ñ ÏÅÐÅÇÀÏÈÑÜÞ ÒÅÊÓÙÅÃÎ ÑÏÈÑÊÀ)\n";
		DrawLine();
		cout<<"4) ÑÎÕÐÀÍÈÒÜ ÒÅÊÓÙÓÞ ÈÍÔÎÐÌÀÖÈÞ Â ÔÀÉË\n";
		DrawLine();
		cout<<"5) ÎÏÅÐÀÖÈÈ ÍÀÄ ÑÏÈÑÊÎÌ\n";
		DrawLine();
		cout<<"6) ÓÄÀËÈÒÜ ÒÅÊÓÙÓÞ ÈÍÔÎÐÌÀÖÈÞ\n";
		DrawLine();
		cout<<"7) ÈÇÌÅÍÈÒÜ ÈÍÔÎÐÌÀÖÈÎÍÍÛÅ ÏÎËß\n";
		DrawLine();
		cout<<"8) ÇÂÓÊÎÂÎÉ ÑÈÃÍÀË\n";
		DrawLine();
		cout<<"ESC - ÂÛÕÎÄ\n";
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
			cout<<"1) ÑÎÐÒÈÐÎÂÀÒÜ ÈÍÔÎÐÌÀÖÈÞ\n";
			DrawLine();
			cout<<"2) ÏÎÈÑÊ ÑÒÓÄÅÍÒÀ\n";
			DrawLine();
			cout<<"3) ÄÎÁÀÂÈÒÜ ÑÒÓÄÅÍÒÀ\n";
			DrawLine();
			cout<<"4) ÐÅÂÅÐÑ ÑÏÈÑÊÀ ÑÒÓÄÅÍÒÎÂ\n";
			DrawLine();
			cout<<"5) ÏÎÌÅÍßÒÜ ÌÅÑÒÀÌÈ ÑÒÓÄÅÍÒÎÂ Â ÑÏÈÑÊÅ\n";
			DrawLine();
			cout<<"6) ÊÎÏÈÐÎÂÀÒÜ ÑÒÓÄÅÍÒÀ Â ÏÎÇÈÖÈÞ\n";
			DrawLine();
			cout<<"ESC - ÂÎÇÂÐÀÒ\n";
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
				cout<<"1) ÄÎÁÀÂÈÒÜ Â ÊÎÍÅÖ ÑÏÈÑÊÀ\n";
				DrawLine();
				cout<<"2) ÄÎÁÀÂÈÒÜ Â ÍÀ×ÀËÎ ÑÏÈÑÊÀ\n";
				DrawLine();
				cout<<"3) ÄÎÁÀÂÈÒÜ Â ÏÎÇÈÖÈÞ\n";
				DrawLine();						
				cout<<"ESC - ÂÎÇÂÐÀÒ\n";
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
			cout<<"1) ÓÄÀËÈÒÜ ÈÍÔÎÐÌÀÖÈÞ Î ÊÎÍÊÐÅÒÍÎÌ ÑÒÓÄÅÍÒÅ\n";
			DrawLine();
			cout<<"2) ÓÄÀËÈÒÜ ÈÍÔÎÐÌÀÖÈÞ Î ÂÑÅÕ ÊÐÎÌÅ ÏÅÐÂÎÃÎ (CAR)\n";
			DrawLine();
			cout<<"3) ÓÄÀËÈÒÜ ÈÍÔÎÐÌÀÖÈÞ Î ÏÅÐÂÎÌ ÑÒÓÄÅÍÒÅ (CDR)\n";
			DrawLine();
			cout<<"4) ÓÄÀËÈÒÜ ÂÑÞ ÈÍÔÎÐÌÀÖÈÞ (CADR)\n";
			DrawLine();
			cout<<"5) ÓÄÀËÈÒÜ ÂÑÞ ÈÍÔÎÐÌÀÖÈÞ (ÎÑÂÎÁÎÆÄÅÍÈÅ ÏÀÌßÒÈ)\n";
			DrawLine();				
			cout<<"ESC - ÂÎÇÂÐÀÒ\n";
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
			cout<<"1) ÈÇÌÅÍÈÒÜ ÂÑÅ ÏÎËß\n";
			DrawLine();
			cout<<"2) ÈÇÌÅÍÈÒÜ ÎÄÍÎ ÏÎËÅ\n";
			DrawLine();
			cout<<"ESC - ÂÎÇÂÐÀÒ\n";
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
	cout<<"\nÍÀÆÌÈÒÅ ESC ÄËß ÂÎÇÂÐÀÒÀ...";
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