#include "EXTRA FUNCTIONS.h"
// EXTRA FUNCTIONS
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
// DISPLAYS INPUT ERROR MESSAGE
void InputErrorMsg()
{
	if (!cin) 
		{
			cout<<"\n������ �����!!! ��������� �� ��������\n";
			cout<<"\n������� ����� �������...";
			_getch();
			exit(0);
		}
}