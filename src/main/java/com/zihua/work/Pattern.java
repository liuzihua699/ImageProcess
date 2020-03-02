package com.zihua.work;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Pattern {
    
    static boolean condition(char[] chs) {
        boolean flag = true;
        Stack s = new Stack(); Queue q = new LinkedList();

        int i = 0;
        char c = chs[0];
        while ('@' != c) {  // 匹配一个非终结符
            if ('&' == c) { flag = false; c = chs[++i]; continue; }
            if (flag) {
                s.push(c);
            } else {
                q.offer(c);
            }
            c = chs[++i];
        }
        while (!s.empty() && !q.isEmpty()) {
            if (s.pop() != q.poll()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[] str1 = "a+b&b+a@asdasd".toCharArray();
        char[] str2 = "1+3&3-1@asdzxc".toCharArray();
        System.out.println(condition(str1));
        System.out.println(condition(str2));
    }
}
