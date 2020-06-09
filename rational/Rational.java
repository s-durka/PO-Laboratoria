package com.company;

public class Rational {
    private Integer numerator; // >= 0
    private Integer denominator; // > 0
    private Integer sign;

    private static int gcd(int x, int y) {
        while(x > 0 && y > 0){
            if(x > y) {
                x = x % y;
            }
            else {
                y = y % x;
            }
        }
        if (x == 0) return y;
        else return x;
    }

    public Rational inverse() {
        return new Rational(this.denominator,this.numerator * this.sign);
    }
    public Rational opposite() {
        return new Rational(this.numerator * this.sign * (-1), this.denominator);
    }

    // konstruktory:
    public Rational(int p, int q){
        System.out.println("aaaaaaaaaaa");
        // od razu wykonuje skracanie
        // oraz ustawia znak w zaleznosci od licznika i mianownika
        int gcd = gcd(Math.abs(p),Math.abs(q));
        p /= gcd;
        q /= gcd;
        int dummy = p/q; // jesli q == 0, wywola sie exception
        this.numerator = p;
        this.denominator = q;
        this.sign = 1;
        if (this.numerator == 0) this.sign = 0;
        else{
            if(this.numerator < 0) {
                this.sign *= (-1);
                this.numerator *= (-1);
            }
            if(this.denominator < 0) { // moglo sie tak zdarzyc np. przy dzieleniu
                this.sign *= -1;
                this.denominator *= (-1);
            }
        }
    }
    public Rational(int n) {
        this(n, 1);

    }
    // akcesory:
    public Integer sign() {
        return sign;
    }
    public Integer numerator() {
        return numerator;
    }
    public Integer denominator() {
        return denominator;
    }
    // koniec akcesorow

    public Rational add(Rational x) {
        // wersja ze sprowadzaniem do najmniejszego wsp. mianownika:
        // uznalem, ze jesli skracam ulamek w momencie tworzenia obiektu,
        // to uzycie gcd dwukrotnie tylko spowolni program

        // int gcd_of_den = gcd(this.denominator, x.denominator);
        // int lcm_of_den = (this.denominator * x.denominator) / gcd_of_den; // NWW
        // int new_num1 = this.numerator * lcm_of_den / this.denominator;
        // int new_num2 = x.numerator * lcm_of_den / x.denominator;
        // int new_den = lcm_of_den;
        // int new_num = new_num1 + new_num2;

        int new_denominator = this.denominator * x.denominator;
        int new_numerator = this.numerator * x.denominator * this.sign + x.numerator * this.denominator * x.sign;
        return new Rational(new_numerator, new_denominator);
    }

    public Rational subtract(Rational x) {
        return add(x.opposite());
    }

    public Rational multiply(Rational x) {
        int new_numerator = this.numerator *this.sign * x.numerator * x.sign;
        int new_denominator = this.denominator * x.denominator;
        return new Rational(new_numerator, new_denominator);
    }

    public Rational divide(Rational x) {
        return multiply(x.inverse());
    }

    public String toString() {
        String s = "";
        if (this.sign == -1) s = "-";
        return s + this.numerator + "/" + this.denominator;
    }

}
