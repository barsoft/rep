#include "Console.h"
void Console::SetColor(_Color text, _Color background)
{
	SetConsoleTextAttribute(handle,background<<4 | text);
}


HWND Console::GetConsoleHwnd(void)
{
    HWND hwndFound;        
    char * pszNewWindowTitle="STUDENTS DATABASE";
    SetConsoleTitle(pszNewWindowTitle);
    Sleep(1);
    hwndFound=FindWindow(NULL, pszNewWindowTitle);
    return(hwndFound);
} 
