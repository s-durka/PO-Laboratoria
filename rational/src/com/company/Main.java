package com.company;

public class Main {
    public static void main(String[] args) {
        Rational x = new Rational(-2,1);
        Rational y = new Rational(1,0);
        System.out.print(x.divide(y));
        System.out.print(x.multiply(y));

    }
}
