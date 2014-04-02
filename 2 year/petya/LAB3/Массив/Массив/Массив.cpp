/*Код Присяжного Максима
9) Дано целое число N. Сформировать матрицу a(i,j) порядка N, для которой: a(i,j)=sin(i+j+k);
где k - псевдослучайное число. Упорядочить строки матрицы по убыванию минимальных элементов строк.
*/
#include <iostream>
#include <conio.h>
#include <time.h>
void main()
{
	class ArrayCS
	{
		public:
			// GLOBAL VAIRIABLES
			long double mas[100][100];
			int N;
			// METHODS
			void InputArr() // ВВОД МАССИВА
			{
				int k;
				std::cout<<"Input size of array:"<<'\n';
				std::cin>>N;
				system("cls");
				srand(time(NULL));
				for (int i=0;i<N;i++)
					for (int j=0;j<N;j++)
					{
						k=rand() % 65535 - 32767;
						mas[i][j]=sin(long double(i+j+k));
					}
			}
			void OutputArr() // ВЫВОД МАССИВА
			{
				for (int i=0;i<N;i++)
					{
						for (int j=0;j<N;j++)
						{
							//std::cout<<mas[i][j]<<' ';
							std::cout<<mas[i][j]<<' ';
						}
						std::cout<<'\n';
					}
			}
			void SortArray() // СОРТИРОВКА МАССИВА ПО УБЫВАНИЮ
			{
				std::cout<<"Press ENTER to sort by decrement:"<<"\n\n";
				char key;
				do
				key=_getch();
				while (key!=13);
				long double mas2[100001];
				long double buffer=mas[0][0];
				long double buffer2=0;
				int k=0;
				for (int i=0;i<N;i++)
					{
						buffer=mas[i][0];
						for (int j=1;j<N;j++)	if (mas[i][j]<buffer) buffer=mas[i][j];
						*(mas2+k)=buffer;
						k++;
					}
				buffer=0; 
				std::cout<<"Minimums:\n\n";
				for (int i=0;i<k;i++) std::cout<<mas2[i]<<'\n';
				for (int i=1; i<N ; i++)
					for (int j=0; j<N-i; j++) 
						if (*(mas2 +j+1)>mas2 [j])
							{
								buffer=*(mas2+j);
								*(mas2+j)=*(mas2+j+1);
								*(mas2+j+1)=buffer;
								for (k=0;k<N;k++)
								{
									buffer2=mas[j][k];
									mas[j][k]=mas[j+1][k];
									mas[j+1][k]=buffer2;
								}
							}
				std::cout<<'\n';
				std::cout<<"Sorted minimums:\n\n";
				for (int i=0;i<k;i++) std::cout<<*(mas2+i)<<'\n';
			}	
	};
	ArrayCS a;
	a.InputArr();
	std::cout<<"Your array:"<<"\n\n";
	a.OutputArr();
	std::cout<<'\n';
	a.SortArray();
	std::cout<<'\n';
	std::cout<<"Sorted array:"<<"\n\n";
	a.OutputArr();
	_getch();
}