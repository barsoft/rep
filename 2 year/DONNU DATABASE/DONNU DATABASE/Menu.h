#include "PopUpMenu.h"
static bool paintMenuFile;
static bool paintMenuEdit;
static bool paintMenuHelp;
Console console(GetStdHandle(STD_OUTPUT_HANDLE));
class _Menu
{
private: 
	Console con;
public:
	_Button FileMenu;
		PopUpMenu PopUpMenuFile; 	
		_Button NewButton;
		_Button OpenButton;
		_Button SaveButton;
		_Button ExitButton;
		_Button FileButtonsArr[4];
	_Button EditMenu;
		PopUpMenu PopUpMenuEdit; 
		_Button ResetButton;
		_Button EditButtonArr[1];
	
		
	_Button HelpMenu;
	
	
	_Menu(Console _con)
	{
		con=_con;
	}
	void OnPaint();
	void Create();
};
void _Menu::OnPaint()
{
	FileMenu.OnPaint();
	EditMenu.OnPaint();
	HelpMenu.OnPaint();
	PopUpMenuFile.OnPaint(paintMenuFile);
	PopUpMenuEdit.OnPaint(paintMenuEdit);
}
void _Menu::Create()
{
	con.SetColor(Red,LightGray);
	COORD c={0,0};
	SetConsoleCursorPosition(con.handle, c);
	int i=0;
	while (i<32 ) {cout<<"|"; i++;}
	FileMenu.Create(con,0,0,10,LightGray,Black,"File");
	EditMenu.Create(con,11,0,10,LightGray,Black,"Edit");
	HelpMenu.Create(con,22,0,10,LightGray,Black,"Help");
	NewButton.Fill(con,0,2,9,LightGray,Black,"New");
	OpenButton.Fill(con,0,3,9,LightGray,Black,"Open");
	SaveButton.Fill(con,0,4,9,LightGray,Black,"Save");
	ExitButton.Fill(con,0,5,9,LightGray,Black,"Exit");
	FileButtonsArr[0]=NewButton; FileButtonsArr[1]=OpenButton; FileButtonsArr[2]=SaveButton; FileButtonsArr[3]=ExitButton;
	PopUpMenuFile.Fill(con,0,1,9,FileButtonsArr,4);
	ResetButton.Fill(con,11,2,9,LightGray,Black,"Reset");
	EditButtonArr[0]=ResetButton;
	PopUpMenuEdit.Fill(con,11,1,9,EditButtonArr,1);
}
