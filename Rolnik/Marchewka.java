package com.company;

public class Marchewka extends Warzywo {
    public Marchewka(int czasOdPosadzenia) {
        this.nazwa = "Marchewka";
        this.wartość = 0;
        this.kosztPosadzenia = 4; // marchewka jest dość prestiżowym warzywem
    }

    public Integer funkcjaWartości(int czasOdPosadzenia) {
        if (czasOdPosadzenia <= 3) return 0;
        int ret = 0;
        if (czasOdPosadzenia <= 8) {
            ret = (czasOdPosadzenia - 7) * 2;
        }
        else if (czasOdPosadzenia <= 14) {
            ret = funkcjaWartości(8) + (czasOdPosadzenia - 7) * 1;
        }
        else {
            ret = funkcjaWartości(14) - (czasOdPosadzenia - 14);
        }
        if (ret < 0) return 0;
        else return ret;
    }
}
