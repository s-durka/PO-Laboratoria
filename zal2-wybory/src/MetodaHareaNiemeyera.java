public class MetodaHareaNiemeyera implements MetodaPrzeliczaniaGłosów {
    @Override
    public Para<String, Integer>[] rozdajMandaty(int liczbaMandatów, Para<String, Integer>[] tablicaGłosów) {
        Para<String, Integer>[] tablicaGłosówPom = (Para<String, Integer>[]) new Para<?, ?>[tablicaGłosów.length];
        int sumaGłosów = liczbaMandatów * 10;
        for (int i = 0; i < tablicaGłosów.length; i++) {
            tablicaGłosówPom[i] = new Para<String, Integer>(tablicaGłosów[i].getPierwszy(), 0);
            tablicaGłosówPom[i].setDrugi((tablicaGłosów[i].getDrugi() * liczbaMandatów)/sumaGłosów);
        }
        return tablicaGłosówPom;
    }


}
