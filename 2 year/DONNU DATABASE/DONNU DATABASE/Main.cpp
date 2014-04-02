#include "FacultyEntity.h"
//#define _WIN32_WINNT 0x0555
FacultyEntity facultyList;
bool _paintFacultyList=false;
void PaintDatabaseWindow();
void mouse(const MOUSE_EVENT_RECORD *Event)
{
    if (Event->dwButtonState & FROM_LEFT_1ST_BUTTON_PRESSED)
    {
		if (menu.FileMenu.OnFocus())
		{
			paintMenuFile=!paintMenuFile;
			paintMenuEdit=NULL;
		}
		else if (menu.ExitButton.OnFocus())
			ExitProcess(0);
		else if (menu.NewButton.OnFocus())
		{
		
			wb.PaintInputWindow("Input name of the university:",_inputUniversityName);
		}
		else if (menu.EditMenu.OnFocus())
		{
			paintMenuEdit=!paintMenuEdit;
			paintMenuFile=NULL;
		}
		else if (wb.AddStudent.OnFocus())
		{
			
		//	studentList.InputStudentToEnd();
		//	FacultyList=true;
		}
		else if (wb.AddFaculty.OnFocus())
		{	
			facultyList.InputFacultyToEnd();
			FacultyList=true;
		}
    }
	else if (menu.EditMenu.OnFocus())
	{
		if (paintMenuFile) paintMenuEdit=TRUE;
		paintMenuFile=NULL;
	}
	else if (menu.FileMenu.OnFocus())
	{
		if (paintMenuEdit) paintMenuFile=TRUE;
		paintMenuEdit=NULL;
	}
	else if (FacultyList)
	{
		
		static int c=0;
		wb.PaintDatabaseWindow();
		
		if (c>0) 
		{	
			_paintFacultyList=true;
		}
		else wb.AddFaculty.Create(console,9,10,15,LightGray,Black,"add faculty...");
		workbench=true;
		c++;
	}
}
void keyboard(const KEY_EVENT_RECORD *Event)
{
	if (!FacultyList && Event->wVirtualKeyCode == VK_F1) wb.PaintInputWindow("Input name of the university:",_inputUniversityName);
    if (Event->wVirtualKeyCode == VK_ESCAPE)
    {
        ExitProcess(0);
    }
}
int main()
{    
	console.SetColor(tc,bc);
	MoveWindow(wh,GetSystemMetrics(SM_CXSCREEN)/2-200,GetSystemMetrics(SM_CYSCREEN)/2-240,700,480,1);
	ShowWindow(wh,5);
	clrscr();
	menu.Create();
	
    while(1)
	{
        GetWindowRect(wh, &r);
		DWORD n;
        ReadConsoleInput(input, &console1, 1, &n);
		menu.OnPaint();
		if (workbench)
			wb.AddFaculty.OnPaint();
		if (_paintFacultyList) facultyList.OnPaint();
		int i=0;
		if (_inputUniversityName)
		{
			console.SetColor(Black,LightGray);
			cin>>name;

			_inputUniversityName=false;
			FacultyList=true;
		}
        switch (console1.EventType)
        {
            case KEY_EVENT:
                keyboard(&console1.Event.KeyEvent);
                break;
            case MOUSE_EVENT:
                mouse(&console1.Event.MouseEvent);
                break;
				return 0;
          //case WINDOW_BUFFER_SIZE_EVENT: break;
          //case FOCUS_EVENT             : break;
          //case MENU_EVENT              : break;
        }
		
		Sleep(100);
    }
}
void clrscr(void)
{
    COORD coords = {0, 0};
    HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_SCREEN_BUFFER_INFO csbi;
    GetConsoleScreenBufferInfo(handle, &csbi);
    DWORD sz = csbi.dwSize.X * csbi.dwSize.Y;
    DWORD dw;
    FillConsoleOutputCharacter(handle, ' ', sz, coords, &dw);
    FillConsoleOutputAttribute(handle, csbi.wAttributes, sz, coords, &dw);
}
