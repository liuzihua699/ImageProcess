package com.zihua.work;

import java.util.Collections;
import java.util.Comparator;

/**
 * @ClassName BinaryTree
 * @Description TODO 二叉树
 * @Author 刘子华
 * @Date 2020/3/4 22:20
 */
public class BinaryTree<T extends Comparable> {
    
    private T ele;
    private BinaryTree left;
    private BinaryTree right;

    private final static int FRONT_TRAVERSAL = 1;
    private final static int MID_TRAVERSAL = 2;
    private final static int BACK_TRAVERSAL = 3;

    public BinaryTree(T ele) {
        this.ele = ele;
    }
    
    private void insert(T ele) {
        this.insert(ele, this);
    }

    // 二叉树-插入元素
    private BinaryTree insert(T ele, BinaryTree root) {
        if (null == root) return new BinaryTree(ele);
        int var1 = ele.compareTo(root.ele); 
        if (var1 <= 0 ) { // 向左插入
            root.left = insert(ele, root.left);
        } else { // 向右插入
            root.right = insert(ele, root.right);
        }
        return root;
    }
    
    private void out(int model) {
        switch (model) {
            case 1 :
                System.out.print("前序遍历：");
                this.left_out(this);break;
            case 2 :
                System.out.print("中序遍历：");
                this.mid_out(this);break;
            case 3 :
                System.out.print("后序遍历：");
                this.right_out(this);break;
        }
        System.out.println();
    }
    
    // 中序遍历
    private static void mid_out(BinaryTree root) {
        if (null != root) {
            mid_out(root.left);
            System.out.print(root.ele.toString() + " ");
            mid_out(root.right);
        }
    }
    
    // 前序遍历
    private static void left_out(BinaryTree root) {
        if (null != root) {
            System.out.print(root.ele.toString() + " ");
            left_out(root.left);
            left_out(root.right);
        }
    }
    
    // 后序遍历
    private static void right_out(BinaryTree root) {
        if (null != root) {
            right_out(root.left);
            right_out(root.right);
            System.out.print(root.ele.toString() + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> root = new BinaryTree(5);
        root.insert(10);
        root.insert(3);
        root.insert(2);
        root.insert(6);
        root.insert(5);
        root.insert(12);
        root.out(BinaryTree.FRONT_TRAVERSAL);
        root.out(BinaryTree.MID_TRAVERSAL);
        root.out(BinaryTree.BACK_TRAVERSAL);
    }
}
