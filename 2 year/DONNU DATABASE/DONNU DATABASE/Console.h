#include <windows.h>
#include <iostream>
#include <conio.h>
#include "Colors.h"
using namespace std;

class Console
{
private:
public:
	HANDLE handle;
	Console(HANDLE h)
	{
		handle=h;
	}
	Console()
	{
	}
	HWND GetConsoleHwnd(void);
	void SetColor(_Color, _Color);
};
