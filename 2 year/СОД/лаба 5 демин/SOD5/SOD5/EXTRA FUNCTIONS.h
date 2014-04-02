#include <iostream>
#include <conio.h>
#include <Windows.h>
#define clrscr system("cls");
using namespace std;
enum ConsoleColor // 16 BIT COLOR TYPE DEFINITION
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
void DrawLine();// DRAWS LINE
void WaitForPress();// WAITS FOR PRESS
void SetColor(ConsoleColor text, ConsoleColor background);// SETS TEXT AND BACKGROUND COLOR
void InputErrorMsg(); // DISPLAYS INPUT ERROR MESSAGE