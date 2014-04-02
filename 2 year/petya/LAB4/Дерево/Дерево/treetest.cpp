//treetest.cpp 
//�������� ������ Tree 
#include <iostream> 
#include <iomanip> 
#include "tree.h" 
using namespace std;
void main() 
{ 
//�������� ������� ������ Tree<int> 
        Tree<int> intTree; 
        int intVal; 
        cout <<"Input 10 integer number: " << endl; 
//���������� ��������� ������ ������ 
        for (int i=0; i<10; i++) 
        { 
               cin>> intVal; 
               intTree.insertNode(intVal); 
        } 
//����� �������� ����� ��������� ������ ������ 
//������, ���������������� � �������� ������� 
        cout << endl << "Prefix ordered Traversal" << endl; 
        intTree.preOrderTraversal(); 

        cout << endl << "Infix ordered Traversal" << endl; 
        intTree.inOrderTraversal(); 

        cout << endl << "Postfix ordered Traversal" << endl; 
        intTree.postOrderTraversal(); 
//�������� ������� ������ Tree<float> 
        Tree<float> floatTree; 
        float floatVal; 
//��������� ���������� ������ � ������� �������������, 
//��������� � <iomanip.h> 
        cout << endl << endl << endl 
                <<"Input 10 float number: " 
                << endl << setiosflags(ios::fixed | ios::showpoint) 
                << setprecision (1); 
//����� �������� ����� ��������� ������ ������ 
//������, ���������������� � �������� ������� 

        for(int i=0; i<10;i++) 
        { 
                cin >>floatVal; 
                floatTree.insertNode(floatVal); 

        } 
        cout << endl << "Prefix ordered Traversal" << endl; 
        floatTree.preOrderTraversal(); 

        cout << endl << "Infix ordered Traversal" << endl; 
        floatTree.inOrderTraversal(); 

        cout << endl << "Postfix ordered Traversal" << endl; 
        floatTree.postOrderTraversal(); 
        cout << endl; 
} 

