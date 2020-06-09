package com.company;

public class Ziemniak extends Warzywo {

    public Ziemniak(int czasOdPosadzenia) {
        this.nazwa = "Ziemniak";
        this.wartość = 0;
        this.kosztPosadzenia = 1;
    }

    public Integer funkcjaWartości(int czasOdPosadzenia) {
        if (czasOdPosadzenia <= 10) return 0;
        else return 5;
    }
}