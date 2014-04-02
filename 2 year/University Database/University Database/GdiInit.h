#include <GdiPlus.h>
#include <Windows.h>
#pragma comment(lib,"GdiPlus.lib")
#pragma once
using namespace Gdiplus;
/////////////////////////////////////////////////////////////////////////////////////////////////////
class CGdiPlusInit  
{
public:
	CGdiPlusInit();
	virtual ~CGdiPlusInit();
	bool Good(){ return present; }
private:
	bool present;
	ULONG_PTR token;
};
/////////////////////////////////////////////////////////////////////////////////////////////////////
CGdiPlusInit::CGdiPlusInit()
{
	present=true;
	Gdiplus::GdiplusStartupInput input;
	Gdiplus::GdiplusStartup(&token, &input, 0);
}
CGdiPlusInit::~CGdiPlusInit()
{
	if(present) Gdiplus::GdiplusShutdown(token);
}

