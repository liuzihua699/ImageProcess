package com.zihua.work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Binary {

    private static int[] arr = arr = new int[20];
    private static int len = 11;
    
    static {
        for (int i = 0; i < 10; i++) {
            arr[i] = i + 1;
        };
    }
    
    static int append (int pos, int x) {
        for (int i = len; i > pos; i--) {
            arr[i] = arr[i - 1];
        }
        arr[pos] = x;
        return pos;
    }

    // 二分插入。查找的时间是θ(logn)，插入的时间是θ(n)，时间复杂度为O(n)。
    static int binaryInsert(int a, int l, int r) {
        int m = 0;
        int low = l,high = r;
        for (;;) {
            m = (low + high) / 2;
            if (m == 0) return append(0, a);
            if (low >= high || a == arr[m]) return append(m + 1, a);
            if (a < arr[m]) {
                high = m - 1;
            } else {
                low = m + 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("插入前：");
        out(arr);
        binaryInsert(8, 0, 9);
        binaryInsert(0, 0, 10);
        binaryInsert(100, 0, 11);
        System.out.println("插入后：");
        out(arr);
    }
    
    public static void out(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
