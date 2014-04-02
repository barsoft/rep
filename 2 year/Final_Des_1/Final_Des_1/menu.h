#include "Link.h"


string number;
string name;

void MenuRouts()
{
	int choice;
	do
		{
			cout<<"1 - Add new route"<<endl;
			cout<<"2 - Remove route"<<endl;
			cout<<"3 - Display list(routs)"<<endl;
			cout<<"4 - Add new vehicle"<<endl;
			cout<<"5 - Display list(vehicles)"<<endl;
			cout<<"6 - Add new driver"<<endl;
			cout<<"7 - Display list(drivers)"<<endl;
			cout<<"8 - Add connection(route-vehicle/vehicle-route)"<<endl;
			cout<<"9 - Add connection(vehicle-driver/driver-vehicle"<<endl;
			cout<<"10 - Delete route"<<endl;
			cout<<"11 - Delete vehicle"<<endl;
			cout<<"12 - Delete Driver"<<endl;
			cout<<"13 - Change route"<<endl;
			cout<<"14 - Change vehicle"<<endl;
			cout<<"15 - Change driver"<<endl;
			cout<<"16 - Request name"<<endl;
			cout<<"17 - Request state"<<endl;
			cout<<"18 - Request capacity"<<endl;
			cout<<"0 - Exit"<<endl;
			cin>>choice;
			system("cls");
			
			switch (choice)
				{
					case 1: 					
							A.AddNew();
							system("cls");
							break;
					case 2: 
							cout<<"Number of element to remove: ";							
							break;
					case 3: 
							cout<<"List of routs:"<<endl;
							A.ShowList();
							break;	
					case 4: 
							B.AddNew();
						    cout<<"creation of links"<<endl;
							AddLinkUp( A.SearchRouteByKey() ,--(B.vehicle.end()) );
							break;
					case 5: 
							cout<<"List of vehicles:"<<endl;
							B.ShowList();
							break;
					case 6: 
							C.AddNew();
							cout<<"creation of links"<<endl;	
							AddLinkDown( B.SearchVehicleByKey() ,--(C.driver.end()) );
							break;
					case 7: 
							cout<<"List of vehicles:"<<endl;
						    C.ShowList();
							break;
					case 8:
							cout<<"Select the route number and the number of cars that you want to associate."<<endl;
							AddLinkUp( A.SearchRouteByKey() ,B.SearchVehicleByKey() );
							break;
					
					case 9:
							cout<<"Select the number of car and the name of driver that you want to associate."<<endl;
							AddLinkDown( B.SearchVehicleByKey() ,C.SearchDriverByKey() );
							break;
					
					case 10:
							int num;
							cout<<"Enter number of route:"<<endl;
							cin>>num;	
							BreakTheLinkR(num);
							A.Del(num);
							break;			
					
					case 11:							
							cout<<"Enter number of vehicle"<<endl;
							cin>>number;
							BreakTheLinkV1(number);
							BreakTheLinkV2(number);
							B.Del(number);
							break;
					case 12:
							cout<<"Enter Name of Driver"<<endl;
							cin>>name;
							BreakTheLinkD(name);
							C.Del(name);

					case 13:
							A.ChangeRoute( A.SearchRouteByKey() );
							break;
					
					case 14:
							B.ChangeVehicle( B.SearchVehicleByKey() );
							break;
					case 15:
							C.ChangeDriver( C.SearchDriverByKey() );
							break;
					case 16:
							RequestName();
							break;
					case 17:
							RequestState();
							break;
					case 18:
							RequestCapacity();
							break;
					
					case 19:
							testup();
							break;

					case 20:
							testdown();
							break;

				}
		
		}
	while(choice!=0);
	

}



