package com.company;

public class Pomidor extends Warzywo {

    public Pomidor(long czasPosadzenia) {
        super();
        this.nazwa = "Pomidor";
        this.wartość = 0;
        this.kosztPosadzenia = 2;
    }

    public Integer funkcjaWartości(int czasOdPosadzenia) {
        if (czasOdPosadzenia <= 10) return 0;
        else if (czasOdPosadzenia <= 15) {
            return (czasOdPosadzenia - 10) * 2;
        }
        else if (czasOdPosadzenia <= 20) {
            return funkcjaWartości(15) - (czasOdPosadzenia - 15) * 1;
        }
        else return 0;
    }
}