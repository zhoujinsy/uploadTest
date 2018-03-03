package com.test.redblacktree;

public class Test {

	public static void main(String[] args) {
		//,25,23
		int[] arr={16,15,18,14,13,11,12,17,22,9};
		RBTree rbTree=new RBTree();
		for(int i=0;i<arr.length;i++){
			rbTree.addNode(arr[i]);
		}
		rbTree.inorderTraversal();
		System.out.println();
		rbTree.preorderTraversal();
		System.out.println();
		rbTree.deleteNode(14);
		rbTree.preorderTraversal();

	}

}
