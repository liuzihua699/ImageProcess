package com.zihua.work;

public class LinkedNode {
    
    static class Node {
        private int value;
        private Node next;
        public Node(int val) {
            this.value = val;
        }

        @Override
        public String toString() {
            return value + "->" + (next==null ? "" : next) + (next == null ? "null" : "");
        }
    }
    
    // 创建链表
    public static Node createLinkedNodes(int n) {
        Node head = new Node(1);
        Node p = head;
        for (int i = 2; i <=n; i++) {
            p.next = new Node(i);
            p = p.next;
        }
        return head;
    }
    
    // 链表反序
    public static Node reverse(Node root) {
        if (root == null || root.next == null) return root;
        Node p = reverse(root.next);
        root.next.next = root;
        root.next = null;
        return p;
    }

    public static void main(String[] args) {
        Node head = createLinkedNodes(100);
        System.out.println("原链表：");
        System.out.println(head);
        System.out.println("反序后的链表：");
        System.out.println(reverse(head));
    }
}
