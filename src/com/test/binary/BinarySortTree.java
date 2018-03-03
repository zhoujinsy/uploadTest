package com.test.binary;

public class BinarySortTree {
	private BinaryNode sentinel=new BinaryNode();
	final private boolean leftDirect=true;
	final private boolean rightDirect=false;
	
	//添加节点入口方法
	public void add(int value){
		//哨兵节点左节点初始化，防止传空节点
		if(sentinel.leftNode==null){
			BinaryNode sonNode=new BinaryNode();
			sonNode.data=value;
			sentinel.leftNode=sonNode;
			return;
		}
		add(value,sentinel.leftNode);
	};
	//添加节点递归方法
	private void add(int value,BinaryNode currNode){
		//currNode.data>value，左子树处理
		if(currNode.data>value){
			if(currNode.leftNode==null){
				BinaryNode sonNode=new BinaryNode();
				sonNode.data=value;
				currNode.leftNode=sonNode;
				return;
			}
			add(value,currNode.leftNode);
		}
		//currNode.data<value，右子树处理
		else if(currNode.data<value){
			if(currNode.rightNode==null){
				BinaryNode sonNode=new BinaryNode();
				sonNode.data=value;
				currNode.rightNode=sonNode;
				return;
			}
			add(value,currNode.rightNode);
		}
		//currNode.data==value，节点重复处理
		else{
			System.out.println("节点"+value+"已经存在");
		}
	};
	
	//中序遍历入口方法
	public void preorderTraversal(){
		if(sentinel.leftNode==null){
			System.out.println("树为空");
			return;
		}
		preorderTraversal(sentinel.leftNode);
	};
	//中序遍历递归方法
	private void preorderTraversal(BinaryNode currNode) {
		//校验当前节点是否为空，防止出现空指针异常
		if(currNode==null){
			return;
		}
		preorderTraversal(currNode.leftNode);
		System.out.print(currNode.data+", ");
		preorderTraversal(currNode.rightNode);
	};
	
	//先序遍历入口方法
	public void inorderTraversal(){
		if(sentinel.leftNode==null){
			System.out.println("树为空");
			return;
		}
		inorderTraversal(sentinel.leftNode);
	};
	//先序遍历递归方法
	private void inorderTraversal(BinaryNode currNode){
		//校验当前节点是否为空，防止出现空指针异常
				if(currNode==null){
					return;
				}
				System.out.print(currNode.data+", ");
				inorderTraversal(currNode.leftNode);
				inorderTraversal(currNode.rightNode);
	};
	
	//删除节点入口方法
	public BinaryNode deleteNode(int value){
		if(sentinel.leftNode==null){
			System.out.println("树为空");
			return null;
		}
		return deleteNode(value,sentinel,sentinel.leftNode,leftDirect);
	};
	
	//删除节点递归方法
	private BinaryNode deleteNode(int value,BinaryNode parentNode,BinaryNode currNode,boolean isleft){
		if(currNode==null){
			System.out.println("没有此节点");
			return null;
		}
		if(currNode.data>value){
			//currNode.data>value，向树的左向搜索
			return deleteNode(value,currNode,currNode.leftNode,leftDirect);
		}else if(currNode.data<value){
			//currNode.data<value，向树的右向搜索
			return deleteNode(value,currNode,currNode.rightNode,rightDirect);
		}else{
			//currNode.data==value，删除当前节点
			BinaryNode deletedNode;
			//如果是左向,deletedNode=parentNode.leftNode
			if(isleft){
				if(currNode.leftNode==null){
					//如果为叶子节点，左右子节点均为空
					if(currNode.rightNode==null){
						deletedNode=currNode;
						parentNode.leftNode=null;
					}else{
					//左为空而右不为空
						deletedNode=currNode;
						parentNode.leftNode=currNode.rightNode;
					}
				}else{
					//右为空而左不为空
					if(currNode.rightNode==null){
						deletedNode=currNode;
						parentNode.leftNode=currNode.leftNode;
					}else{
					//左右均不为空，需要做调整
						deletedNode=currNode;
						BinaryNode preNode=currNode;
						BinaryNode replaceNode=currNode.rightNode;
						while(replaceNode.leftNode != null){
							preNode=replaceNode;
							replaceNode=replaceNode.leftNode;
						}
						
						if(preNode!=deletedNode){
							preNode.leftNode=replaceNode.rightNode;
							replaceNode.leftNode=deletedNode.leftNode;
							replaceNode.rightNode=deletedNode.rightNode;
							parentNode.leftNode=replaceNode;
						}else{
							replaceNode.leftNode=deletedNode.leftNode;
							parentNode.leftNode=replaceNode;
						}
						 
					}
				}	
			}
			
			//如果是右向
			else{
				if(currNode.leftNode==null){
					//如果为叶子节点，左右子节点均为空
					if(currNode.rightNode==null){
						deletedNode=currNode;
						parentNode.rightNode=null;
					}else{
					//左为空而右不为空
						deletedNode=currNode;
						parentNode.rightNode=currNode.rightNode;
					}
				}else{
					//右为空而左不为空
					if(currNode.rightNode==null){
						deletedNode=currNode;
						parentNode.rightNode=currNode.leftNode;
					}else{
					//左右均不为空，需要做调整
						deletedNode=currNode;
						BinaryNode preNode=currNode;
						BinaryNode replaceNode=currNode.rightNode;
						while(replaceNode.leftNode != null){
							preNode=replaceNode;
							replaceNode=replaceNode.leftNode;
						}
						
						if(preNode!=deletedNode){
							preNode.leftNode=replaceNode.rightNode;
							replaceNode.leftNode=deletedNode.leftNode;
							replaceNode.rightNode=deletedNode.rightNode;
							parentNode.rightNode=replaceNode;
						}else{
							replaceNode.leftNode=deletedNode.leftNode;
							parentNode.rightNode=replaceNode;
						}
						 
					}
				}	
			}
			return deletedNode;
		}
	}
	
	//删除调整节点方法
}
