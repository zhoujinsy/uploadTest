package com.test.redblacktree;

public class RBTree {
	//用作标志当前节点是否已经被遍历输出。
	public boolean notT=true;
	public final static int RED=0;
	public final static int BLACK=1;
	private RBNode root;
	
	private void leftRotate(RBNode center){
		RBNode PNode;//存放旋转节点的父节点-
		PNode =center.parent;
		//center是被旋转下来的节点
		if(PNode==null){
			//表明center是根节点
			root=center.rNode;
			//将根节点的parent指针变null
			root.parent=null;
			center.rNode=root.lNode;
			if(root.lNode!=null){
				center.rNode.parent=center;
			}
			root.lNode=center;
			center.parent=root;
			return;
		}
		if(center==PNode.lNode){
			PNode.lNode=center.rNode;
			PNode.lNode.parent=PNode;
			center.rNode=PNode.lNode.lNode;
			if(PNode.lNode.lNode!=null){
				center.rNode.parent=center;
			}
			PNode.lNode.lNode=center;
			center.parent=PNode.lNode;
			return;
		}
		if(center==PNode.rNode){
			PNode.rNode=center.rNode;
			PNode.rNode.parent=PNode;
			center.rNode=PNode.rNode.lNode;
			if(PNode.rNode.lNode!=null){
				center.rNode.parent=center;
			}
			PNode.rNode.lNode=center;
			center.parent=PNode.rNode;
			return;
		}
	}
	
	private void rightRotate(RBNode center){
		RBNode PNode;//存放旋转节点的父节点
		PNode =center.parent;
		//center是被旋转下来的节点
		if(PNode==null){
			//表明center是根节点
			root=center.lNode;
			//将根节点的parent指针变null
			root.parent=null;
			center.lNode=root.rNode;
			if(root.rNode!=null){
				center.lNode.parent=center;
			}
			root.rNode=center;
			center.parent=root;
			return;
			
		}
		if(center==PNode.lNode){
			PNode.lNode=center.lNode;
			PNode.lNode.parent=PNode;
			center.lNode=PNode.lNode.rNode;
			if(PNode.lNode.rNode!=null){
				center.lNode.parent=center;
			}
			PNode.lNode.rNode=center;
			center.parent=PNode.lNode;
			return;
		}
		if(center==PNode.rNode){
			PNode.rNode=center.lNode;
			PNode.rNode.parent=PNode;
			center.lNode=PNode.rNode.rNode;
			if(PNode.rNode.rNode!=null){
				center.lNode.parent=center;
			}
			PNode.rNode.rNode=center;
			center.parent=PNode.rNode;
			return;
		}
	}
	//颜色置换
	private void colorChange(RBNode first,RBNode second){
		int middle;
		middle=first.color;
		first.color=second.color;
		second.color=middle;
	}
	
	public void addNode(int value){
		RBNode sNode=new RBNode();//new一个新节点，存放data数据
		sNode.data=value;
		
		//添加
		if(root==null){
			root=sNode;//如果根节点为null，则添加为根节点。
			root.color=RBTree.BLACK;
			return;
		}
		RBNode curr = root;
		while(curr!=null){
			if(curr.data>value){//表明向左添加
				if(curr.lNode==null){
					curr.lNode=sNode;//如果当前节点左节点为空，直接将新节点添加进来
					sNode.parent=curr;
					break;
				};
				curr=curr.lNode;
				continue;
			}else if(curr.data<value){//表明向右添加
				if(curr.rNode==null){
					curr.rNode=sNode;//如果当前节点右节点为空，直接将新节点添加进来
					sNode.parent=curr;
					break;
				};
				curr=curr.rNode;
				continue;
			}else{
				System.out.println("节点已经存在");
				break;
			}
		}
		
		//调整
		while(sNode!=root&&sNode.parent.color!=RBTree.BLACK){
			//被添加节点是根节点或者父节点color是黑色，均不需要操作
			//如果sNode.parent.color!=RBTree.BLACK则sNode一定有爷爷节点，应为root的颜色肯定是black
			if(sNode.parent==sNode.parent.parent.lNode){
				if(sNode.parent.parent.rNode==null||sNode.parent.parent.rNode.color==RBTree.BLACK){
					//如果叔叔节点是null或者是black
					if(sNode==sNode.parent.lNode){
						//添加节点是父节点的左节点（左左）
						colorChange(sNode.parent,sNode.parent.parent);
						rightRotate(sNode.parent.parent);
						return;
					}else{
						//添加节点是父节点的右节点（左右）
						leftRotate(sNode.parent);
						sNode=sNode.lNode;
						continue;
					}
				}else{
					//叔叔节点是红色
					sNode.parent.color=RBTree.BLACK;
					sNode.parent.parent.rNode.color=RBTree.BLACK;
					sNode.parent.parent.color=RBTree.RED;
					sNode=sNode.parent.parent;
					continue;
				}
			}else{
				//父亲节点是爷爷节点的右节点
				if(sNode.parent.parent.lNode==null||sNode.parent.parent.lNode.color==RBTree.BLACK){
					//如果叔叔节点是null或者是black
					if(sNode==sNode.parent.rNode){//右右
						colorChange(sNode.parent,sNode.parent.parent);
						leftRotate(sNode.parent.parent);
						return;
					}else{
						rightRotate(sNode.parent);
						sNode=sNode.rNode;
						continue;
					}
				}else{
					sNode.parent.color=RBTree.BLACK;
					sNode.parent.parent.lNode.color=RBTree.BLACK;
					sNode.parent.parent.color=RBTree.RED;
					sNode=sNode.parent.parent;
					continue;
				}
			}
		}
		
		root.color=RBTree.BLACK;	
	}
	
