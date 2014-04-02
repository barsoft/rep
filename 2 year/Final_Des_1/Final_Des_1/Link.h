#include "List1.h"
#include "List2.h"
#include "List3.h"
ListOfRoutes A;
ListOfVehicles B;
ListOfDrivers C;
struct StructLinkUp
	{
		LISTROUTE::iterator  LRoute;
		LISTVEHICLE::iterator LVehicle;

	};

 typedef list<StructLinkUp> LISTLINKSUP;
 LISTLINKSUP LinkUp;

 void AddLinkUp(LISTROUTE::iterator a, LISTVEHICLE::iterator c )
 {
	 StructLinkUp b;
	 b.LRoute = a;
	 b.LVehicle = c;

	 LinkUp.insert(LinkUp.end(),1,b);

 }


 struct StructLinkDown
	{
		LISTVEHICLE::iterator  LVehicle;
		LISTDRIVER::iterator LDriver;

	};

 typedef list<StructLinkDown> LISTLINKSDOWN;
 LISTLINKSDOWN LinkDown;

 void AddLinkDown(LISTVEHICLE::iterator a, LISTDRIVER::iterator c )
 {
	 StructLinkDown b;
	 b.LVehicle = a;
	 b.LDriver = c;

	 LinkDown.insert(LinkDown.end(),1,b);

 } 
 
 
 
 void testup()
 {
	 LISTLINKSUP::iterator i;
	 for(i = LinkUp.begin();i != LinkUp.end(); i++)
	 {
		 StructLinkUp c;
		 StructRoute c1;
		 StructVehicle c2;
		 c = *i;
		 c1 = *(c.LRoute);
		 c2 = *(c.LVehicle);
		 cout<<c1.NumberRoute<<": "<<c2.Number<<endl;	
	 }
}

  void testdown()
 {
	 LISTLINKSDOWN::iterator i;
	 for(i = LinkDown.begin();i != LinkDown.end(); i++)
	 {
		 StructLinkDown c;
		 StructVehicle c1;
		 StructDriver c2;
		 c = *i;
		 c1 = *(c.LVehicle);
		 c2 = *(c.LDriver);
		 cout<<c1.Number<<": "<<c2.Name<<endl;	
	 }
}


void BreakTheLinkR(int a)
{
	LISTLINKSUP::iterator i = LinkUp.begin();
	StructLinkUp c;
	StructRoute c1;

	while(i != LinkUp.end() )
	{
		c = *i;
		c1 = *(c.LRoute);



		if(c1.NumberRoute == a)
			i = LinkUp.erase(i);
		else 
			++i;
	}
}

void BreakTheLinkV1(string a)
{
	LISTLINKSUP::iterator i = LinkUp.begin();
	StructLinkUp c;
	StructVehicle c1;

	while(i != LinkUp.end() )
	{
		c = *i;
		c1 = *(c.LVehicle);



		if(c1.Number == a)
			i = LinkUp.erase(i);
		else 
			++i;
	}
}

void BreakTheLinkV2(string a)
{
	LISTLINKSDOWN::iterator i = LinkDown.begin();
	StructLinkDown c;
	StructVehicle c1;

	while(i != LinkDown.end() )
	{
		c = *i;
		c1 = *(c.LVehicle);

		if(c1.Number == a)
			i = LinkDown.erase(i);
		else 
			++i;
	}
}

void BreakTheLinkD(string a)
{
	LISTLINKSDOWN::iterator i = LinkDown.begin();
	StructLinkDown c;
	StructDriver c1;

	while(i != LinkDown.end() )
	{
		c = *i;
		c1 = *(c.LDriver);

		if(c1.Name == a)
			i = LinkDown.erase(i);
		else 
			++i;
	}
}

void RequestName()
{
	LISTROUTE::iterator j;
	LISTLINKSUP::iterator i;
	LISTLINKSDOWN::iterator k;
	j = A.SearchRouteByKey();
	for(i = LinkUp.begin();i != LinkUp.end();i++)
	{
		StructLinkUp c;
		c = *i;
		
		
		if(c.LRoute == j)
		{
			
			StructVehicle d;
			d = *(c.LVehicle);
			if(d.Capacity < 30)
			{
				for(k = LinkDown.begin();k != LinkDown.end();k++)
				{
					StructLinkDown f;
					f = *k;
					if(f.LVehicle == c.LVehicle)
						{
							StructDriver dri;
							dri = *(f.LDriver);
							cout<<dri.Name<<", ";
						}
				}

				
			}

		
		
		}


	}
	cout<<endl;
}

void RequestState()
{
	LISTROUTE::iterator j;
	LISTLINKSUP::iterator i;
	LISTLINKSDOWN::iterator k;
	
	for(i = LinkUp.begin();i != LinkUp.end();i++)
	{
		StructLinkUp c;
		StructRoute rt;
		c = *i;
		rt = *(c.LRoute);
		
		if(rt.Length > 15 )
		{
			
			StructVehicle d;
			d = *(c.LVehicle);
			
			
				for(k = LinkDown.begin();k != LinkDown.end();k++)
				{
					StructLinkDown f;
					f = *k;
					if(f.LVehicle == c.LVehicle)
						{
							StructDriver dri;
							dri = *(f.LDriver);
							if(dri.Old > 50)
							{
								cout<<d.State;
							}


						}
				}

				
			

		
		
		}


	}
	cout<<endl;

}

void RequestCapacity()
{
	LISTROUTE::iterator j;
	LISTLINKSUP::iterator i;
	LISTLINKSDOWN::iterator k;
	
	for(i = LinkUp.begin();i != LinkUp.end();i++)
	{
		StructLinkUp c;
		StructRoute rt;
		c = *i;
		rt = *(c.LRoute);
		
		if(rt.StartWorkingDay == 5 )
		{
			
			StructVehicle d;
			d = *(c.LVehicle);
			
			
				for(k = LinkDown.begin();k != LinkDown.end();k++)
				{
					StructLinkDown f;
					f = *k;
					if(f.LVehicle == c.LVehicle)
						{
							StructDriver dri;
							dri = *(f.LDriver);
							if(dri.EXP >= 10)
							{
								cout<<d.Capacity;
							}


						}
				}

				
			

		
		
		}


	}
	cout<<endl;

}