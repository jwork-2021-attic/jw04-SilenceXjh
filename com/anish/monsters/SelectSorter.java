package com.anish.monsters;

public class SelectSorter<T extends Comparable<T>> implements Sorter<T> {
    
    private T[][] a;
    private int m;
    private int n;

    @Override
    public void load(T[][] a) {
        this.a = a;
        m = a.length;
        n = a[0].length;
    }

    private void swap(int i, int j) {
        T temp;
        temp = a[i / n][i % n];
        a[i / n][i % n] = a[j / n][j % n];
        a[j / n][j % n] = temp;
        plan += "" + a[i / n][i % n] + "<->" + a[j / n][j % n] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        for(int i = m * n - 1; i >= 1; --i) {
            int k = 0;
            for(int j = 1; j <= i; ++j) {
                if(a[j / n][j % n].compareTo(a[k / n][k % n]) > 0) {
                    k = j;
                }
            }
            if(k != i) {
                swap(k, i);
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}