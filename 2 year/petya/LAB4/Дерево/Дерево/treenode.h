//treenode.h Определение класса TreeNode 
#ifndef TREENODE_H 
#define TREENODE_H 

//Прототип класса Tree 
template<class NODETYPE> 
class Tree; 
//Объявление объекта для узла бинарного дерева с помощью 
//шаблона бинарного дерева 
template<class NODETYPE> 
class TreeNode 
{ 
//Объявление класса Tree, как дружественного данному классу, 
//что обеспечит классу Tree доступ к закрытым данным класса 
//TreeNode (*leftPtr,*rightPtr,       data) 
        friend class Tree<NODETYPE>; 

        public: 
               TreeNode(const NODETYPE&);//Конструктор 
               NODETYPE getData() const; //Возвращение данных 
        private: 
               TreeNode *leftPtr;              //Указатель на левый дочерний узел 
               NODETYPE data;                                 //Значение в узле дерева 
               TreeNode *rightPtr;             //Указатель на правый дочерний узел 

}; 

//Конструктор устанавливает начальное значение узла (первым 
//будет идти корень) и нулевые указатели на левое и правое  
//поддеревья 

template<class NODETYPE> 
TreeNode<NODETYPE>::TreeNode(const NODETYPE& d) 
{ 
        data = d; 
        leftPtr=rightPtr=NULL; 

} 

//Возвращение копии значений данных 
template<class NODETYPE> 
NODETYPE TreeNode<NODETYPE>::getData() const 
{ return data;} 

#endif 