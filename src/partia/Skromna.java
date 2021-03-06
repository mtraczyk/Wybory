package partia;

import kandydat.Kandydat;
import wyborca.Wszechstronny;

public class Skromna extends Partia {

    public Skromna(String nazwaPartii, int budżet, int liczbaDziałań, int liczbaOkręgów,
            int działania[][]) {
        super(nazwaPartii, budżet, liczbaDziałań, liczbaOkręgów, działania);
    }

    @Override
    protected int ustawStartowyKosztDziałania() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected boolean Strategia(int numerDziałania, int numerOkręgu,
            Wszechstronny[] wszechstronniWyborcy, Kandydat[] kandydaci) {
        if (!getCzyMożnaWykonaćNastępneDziałanie() || kosztDziałania(numerOkręgu, numerDziałania,
                wszechstronniWyborcy) < kosztDziałania(getWJakimOkręguWykonaćNastępneDziałanie(),
                        getNastępneDziałanie(), wszechstronniWyborcy)) {
            return true;
        }

        return false;
    }

}
