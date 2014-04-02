#include "FacultyEntity.h"
#include "CafedraEntity.h"
#include "StudentEntity.h"
class UniversityEntity
{
	char * name;
public:
	UniversityEntity()
	{
		name=new char[80];
	}
	void InputUniversityName();
	char* GetUniversityName();
	FacultyEntity faculty;
	CafedraEntity cafedra;
	StudentEntity student;
};
void UniversityEntity::InputUniversityName()
{
	cin>>name;
}
char * UniversityEntity::GetUniversityName()
{
	return name;
}