#include <Windows.h>
#include <iostream>
#include <conio.h>
#include "Main.h"
#define clrscr system("cls")
// COLOR DEFINES
#define UB DarkGray // USER BACKGROUND
#define UT White // USER TEXT
#define MBB Black // MESSAGE BOX BACKGROUND
#define TB Blue // TITLE/HELPER BACKGROUND
#define SB LightCyan // SELECTOR BACKGROUND
#define ST Black // SELECTOR TEXT
#define HB Magenta // EXTENDED HELPER BACKGROUND
#define EB Magenta // EDITOR BACKGROUND
using namespace std;
enum direction {up,down};
struct Stud
{
	char* ThirdName,* FirstName,* SecondName,* JobType, *Faculty;
	int Salary;
	Stud()
	{
		ThirdName=new char[80];
		FirstName=new char[80];
		SecondName=new char[80];
		Faculty=new char[80];
		JobType=new char[80];
	}
};
class Folder
{
protected:
	const char* path;
	wchar_t* wpath;
	HANDLE handle;
	HANDLE h;
	COORD cp;
	WIN32_FIND_DATA wfd;
	int CountUniver,CountStudent;
	FILE* file;
	Stud BuffStr;
	char* a[6];
public:
	Folder()
	{
		a[0]="‘¿Ã»À»ﬂ";
		a[1]="»Ãﬂ";
		a[2]="Œ“◊≈—“¬Œ";
		a[3]="‘¿ ”À‹“≈“";
		a[4]="“»œ –¿¡Œ“";
		a[5]="«¿–œÀ¿“¿";
		path="Data\\";
		wpath=new wchar_t[80];
		mbstowcs(wpath,path,80);
		h=GetStdHandle(STD_OUTPUT_HANDLE);
		CreateDirectory(wpath,NULL);
	}
	SHORT GetCountUniver()
	{
		return CountUniver+2;
	}
	void Selector(ConsoleColor, ConsoleColor, direction, SHORT);
	void CreateFolder(char* name);
	void RemoveFolder(int pos);
	void RenameFolder(char* name,int pos);
	void ShowFolderList();
};
class File: public Folder
{
public:
	void ExtendedMenu(SHORT pos);
	void ExtendedSelector(char* path2,ConsoleColor, ConsoleColor, direction, SHORT);
	void ExtraSelector(ConsoleColor, ConsoleColor,direction, SHORT);
	void CreateFile(char* path2,char* name,int pos);
	void RemoveFile(char* path2,int pos);
	void RenameFile(char* path2,char* name,int pos);
	void ShowFileList(char* path2);
	void ShowFile(char* path2,int pos);
	void ChangeFileAll(char* path2);
	void ChangeFileItem(char* path2,int pole);
};