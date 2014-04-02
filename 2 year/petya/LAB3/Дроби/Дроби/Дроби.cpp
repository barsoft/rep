/* Код Присяжного Максима
Определите класс для дробей -рациональных чисел, являющихся отношением двух целых чисел. Напишите функции - члены для сложения, 
вычетания, умножения и деления дробей и приведения дроби к общему знаменателю. Выполните тестирование и отладку созданной вами
программы обработки дробей.
*/
#include <iostream>
#include <conio.h>
void main()
{
	class DrobCS
	{
	public:
		// ПЕРЕМЕНННЫЕ
		int chisl1,znam1;
		int chisl2,znam2;
		// МЕТОДЫ
		void InputDrob()
		{// ВВОД ДРОБИ
			do
			{
				system("cls");
				std::cout<<"Input chislitel of drob A\n";
				std::cin>>chisl1;
				std::cout<<"Input znamenatel of drob A !=0\n";
				std::cin>>znam1;
				std::cout<<"Input chislitel of drob B\n";
				std::cin>>chisl2;
				std::cout<<"Input znamenatel of drob B !=0\n";
				std::cin>>znam2;
			}
			while (znam1==0 || znam2==0);
		}
		void OutputDrob()
		{// ФОРМАТНЫЙ ВЫВОД ДРОБИ
			char string[16];
			int k,k1;
			int max;
			sprintf(string,"%g",chisl1);
			k=strlen(string);
			sprintf(string,"%g",znam1);
			k1=strlen(string);
			max=((k>k1)?k:k1);
			std::cout<<'\n';
			std::cout.width(max+1);
			std::cout.setf(std::ios::left);
			std::cout<<"A";
			std::cout<<"B\n\n";
			std::cout.width(max+1);
			std::cout.setf(std::ios::left);
			std::cout<<chisl1;
			std::cout<<chisl2<<'\n';
			for (int i=1;i<=max;i++) std::cout<<'_';
			std::cout<<' ';
			for (int i=1;i<=max;i++) std::cout<<'_';
			std::cout<<'\n';
			std::cout.width(max+1);
			std::cout.setf(std::ios::left);
			std::cout<<znam1;
			std::cout<<znam2<<'\n';
		}
		double SumDrob()
		{// СУММА
			return (chisl1/(double)znam1+chisl2/(double)znam2);
		}
		double SubDrobAB()
		{// РАЗНОСТЬ А-В
			return (chisl1/(double)znam1-chisl2/(double)znam2);
		}
		double SubDrobBA()
		{// РАЗНОСТЬ В-А
			return (chisl2/(double)znam2-chisl1/(double)znam1);
		}
		double MulDrob()
		{// ПРОИЗВЕДЕНИЕ ДРОБЕЙ
			return (chisl2/(double)znam2*chisl1/(double)znam1);
		}
		double DivDrobAB()
		{// ДЕЛЕНИЕ А/B
			return ((chisl1/(double)znam1)/(chisl2/(double)znam2));
		}
		double DivDrobBA()
		{// ДЕЛЕНИЕ B/A
			return ((chisl2/(double)znam2)/(chisl1/(double)znam1));
		}
		int gcd()
		{// НОД (ВСПОМАГАТЕЛЬНЫЙ МЕТОД)
			while (znam1 != znam2)
			  if (znam1 > znam2)
				znam1 -= znam2;
			  else
				znam2 -= znam1;
			return znam1;
		}// НОК
		int NokDrobBA () 
		{
			return (znam1  * znam2)/ gcd ();
		}
	};
	DrobCS drob;
	drob.InputDrob();
	drob.OutputDrob();
	std::cout<<"\nA+B="<<drob.SumDrob();
	std::cout<<"\nA-B="<<drob.SubDrobAB();
	std::cout<<"\nB-A="<<drob.SubDrobBA();
	std::cout<<"\nA*B="<<drob.MulDrob();
	std::cout<<"\nA/B="<<drob.DivDrobAB();
	std::cout<<"\nB/A="<<drob.DivDrobBA();
	std::cout<<"\nNOK(A,B)="<<drob.NokDrobBA();
	_getch();
}