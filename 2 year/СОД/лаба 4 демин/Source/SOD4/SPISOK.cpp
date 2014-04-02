/////////////////////////////////////////////////////////////////////////////////////////////////////
// SPISOK.CPP contains main class implementation ////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
#include "SPISOK.h"
#include "Main.h"
/////////////////////////////////////////////////////////////////////////////////////////////////////
// PRIVATE EXTRA METHODS: ///////////////////////////////////////////////////////////////////////////
// STANDARD INPUT EXTRA METHOD
void Spisok::StandardInput(int &StudCounter,Item *&List)
{
	cout<<"ÑÒÓÄÅÍÒ ¹"<<StudCounter<<":\n";
	DrawLine();
	cout<<"ÂÂÅÄÈÒÅ ÔÀÌÈËÈÞ:\n";
	cin>>List->info.ThirdName;
	DrawLine();
	cout<<"ÂÂÅÄÈÒÅ ÈÌß:\n";
	cin>>List->info.FirstName;
	DrawLine();
	cout<<"ÂÂÅÄÈÒÅ ÎÒ×ÅÑÒÂÎ:\n";
	cin>>List->info.SecondName;
	DrawLine();
	cout<<"ÂÂÅÄÈÒÅ ÔÀÊÓËÜÒÅÒ:\n";
	cin>>List->info.Faculty;
	DrawLine();
	cout<<"ÂÂÅÄÈÒÅ ÒÈÏ ÐÀÁÎÒ:\n";
	cin>>List->info.JobType;
	DrawLine();
	cout<<"ÂÂÅÄÈÒÅ ÎÊËÀÄ (ÃÐÍ):\n";
	do
	{
		cin>>List->info.Salary;
		InputErrorMsg();
	}
	while(List->info.Salary<1);
	
	DrawLine();
}
void Spisok::InputErrorMsg()
{
	if (!cin) 
		{
			cout<<"\nÎØÈÁÊÀ ÂÂÎÄÀ!!! ÏÐÎÃÐÀÌÌÀ ÍÅ ÎÒÂÅ×ÀÅÒ\n";
			cout<<"\nÍÀÆÌÈÒÅ ËÞÁÓÞ ÊËÀÂÈØÓ...";
			_getch();
			exit(0);
		}
}
// DISPLAYS FIELDS POSSIBLE FOR PROCESSING 
void Spisok::SelectionMessage()
{
	DrawLine();
	cout<<"1) ÔÀÌÈËÈß\n";
	DrawLine();
	cout<<"2) ÈÌß\n";
	DrawLine();
	cout<<"3) ÎÒ×ÅÑÒÂÎ\n";
	DrawLine();
	cout<<"4) ÔÀÊÓËÜÒÅÒ\n";
	DrawLine();
	cout<<"5) ÒÈÏ ÐÀÁÎÒ\n";
	DrawLine();
	cout<<"6) ÎÊËÀÄ\n";
	DrawLine();
}
// MESSAGE INDICATES SEARCH RESULTS
void Spisok::FoundMessage(int & x,Item* & buff)
{
	cout<<"\nÍÀÉÄÅÍÎ Â ÏÎÇÈÖÈÈ: "<<x+1<<'\n';
	cout<<"\nCÒÓÄÅÍÒ ¹"<<x+1<<": ";
	cout<<" "<<buff->info.ThirdName;
	cout<<" "<<buff->info.FirstName;
	cout<<" "<<buff->info.SecondName;
	cout<<" "<<buff->info.Faculty;
	cout<<" "<<buff->info.JobType;
	cout<<" "<<buff->info.Salary;
	cout<<endl;
	DrawLine();
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
// PUBLIC MAIN METHODS: /////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
// 1) INPUT-OUTPUT: /////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
// INPUT FROM CONSOLE
void Spisok::InputConsole() 
{
	do
	{
		clrscr;
		cout<<"ÂÂÅÄÈÒÅ ÊÎËÈ×ÅÑÒÂÎ ÑÒÓÄÅÍÒÎÂ <=15:\n";
		cin>>CountStud;
		InputErrorMsg();
	}
	while (CountStud<1|| CountStud>15);
	Student=Head;
	for (int i=1;i<CountStud+1;i++)
	{
		clrscr;
		Student->next=new Item;
		Student=Student->next;
		StandardInput(i,Student);
		Student->next=NULL;
	}
}
// OUTPUT TO CONSOLE
void Spisok::OutputConsole()
{
	clrscr;
	Item *buff;
	buff=Head->next;
	if (buff==NULL) 
		{
			cout<<"ÇÄÅÑÜ ÏÎÊÀ ÍÅÒ ÍÈ ÎÄÍÎÉ ÇÀÏÈÑÈ...\n";
			DrawLine();
			empty=true;
		}
	else
	{
		empty=false;
		for (int i=0;i<CountStud;i++)
			{
				cout<<"CÒÓÄÅÍÒ ¹"<<i+1<<": ";
				cout<<" "<<buff->info.ThirdName;
				cout<<" "<<buff->info.FirstName;
				cout<<" "<<buff->info.SecondName;
				cout<<" "<<buff->info.Faculty;
				cout<<" "<<buff->info.JobType;
				cout<<" "<<buff->info.Salary;
				cout<<endl;
				buff=buff->next;
				DrawLine();
			}
	}
}
// INPUT FROM FILE
void Spisok::InputFile()
{
	clrscr;
	if ((file = fopen("Students.tdt","r")) == NULL)
	{
		cout<<"ÎØÈÁÊÀ ×ÒÅÍÈß ÈÇ ÔÀÉËÀ!\n";
	}
	else
	{
		cout<<"ÔÀÉË ÇÀÃÐÓÆÅÍ!\n";
		Student=Head;
		CountStud=0;
		while (!feof(file))
		{
			CountStud++;
			char s[80];
			Student->next=new Item;
			Student=Student->next;
			fscanf(file,"%s",Student->info.ThirdName);
			fscanf(file,"%s",Student->info.FirstName);
			fscanf(file,"%s",Student->info.SecondName);
			fscanf(file,"%s",Student->info.Faculty);
			fscanf(file,"%s",Student->info.JobType);
			fscanf(file,"%s",s);
			Student->info.Salary=atoi(s);
			Student->next=NULL;
		}
		fclose(file);
		DrawLine();
	}
}
// OUTPUT TO FILE
void Spisok::OutputFile()
{
	clrscr;
	if ((file = fopen("StudentsOut.tdt","w")) == NULL)
	{
		cout<<"ÎØÈÁÊÀ ÑÎÇÄÀÍÈß ÔÀÉËÀ!\n";
	}
	else
	{
		cout<<"ÔÀÉË ÑÎÕÐÀÍÅÍ!\n";
		Item *buff;
		buff=Head->next;
		for (int i=0;i<CountStud;i++)
		{
			if (i) fprintf(file,"\n");
			fprintf(file,"%s\n",buff->info.ThirdName);
			fprintf(file,"%s\n",buff->info.FirstName);
			fprintf(file,"%s\n",buff->info.SecondName);
			fprintf(file,"%s\n",buff->info.Faculty);
			fprintf(file,"%s\n",buff->info.JobType);
			fprintf(file,"%d",buff->info.Salary);
			buff=buff->next;
		}
		fclose(file);
		DrawLine();
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
// 2) DELETION: /////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
// DELETION OF ELEMENT FROM POSITION
void Spisok::DeleteElem()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
	{
		OutputConsole();
		cout<<"\nÂÂÅÄÈÒÅ ÍÎÌÅÐ ÑÒÓÄÅÍÒÀ, ÊÎÒÎÐÎÃÎ ÂÛ ÑÎÁÈÐÀÅÒÅÑÜ ÓÄÀËÈÒÜ ÈÇ ÑÏÈÑÊÀ <="<<(CountStud?CountStud:1)<<":\n";
		int InpPos;
		do
		{
			cin>>InpPos;
			InputErrorMsg();
		}
		while (InpPos<1 || InpPos>CountStud);
		clrscr;
		Item *buff,*buff2=new Item,*buff3=new Item;
		buff=Head;
		for (int i=0;i<InpPos;i++)
		buff=buff->next;
		buff3=buff->next;
		buff->next=NULL;
		buff=Head;
		for (int i=0;i<InpPos-1;i++)
		buff=buff->next;
		buff->next=new Item;
		buff->next=buff3;
		CountStud--;
		cout<<"ÈÍÔÎÐÌÀÖÈß ÓÄÀËÅÍÀ!\n";
		DrawLine();
	}
}
// SELECTION OF FIRST ELEMENT (DELETE ALL EXCEPT FIRST) (CAR)
void Spisok::ViborFirst()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
		CountStud--;
		cout<<"ÈÍÔÎÐÌÀÖÈß ÓÄÀËÅÍÀ!\n";
		DrawLine();
	} 
	else
	{
		Item *buff,*bufhead;
		buff=Head;
		bufhead=buff;
		buff=buff->next;
		buff->next=NULL;
		Student=bufhead;
		CountStud=1;
		cout<<"ÈÍÔÎÐÌÀÖÈß ÓÄÀËÅÍÀ!\n";
		DrawLine();
	}
}
// DELETE FIRST ELEMENT (CDR)
void Spisok::DeleteFirst()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
	{
		Item *buff,*bufhead;
		Student=Head;
		buff=Head;
		bufhead=buff;
		buff->next=Student->next->next;
		Student=bufhead;
		CountStud--;
		cout<<"ÈÍÔÎÐÌÀÖÈß ÓÄÀËÅÍÀ!\n";
		DrawLine();
	}
}
// CLEAN LIST (CADR)
void Spisok::ViborAndDeleteFirst()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
	{
		ViborFirst();
		DeleteFirst();
	}
}
// RESET ALL LIST
void Spisok::ResetAll()
{
	clrscr;
	Student=Head;
	Student->next=NULL;
	cout<<"ÈÍÔÎÐÌÀÖÈß ÓÄÀËÅÍÀ!\n";
	DrawLine();
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
// 3) ADDITION: /////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
// ADD ELEMENT TO HEAD
void Spisok::DobavlElemVNach()
{
	clrscr;
	Item *buff,*bufhead;
	bufhead=new Item;
	buff=bufhead;
	buff->next=new Item;
	buff=buff->next;
	int i=1;
	StandardInput(i,buff);
	buff->next=NULL;
	Student=Head->next;
	buff->next=new Item;
	buff->next=Student;
	Head=bufhead;
	Student=Head;
	CountStud++;
}
// ADD ELEMENT TO TAIL
void Spisok::DobavlElemVKonets()
{
	clrscr;
	Student->next=new Item;
	Student=Student->next;
	Student->next=NULL;
	CountStud++;
	StandardInput(CountStud,Student);
}
// ADD ELEMENT TO POSITION
void Spisok::DobavlElemVPosiziy()
{
	clrscr;
	cout<<"ÂÂÅÄÈÒÅ ÏÎÇÈÖÈÞ Â ÑÏÈÑÊÅ, Â ÊÎÒÎÐÓÞ ÑÎÁÈÐÀÅÒÅÑÜ ÄÎÁÀÂÈÒÜ ÑÒÓÄÅÍÒÀ <="<<(CountStud?CountStud:1)<<":\n";
	int InpPos;
	do
		{
			cin>>InpPos;
			InputErrorMsg();
		}
	while (InpPos<1 || InpPos>CountStud);
	clrscr;
	Item *buff,*buff2=new Item,*buff3=new Item;
	buff=Head;
	for (int i=0;i<InpPos-1;i++)
	buff=buff->next;
	buff3=buff->next;
	buff->next=NULL;
	StandardInput(InpPos,buff2);
	buff->next=new Item;
	buff->next=buff2;
	buff=buff->next;
	buff->next=new Item;
	buff->next=buff3;
	CountStud++;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
// 4) CHANGE: ///////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
// CHNGE ALL FIELDS OF STRUCTURE IN LIST
void Spisok::ChangeAllFields()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
	{
		OutputConsole();
		cout<<"\nÂÂÅÄÈÒÅ ÍÎÌÅÐ ÑÒÓÄÅÍÒÀ, ÄÀÍÍÛÅ Î ÊÎÒÎÐÎÌ ÕÎÒÈÒÅ ÈÇÌÅÍÈÒÜ: <="<<CountStud<<'\n';
		int NumStud;
		do
		{
			cin>>NumStud;
			InputErrorMsg();
		}
		while (NumStud<1 || NumStud>CountStud);
		clrscr;
		Item *buff;
		buff=Head;
		for (int i=0;i<NumStud;i++)
		buff=buff->next;
		StandardInput(NumStud,buff);
	}
}
// CHNGE FIELD OF STRUCTURE IN LIST
void Spisok::ChangeVal()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
	{
		OutputConsole();
		cout<<"\nÂÂÅÄÈÒÅ ÍÎÌÅÐ ÑÒÓÄÅÍÒÀ, ÄÀÍÍÛÅ Î ÊÎÒÎÐÎÌ ÕÎÒÈÒÅ ÈÇÌÅÍÈÒÜ: <="<<CountStud<<'\n';
		DrawLine();
		int NumStud;
		do
		{
			cin>>NumStud;
			InputErrorMsg();
		}
		while (NumStud<1 || NumStud>CountStud);
		clrscr;
		Item *buff;
		buff=Head;
		for (int i=0;i<NumStud;i++)
		buff=buff->next;
		cout<<"ÂÂÅÄÈÒÅ ÏÎËÅ, ÊÎÒÎÐÎÅ ÕÎÒÈÒÅ ÈÇÌÅÍÈÒÜ: <="<<CountStud<<'\n';
		SelectionMessage();
		int FieldNum;
		cin>>FieldNum;
		switch(FieldNum)
		{
		case 1:
			{

				cout<<"ÂÂÅÄÈÒÅ ÔÀÌÈËÈÞ:\n";
				cin>>buff->info.ThirdName;
				break;
			}
		case 2:
			{
				cout<<"ÂÂÅÄÈÒÅ ÈÌß:\n";
				cin>>buff->info.FirstName;
				break;
			}
		case 3:
			{
				cout<<"ÂÂÅÄÈÒÅ ÎÒ×ÅÑÒÂÎ:\n";
				cin>>buff->info.SecondName;
				break;
			}
		case 4:
			{
				cout<<"ÂÂÅÄÈÒÅ ÔÀÊÓËÜÒÅÒ:\n";
				cin>>buff->info.Faculty;
				break;
			}
		case 5:
			{
				cout<<"ÂÂÅÄÈÒÅ ÒÈÏ ÐÀÁÎÒ:\n";
				cin>>buff->info.JobType;
				break;
			}
		case 6:
			{
				cout<<"ÂÂÅÄÈÒÅ ÎÊËÀÄ (ÃÐÍ):\n";
				do
				{
					cin>>buff->info.Salary;
					InputErrorMsg();
				}
				while (buff->info.Salary<1);
				break;
			}
		}		
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
// 5) SPECIAL: //////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
// SEARCH OF STUDENT IN LIST BY ANY KEY
void Spisok::Search()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
		if (CountStud<2)
		{
			{
			cout<<"ÑÏÈÑÎÊ ÑËÈØÊÎÌ ÊÎÐÎÒÊÈÉ!\n";
			DrawLine();
			} 
		}
		else
			{
				cout<<"ÂÛÁÅÐÈÒÅ ÏÎËÅ, ÏÎ ÊÎÒÎÐÎÌÓ ÂÛ ÕÎÒÈÒÅ ÏÐÎÈÇÂÅÑÒÈ ÏÎÈÑÊ: <="<<CountStud<<'\n';
				SelectionMessage();
				cout<<"7) ÍÎÌÅÐ ÑÒÓÄÅÍÒÀ\n";
				DrawLine();
				int FieldNum;
				do
				{
					cin>>FieldNum;
					InputErrorMsg();
				}
				while (FieldNum<1 || FieldNum >7);
				int n;
				char *s=new char[80];
				OutputConsole();
				Item *buff;
				buff=Head->next;
				int i,x;
				bool found;
				switch(FieldNum)
				{
				case 1:
					{
						found=false;
						cout<<"ÂÂÅÄÈÒÅ ÔÀÌÈËÈÞ:\n";
						cin>>s;
						OutputConsole();
						for (i=0;i<CountStud;i++)
							{
								if (!strcmp(buff->info.ThirdName,s)) 
								{
									found=true;
									FoundMessage(i,buff);
								}
								buff=buff->next;
							}
						x=i+1;
						break;
					}
				case 2:
					{
						found=false;
						cout<<"ÂÂÅÄÈÒÅ ÈÌß:\n";
						cin>>s;
						OutputConsole();
						for (i=0;i<CountStud;i++)
							{
								if (!strcmp(buff->info.FirstName,s)) 
								{
									found=true;
									FoundMessage(i,buff);
								}
								buff=buff->next;
							}
						x=i+1;
						break;
					}
				case 3:
					{
						found=false;
						cout<<"ÂÂÅÄÈÒÅ ÎÒ×ÅÑÒÂÎ:\n";
						cin>>s;
						OutputConsole();
						for (i=0;i<CountStud;i++)
							{
								if (!strcmp(buff->info.SecondName,s)) 
								{
									found=true;
									FoundMessage(i,buff);
								}
								buff=buff->next;
							}
						x=i+1;
						break;
					}
				case 4:
					{
						found=false;
						cout<<"ÂÂÅÄÈÒÅ ÔÀÊÓËÜÒÅÒ:\n";
						cin>>s;
						OutputConsole();
						for (i=0;i<CountStud;i++)
							{
								if (!strcmp(buff->info.Faculty,s)) 
								{
									found=true;
									FoundMessage(i,buff);
								}
								buff=buff->next;
							}
						x=i+1;
						break;
					}
				case 5:
					{
						found=false;
						cout<<"ÂÂÅÄÈÒÅ ÒÈÏ ÐÀÁÎÒ:\n";
						cin>>s;
						OutputConsole();
						for (i=0;i<CountStud;i++)
							{
								if (!strcmp(buff->info.JobType,s)) 
								{
									found=true;
									FoundMessage(i,buff);
								}
								buff=buff->next;
							}
						x=i+1;
						break;
					}
				case 6:
					{
						found=false;
						cout<<"ÂÂÅÄÈÒÅ ÎÊËÀÄ (ÃÐÍ):\n";
						do
						{
							cin>>n;
							InputErrorMsg();
						}
						while(n<1);
						OutputConsole();
						for (i=0;i<CountStud;i++)
							{
								if (buff->info.Salary==n) 
								{
									found=true;							
									FoundMessage(i,buff);
								}
								buff=buff->next;
							}
						x=i+1;
						break;
					}
				case 7:
					{
						found=false;
						cout<<"ÂÂÅÄÈÒÅ ÍÎÌÅÐ ÑÒÓÄÅÍÒÀ:\n";
						do
						{
							cin>>n;
							InputErrorMsg();
						}
						while (n>CountStud || n<1);
						OutputConsole();
						for (i=0;i<CountStud;i++)
							{
								if (i+1==n) 
								{
									found=true;
									FoundMessage(i,buff);
								}
								buff=buff->next;
							}
						x=i+1;
						break;
					}
				}	
				if (!found)
				{
					clrscr;
					cout<<"ÑÒÓÄÅÍÒ ÍÅ ÍÀÉÄÅÍ!\n";
					DrawLine();
				}
			}
}
// LIST REVERSE
void Spisok::Reverse()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
		if (CountStud<2)
		{
			{
			cout<<"ÑÏÈÑÎÊ ÑËÈØÊÎÌ ÊÎÐÎÒÊÈÉ!\n";
			DrawLine();
			} 
		}
		else
			{
				Item *buff=Head->next,*buff2,*buff2head;
				buff2head=new Item;
				buff2head->next=NULL;
				buff2=buff2head;
				for (int j=0;j<CountStud;j++)
				{
					buff=Head->next;
					for (int i=0;i<CountStud-j-1;i++)
					buff=buff->next;
					buff2->next=new Item;
					buff2=buff2->next;
					buff2->info=buff->info;
					buff2->next=NULL;	
				}
				buff2=buff2head->next;
				buff=buff2;
				Head=buff2head;
				cout<<"ÑÏÈÑÎÊ ÎÁÐÀÙÅÍ!\n";
				DrawLine();
			}
}
// SORT BY ANY KEY
void Spisok::Sort()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
		if (CountStud<2)
		{
			{
			cout<<"ÑÏÈÑÎÊ ÑËÈØÊÎÌ ÊÎÐÎÒÊÈÉ!\n";
			DrawLine();
			} 
		}
		else
			{
				cout<<"ÂÛÁÅÐÈÒÅ ÏÎËÅ, ÏÎ ÊÎÒÎÐÎÌÓ ÂÛ ÕÎÒÈÒÅ ÏÐÎÈÇÂÅÑÒÈ ÑÎÐÒÈÐÎÂÊÓ: <="<<CountStud<<'\n';
				SelectionMessage();
				int FieldNum;
				do
				{
					cin>>FieldNum;
					InputErrorMsg();
				}
				while (FieldNum<1 || FieldNum >6);
				Item *trash = new Item,*buff=Head; 
				switch(FieldNum)
				{
				case 1:
					for (int i = 0; i < CountStud; i++)
					{
						buff=Head->next;
						for (int j=0; j < CountStud-i-1; j++) 
						{
							if (strcmp(buff->info.ThirdName,buff->next->info.ThirdName)>0)
							{
								trash->info=buff->info;
								buff->info=buff->next->info;
								buff->next->info=trash->info;
							}
							buff=buff->next;
						}
					}
					Student=buff;
					break;
				case 2:
					for (int i = 0; i < CountStud; i++)
					{
							buff=Head->next;
						for (int j=0; j < CountStud-i-1; j++) 
						{
							if (strcmp(buff->info.FirstName,buff->next->info.FirstName)>0)
							{
								trash->info=buff->info;
								buff->info=buff->next->info;
								buff->next->info=trash->info;
							}
							buff=buff->next;
						}
					}
					Student=buff;
					break;
				case 3:
					for (int i = 0; i < CountStud; i++)
					{
						buff=Head->next;
						for (int j=0; j < CountStud-i-1; j++) 
						{
							if (strcmp(buff->info.SecondName,buff->next->info.SecondName)>0)
							{
									trash->info=buff->info;
								buff->info=buff->next->info;
								buff->next->info=trash->info;
							}
							buff=buff->next;
						}
					}
					Student=buff;
					break;
				case 4:
					for (int i = 0; i < CountStud; i++)
					{
						buff=Head->next;
							for (int j=0; j < CountStud-i-1; j++) 
						{
							if (strcmp(buff->info.Faculty,buff->next->info.Faculty)>0)
							{
								trash->info=buff->info;
								buff->info=buff->next->info;
								buff->next->info=trash->info;
							}
							buff=buff->next;
						}
					}
					Student=buff;
					break;
				case 5:
					for (int i = 0; i < CountStud; i++)
					{
						buff=Head->next;
						for (int j=0; j < CountStud-i-1; j++) 
						{
								if (strcmp(buff->info.JobType,buff->next->info.JobType)>0)
							{
								trash->info=buff->info;
									buff->info=buff->next->info;
								buff->next->info=trash->info;
							}
							buff=buff->next;
						}
					}
					Student=buff;
					break;
				case 6:
					for (int i = 0; i < CountStud; i++)
					{
						buff=Head->next;
						for (int j=0; j < CountStud-i-1; j++) 
						{
							if (buff->info.Salary>buff->next->info.Salary)
							{
								trash->info=buff->info;
								buff->info=buff->next->info;
								buff->next->info=trash->info;
							}
							buff=buff->next;
						}
					}
					Student=buff;
					break;
				}
				clrscr;
				cout<<"ÈÍÔÎÐÌÀÖÈß ÎÒÑÎÐÒÈÐÎÂÀÍÀ!\n";
				DrawLine();
			}
}
// TWO ELEMENTS ASSIGN EACH OTHER
void Spisok::Rakirovka()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
		if (CountStud<2)
		{
			{
			cout<<"ÑÏÈÑÎÊ ÑËÈØÊÎÌ ÊÎÐÎÒÊÈÉ!\n";
			DrawLine();
			} 
		}
		else
			{
				OutputConsole();
				cout<<"ÂÂÅÄÈÒÅ ÏÎÇÈÖÈÞ ÏÅÐÂÎÃÎ ÑÒÓÄÅÍÒÀ Â ÑÏÈÑÊÅ <="<<(CountStud?CountStud:1)<<":\n";
				int InpPos1;
				do
				{
					cin>>InpPos1;
					InputErrorMsg();
				}
				while (InpPos1>CountStud ||InpPos1<1);
				cout<<"ÂÂÅÄÈÒÅ ÏÎÇÈÖÈÞ, Â ÊÎÒÎÐÓÞ ÑÎÁÈÐÀÅÒÅÑÜ ÂÑÒÀÂÈÒÜ ÑÒÓÄÅÍÒÀ <="<<(CountStud?CountStud:1)<<":\n";
				int InpPos2;
				do 
				{	
					cin>>InpPos2;
					InputErrorMsg();
				}
				while (InpPos2==InpPos1 || InpPos2>CountStud || InpPos2<1);
				Item *buff,*buff1=new Item,*buff2,*buff3=new Item;
				buff=Head->next;
				for(int i=0;i<InpPos1-1;i++)
				{
					buff=buff->next;
				}
				buff1=buff;
				buff=Head->next;
				for(int i=0;i<InpPos2-1;i++)
				{
					buff=buff->next;
				}
				buff2=buff;
				buff3->info=buff1->info;
				buff1->info=buff2->info;
				buff2->info=buff3->info;
				buff=Head;
				clrscr;
				cout<<"ÈÍÔÎÐÌÀÖÈß ÈÇÌÅÍÅÍÀ!\n";
				DrawLine();
			}
}
// COPY STUDENT TO OTHER POSITION 
void Spisok::CopyTo()
{
	clrscr;
	if (empty)
	{
		cout<<"ÑÏÈÑÎÊ ÏÓÑÒ!\n";
		DrawLine();
	} 
	else
		if (CountStud<2)
		{
			{
			cout<<"ÑÏÈÑÎÊ ÑËÈØÊÎÌ ÊÎÐÎÒÊÈÉ!\n";
			DrawLine();
			} 
		}
		else
		{
			OutputConsole();
			cout<<"ÂÂÅÄÈÒÅ ÏÎÇÈÖÈÞ Â ÑÏÈÑÊÅ ÑÒÓÄÅÍÒÀ, ÊÎÒÎÐÎÃÎ ÂÛ ÑÎÁÈÐÀÅÒÅÑÜ ÊÎÏÈÐÎÂÀÒÜ  <="<<(CountStud?CountStud:1)<<":\n";
			int InpPos1;
			do
			{
				cin>>InpPos1;
				InputErrorMsg();
			}
			while (InpPos1>CountStud ||InpPos1<1);
			cout<<"ÂÂÅÄÈÒÅ ÏÎÇÈÖÈÞ, Â ÊÎÒÎÐÓÞ ÑÎÁÈÐÀÅÒÅÑÜ ÂÑÒÀÂÈÒÜ ÑÒÓÄÅÍÒÀ <="<<(CountStud?CountStud:1)<<":\n";
			int InpPos2;
			do 
			{
				cin>>InpPos2;
				InputErrorMsg();
			}
			while (InpPos2==InpPos1 || InpPos2>CountStud || InpPos2<1);
			Item *buff,*buff1=new Item,*buff2,*buff3=new Item;
			buff=Head->next;
			for(int i=0;i<InpPos1-1;i++)
			{
				buff=buff->next;
			}
			buff1=buff;
			buff=Head->next;
			for(int i=0;i<InpPos2-1;i++)
			{
				buff=buff->next;
			}
			buff2=buff;
			buff2->info=buff1->info;
			buff=Head;
			clrscr;
			cout<<"ÈÍÔÎÐÌÀÖÈß ÈÇÌÅÍÅÍÀ!\n";
			DrawLine();
		}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
// 6) OTHER: ////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
// SOUND SIGNAL
void Spisok::Sound()
{
	Beep(1000,200);
}