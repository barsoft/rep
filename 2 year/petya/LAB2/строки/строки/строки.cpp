#include <iostream>
#include <conio.h>
#include <string.h>
#include <stdio.h>
#define clrscr(); system("cls");
using std::cin;
using std::cout;
using std::endl;
void DrawLine() // Рисует линию
{
	for (int i=0;i<80;i++) cout<<'_';
}
void ResetStr(char str[],int num) // Обнуляет массив
{
	for (int i=0;i<num;i++) str[i]=0;
}
char *strncpyUser(char *dest,const char *s1,size_t maxlen) // Пользователская функция
{
	for (int i=0;i<maxlen;i++)
	{
		*(dest+i)=*(s1+i);
	}
	return dest;
}
void main()
{
	FILE *file, *file1;
	const char *string_1;
	char string_1Input[80];
	char string_2[80];
	if ((file = fopen("Strings.txt","w")) == NULL) printf("File build error!\n");
	cout<<"Enter your string:"<<endl;
	cin>>string_1Input;
	string_1=string_1Input;
	fprintf(file,"Your string: %s \n",string_1);
	DrawLine();
	cout<<"Enter number of elements to copy:"<<endl;
	int Kol;
	cin>>Kol;
	fprintf(file,"Copied %d %s \n",Kol," characters");
	DrawLine();	
	// Пользовательская функция
	cout<<"1 - Using user function"<<endl;
	strncpyUser(string_2,string_1,Kol);
	string_2[Kol]='\0';
	cout<<endl<<"New String:"<<string_2<<endl<<endl;
	fprintf(file,"New string (using user function): %s \n",string_2);
	DrawLine();
	ResetStr(string_2,Kol+1);
	// Стандартная функция
	cout<<"2 - Using standard function"<<endl;
	strncpy(string_2,string_1,Kol);
	string_2[Kol]='\0';	
	cout<<endl<<"New String:"<<string_2<<endl<<endl;
	fprintf(file,"New string (using standard function): %s \n ",string_2);
	DrawLine();
	fclose(file);
	_getch();
}