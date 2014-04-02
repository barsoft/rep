#include<iostream>
#include<list>
#include<string>
using namespace std;

 struct StructVehicle{
					string Type;
					string State;
					int Capacity;
					int MaxSpeed;
					string Number;
					};

 typedef list<StructVehicle> LISTVEHICLE; 
 
 class ListOfVehicles
 {     
		public:
		LISTVEHICLE vehicle;
		LISTVEHICLE::iterator i;
		 
		void AddNew();
		void ShowList();
		void ShowKeys();
		void Del(string a);
		LISTVEHICLE::iterator SearchVehicleByKey();
		void ChangeVehicle(LISTVEHICLE::iterator j);
		
 };