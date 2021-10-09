package com.anish.monsters;

import java.util.Random;

public class RandomArray {
    private int[] a;

    public RandomArray(int len) {
        a = new int [len];
        for(int i = 0; i < len; ++i) {
            a[i] = i+1;
        }
        Random r = new Random();
        for(int i = 0; i < len; ++i) {
            int r1 = r.nextInt(len);
            int tmp = a[i];
            a[i] = a[r1];
            a[r1] = tmp;
        }
    }

    public int get(int i) {
        return a[i];
    }
}