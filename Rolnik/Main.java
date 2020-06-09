package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Ogród o = new Ogród(6);
        pracownikPGR PGRboi = new pracownikPGR(50);
        PGRboi.symuluj(40, o);
        Gospodarz G = new Gospodarz(30);
        G.symuluj(30, o);
    }
}