	//删除节点。
	public RBNode deleteNode(int value){
		if(root==null){
			System.out.println("树为空");
			return null;
		}
		RBNode deleteNode=null;
		RBNode currNode=null;
		RBNode upNode=null;
		RBNode reUpNode=null;
		
		currNode=root;
		while(currNode!=null){
			if(currNode.data>value){
				if(currNode.lNode==null){
					System.out.println("节点不存在");
					return null;
				}
				currNode=currNode.lNode;
				continue;
			}
			if(currNode.data<value){
				if(currNode.rNode==null){
					System.out.println("节点不存在");
					return null;
				}
				currNode=currNode.rNode;
				continue;
			}
			if(currNode.data==value){
				//将当前节点赋值给deleteNode;
				deleteNode=currNode;
				if(currNode.lNode==null){
					if(currNode.rNode!=null){
					//左子树为空而右子树不为空
						upNode=currNode.rNode;
						upNode.parent=null;
					}
					//左右子树均为空，不给upNode赋值，表明被删除的是叶子节点。叶子节点颜色一定为红是错误的
				}else{
					if(currNode.rNode==null){
					//右子树为空，而左子树不为空。
						upNode=currNode.lNode;
						upNode.parent=null;
					}
					//左右子树均不为空，找右子树的最左子节点。
					upNode=currNode.rNode;
					if(upNode.lNode!=null){
						while(upNode!=null){
							if(upNode.lNode!=null){
								upNode=upNode.lNode;
								continue;
							}
							//给真正的up节点赋值。
								//reUpNode用于调整时，判断上升节点的颜色
								reUpNode=upNode.rNode;
								upNode.parent.lNode=reUpNode;
							if(upNode.rNode!=null){
								reUpNode.parent=upNode.parent;
							}
							
							//重点，将currNode的颜色与upNode的颜色互换，那么currNode就真实记录下了
							//实际删除节点的颜色——upNode的颜色。这样就保证了调整判断中，均用currNode
							//的颜色做判断的一致性。而且upNode上去之后，由于保持了currNode的颜色，
							//此处结构不用改变，因而，只需要调整reUpNode处的结构
							colorChange(currNode, upNode);
							//先保证将currNode.rNode的右节点给到upNode.rNode上
							upNode.rNode=currNode.rNode;
							upNode.rNode.parent=upNode;
							deleteNode.parent=upNode.parent;//这样调整的时候，用currNode去取p b bl br就可以做到一致化操作。
							break;
						}
					}
					//一致化操作，保证左右子树均不为null的两种状况下，currNode.lNode均不会丢失。
					upNode.lNode=currNode.lNode;
					upNode.lNode.parent=upNode;
					upNode.parent=null;
					//至此，currNode，upNode，和reUpNode都已找到并且调整好，退出找节点循环，进行删除操作。
					break;
				}
			}
		}
		//删除操作
		if(currNode==root){
			root=upNode;
			//由于upNode可能为null，所以，应该在上面操作中让upNode.parent=null,防止出现空指针异常
//			root.parent=null;
		}else if(currNode==currNode.parent.lNode){
			currNode.parent.lNode=upNode;
			if(upNode!=null){
				upNode.parent=currNode.parent;
			}
			
		}else{
			currNode.parent.rNode=upNode;
			if(upNode!=null){
				upNode.parent=currNode.parent;
			}
		}
		
		currNode.parent=deleteNode.parent;
		//调整结构
		if(upNode.lNode!=null&&upNode.rNode!=null){
			//让双子树均不为null的情况下的真正reUpNode赋值给upNode，后面利用upNode进行一致性操作。
			upNode=reUpNode;
		}
		//循环条件，被删节点不是叶子节点，被删节点不是根节点，被删节点颜色不是红色(这几种情况均不用操作)
		if(currNode.parent!=null&&currNode.color!=RBTree.RED){
			if(upNode==null){
				upNode=currNode;
			}
			while(upNode!=null&&upNode.parent!=null){
				if(upNode.color==RBTree.RED){
					//upnode是红色，直接变为黑色，重新平衡
					upNode.color=RBTree.BLACK;
					break;
				}
				//当upNode是左子树时
				if(upNode.parent.lNode==null||upNode==upNode.parent.lNode){
					//兄弟节点为red时，交换p和b的颜色，对p做左旋，再循环操作（保证下次循环时，b为黑）
					if(upNode.parent.rNode.color==RBTree.RED){
						colorChange(upNode.parent, upNode.parent.rNode);
						leftRotate(upNode.parent);
						continue;
					}
					//兄弟节点的右子树（br）为red时，对p做左旋，交换p，b颜色，将br变黑，完成平衡，break
					if(upNode.parent.rNode.rNode!=null&&upNode.parent.rNode.rNode.color==RBTree.RED){
						upNode.parent.rNode.rNode.color=RBTree.BLACK;
						colorChange(upNode.parent, upNode.parent.rNode);
						leftRotate(upNode.parent);
						break;
					}
					//兄弟节点的左子树为red（bl为红），右子树为black时，交换b和bl的颜色，对s右旋，继续循环（br为红）
					if(upNode.parent.rNode.lNode!=null&&upNode.parent.rNode.lNode.color==RBTree.RED){
						colorChange(upNode.parent.rNode.lNode, upNode.parent.rNode);
						rightRotate(upNode.parent.rNode);
						continue;
					}
					//上面的情况均不是，表明b,bl,br均为黑
					if(upNode.parent.color==RBTree.RED){
						colorChange(upNode.parent, upNode.parent.rNode);
						break;
					}
					//表明p b bl br全黑，无法平衡，向上转移
					upNode.parent.rNode.color=RBTree.RED;
					upNode=upNode.parent;
					continue;
				}else{
					//当upNode是右子树时
					
					//兄弟节点为red时，交换p和b的颜色，对p做左旋，再循环操作（保证下次循环时，b为黑）
					if(upNode.parent.lNode.color==RBTree.RED){
						colorChange(upNode.parent, upNode.parent.lNode);
						rightRotate(upNode.parent);
						continue;
					}
					//兄弟节点的右子树（br）为red时，对p做左旋，交换p，b颜色，将br变黑，完成平衡，break
					if(upNode.parent.lNode.lNode!=null&&upNode.parent.lNode.lNode.color==RBTree.RED){
						upNode.parent.lNode.lNode.color=RBTree.BLACK;
						colorChange(upNode.parent, upNode.parent.lNode);
						rightRotate(upNode.parent);
						break;
					}
					//兄弟节点的左子树为red（bl为红），右子树为black时，交换b和bl的颜色，对s右旋，继续循环（br为红）
					if(upNode.parent.lNode.rNode!=null&&upNode.parent.lNode.rNode.color==RBTree.RED){
						colorChange(upNode.parent.lNode.rNode, upNode.parent.lNode);
						leftRotate(upNode.parent.lNode);
						continue;
					}
					//上面的情况均不是，表明b,bl,br均为黑
					if(upNode.parent.color==RBTree.RED){
						colorChange(upNode.parent, upNode.parent.lNode);
						break;
					}
					//表明p b bl br全黑，无法平衡，向上转移
					upNode.parent.lNode.color=RBTree.RED;
					upNode=upNode.parent;
					continue;
				}
				
			}
		}
		
		
		if(root!=null){
			root.color=RBTree.BLACK;
		}
		deleteNode.parent=null;
		deleteNode.lNode=null;
		deleteNode.rNode=null;
		return deleteNode;
	}
	
	
	
