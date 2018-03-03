package com.test.binary;

public class TestBinary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySortTree bsTree=new BinarySortTree();
		int[] arr={5,8,2,4,16,9,3,24,11,1,22};
		for(int i=0;i<arr.length;i++){
			bsTree.add(arr[i]);
		}
		bsTree.preorderTraversal();
		System.out.println();
		
		bsTree.inorderTraversal();
		System.out.println();
		
		BinaryNode deleteNode = bsTree.deleteNode(2);
		System.out.println(deleteNode.data);
		bsTree.preorderTraversal();
		System.out.println();
		
		BinaryNode deleteNode2 = bsTree.deleteNode(3);
		System.out.println(deleteNode2.data);
		bsTree.preorderTraversal();
		System.out.println();
		
		BinaryNode deleteNode3 = bsTree.deleteNode(16);
		System.out.println(deleteNode3.data);
		bsTree.preorderTraversal();
		System.out.println();
		
/*		BinaryNode deleteNode4 = bsTree.deleteNode(22);
		System.out.println(deleteNode4.data);
		bsTree.preorderTraversal();
		System.out.println();*/
		
		BinaryNode deleteNode5 = bsTree.deleteNode(9);
		System.out.println(deleteNode5.data);
		bsTree.preorderTraversal();
		System.out.println();
	}

}
