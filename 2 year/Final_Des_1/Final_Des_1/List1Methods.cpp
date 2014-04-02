#include "List1.h";

void ListOfRoutes::AddNew()
 {
		StructRoute b;
	    cout<<"Fill the attributes of route"<<endl;
	    cout<<"NumberRoute: ";
		cin>>b.NumberRoute;
		cout<<"StartStation: ";
		cin>>b.StartStation;
		cout<<"FinalStation: ";
		cin>>b.FinalStation;
		cout<<"Length: ";
		cin>>b.Length;
		cout<<"StartWorkingDay: ";
		cin>>b.StartWorkingDay;
		cout<<"FinishWorkingDay: ";
		cin>>b.FinishWorkingDay;

		route.insert(route.end(),1,b); 
 }

 void ListOfRoutes::ShowList()
 {
	 StructRoute c;
	 for(i = route.begin(); i!=route.end(); i++)
	 {
		 c = *i;
		
		 cout<< c.NumberRoute<<"  "<<c.StartStation<<"  "<<c.FinalStation<<"  "<<c.Length<<"  "<<c.StartWorkingDay<<"  "<<c.FinishWorkingDay<<endl;

	 }

 
 }

 void ListOfRoutes::ShowKeys()
 {
	 cout<<"Number of routes:";
	 
	 StructRoute c;
	 for(i = route.begin(); i!=route.end(); i++)
	 {
		 c = *i;
		 cout<< c.NumberRoute<<", ";

	 }
	 cout<<endl;
}


LISTROUTE::iterator ListOfRoutes::SearchRouteByKey()
{
	int num;
	bool flag;
	flag = false;	
	
	while(flag != true)
	{	ShowKeys();
		cout<<"Please select number of route:";
		cin>>num;
		StructRoute c;
		for(i = route.begin(); i!=route.end(); )
		{
			c = *i;
			if(c.NumberRoute == num)
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

void ListOfRoutes::Del(int a)
{
	
	StructRoute c;
	i = route.begin();
	
	while(i !=route.end())
	{
		c= *i;

		if(c.NumberRoute == a)
		{				
			i = (route.erase(i));
			break;
		}
		else
			++i;
	}

}
void ListOfRoutes::ChangeRoute(LISTROUTE::iterator j)
{
	StructRoute c;
	cout<<"Fill the new attributes of route"<<endl;
	    cout<<"NumberRoute: ";
		cin>>c.NumberRoute;
		cout<<"StartStation: ";
		cin>>c.StartStation;
		cout<<"FinalStation: ";
		cin>>c.FinalStation;
		cout<<"Length: ";
		cin>>c.Length;
		cout<<"StartWorkingDay: ";
		cin>>c.StartWorkingDay;
		cout<<"FinishWorkingDay: ";
		cin>>c.FinishWorkingDay;

		(&(*j))->NumberRoute = c.NumberRoute;
		(&(*j))->StartStation = c.StartStation;
		(&(*j))->FinalStation = c.FinalStation;
		(&(*j))->Length = c.Length;
		(&(*j))->StartWorkingDay = c.StartWorkingDay;
		(&(*j))->FinishWorkingDay = c.FinishWorkingDay;
}