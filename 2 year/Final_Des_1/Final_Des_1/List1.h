#include<iostream>
#include<list>
#include<string>
using namespace std;

 struct StructRoute{
					string StartStation;
					string FinalStation;
					int NumberRoute;
					int Length;
					double StartWorkingDay;
					double FinishWorkingDay;
					};

 typedef list<StructRoute> LISTROUTE; 
 
 class ListOfRoutes

 {     
		public:
		LISTROUTE route;
		LISTROUTE::iterator i;
		 	
		void AddNew();
		void ShowList();
		void ShowKeys();
		void Del(int a);
		LISTROUTE::iterator SearchRouteByKey();
		void ChangeRoute(LISTROUTE::iterator j);
		
 };

 


