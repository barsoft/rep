#pragma once
#include <iostream>
#include <vector>
using namespace std;

class Entity
{
protected:
	int IDcounter;
public:
	Entity()
	{
		IDcounter=0;
	}
	virtual int GetItemCount()=0;
	virtual void DeleteItem(int _i)=0;
};