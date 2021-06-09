package com.heyudev.base;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author heyudev
 * @date 2021/06/09
 */
public class MyTree {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(null, null, 1);
        TreeNode treeNode2 = new TreeNode(null, null, 2);
        TreeNode treeNode3 = new TreeNode(treeNode1, treeNode2, 3);
        TreeNode treeNode4 = new TreeNode(null, null, 4);
        TreeNode treeNode5 = new TreeNode(treeNode4, null, 5);
        TreeNode treeNode6 = new TreeNode(treeNode3, treeNode5, 6);

        TreeNode root = treeNode6;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.println(node.value);
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
            }
        }
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;

        public TreeNode(TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
        }

        public TreeNode(TreeNode left, TreeNode right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }
}
