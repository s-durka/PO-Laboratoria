public class Para <T1, T2 extends Comparable<T2>> implements Comparable<Para<T1, T2>>{

    private T1 pierwszy;

    private T2 drugi;

    public Para(T1 pierwszy, T2 drugi){
        this.pierwszy = pierwszy;
        this.drugi = drugi;
    }

    @Override
    public int compareTo(Para<T1, T2> para) { //porównuje względem drugiej współrzędnej
        return drugi.compareTo(para.drugi);
    }

    public T1 getPierwszy() {
        return pierwszy;
    }

    public void setPierwszy(T1 pierwszy) {
        this.pierwszy = pierwszy;
    }


    public T2 getDrugi() {
        return drugi;
    }

    public void setDrugi(T2 drugi) {
        this.drugi = drugi;
    }

    @Override
    public String toString() {
        return "("+pierwszy + ", " + drugi + ")";
    }
}

