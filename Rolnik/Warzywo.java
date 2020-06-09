package com.company;

public abstract class Warzywo {
    protected String nazwa;
    protected Integer wartość;
    protected Boolean zebrane;
    protected int czasPosadzenia;
    protected Integer kosztPosadzenia;

    // konstruktor:
    public Warzywo() {
        this.wartość = 0;
        this.zebrane = false;
        this.czasPosadzenia = czasPosadzenia;
    }

    public Integer dajKosztPosadzenia() {
        return kosztPosadzenia;
    }

    public String toStringZebranie() {
        return nazwa + " (" + wartość + " zł)";
    }
    public String toStringSadzenie() { return nazwa + " (-" + kosztPosadzenia + " zł)"; }

    // metoda zwracająca wartość warzywa jeśli nie jest jeszcze zebrane
    public abstract Integer funkcjaWartości(int czasOdPosadzenia);

    // ocenia oraz ustala wartość warzywa.
    // wywoływana przy każdym sprawdzeniu oraz zerwaniu warzywa przez ogród
    public int oceńWartość(int czas) {
        if (zebrane) return this.wartość;
        int czasOdPosadzenia = czas - this.czasPosadzenia;
        this.wartość = funkcjaWartości(czasOdPosadzenia);
        return this.wartość;
    }

    public void zaznaczŻeZebrane() { this.zebrane = true; }

    // zmienia wartość atrybutu wartość oraz zwraca różnicę pomiędzy poprzednią wartością ważywą, a aktualną
    public int porównajWartość(int czas) {
        int wartość_wcześniej = this.wartość;
        int czasOdPosadzenia = czas - this.czasPosadzenia;
        this.wartość = funkcjaWartości(czasOdPosadzenia);
        return this.wartość - wartość_wcześniej;
    }
}