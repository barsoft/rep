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
// DISPLAYS INPUT ERROR MESSAGE
void InputErrorMsg()
{
	if (!cin) 
		{
			cout<<"\nÎØÈÁÊÀ ÂÂÎÄÀ!!! ÏÐÎÃÐÀÌÌÀ ÍÅ ÎÒÂÅ×ÀÅÒ\n";
			cout<<"\nÍÀÆÌÈÒÅ ËÞÁÓÞ ÊËÀÂÈØÓ...";
			_getch();
			exit(0);
		}
}