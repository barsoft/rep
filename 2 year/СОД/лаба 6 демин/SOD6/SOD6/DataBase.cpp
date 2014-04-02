#include "DataBase.h"
void Folder::CreateFolder(char* name)
{
	char* s=new char[80];
	wchar_t* w=new wchar_t[80];
	sprintf(s,"%s%s",path,name);
	mbstowcs(w,s,80);
	CreateDirectory(w,NULL);
}
void File::CreateFile(char *path2,char* name,int pos)
{
	char* s=new char[80];
	wchar_t* w=new wchar_t[80];
	sprintf(s,"%s%s%s%s",path,path2,name,".dtb");
	cout<<s;
	if ((file = fopen(s,"w")) == NULL)
	{
		cout<<"ÎØÈÁÊÀ ×ÒÅÍÈß ÈÇ ÔÀÉËÀ!\n";
	}
	else
	{
		clrscr;
		SetTitle(UT,TB,"STUDENTS DATABASE | ÁÀÇÀ ÄÀÍÍÛÕ Î ÑÒÓÄÅÍÒÀÕ ÓÍÈÂÅÐÑÈÒÅÒÎÂ ÄÎÍÅÖÊÀ");
		StaticHelpString(UT,HB,"ÂÂÅÄÈÒÅ ÄÀÍÍÛÅ Î ÑÒÓÄÅÍÒÅ:",NULL);
		SetColor(UT,MBB);
		cout<<"ÂÂÅÄÈÒÅ ÔÀÌÈËÈÞ:\n";
		cin>>BuffStr.ThirdName;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÈÌß:\n";
		cin>>BuffStr.FirstName;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÎÒ×ÅÑÒÂÎ:\n";
		cin>>BuffStr.SecondName;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÔÀÊÓËÜÒÅÒ:\n";
		cin>>BuffStr.Faculty;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÒÈÏ ÐÀÁÎÒ:\n";
		cin>>BuffStr.JobType;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÎÊËÀÄ (ÃÐÍ):\n";
		do
		{
			cin>>BuffStr.Salary;
		}
		while(BuffStr.Salary<1);
		fprintf(file,"%s%s",BuffStr.ThirdName,"\n");
		fprintf(file,"%s%s",BuffStr.FirstName,"\n");
		fprintf(file,"%s%s",BuffStr.SecondName,"\n");
		fprintf(file,"%s%s",BuffStr.Faculty,"\n");
		fprintf(file,"%s%s",BuffStr.JobType,"\n");
		fprintf(file,"%d",BuffStr.Salary);
		fclose(file);
	}
}
void Folder::RemoveFolder(int pos)
{
	char* s=new char[80],* s1=new char[80];
	wchar_t* w=new wchar_t[80];
	int i=1;
	sprintf(s,"%s%s",path,"*");
	mbstowcs(w,s,80);
	handle=FindFirstFile(w,&wfd);
	while(FindNextFile(handle,&wfd) && i++<pos-1) {}
	wcstombs(s1,wfd.cFileName,80);
	sprintf(s,"%s%s%s",path,s1,"\\");
	mbstowcs(w,s,80);
	RemoveDirectory(w);
}
void File::RemoveFile(char *path2,int pos)
{
	char* s=new char[80],* s1=new char[80];
	wchar_t* w=new wchar_t[80];
	int i=1;
	sprintf(s,"%s%s%s",path,path2,"*");
	mbstowcs(w,s,80);
	handle=FindFirstFile(w,&wfd);
	while(FindNextFile(handle,&wfd) && i++<pos-1) {}
	wcstombs(s1,wfd.cFileName,80);
	sprintf(s,"%s%s%s",path,path2,s1);
	mbstowcs(w,s,80);
	DeleteFile(w);
}
void Folder::RenameFolder(char* name,int pos)
{
	char* s=new char[80],* s1=new char[80],* s2=new char[80];
	wchar_t* w=new wchar_t[80],* w1=new wchar_t[80];
	int i=1;
	sprintf(s,"%s%s",path,"*");
	mbstowcs(w,s,80);
	handle=FindFirstFile(w,&wfd);
	cout<<pos-1<<endl;
	while(FindNextFile(handle,&wfd) && i++<pos-1) {}
	wcstombs(s1,wfd.cFileName,80);
	sprintf(s,"%s%s%s",path,s1,"\\");
	mbstowcs(w,s,80);
	cout<<pos-1;
	sprintf(s2,"%s%s%s",path,name,"\\");
	mbstowcs(w1,s2,80);
	MoveFile(w,w1);
}
void File::RenameFile(char* path2,char* name,int pos)
{
	char* s=new char[80],* s1=new char[80],* s2=new char[80];
	wchar_t* w=new wchar_t[80],* w1=new wchar_t[80];
	int i=1;
	sprintf(s,"%s%s%s",path,path2,"*");
	mbstowcs(w,s,80);
	handle=FindFirstFile(w,&wfd);
	cout<<pos-1<<endl;
	while(FindNextFile(handle,&wfd) && i++<pos-1) {}
	wcstombs(s1,wfd.cFileName,80);
	sprintf(s,"%s%s%s",path,path2,s1);
	mbstowcs(w,s,80);
	cout<<pos-1;
	sprintf(s2,"%s%s%s%s",path,path2,name,".dtb");
	mbstowcs(w1,s2,80);
	MoveFile(w,w1);
}
void Folder::Selector(ConsoleColor text, ConsoleColor background, direction d, SHORT pos)
{
	char* s=new char[80];
	wchar_t* w=new wchar_t[80];
	int i=1;
	HANDLE h=GetStdHandle(STD_OUTPUT_HANDLE);
	COORD cp;
	sprintf(s,"%s%s",path,"*");
	mbstowcs(w,s,80);
	handle=FindFirstFile(w,&wfd);
	switch (d)
	{
	case up:
		if (CountUniver)
		{
			SetColor(text,background);
			while(FindNextFile(handle,&wfd) && i++<pos-1) {}
			wcout<<i-2<<')'<<wfd.cFileName<<endl;
			if ( CountUniver!=1)
			{
				SetColor(UT,UB);
				FindNextFile(handle,&wfd);
				wcout<<i-1<<')'<<wfd.cFileName;
			}
		}
		break;
	case down:
		if (CountUniver && CountUniver!=1)
		{
			SetColor(UT,UB);
			while(FindNextFile(handle,&wfd) && i++<pos-1) {}
			wcout<<i-2<<')'<<wfd.cFileName<<endl;
			SetColor(text,background);
			FindNextFile(handle,&wfd);
			wcout<<i-1<<')'<<wfd.cFileName;
		}
		break;
	}
}
void File::ExtendedSelector(char* path2,ConsoleColor text, ConsoleColor background, direction d, SHORT pos)
{
	char* s=new char[80];
	wchar_t* w=new wchar_t[80];
	int i=1;
	HANDLE h=GetStdHandle(STD_OUTPUT_HANDLE);
	COORD cp;
	sprintf(s,"%s%s%s",path,path2,"*");
	mbstowcs(w,s,80);
	handle=FindFirstFile(w,&wfd);
	switch (d)
	{
	case up:
		if (CountStudent)
		{
			SetColor(text,background);
			while(FindNextFile(handle,&wfd) && i++<pos-1) {}
			wcout<<i-2<<')'<<wfd.cFileName<<endl;
			if ( CountStudent!=1)
			{
				SetColor(UT,UB);
				FindNextFile(handle,&wfd);
				wcout<<i-1<<')'<<wfd.cFileName;
			}
		}
		else 
		{
			DrawLine();
			cout<<"ÇÄÅÑÜ ÏÎÊÀ ÍÅ ÇÀÐÅÃÈÑÒÐÈÐÎÂÀÍ ÍÈ ÎÄÈÍ ÑÒÓÄÅÍÒ...";
		}
		break;
	case down:
		if ((CountStudent) && ((CountStudent)!=1))
		{
			SetColor(UT,UB);
			while(FindNextFile(handle,&wfd) && i++<pos-1) {}
			wcout<<i-2<<')'<<wfd.cFileName<<endl;
			SetColor(text,background);
			FindNextFile(handle,&wfd);
			wcout<<i-1<<')'<<wfd.cFileName;
		}
		else
		{
			DrawLine();
			cout<<"ÇÄÅÑÜ ÏÎÊÀ ÍÅ ÇÀÐÅÃÈÑÒÐÈÐÎÂÀÍ ÍÈ ÎÄÈÍ ÑÒÓÄÅÍÒ...";
		}
		break;
	}
}
void File::ExtraSelector(ConsoleColor text, ConsoleColor background,direction d, SHORT pos)
{
	HANDLE h=GetStdHandle(STD_OUTPUT_HANDLE);
	COORD cp;
	switch (d)
	{
	case up:
		if (pos>-1)
		{
			cp.X=18;
			cp.Y=pos+10;
			SetConsoleCursorPosition(h,cp);
			SetColor(text,background);
			cout<<a[pos];
			SetConsoleCursorPosition(h,cp);
			if ( pos!=-2)
			{
				SetColor(UT,MBB);
				pos++;
				cp.X=18;
				cp.Y=pos+10;
				SetConsoleCursorPosition(h,cp);
				cout<<a[pos];
				pos--;
				cp.X=18;
				cp.Y=pos+10;
				SetConsoleCursorPosition(h,cp);
			}	
		}
		break;
	case down:
		if (pos<6)
		{
			SetColor(UT,MBB);
			cout<<a[pos];
			SetColor(text,background);
			pos++;
			cp.X=18;
			cp.Y=pos+10;
			SetConsoleCursorPosition(h,cp);
			cout<<a[pos];
		}
		break;
	}
}
void Folder::ShowFolderList()
{
	char* s=new char[80];
	wchar_t* w=new wchar_t[80];
	int i=0;
	sprintf(s,"%s%s",path,"*");
	mbstowcs(w,s,80);
	handle=FindFirstFile(w,&wfd);
	FindNextFile(handle,&wfd);
	while (FindNextFile(handle,&wfd)) {wcout<<i+1<<')'<<wfd.cFileName<<endl; i++;}
	CountUniver=i;
	if (!CountUniver)
	{
		DrawLine();
		cout<<"ÇÄÅÑÜ ÏÎÊÀ ÍÅÒ ÈÍÔÎÐÌÀÖÈÈ..";
	}
}
void File::ShowFileList(char* path2)
{
	char* s=new char[80];
	wchar_t* w=new wchar_t[80];
	int i=0;
	sprintf(s,"%s%s%s",path,path2,"*");
	mbstowcs(w,s,80);
	handle=FindFirstFile(w,&wfd);
	FindNextFile(handle,&wfd);
	while (FindNextFile(handle,&wfd)) {wcout<<i+1<<')'<<wfd.cFileName<<endl; i++;}
	CountStudent=i;
}
void File::ShowFile(char* path2,int pos)
{
	char* s=new char[80],*s1=new char[80],*s2=new char[80];
	wchar_t* w=new wchar_t[80];
	bool stop=false;
	int i=0;
	char* x=new char[80];
	sprintf(s,"%s%s%s",path,path2,"*");
	mbstowcs(w,s,80);
	handle=FindFirstFile(w,&wfd);
	FindNextFile(handle,&wfd);
	while (FindNextFile(handle,&wfd)&& i++<pos-3) {}
	wcstombs(s2,wfd.cFileName,80);
	sprintf(s1,"%s%s%s",path,path2,s2);
	BuffStr.ThirdName="";
	BuffStr.FirstName="";
	BuffStr.SecondName="";
	BuffStr.Faculty="";
	BuffStr.JobType="";
	BuffStr.Salary=0;
	if ((file = fopen(s1,"r")) == NULL)
	{
		MessageBox(UT,MBB,3,30,"ÎØÈÁÊÀ ×ÒÅÍÈß ÈÇ ÔÀÉËÀ!\n");
		_getch();
	}
	else
	{
		BuffStr.ThirdName=new char[80];
		BuffStr.FirstName=new char[80];
		BuffStr.SecondName=new char[80];
		BuffStr.Faculty=new char[80];
		BuffStr.JobType=new char[80];
		fscanf(file,"%s",BuffStr.ThirdName);
		fscanf(file,"%s",BuffStr.FirstName);
		fscanf(file,"%s",BuffStr.SecondName);
		fscanf(file,"%s",BuffStr.Faculty);
		fscanf(file,"%s",BuffStr.JobType);
		fscanf(file,"%s",x);
		BuffStr.Salary=atoi(x);
	}
	if (BuffStr.ThirdName=="" || BuffStr.FirstName=="" || BuffStr.SecondName=="" || 
	BuffStr.Faculty=="" || BuffStr.JobType=="" || BuffStr.Salary==0)
	{
		MessageBox(UT,MBB,3,30,"ÎØÈÁÊÀ ×ÒÅÍÈß ÈÇ ÔÀÉËÀ!\n");
		_getch();
	}
	else
		do
		{
			SetColor(UT,EB);
			clrscr;
			SetTitle(UT,TB,"STUDENTS DATABASE | ÁÀÇÀ ÄÀÍÍÛÕ Î ÑÒÓÄÅÍÒÀÕ ÓÍÈÂÅÐÑÈÒÅÒÎÂ ÄÎÍÅÖÊÀ");
			SlidingHelp(UT,TB,"ÈÇÌÅÍÈÒÜ ÎÄÍÎ ÏÎËÅ","ÈÇÌÅÍÈÒÜ ÂÑÅ ÏÎËß",NULL);
			StaticHelpString(UT,HB,"ÄÀÍÍÛÅ ÑÒÓÄÅÍÒÀ:",NULL);
			cout<<"ÔÀÌÈËÈß  :"<<BuffStr.ThirdName<<'\n';
			cout<<"ÈÌß      :"<<BuffStr.FirstName<<'\n';
			cout<<"ÎÒ×ÅÑÒÂÎ :"<<BuffStr.SecondName<<'\n';
			cout<<"ÔÀÊÓËÜÒÅÒ:"<<BuffStr.Faculty<<'\n';
			cout<<"ÒÈÏ ÐÀÁÎÒ:"<<BuffStr.JobType<<'\n';
			cout<<"ÎÊËÀÄ    :"<<BuffStr.Salary<<'\n';
			char key,key1;
			bool stop2=false;
			int pole=0;
			do
				key=_getch();
			while (key!='1' && key!='2' && key!=13 && key!=27);
			switch(key)
			{
			case '1': 
				MessageBox(UT,MBB,8,45,"ÂÛÁÅÐÈÒÅ ÏÎËÅ, ÊÎÒÎÐÎÅ ÑÎÁÈÐÀÅÒÅÑÜ ÈÇÌÅÍÈÒÜ:");
				cp.Y=9;
				for(int i=0;i<6;i++)
				{
					cp.X=18;
					cp.Y++;
					SetConsoleCursorPosition(h,cp);
					cout<<a[i]<<endl;
				}
				cp.X=18;
				cp.Y=10;
				SetConsoleCursorPosition(h,cp);
				ExtraSelector(ST,SB,down,cp.Y-10);cp.X=18; cp.Y++; SetConsoleCursorPosition(h,cp);
				cp.Y--; cp.X=18;	SetConsoleCursorPosition(h,cp); ExtraSelector(ST,SB,up,cp.Y-10);
				do
				{	
					do
					key1=_getch();
					while (key1!=80 && key1!=72 && key1!=13 && key1!=27);
					switch(key1)
					{
					case 72: if (cp.Y>10) {cp.Y--; cp.X=18;	SetConsoleCursorPosition(h,cp); ExtraSelector(ST,SB,up,cp.Y-10);} break;
					case 80: if (cp.Y<15) { ExtraSelector(ST,SB,down,cp.Y-10);cp.X=18; cp.Y++;SetConsoleCursorPosition(h,cp);}	 break;
					case 13: ChangeFileItem(s1,cp.Y-9);stop2=true; break;
					case 27: stop2=true;break;
					}
				}
				while (!stop2);
				break;
			case '2': ChangeFileAll(s1); break;
			case 13: 
			case 27:  stop=true;break;
			}
		}
		while (!stop);
	fclose(file);
}
void File::ChangeFileAll(char* path2)
{
	if ((file = fopen(path2,"w")) == NULL)
	{
		cout<<"ÎØÈÁÊÀ ×ÒÅÍÈß ÈÇ ÔÀÉËÀ!\n";
	}
	else
	{
		SetColor(UT,MBB);
		clrscr;
		SetTitle(UT,TB,"STUDENTS DATABASE | ÁÀÇÀ ÄÀÍÍÛÕ Î ÑÒÓÄÅÍÒÀÕ ÓÍÈÂÅÐÑÈÒÅÒÎÂ ÄÎÍÅÖÊÀ");
		StaticHelpString(UT,HB,"ÂÂÅÄÈÒÅ ÄÀÍÍÛÅ Î ÑÒÓÄÅÍÒÅ:",NULL);
		SetColor(UT,MBB);
		cout<<"ÂÂÅÄÈÒÅ ÔÀÌÈËÈÞ:\n";
		cin>>BuffStr.ThirdName;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÈÌß:\n";
		cin>>BuffStr.FirstName;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÎÒ×ÅÑÒÂÎ:\n";
		cin>>BuffStr.SecondName;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÔÀÊÓËÜÒÅÒ:\n";
		cin>>BuffStr.Faculty;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÒÈÏ ÐÀÁÎÒ:\n";
		cin>>BuffStr.JobType;
		DrawLine();
		cout<<"ÂÂÅÄÈÒÅ ÎÊËÀÄ (ÃÐÍ):\n";
		do
		{
			cin>>BuffStr.Salary;
		}
		while(BuffStr.Salary<1);
		fprintf(file,"%s%s",BuffStr.ThirdName,"\n");
		fprintf(file,"%s%s",BuffStr.FirstName,"\n");
		fprintf(file,"%s%s",BuffStr.SecondName,"\n");
		fprintf(file,"%s%s",BuffStr.Faculty,"\n");
		fprintf(file,"%s%s",BuffStr.JobType,"\n");
		fprintf(file,"%d",BuffStr.Salary);
		fclose(file);
	}
}
void File::ChangeFileItem(char* path2,int pole)
{
	BuffStr.ThirdName="";
	BuffStr.FirstName="";
	BuffStr.SecondName="";
	BuffStr.Faculty="";
	BuffStr.JobType="";
	BuffStr.Salary=0;
	char *x=new char[80];
	if ((file = fopen(path2,"r")) == NULL)
	{
		MessageBox(UT,MBB,3,30,"ÎØÈÁÊÀ ×ÒÅÍÈß ÈÇ ÔÀÉËÀ!\n");
		_getch();
	}
	else
	{
		BuffStr.ThirdName=new char[80];
		BuffStr.FirstName=new char[80];
		BuffStr.SecondName=new char[80];
		BuffStr.Faculty=new char[80];
		BuffStr.JobType=new char[80];
		fscanf(file,"%s",BuffStr.ThirdName);
		fscanf(file,"%s",BuffStr.FirstName);
		fscanf(file,"%s",BuffStr.SecondName);
		fscanf(file,"%s",BuffStr.Faculty);
		fscanf(file,"%s",BuffStr.JobType);
		fscanf(file,"%s",x);
		BuffStr.Salary=atoi(x);
	}
	if ((file = fopen(path2,"w")) == NULL)
	{
		cout<<"ÎØÈÁÊÀ ×ÒÅÍÈß ÈÇ ÔÀÉËÀ!\n";
	}
	else
	{
		switch(pole)
		{
		case 1: MessageBox(UT,MBB,8,45,"ÂÂÅÄÈÒÅ ÔÀÌÈËÈÞ:");
		cin>>BuffStr.ThirdName;
		DrawLine(); break;
		case 2:MessageBox(UT,MBB,8,45,"ÂÂÅÄÈÒÅ ÈÌß:");
		cin>>BuffStr.FirstName;
		DrawLine(); break;
		case 3:MessageBox(UT,MBB,8,45,"ÂÂÅÄÈÒÅ ÎÒ×ÅÑÒÂÎ:");
		cin>>BuffStr.SecondName;
		DrawLine(); break;
		case 4:MessageBox(UT,MBB,8,45,"ÂÂÅÄÈÒÅ ÔÀÊÓËÜÒÅÒ:");
		cin>>BuffStr.Faculty;
		DrawLine(); break;
		case 5:MessageBox(UT,MBB,8,45,"ÂÂÅÄÈÒÅ ÒÈÏ ÐÀÁÎÒ:");
		cin>>BuffStr.JobType;
		DrawLine(); break;
		case 6:
			MessageBox(UT,MBB,8,45,"ÂÂÅÄÈÒÅ ÎÊËÀÄ (ÃÐÍ):");
			do
			{
				cin>>BuffStr.Salary;
			}
			while(BuffStr.Salary<1);
			break;
		}
		fprintf(file,"%s%s",BuffStr.ThirdName,"\n");
		fprintf(file,"%s%s",BuffStr.FirstName,"\n");
		fprintf(file,"%s%s",BuffStr.SecondName,"\n");
		fprintf(file,"%s%s",BuffStr.Faculty,"\n");
		fprintf(file,"%s%s",BuffStr.JobType,"\n");
		fprintf(file,"%d",BuffStr.Salary);
		fclose(file);
	}
}
void File::ExtendedMenu(SHORT pos)
{
	bool stop1=false;
	do
	{
		SetColor(UT,UB);
		clrscr;
		SetTitle(UT,TB,"STUDENTS DATABASE | ÁÀÇÀ ÄÀÍÍÛÕ Î ÑÒÓÄÅÍÒÀÕ ÓÍÈÂÅÐÑÈÒÅÒÎÂ ÄÎÍÅÖÊÀ");
		SlidingHelp(UT,TB,"ÄÎÁÀÂÈÒÜ","ÓÄÀËÈÒÜ","ÏÅÐÅÈÌÅÍÎÂÀÒÜ",NULL);
		char* name=new char[80];
		SetColor(UT,UB);
		//
		char* s=new char[80];
		char* path2=new char[80];
		wchar_t* w=new wchar_t[80];
		int i=0;
		sprintf(s,"%s%s",path,"*");
		mbstowcs(w,s,80);
		handle=FindFirstFile(w,&wfd);
		FindNextFile(handle,&wfd);
		while (FindNextFile(handle,&wfd) && i++<pos) {}
		wcstombs(s,wfd.cFileName,80);
		sprintf(path2,"%s%s",s,"\\");
		//
		StaticHelpString(UT,HB,"ÑÏÈÑÎÊ ÑÒÓÄÅÍÒÎÂ ",s,": ","(",path,path2,")",NULL);
		SetColor(UT,UB);
		ShowFileList(path2);
		cp.X=0; cp.Y=3;
		SetConsoleCursorPosition(h,cp);
		cp.X=0; cp.Y=3;
		ExtendedSelector(path2,ST,SB,up,cp.Y); 
		SetConsoleCursorPosition(h,cp);
		char key;
		COORD coord;
		bool stop=false;
		do
		{
			do
			key=_getch();
			while (key!='1' && key!='2' && key!='3' && key!=80 && key!=72 && key!=13 && key!=27);
			switch (key)
			{
			case '1':
				coord=cp;
				MessageBox(UT,MBB,3,37,"ÂÂÅÄÈÒÅ ÈÌß ÔÀÉËÀ:");
				cin>>name;;
				CreateFile(path2,name,cp.Y);
				stop=true;
				break;
			case '2':
				RemoveFile(path2,cp.Y);
				stop=true;
				break;
			case '3':
				coord=cp;
				MessageBox(UT,MBB,3,37,"ÂÂÅÄÈÒÅ ÍÎÂÎÅ ÈÌß ÔÀÉËÀ:");
				cin>>name;
				RenameFile(path2,name,coord.Y);
				stop=true;
				break;
			/////////////////////////////////////// || //////////////////////////////
			/////////////////////////////////////// \/ //////////////////////////////
			case 80:
				if (cp.Y<CountStudent+2 &&CountStudent+2 )
				{
					cp.X=0; 
					SetConsoleCursorPosition(h,cp); 
					ExtendedSelector(path2,ST,SB,down,cp.Y); 
					cp.Y++; cp.X=0;
					SetConsoleCursorPosition(h,cp); 
				}
				break;
			/////////////////////////////////////// /\ //////////////////////////////
			/////////////////////////////////////// || //////////////////////////////
			case 72:
				if (cp.Y>3 && CountStudent+2)
				{
					cp.Y--; cp.X=0; 
					SetConsoleCursorPosition(h,cp); 
					ExtendedSelector(path2,ST,SB,up,cp.Y); 
					cp.X=0;
					SetConsoleCursorPosition(h,cp); 
				}
				break;
			case 13: ShowFile(path2,cp.Y);stop=true; break;
			case 27: stop1=true; stop=true; break;
			}
		}
		while (!stop);
	}
	while (!stop1);
}