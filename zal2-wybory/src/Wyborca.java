import java.util.List;

public abstract class Wyborca {
    protected int numerOkręgu;
    protected String imięNazwisko;
    public String przedstawSię() {
        return imięNazwisko;
    }

    public Wyborca(String imięNazwisko, int numerOkręgu) {
        this.imięNazwisko = imięNazwisko;
        this.numerOkręgu = numerOkręgu;
    }

    // zwraca null jeśli wyborca nie ma opinii, w przeciwnym wypadku (jeśli jest Wszechstronny) zwraca wektor wag
    public abstract WektorCech wyraźPoglądy();
    public abstract void zmieńPoglądy(WektorCech wektorZmian);
    public abstract Głos wybierzKandydata(ListaWyborcza lW);
}






