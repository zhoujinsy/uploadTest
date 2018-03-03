package com.test.avltreetest;

import com.test.binary.BinaryNode;

public class AVLTree {
	private AVLNode sentinel=new AVLNode();
	final private boolean leftDirect=true;
	final private boolean rightDirect=false;
	private AVLNode deletedNode;
	private AVLNode deleteAdjustNode;
	
    //添加节点入口方法
	/**添加方法中平衡因子的改变,与子树的树高是否增长正相关。
	 * 如果子树原平衡因子为0，而它的子树向它返回一个增长量[1]，那么子树长高，也要向上返回1.
	 * 如果子树原平衡因子为1，而它的子树向他返回一个增长量1，根据左加右减，计算出平衡因子值。如果bf变为0，
	 * 说明子树没有长高，所以向上也返回0，至此，它上面的树都没有长高，所以都返回0.如果bf变为2（或-2），
	 * 则需要对树做调整，调整后，平衡因子为0，向上返回也是0.
	 * 所以说，在递归中向上返回的值是子树的树高变化值。
	 * 有一个方法可以做到反映树高变化值。由于只要子树返回值，和平衡因子值有一个为0，那么树高就为0
	 * 那么将两个值做&运算即可。
	 * 所以，让递归add方法返回一个int值
	 * 在父几点中分别用lSonChange和rSonChange接受子树的变化值，
	 * 子树在做完相应的改变后，返回lSonChange&bf或rSonChange&bf（此时，要注意是否带符号返回。）
	 * 如果不带符号返回，则不平衡的结构（两种情况）信息就会丢失，所以最好是带符号。
	 * 带符号有问题吗？如果我们从递归最底层，直接返回bf，则是带符号，那么上层的lSonChange&bf rSonChange&bf
	 * 能够将符号信息保存下来吗？
	 * 1&(-1)=1
	 * 但是，左子树里绝对不会出现lSonChange=-1，而bf运算后=1的情况，而只会有0或则-1，或则-2的情况，所以不必
	 * 担心
	 * 那么左向的平衡因子运算就为bf+=lSonChange&1(相当于取绝对值)。而右向的平衡因子运算是bf-=lSonChange&1，
	 * 决定两个方向平衡因子值变化的根源在于，从左子树返回的树高变化值，还是从右子树返回的树高变化值。
	 * 
	 * 
	 * 
	 * 
	 * @param value
	 */
	public void add(int value){
		//哨兵节点左节点初始化，防止传空节点
		if(sentinel.leftNode==null){
			AVLNode sonNode=new AVLNode();
			sonNode.data=value;
			sentinel.leftNode=sonNode;
			return;
		}
		add(value,sentinel.leftNode,sentinel,leftDirect);
	};
	//添加节点递归方法
	private int add(int value,AVLNode currNode,AVLNode parentNode,boolean isLDirect){
		//currNode.data>value，左子树处理
		if(currNode.data>value){
			if(currNode.leftNode==null){
				AVLNode sonNode=new AVLNode();
				sonNode.data=value;
				currNode.leftNode=sonNode;
				currNode.bf =currNode.bf+1;
				if(currNode.bf==0){
					return 0;
				}
				return 1;
			}else{
				//左子树不为空，继续向左添加
				int lSonChange = add(value,currNode.leftNode,currNode,leftDirect);
				//虽然在判断左左不平衡和左右不平衡的时候，依赖currNode.leftNode.bf是1还是-1就可以
				//但是lSonChange不可以省略，lSonChange的作用在于向上一层传递子树的高度变化值
				//之所以两者都能用来判断上面的左左和左右结构，在于不平衡点的子树平衡因子一定是1或-1，而且
				//该子树的高度变化也一定是1或-1，两者恰好相等。但是当lSonChange/rSonChange是0时，如果当
				//前节点的bf是1/-1，向parentNode返回变化值（此时应该是0）时，就不能用currNode的bf了。
				currNode.bf =currNode.bf+lSonChange;
				//8, 5, 2, -5, -9, 1, 3, 6, 7, 18, 12, 9, 17, 24, -9, -5, 1, 2, 3, 5, 6, 7, 8, 9, 12, 17, 18, 24, 
				if(currNode.bf==2){
					leftRebalance(currNode, parentNode, isLDirect);
				}
				if(lSonChange==0||currNode.bf==0){
					return 0;
				}
				return 1;
			}
			
		}
		//currNode.data<value，右子树处理
		else if(currNode.data<value){
			if(currNode.rightNode==null){
				AVLNode sonNode=new AVLNode();
				sonNode.data=value;
				currNode.rightNode=sonNode;
				currNode.bf =currNode.bf-1;
				if(currNode.bf==0){
					return 0;
				}
				return 1;
			}
			int rSonChange = add(value,currNode.rightNode,currNode,rightDirect);
			currNode.bf =currNode.bf-rSonChange;
			if(currNode.bf==-2){
				rightRebalance(currNode, parentNode, isLDirect);
			}
			if(rSonChange==0||currNode.bf==0){
				return 0;
			}
			return 1;
		}
		//currNode.data==value，节点重复处理
		else{
			System.out.println("节点"+value+"已经存在");
			return 0;
		}
	};
	
	
	public AVLNode delete(int value){
		if(sentinel.leftNode==null){
			System.out.println("树为空");
			return null;
		}
		delete(value,sentinel.leftNode,sentinel,leftDirect);
		return deletedNode;
	}
	
