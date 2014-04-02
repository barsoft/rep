//Tree.h 

#ifndef TREE_H 
#define TREE_H 

#include <iostream> 
#include <assert.h> 
#include "treenode.h" 
using namespace std;
//���������� ������� ��� ���� ��������� ������ ������  
//� ������� ������� ��������� ������ ������ 

template<class NODETYPE> 
class Tree 
{ 
        public: 

        Tree();                        //����������� 
//��������� �������� � ���� ������ 
        void insertNode(const NODETYPE&); 
//����� ������ �������� �������� � ������ �������� � �����  
        void preOrderTraversal() const; 
        void inOrderTraversal() const; 
        void postOrderTraversal() const; 
       private: 
//���������� ��������� �� �������� ���� ������ 
               TreeNode<NODETYPE> *rootPtr; 
//��������� ���� � ������ � �������� ��������� ���� 
       void insertNodeHelper(TreeNode<NODETYPE> **,const NODETYPE&); 

       void preOrderHelper(TreeNode<NODETYPE> *) const; 
       void inOrderHelper(TreeNode<NODETYPE> *) const; 
       void postOrderHelper(TreeNode<NODETYPE> *) const; 
}; 

//�����������, ���������� ������ ����������� ������ 
template<class NODETYPE> 
Tree<NODETYPE>::Tree(){ rootPtr=NULL;} 

template<class NODETYPE> 
void Tree<NODETYPE>::insertNode(const NODETYPE& value) 
        {      insertNodeHelper(& rootPtr, value); 
        } 
//���������� ��������� ������� ������ �������� �  
//�������� ������ ������  
template<class NODETYPE> 
void Tree<NODETYPE>::insertNodeHelper(TreeNode<NODETYPE> ** ptr, 
                                                            const NODETYPE& value) 
{ 
        if (*ptr==NULL) 
        { 
               *ptr=new TreeNode<NODETYPE>(value); 
               assert(*ptr!=NULL); 
        } 
        else 
               if(value<(*ptr)->data) 
                      insertNodeHelper(& ((*ptr)->leftPtr),value); 
               else 
                      if(value>(*ptr)->data) 
                      insertNodeHelper(& ((*ptr)->rightPtr),value); 
               else 
                      cout << value << " Dub" <<endl; 
} 

template<class NODETYPE> 
void Tree<NODETYPE>::preOrderTraversal() const 
        {      preOrderHelper(rootPtr);} 

template<class NODETYPE> 
void Tree<NODETYPE>::preOrderHelper(TreeNode<NODETYPE> *ptr) const 
{ 
       if (ptr!=NULL) 
        { 
               cout <<ptr->data <<' '; 
               preOrderHelper(ptr->leftPtr); 
               preOrderHelper(ptr->rightPtr); 
        } 
} 

template<class NODETYPE> 
void Tree<NODETYPE>::inOrderTraversal() const 
        {      postOrderHelper(rootPtr);} 

template<class NODETYPE> 
void Tree<NODETYPE>::inOrderHelper(TreeNode<NODETYPE> *ptr) const 
{ 
       if (ptr!=NULL) 
        { 
               inOrderHelper(ptr->leftPtr); 
               cout <<ptr->data <<' '; 
               inOrderHelper(ptr->rightPtr); 
        } 
} 

template<class NODETYPE> 
void Tree<NODETYPE>::postOrderTraversal() const 
        {      postOrderHelper(rootPtr);} 

template<class NODETYPE> 
void Tree<NODETYPE>::postOrderHelper(TreeNode<NODETYPE> *ptr) const 
{ 
       if (ptr!=NULL) 
        { 
               postOrderHelper(ptr->leftPtr); 
               postOrderHelper(ptr->rightPtr); 
               cout <<ptr->data <<' '; 

        } 
} 

#endif 