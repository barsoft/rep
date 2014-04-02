#include<iostream>
#include<list>
#include<string>
using namespace std;

 struct StructDriver{
						string Name;
						int EXP;
						int Old;
						int NumCrash;
						int Paiment;
					};

 typedef list<StructDriver> LISTDRIVER; 
 
 class ListOfDrivers

 {     
		public:
		LISTDRIVER driver;
		LISTDRIVER::iterator i;
		 
	
		void AddNew();
		void ShowList();
		void ShowKeys();
		void Del(string a);
		LISTDRIVER::iterator SearchDriverByKey();
		void ChangeDriver(LISTDRIVER::iterator j);

		
 };

 