	//中序遍历
	public void inorderTraversal(){
		if(root==null){
			System.out.println("树为空");
			return;
		}
		RBNode curr=root;
		while(curr!=null){
			if(curr.lNode!=null&&curr.lNode.tSign==notT){
				//左子树不为null并且没有被遍历
				curr=curr.lNode;
				continue;
			}
			if(curr.tSign==notT){
				//当前节点没有别遍历
				System.out.print((curr.color==0?"red":"black")+curr.data+",");
				curr.tSign=!notT;
			}
			if(curr.rNode!=null&&curr.rNode.tSign==notT){
				//右子树不为null并且没有被遍历
				curr=curr.rNode;
				continue;
			}
			if(curr.parent!=null){
				//前面情况说明当前节点和其左右子树都已经遍历，将父节点变成当前节点，继续循环
				curr=curr.parent;
				continue;
			}
			if(curr==root&&curr.tSign!=notT){
				//如果根节点左右子树都已经被遍历打印，并且本身也被遍历打印，表明所有节点均被遍历
				//可以进一步改进，遍历其实在最大节点被遍历后，就已经结束了，所以可以先查询最大节点，
				//再遍历，终结条件设置为当前节点为最大节点，且左右子树都已经被遍历或者为null。
				notT=!notT;
				return;
			}
		}
	}
	
	//先序遍历
	public void preorderTraversal(){
		if(root==null){
			System.out.println("树为空");
			return;
		}
		RBNode curr=root;
		while(curr!=null){
			if(curr.tSign==notT){
				System.out.print((curr.color==0?"red":"black")+curr.data+",");
				curr.tSign=!notT;
			}
			if(curr.lNode!=null&&curr.lNode.tSign==notT){
				curr=curr.lNode;
				continue;
			}
			if(curr.rNode!=null&&curr.rNode.tSign==notT){
				curr=curr.rNode;
				continue;
			}
			if(curr.parent!=null){
				curr=curr.parent;
				continue;
			}
			if(curr==root&&curr.tSign!=notT){
				notT=!notT;
				return;
			}
		}
	}
	
	
	
}
