package com.test.avltreetest;

public class AVLTest {

	public static void main(String[] args) {
		AVLTree avlTree=new AVLTree();
		//,17,-5,-9
		int[] arr={5,3,6,9,8,1,5,2,6,7,18,24,12,17,-5,-9,21,28,10,20,22,26,31,23,27};
		for(int i=0;i<arr.length;i++){
			avlTree.add(arr[i]);
		}
		avlTree.inorderTraversal();
		avlTree.preorderTraversal();
/*		AVLNode deleteNode = avlTree.delete(7);
		System.out.println(deleteNode.data);
		avlTree.inorderTraversal();
		System.out.println();
		AVLNode deleteNode2 = avlTree.delete(24);
		System.out.println(deleteNode2.data);
		avlTree.inorderTraversal();*/
		AVLNode deleteNode = avlTree.delete(18);
		System.out.println(deleteNode.data);
		avlTree.inorderTraversal();
		System.out.println();
		AVLNode deleteNode1 = avlTree.delete(1);
		System.out.println(deleteNode1.data);
		avlTree.inorderTraversal();
		System.out.println();
		AVLNode deleteNode2 = avlTree.delete(-9);
		System.out.println(deleteNode2.data);
		avlTree.inorderTraversal();
		System.out.println();
	}

}
