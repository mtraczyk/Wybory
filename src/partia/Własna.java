package partia;

import java.util.Random;

import kandydat.Kandydat;
import wyborca.Wszechstronny;

public class Własna extends Partia {

    public Własna(String nazwaPartii, int budżet, int liczbaDziałań, int liczbaOkręgów,
            int działania[][]) {
        super(nazwaPartii, budżet, liczbaDziałań, liczbaOkręgów, działania);
    }

    @Override
    protected int ustawStartowyKosztDziałania() {
        return 0;
    }

    @Override
    protected boolean Strategia(int numerDziałania, int numerOkręgu,
            Wszechstronny[] wszechstronniWyborcy, Kandydat[] kandydaci) {
        if (!getCzyMożnaWykonaćNastępneDziałanie()) {
            return true;
        } else {
            Random generator = new Random();
            int pomocnicza = generator.nextInt(2);

            if (pomocnicza == 1) {
                return true;
            }
        }

        return false;
    }

}
