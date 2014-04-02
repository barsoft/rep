#include "List2.h";

void ListOfVehicles::AddNew()
 {
		StructVehicle b;
	    cout<<"Fill the attributes of Vehicle"<<endl;
	    cout<<"Type: ";
		cin>>b.Type;
		cout<<"State: ";
		cin>>b.State;
		cout<<"Capacity: ";
		cin>>b.Capacity;
		cout<<"MaxSpeed: ";
		cin>>b.MaxSpeed;
		cout<<"Number: ";
		cin>>b.Number;
		

		vehicle.insert(vehicle.end(),1,b); 
 }

 void ListOfVehicles::ShowList()
 {
	 StructVehicle c;
	 for(i = vehicle.begin(); i!=vehicle.end(); i++)
	 {
		 c = *i;
		
		 cout<< c.Type<<"  "<<c.State<<"  "<<c.Capacity<<"  "<<c.MaxSpeed<<"  "<<c.Number<<endl;

	 }

 
 }

  void ListOfVehicles::ShowKeys()
 {
	 cout<<"Number of vehicles:";
	 
	 StructVehicle c;
	 for(i = vehicle.begin(); i!=vehicle.end(); i++)
	 {
		 c = *i;
		 cout<< c.Number<<", ";

	 }
	 cout<<endl;
}


LISTVEHICLE::iterator ListOfVehicles::SearchVehicleByKey()
{
	string num;
	bool flag;
	flag = false;	
	
	while(flag != true)
	{	ShowKeys();
		cout<<"Please select number of vehicle:";
		cin>>num;
		StructVehicle c;
		for(i = vehicle.begin(); i!=vehicle.end(); )
		{
			c = *i;
			if(c.Number == num)
			{ 
				flag = true;
				break;
			}
			i++;


		}
		system("cls");
	}


return i;
}


void ListOfVehicles::Del(string a)
{
	
	StructVehicle c;
	i = vehicle.begin();
	
	while(i !=vehicle.end())
	{
		c= *i;

		if(c.Number == a)
		{				
			i = (vehicle.erase(i));
			break;
		}
		else
			++i;
	}

}
void ListOfVehicles::ChangeVehicle(LISTVEHICLE::iterator j)
{
		StructVehicle c;
	    cout<<"Fill the new attributes of Vehicle"<<endl;
	    cout<<"Type: ";
		cin>>c.Type;
		cout<<"State: ";
		cin>>c.State;
		cout<<"Capacity: ";
		cin>>c.Capacity;
		cout<<"MaxSpeed: ";
		cin>>c.MaxSpeed;
		cout<<"Number: ";
		cin>>c.Number;

		(&(*j))->Type = c.Type;
		(&(*j))->State = c.State;
		(&(*j))->Capacity = c.Capacity;
		(&(*j))->MaxSpeed = c.MaxSpeed;
		(&(*j))->Number = c.Number;
}