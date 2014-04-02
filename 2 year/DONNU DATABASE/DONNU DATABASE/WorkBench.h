#include "Menu.h"
char *name=new char[80];
char *s=new char[80];
static bool _inputUniversityName=false;
static bool _inputStudentFirstName=false;
static bool _inputStudentSecondName=false;
static bool _inputStudentThirdName=false;
bool FacultyList=false;
bool workbench=false;
INPUT_RECORD console1;
_CONSOLE_SELECTION_INFO console2;
HWND wh=console.GetConsoleHwnd();
COORD c={1,2};
HANDLE input  = GetStdHandle(STD_INPUT_HANDLE);
HANDLE output = GetStdHandle(STD_OUTPUT_HANDLE);
void clrscr(void);

class WorkBench
{
public:
	_Button AddStudent;
	_Button AddFaculty;
	void PaintDatabaseWindow();
	void PaintInputWindow(char * message,bool &inputVarType);
};
void WorkBench::PaintDatabaseWindow()
{
	paintMenuFile=NULL;
	paintMenuEdit=NULL;
	console.SetColor(Black,White);
	COORD c={3,6};
	SetConsoleCursorPosition(console.handle, c);
	for (int i=0;i<5; i++)
	{
		for (int j=0;j<70; j++)
		{
			cout<<' ';
		}
		c.Y++;
		SetConsoleCursorPosition(console.handle, c);
	}
	console.SetColor(Black,White);
	sprintf(s,"%s%s",name," university:");
	c.X=23-strlen(s)/2;
	c.Y=7;
	SetConsoleCursorPosition(console.handle, c);
	cout<<s;
	c.X=7;
	c.Y=8;
	SetConsoleCursorPosition(console.handle, c);
	printf("%c",218);
	for (int i=0;i<60; i++)
	{
		printf("%c",196);
	}
	printf("%c",191);
	for (int i=0;i<20; i++)
	{
		c.Y++;
		SetConsoleCursorPosition(console.handle, c);
		printf("%c",179);
	}
	c.Y=28;
	SetConsoleCursorPosition(console.handle, c);
	printf("%c",192);
	for (int i=0;i<60; i++)
	{
		printf("%c",196);
	}
	printf("%c",217);
	c.X=68;
	c.Y=8;
	SetConsoleCursorPosition(console.handle, c);
	for (int i=0;i<19; i++)
	{
		c.Y++;
		SetConsoleCursorPosition(console.handle, c);
		printf("%c",179);
	}
	global.X=8;
	global.Y=9;
	FacultyList=false;
	//AddStudent.Create(console,9,10,15,LightGray,Black,"add student...");
}
void WorkBench::PaintInputWindow(char * message,bool &inputVarType)
{
	paintMenuFile=NULL;
	paintMenuEdit=NULL;
	console.SetColor(Black,White);
	COORD c={3,6};
	SetConsoleCursorPosition(console.handle, c);
	for (int i=0;i<25; i++)
	{
		for (int j=0;j<70; j++)
		{
			cout<<' ';
		}
		c.Y++;
		SetConsoleCursorPosition(console.handle, c);
	}
	c.X=8;
	c.Y=7;
	SetConsoleCursorPosition(console.handle, c);
	console.SetColor(Black,White);
	cout<<message;
	c.X=8;
	c.Y=8;
	console.SetColor(Black,LightGray);
	SetConsoleCursorPosition(console.handle, c);
	for (int i=0;i<29; i++)
	{
		cout<<' ';
	}
	global.X=8;
	global.Y=8;
	SetConsoleCursorPosition(console.handle, global);
	 inputVarType=true;
}