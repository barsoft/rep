#include "MULTI SPISOK.h"
void MultiSpisok::InputConsole()
{
	do
	{
		clrscr;
		cout<<"������� ���������� ������������� <=15:\n";
		cin>>CountUniver;
		InputErrorMsg();
	}
	while (CountUniver<1|| CountUniver>15);
	University=Head;
	for (int i=0;i<CountUniver;i++)
	{
		clrscr;
		University->next=new ItemMult;
		University=University->next;
		cout<<"������� �������� ������������ �"<<i+1<<":\n";
		cin>>University->UniversityName;
		University->next=NULL;
	}
}
void MultiSpisok::OutputConsole()
{
	clrscr;
	ItemMult *buff;
	buff=Head->next;
	if (buff==NULL) 
		{
			cout<<"������ ������������� ����...\n";
			DrawLine();
			emptyMult=true;
		}
	else
	{
		emptyMult=false;
		for (int i=0;i<CountUniver;i++)
			{
				cout<<i+1<<") "<<buff->UniversityName<<": \n";
				buff->ListCS.OutputConsole();
				buff=buff->next;
			}
	}
}
void MultiSpisok::InputFile()
{
	University=Head;
	emptyMult=false;
	University->next=new ItemMult;
	University=University->next;
	char *s1="DonNU";
	char *s2="DonNTU";
	University->UniversityName=s1;
	University->ListCS.InputFile("DonNU.tdt");
	University->next=NULL;
	University->next=new ItemMult;
	University=University->next;
	University->UniversityName=s2;
	University->ListCS.InputFile("DonNTU.tdt");
	University->next=NULL;
	CountUniver=2;
}
void MultiSpisok::OutputFile()
{
	clrscr;
	ItemMult *buff;
	buff=Head->next;
	if (buff==NULL) 
		{
			cout<<"������ ������������� ����...\n";
			DrawLine();
			emptyMult=true;
		}
	else
	{
		char *s=new char[80];
		for (int i=0;i<CountUniver;i++)
		{
			sprintf(s,"%s%s",buff->UniversityName,"Out.tdt");
			buff->ListCS.OutputFile(s);
			buff=buff->next;
		}
	}
}
void MultiSpisok::SelectUniversity()
{
	clrscr;
	ItemMult *buff,*bufhead=NULL;
	buff=Head->next;
	if (buff==NULL) 
		{
			cout<<"������ ������������� ����...\n";
			DrawLine();
			WaitForPress();
			emptyMult=true;
		}
	else
	{
		for (int i=0;i<CountUniver;i++)
			{
				cout<<i+1<<") "<<buff->UniversityName<<"\n";				
				buff=buff->next;
			}
		buff=Head;
		cout<<"�������� �����������, ���������� � ��������� �������� ������ �������� <"<<CountUniver<<":\n";
		int NumUniver;
		cin>> NumUniver;
		University=Head;
		for (int i=0;i<NumUniver;i++)
		University=University->next;
		char s[80],s1[80];
		sprintf(s,"%s%s",University->UniversityName,".tdt");
		sprintf(s1,"%s%s",University->UniversityName,"Out.tdt");
		University->ListCS.MainMenu(s,s1);
	}
}
void MultiSpisok::DeleteElem()
{
	clrscr;
	if (emptyMult)
	{
		cout<<"������ ����!\n";
		DrawLine();
	} 
	else
	{
		ItemMult *buff,*buff2=new ItemMult,*buff3=new ItemMult;
		buff=Head->next;
		for (int i=0;i<CountUniver;i++)
			{
				cout<<i+1<<") "<<buff->UniversityName<<"\n";				
				buff=buff->next;
			}
		buff=Head;
		cout<<"\n������� ����� ������������, �������� �� ����������� ������� �� ������ <="<<(CountUniver?CountUniver:1)<<":\n";
		int InpPos;
		do
		{
			cin>>InpPos;
			InputErrorMsg();
		}
		while (InpPos<1 || InpPos>CountUniver);
		clrscr;
		buff=Head;
		for (int i=0;i<InpPos;i++)
		buff=buff->next;
		buff3=buff->next;
		buff->next=NULL;
		buff=Head;
		for (int i=0;i<InpPos-1;i++)
		buff=buff->next;
		buff->next=new ItemMult;
		buff->next=buff3;
		CountUniver--;
		cout<<"���������� �������!\n";
		DrawLine();
	}
}
void MultiSpisok::ResetAll()
{
	clrscr;
	University=Head;
	University->next=NULL;
	cout<<"���������� �������!\n";
	DrawLine();
}
void MultiSpisok::DobavlElemVPosiziy()
{
	clrscr;
	cout<<"������� ������� � ������, � ������� ����������� �������� �������� <="<<(CountUniver?CountUniver:1)<<":\n";
	int InpPos;
	do
		{
			cin>>InpPos;
			InputErrorMsg();
		}
	while (InpPos<1 || InpPos>CountUniver);
	clrscr;
	ItemMult *buff,*buff2=new ItemMult,*buff3=new ItemMult;
	buff=Head;
	for (int i=0;i<InpPos-1;i++)
	buff=buff->next;
	buff3=buff->next;
	buff->next=NULL;
	cout<<"������� �������� ������������ �"<<InpPos<<":\n";
	cin>>buff2->UniversityName;
	buff->next=new ItemMult;
	buff->next=buff2;
	buff=buff->next;
	buff->next=new ItemMult;
	buff->next=buff3;
	CountUniver++;
}
void MultiSpisok::Search()
{
	ItemMult *buff;
	buff=Head->next;
	int i,x;
	bool found;
	found=false;
	char *s=new char[80];
	clrscr;
	cout<<"�������� ����, �� �������� �� ������ ���������� �����: <="<<CountUniver<<'\n';
	DrawLine();
	cout<<"1) �� �������� ������������\n";
	DrawLine();
	cout<<"2) �� ������ ������������\n";
	DrawLine();
	int FieldNum;
	do
	{
		cin>>FieldNum;
		InputErrorMsg();
	}
	while (FieldNum<1 || FieldNum >2);
	clrscr;
	for (int i=0;i<CountUniver;i++)
			{
				cout<<i+1<<") "<<buff->UniversityName<<"\n";				
				buff=buff->next;
			}
	buff=Head->next;
	if (FieldNum==1)
	{
		cout<<"������� �������� ������������:\n";
		cin>>s;
		for (i=0;i<CountUniver;i++)
		{
			if (!strcmp(buff->UniversityName,s)) 
			{
				found=true;
				cout<<"\n������� � �������: "<<i+1<<'\n';
				cout<<i+1<<") "<<buff->UniversityName<<"\n";				
			}
			buff=buff->next;
		}
	}
	else
	{
		cout<<"������� ����� ������������:\n";
		cin>>x;
		for (i=0;i<CountUniver;i++)
		{
			if (i+1==x) 
			{
				found=true;
				cout<<"\n������� � �������: "<<i+1<<'\n';
				cout<<i+1<<") "<<buff->UniversityName<<"\n";
			}
			buff=buff->next;
		}
	}
}
void MultiSpisok::Sound()
{
	ItemMult *buff;
	buff=Head;
	buff->ListCS.Sound();
}