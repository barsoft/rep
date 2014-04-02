#include "List3.h";

void ListOfDrivers::AddNew()
 {
		StructDriver b;
	    cout<<"Fill the attributes of Driver"<<endl;
	    cout<<"Name: ";
		cin>>b.Name;
		cout<<"EXP: ";
		cin>>b.EXP;
		cout<<"Old: ";
		cin>>b.Old;
		cout<<"NumCrash: ";
		cin>>b.NumCrash;
		cout<<"Paiment: ";
		cin>>b.Paiment;
		

		driver.insert(driver.end(),1,b);
 }

 void ListOfDrivers::ShowList()
 {
	 StructDriver c;
	 for(i = driver.begin(); i!=driver.end(); i++)
	 {
		 c = *i;
		
		 cout<< c.Name<<"  "<<c.EXP<<"  "<<c.Old<<"  "<<c.NumCrash<<"  "<<c.Paiment<<endl;

	 }

 
 }


 void ListOfDrivers::ShowKeys()
 {
	 cout<<"Names of drivers:";
	 
	 StructDriver c;
	 for(i = driver.begin(); i!=driver.end(); i++)
	 {
		 c = *i;
		 cout<< c.Name<<", ";

	 }
	 cout<<endl;
}

 LISTDRIVER::iterator ListOfDrivers::SearchDriverByKey()
{
	string name;
	bool flag;
	flag = false;	
	
	while(flag != true)
	{	ShowKeys();
		cout<<"Please select name of driver:";
		cin>>name;
		StructDriver c;
		for(i = driver.begin(); i!=driver.end(); )
		{
			c = *i;
			if(c.Name == name)
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

 void ListOfDrivers::Del(string a)
{
	
	StructDriver c;
	i = driver.begin();
	
	while(i !=driver.end())
	{
		c= *i;

		if(c.Name == a)
		{				
			i = (driver.erase(i));
			break;
		}
		else
			++i;
	}

}

 void ListOfDrivers::ChangeDriver(LISTDRIVER::iterator j)
{
		StructDriver c;
	    cout<<"Fill the new attributes of Driver"<<endl;
	    cout<<"Name: ";
		cin>>c.Name;
		cout<<"EXP: ";
		cin>>c.EXP;
		cout<<"Old: ";
		cin>>c.Old;
		cout<<"NumCrash: ";
		cin>>c.NumCrash;
		cout<<"Paiment: ";
		cin>>c.Paiment;

		(&(*j))->Name = c.Name;
		(&(*j))->EXP = c.EXP;
		(&(*j))->Old = c.Old;
		(&(*j))->NumCrash = c.NumCrash;
		(&(*j))->Paiment = c.Paiment;
}