	private int delete(int value,AVLNode currNode,AVLNode parentNode,boolean isLDirect){
		if(currNode==null){
			System.out.println("没有此节点");
			return 0;
		}
		if(currNode.data>value){
			//currNode.data>value，向树的左向搜索
			int lSonChange = delete(value,currNode.leftNode,currNode,leftDirect);
			currNode.bf=currNode.bf-(lSonChange&1);
			if(currNode.bf==-2){
				rightRebalance(currNode, parentNode, isLDirect);
			}
			if(lSonChange==0||(currNode.bf&1)==1){
				return 0;
			}else{
				return 1;
			}
		}else if(currNode.data<value){
			//currNode.data<value，向树的右向搜索
			int rSonChange=delete(value,currNode.rightNode,currNode,rightDirect);
			currNode.bf=currNode.bf+(rSonChange&1);
			if(currNode.bf==2){
				leftRebalance(currNode, parentNode, isLDirect);
			}
			if(rSonChange==0||(currNode.bf&1)==1){
				return 0;
			}else{
				return 1;
			} 
		}else{
			//currNode.data==value，删除当前节点
			//如果是左向,deletedNode=parentNode.leftNode
			if(isLDirect){
				if(currNode.leftNode==null){
					//如果为叶子节点，左右子节点均为空
						deletedNode=currNode;
						parentNode.leftNode=currNode.rightNode;
					//左为空而右不为空(可以进行一致化操作)
				}else{
					//右为空而左不为空
					if(currNode.rightNode==null){
						deletedNode=currNode;
						parentNode.leftNode=currNode.leftNode;
					}else{
					//左右均不为空，需要做调整
						int rSonChange = deleteAdjust(currNode.rightNode, currNode, rightDirect);
						deletedNode=currNode;
						deleteAdjustNode.leftNode=currNode.leftNode;
						deleteAdjustNode.rightNode=currNode.rightNode;
						deleteAdjustNode.bf=currNode.bf;
						currNode=deleteAdjustNode;
						currNode.bf=currNode.bf+rSonChange;
						if(currNode.bf==2){
							leftRebalance(currNode, parentNode, leftDirect);
						}else{
								parentNode.leftNode=currNode;
						}
						if(rSonChange==0||(currNode.bf&1)==1){
							return 0;
						}	
					}
				}
			}else{
				//如果是左向,deletedNode=parentNode.leftNode
				if(currNode.leftNode==null){
					//如果为叶子节点，左右子节点均为空
						deletedNode=currNode;
						parentNode.rightNode=currNode.rightNode;
					//左为空而右不为空(可以进行一致化操作)
				}else{
					//右为空而左不为空
					if(currNode.rightNode==null){
						deletedNode=currNode;
						parentNode.rightNode=currNode.leftNode;
					}else{
					//左右均不为空，需要做调整
						int rSonChange = deleteAdjust(currNode.rightNode, currNode, rightDirect);
						deletedNode=currNode;
						deleteAdjustNode.leftNode=currNode.leftNode;
						deleteAdjustNode.rightNode=currNode.rightNode;
						deleteAdjustNode.bf=currNode.bf;
						currNode=deleteAdjustNode;
						currNode.bf=currNode.bf+rSonChange;
						if(currNode.bf==2){
							leftRebalance(currNode, parentNode, rightDirect);
						}else{
								parentNode.rightNode=currNode;
						}
						if(rSonChange==0||(currNode.bf&1)==1){
							return 0;
						}	
					}
				}
			}
			return 1;
		}

	}
	
	
	
