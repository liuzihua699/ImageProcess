package com.zihua.work;

// 使用循环链表实现队列
public class Queue {
    
    class Linked {
        private Object ele;
        private Linked next;
    }
    
    Linked head = new Linked();
    Linked rear;
    
    void out() {
        Linked node = head;
        System.out.print("入队顺序：");
        while (node.ele != null) {
            System.out.print(node.ele);
            node = node.next;
            if (node.ele != null) System.out.print("->");
        }
        System.out.println();
    }

    void init() {
        rear = head;
    }

    void en(Object ele) {
        rear.ele = ele;
        rear.next = new Linked();
        rear.next.next = head;
        rear = rear.next;
    }

    Object de() {
        Object ele = head.ele;
        head = head.next;
        rear.next = head;
        return ele;
    }
    
    public static void main(String[] args) {
        Queue q = new Queue();
        q.init();
        q.en(1);
        q.en(2);
        q.en(3);
        q.out();
        System.out.print("出队顺序：");
        System.out.print(q.de() + "->");
        System.out.print(q.de() + "->");
        System.out.println(q.de());
    }
}
