#include <Windows.h>
void MainMenu();
void DrawLine();
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
void SetColor(ConsoleColor, ConsoleColor);
void SetTitle(ConsoleColor, ConsoleColor,const char*);
void SlidingHelp(ConsoleColor, ConsoleColor,const char*,...);
void StaticHelpString(ConsoleColor, ConsoleColor,const char*,...);
void MessageBox(ConsoleColor, ConsoleColor, SHORT, SHORT,const char*);
void HelloWindow();