//treetest.cpp 
//Проверка класса Tree 
#include <iostream> 
#include <iomanip> 
#include "tree.h" 
using namespace std;
void main() 
{ 
//Создание объекта класса Tree<int> 
        Tree<int> intTree; 
        int intVal; 
        cout <<"Input 10 integer number: " << endl; 
//Заполнение бинарного дерева поиска 
        for (int i=0; i<10; i++) 
        { 
               cin>> intVal; 
               intTree.insertNode(intVal); 
        } 
//Вывод значений узлов бинарного дерева поиска 
//прямым, последовательным и обратным обходом 
        cout << endl << "Prefix ordered Traversal" << endl; 
        intTree.preOrderTraversal(); 

        cout << endl << "Infix ordered Traversal" << endl; 
        intTree.inOrderTraversal(); 

        cout << endl << "Postfix ordered Traversal" << endl; 
        intTree.postOrderTraversal(); 
//Создание объекта класса Tree<float> 
        Tree<float> floatTree; 
        float floatVal; 
//Установка параметров вывода с помощью манипуляторов, 
//описанных в <iomanip.h> 
        cout << endl << endl << endl 
                <<"Input 10 float number: " 
                << endl << setiosflags(ios::fixed | ios::showpoint) 
                << setprecision (1); 
//Вывод значений узлов бинарного дерева поиска 
//прямым, последовательным и обратным обходом 

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