	private int deleteAdjust(AVLNode currNode,AVLNode parentNode,boolean isLDirect){
		if(currNode.leftNode==null){
			deleteAdjustNode=currNode;
			if(isLDirect){
				parentNode.leftNode=currNode.rightNode;
			}else{
				parentNode.rightNode=currNode.rightNode;
			}
			return 1;
		}else{
			int lSonChange = deleteAdjust(currNode.leftNode, currNode, leftDirect);
			currNode.bf=currNode.bf-lSonChange;
			if(currNode.bf==-2){
				rightRebalance(currNode, parentNode, isLDirect);
			};
			if(lSonChange==0||(currNode.bf&1)==1){
				return 0;
			}else{
				return 1;
			}
		}
	}
	
	
	private void leftRebalance(AVLNode currNode,AVLNode parentNode,boolean isLDirect){
		AVLNode upNode;
		if(currNode.leftNode.bf==1){
		//做左左不平衡调整
				upNode=currNode.leftNode;
				currNode.leftNode=upNode.rightNode;
				upNode.rightNode=currNode;
				currNode=upNode;
				currNode.bf=0;
				currNode.rightNode.bf=0;
			//将调整好的子树赋给父节点
			if(isLDirect){	
				parentNode.leftNode=currNode;
			}else{
				parentNode.rightNode=currNode;
			}
			
		}else if(currNode.leftNode.bf==-1){
		//做左右不平衡调整
			upNode=currNode.leftNode.rightNode;
			int upNodeBf=upNode.bf;
			currNode.leftNode.rightNode=upNode.leftNode;
			upNode.leftNode=currNode.leftNode;
			currNode.leftNode=upNode.rightNode;
			upNode.rightNode=currNode;
			currNode=upNode;
			currNode.bf=0;
			if(upNode.bf==0){
				currNode.leftNode.bf=0;
				currNode.rightNode.bf=0;
			}else if(upNode.bf==1){
				currNode.leftNode.bf=0;
				currNode.rightNode.bf=-1;
			}else if(upNode.bf==-1){
				currNode.leftNode.bf=1;
				currNode.rightNode.bf=0;
			}
			//将调整后的子树赋给父节点
			if(isLDirect){
				parentNode.leftNode=currNode;
			}else{
				parentNode.rightNode=currNode;
			}
			
		}else if(currNode.leftNode.bf==0){
			//****************只有删除的时候会出现这种情况，平衡调整后bf！=0，需要注意一下
			//两种不平衡调整均可（但是左右调整要分子树树高情况，有些复杂），这里做左左不平衡调整
			upNode=currNode.leftNode;
			currNode.leftNode=upNode.rightNode;
			upNode.rightNode=currNode;
			currNode=upNode;
			currNode.bf=-1;
			currNode.rightNode.bf=1;
			//将调整好的子树赋给父节点
			if(isLDirect){	
				parentNode.leftNode=currNode;
			}else{
				parentNode.rightNode=currNode;
			}
		}
	}
	
	private void rightRebalance(AVLNode currNode,AVLNode parentNode,boolean isLDirect){
		AVLNode upNode;
		if(currNode.rightNode.bf==-1){
		//做右右不平衡调整
			upNode=currNode.rightNode;
			currNode.rightNode=upNode.leftNode;
			upNode.leftNode=currNode;
			currNode=upNode;
			currNode.bf=0;
			currNode.leftNode.bf=0;
			//将调整好的子树赋给父节点
			if(isLDirect){	
				parentNode.leftNode=currNode;
			}else{
				parentNode.rightNode=currNode;
			}
		}else if(currNode.rightNode.bf==1){
		//做右左不平衡调整
			upNode=currNode.rightNode.leftNode;
			int upNodeBf=upNode.bf;
			currNode.rightNode.leftNode=upNode.rightNode;
			upNode.rightNode=currNode.rightNode;
			currNode.rightNode=upNode.leftNode;
			upNode.leftNode=currNode;
			currNode=upNode;
			currNode.bf=0;
			if(upNode.bf==0){
				currNode.leftNode.bf=0;	
				currNode.rightNode.bf=0;	
			}else if(upNode.bf==1){
				currNode.leftNode.bf=0;
				currNode.rightNode.bf=-1;
			}else if(upNode.bf==-1){
				currNode.leftNode.bf=1;
				currNode.rightNode.bf=0;
			}
			//将调整后的子树赋给父节点
			if(isLDirect){
				parentNode.leftNode=currNode;
			}else{
				parentNode.rightNode=currNode;
			}
			
		}else if(currNode.rightNode.bf==0){
			//做右右不平衡调整
			upNode=currNode.rightNode;
			currNode.rightNode=upNode.leftNode;
			upNode.leftNode=currNode;
			currNode=upNode;
			currNode.bf=1;
			currNode.leftNode.bf=-1;
			//将调整好的子树赋给父节点
			if(isLDirect){	
				parentNode.leftNode=currNode;
			}else{
				parentNode.rightNode=currNode;
			}
		}

	}
	
	
	//先序遍历入口方法
	public void preorderTraversal(){
		if(sentinel.leftNode==null){
			System.out.println("树为空");
			return;
		}
		preorderTraversal(sentinel.leftNode);
	};
	//先序遍历递归方法
	private void preorderTraversal(AVLNode currNode) {
		//校验当前节点是否为空，防止出现空指针异常
		if(currNode==null){
			return;
		}
		preorderTraversal(currNode.leftNode);
		System.out.print(currNode.data+", ");
		preorderTraversal(currNode.rightNode);
	};
	
	//中序遍历入口方法
	public void inorderTraversal(){
		if(sentinel.leftNode==null){
			System.out.println("树为空");
			return;
		}
		inorderTraversal(sentinel.leftNode);
	};
	//中序遍历递归方法
	private void inorderTraversal(AVLNode currNode){
		//校验当前节点是否为空，防止出现空指针异常
				if(currNode==null){
					return;
				}
				System.out.print(currNode.data+", ");
				inorderTraversal(currNode.leftNode);
				inorderTraversal(currNode.rightNode);
	};
}
