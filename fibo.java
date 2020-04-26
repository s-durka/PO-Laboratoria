package com.company;

public class Main {
    public static int fibo(int n){
        // wypisuje n-tą liczbę fibonacciego
        int c = 0;
        int a = 1;
        int b = 1;
        int i = 2;
        while(i<n){
            c = a+b;
            b = a;
            a = c;
            i++;
        }
        return a;
    }

    public static void main(String[] args) {
    int x = fibo(3);
    System.out.println(x);
     }
}
