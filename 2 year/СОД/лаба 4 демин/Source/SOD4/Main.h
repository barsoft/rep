/////////////////////////////////////////////////////////////////////////////////////////////////////
// MAIN.CPP contains main functions interface  //////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
#include <conio.h>
#define clrscr system("cls");
// EXTRA MAIN FUNCTIONS
void MainMenu(Spisok &); // CALL POINT
void DrawLine(); // DRAWS LINE
void WaitForPress(); // WAITS FOR PRESS
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
void SetColor(ConsoleColor,ConsoleColor); // SETS TEXT AND BACKGROUND COLOR