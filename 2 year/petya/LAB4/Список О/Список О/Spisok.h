#include <iostream>
using namespace std;
#define empty count==0
#define emptyMsg cout<<"������ ����!!!"
#define maxStud 15
char arr[5];
// MAIN LIST STRUCT
template <class T>
class SpisokSTR
{
public:
	T x;
	SpisokSTR *next;
	SpisokSTR *prev;
};

// MAIN LIST CLASS
template <class T>
class SpisokCS: public SpisokSTR<T>
{
protected:
	SpisokSTR<T> *LIST;
	SpisokSTR<T> *HEAD;
	SpisokSTR<T> *TAIL;
	int count;
	virtual void Search()=0;
public:
	SpisokCS()
	{
		TAIL=LIST=HEAD=new SpisokSTR<T>;
		HEAD->prev=NULL;
		TAIL->next=NULL;
		count=0;
	}
	void CreateSpisok();
	void PrintSpisok();
	void PrintSpisokReverse();
	void DeleteSpisok();
	void AddElement();
	void DeleteElement();
	void ChangeVal();
	void ChangePos();
};
// EXTENDED LIST CLASS
template <class T> 
class SpisokCSExtended: public SpisokCS<char>
{
public:
	void Search();
	bool Found(char,SpisokCSExtended<char> &);
	void AddToTail(SpisokSTR<char> *&,SpisokCSExtended<char> &,int &c);
	SpisokCSExtended<char> operator * (SpisokCSExtended<char> &);
};
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MAIN LIST CLASS METHODS
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
template <class T>
void SpisokCS<T>::CreateSpisok()
{
	LIST=HEAD;
	SpisokSTR *buff;
	cout<<"������� ���������� ��������� ������<="<<maxStud<<": \n";
	cin>>count;
	for (int i=0;i<count;i++)
	{
		buff=LIST;
		LIST->next=new SpisokSTR;
		LIST=LIST->next;
		cin>>LIST->x;
		LIST->prev=new SpisokSTR;
		LIST->prev=buff;
		buff=NULL;
	}
	TAIL=LIST;
	TAIL->next=NULL;
}
template <class T>
void SpisokCS<T>::PrintSpisok()
{
	if (!empty)
	{
		LIST=HEAD->next;
		if (HEAD->next==NULL) cout<<"������ ����!";
		else
			while (LIST!=NULL)
			{
				cout<<LIST->x<<' ';
				LIST=LIST->next;
			}
	}
	else emptyMsg;
}
template <class T>
void SpisokCS<T>::PrintSpisokReverse()
{
	if (!empty)
	{
		LIST=TAIL;
		if (HEAD->next==NULL) cout<<"������ ����!";
		else
			while (LIST->prev!=NULL)
			{
				cout<<LIST->x<<' ';
				LIST=LIST->prev;
			}
	}
	else emptyMsg;
}
template <class T>
void SpisokCS<T>::DeleteSpisok()
{
	if (!empty)
	{
		LIST=TAIL;
		if (HEAD->next==NULL) cout<<"������ ����!";
		else
		{
			while (LIST->prev!=NULL)
			{
				LIST=LIST->prev;
				delete LIST->next;
			}
			LIST->next=NULL;
		}
	}
	else emptyMsg;
}
template <class T>
void SpisokCS<T>::AddElement()
{
	if (!empty)
	{
		if (HEAD->next==NULL) cout<<"������ ����!";
		else
		{
			PrintSpisok();
			LIST=HEAD;
			cout<<"\n\n";
			SpisokSTR<T> *buff1=new SpisokSTR<T>;
			SpisokSTR<T> *buff2=new SpisokSTR<T>;
			cout<<"������� �������, � ������� ������ �������� �������<="<<(count?count:1)<<":\n";
			int NUM;
			cin>>NUM;
			for (int i=0;i<NUM-1;i++)
			LIST=LIST->next;
			buff2=LIST->next;
			cout<<"������� �������� ��������:\n";
			cin>>buff1->x;
			LIST->next=buff1;
			LIST=LIST->next;
			LIST->next=buff2;
		}
	}
}
template <class T>
void SpisokCS<T>::DeleteElement()
{
	if (!empty)
	{
		PrintSpisok();
		cout<<"\n\n";
		LIST=HEAD;
		SpisokSTR<T> *buff1;
		cout<<"������� �������, �� ������� ������ ������� �������<="<<count<<":\n";
		int NUM;
		cin>>NUM;
		for (int i=0;i<NUM-1;i++)
			LIST=LIST->next;
		buff1=LIST->next->next;
		delete LIST->next;
		LIST->next=buff1;
	}
	else emptyMsg;
}
template <class T>
void SpisokCS<T>::ChangeVal()
{
	if (!empty)
	{
		PrintSpisok();
		cout<<"\n\n";
		LIST=HEAD;
		cout<<"������� ������� ��������, �������� �������� ������ ��������<="<<count<<":\n";
		int NUM;
		cin>>NUM;
		for (int i=0;i<NUM;i++)
		LIST=LIST->next;
		cout<<"������� �������� ��������:\n";
		cin>>LIST->x;
	}
	else emptyMsg;
}
template <class T>
void SpisokCS<T>::ChangePos()
{
	PrintSpisok();
	cout<<"\n\n";
	LIST=HEAD;
	SpisokSTR<T> *buff1,*buff2,*buff3;
	cout<<"������� ������� ��������, �������� ������ �����������<="<<count<<":\n";
	int FirstPos;
	cin>>FirstPos;
	cout<<"������� �������, � ������ ������ �����������<="<<count<<":\n";
	int SecondPos;
	cin>>SecondPos;
	for (int i=0;i<FirstPos-1;i++)
		LIST=LIST->next;
	buff2=LIST->next;
	buff1=LIST->next->next;
	LIST->next=buff1;
	LIST=HEAD;
	for (int i=0;i<SecondPos-1;i++)
		LIST=LIST->next;
	buff3=LIST->next;
	LIST->next=buff2;
	LIST=LIST->next;
	LIST->next=buff3;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EXTENDED LIST CLASS (CHAR) METHODS
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
template <class T>
void SpisokCSExtended<T>::Search()
{
	if (!empty)
	{
		PrintSpisok();
		cout<<"\n\n";
		LIST=HEAD->next;
		bool found=false;
		cout<<"������� �������� ��������:\n";
		T v;
		cin>>v;
		int i=0;
		while (LIST!=NULL)
		{
			if (v==LIST->x) 
			{
				found=true;
				cout<<"\n������� � �������: "<<i+1<<'\n';
			}
			LIST=LIST->next;
			i++;
		}
		if (!found) cout<<"������ �� �������!!!";
	}
	else emptyMsg;
}
template <class T>
bool SpisokCSExtended<T>::Found(char elem,SpisokCSExtended<char> &Res)
{
	Res.LIST=Res.HEAD;
	for (int i=0;i<Res.count;i++)
	{
		if (Res.LIST->x==elem) return true;
		Res.LIST=Res.LIST->next;
	}
	return false;
}
template <class T>
void SpisokCSExtended<T>::AddToTail(SpisokSTR *&LIST,SpisokCSExtended<char> &Res,int &c)
{
	Res.LIST=Res.HEAD;
	for (int i=0;i<c;i++)
		Res.LIST=Res.LIST->next;
	Res.LIST->next=LIST;
	Res.LIST=Res.LIST->next;
	Res.count++;
	c++;
}
template <class T>
SpisokCSExtended<char> SpisokCSExtended<T>::operator * (SpisokCSExtended<char> &l)
{
	int c=0;
	LIST=HEAD->next;
	l.LIST=l.HEAD->next;
	SpisokCSExtended<char> Res;
	while(LIST!=NULL)
	{
		l.LIST=l.HEAD->next;
		while(l.LIST!=NULL)
		{
			if (LIST->x==l.LIST->x) 
				if (Found(LIST->x,Res)==false) 
				{
					AddToTail(LIST,Res,c);
				}
			l.LIST=l.LIST->next;
		}
		LIST=LIST->next;
	}
	return Res;
}