//treenode.h ����������� ������ TreeNode 
#ifndef TREENODE_H 
#define TREENODE_H 

//�������� ������ Tree 
template<class NODETYPE> 
class Tree; 
//���������� ������� ��� ���� ��������� ������ � ������� 
//������� ��������� ������ 
template<class NODETYPE> 
class TreeNode 
{ 
//���������� ������ Tree, ��� �������������� ������� ������, 
//��� ��������� ������ Tree ������ � �������� ������ ������ 
//TreeNode (*leftPtr,*rightPtr,       data) 
        friend class Tree<NODETYPE>; 

        public: 
               TreeNode(const NODETYPE&);//����������� 
               NODETYPE getData() const; //����������� ������ 
        private: 
               TreeNode *leftPtr;              //��������� �� ����� �������� ���� 
               NODETYPE data;                                 //�������� � ���� ������ 
               TreeNode *rightPtr;             //��������� �� ������ �������� ���� 

}; 

//����������� ������������� ��������� �������� ���� (������ 
//����� ���� ������) � ������� ��������� �� ����� � ������  
//���������� 

template<class NODETYPE> 
TreeNode<NODETYPE>::TreeNode(const NODETYPE& d) 
{ 
        data = d; 
        leftPtr=rightPtr=NULL; 

} 

//����������� ����� �������� ������ 
template<class NODETYPE> 
NODETYPE TreeNode<NODETYPE>::getData() const 
{ return data;} 

#endif 