#pragma once;
#include <iostream>
#include "FacultyEntity.h"
#include <iostream>
#include <Windows.h>
#include "Console.h"
#include <vector>
using namespace std;
struct Cafedra
{
	int id;
	char * zavkafedroy_fio;
	char * name;
};
struct Faculty
{
	int id;
	char * decan_fio;
	char * name;
};
struct Student
{
	int id;
	char * first_name;
	char * second_name;
	char * third_name;
	int average_grade;
	int course;
	char * speciality;
};
struct LinkFacultyCafedraStruct
 {
 Faculty *faculty;
 Cafedra *cafedra;
 };

struct LinkCafedraStudentStruct
 {
 Cafedra *cafedra;
 Student *student;
 };


typedef vector<LinkFacultyCafedraStruct> LinkFacultyCafedraArray;
 LinkFacultyCafedraArray LinkFacultyCafedra;
 LinkFacultyCafedraArray::iterator LinkFacultyCafedraIterator;
 typedef vector<LinkCafedraStudentStruct> LinkCafedraStudentStructArray;
 LinkCafedraStudentStructArray LinkCafedraStudent;
  LinkCafedraStudentStructArray::iterator LinkCafedraStudentIterator;
 void AddLinkFacultyCafedra(Faculty *f, Cafedra *c)
 {
 LinkFacultyCafedraStruct b;
 b.faculty = f;
 b.cafedra = c;
 LinkFacultyCafedra.insert(LinkFacultyCafedra.end(),1,b);

 }

 void AddLinkCafedraStudent(Cafedra *c ,Student *s)
 {
 LinkCafedraStudentStruct b;
 b.cafedra = c;
 b.student = s;
 LinkCafedraStudent.insert(LinkCafedraStudent.end(),1,b);

 }
