package partia;

import kandydat.Kandydat;
import wyborca.Wszechstronny;

public class Zachłanna extends Partia {

    public Zachłanna(String nazwaPartii, int budżet, int liczbaDziałań, int liczbaOkręgów,
            int[][] działania) {
        super(nazwaPartii, budżet, liczbaDziałań, liczbaOkręgów, działania);
    }

    @Override
    protected int ustawStartowyKosztDziałania() {
        return 0;
    }

    @Override
    protected boolean Strategia(int numerDziałania, int numerOkręgu,
            Wszechstronny[] wszechstronniWyborcy, Kandydat[] kandydaci) {
        if (!getCzyMożnaWykonaćNastępneDziałanie()
                || jakZmieniaSięŚredniaWażonaPoWykonaniuDziałania(numerDziałania, numerOkręgu,
                        wszechstronniWyborcy,
                        kandydaci) > jakZmieniaSięŚredniaWażonaPoWykonaniuDziałania(
                                getNastępneDziałanie(), getWJakimOkręguWykonaćNastępneDziałanie(),
                                wszechstronniWyborcy, kandydaci)) {
            return true;
        }

        return false;
    }

    private int jakZmieniaSięŚredniaWażonaPoWykonaniuDziałania(int numerDziałania, int numerOkręgu,
            Wszechstronny[] wszechstronniWyborcy, Kandydat[] kandydaci) {
        int całkowitaZmiana = 0;

        for (Kandydat kandydat : kandydaci) {
            for (Wszechstronny wyborca : wszechstronniWyborcy) {
                if (kandydat.getNazwaPartii().equals(getNazwaPartii())
                        && kandydat.getNumerOkręgu() == numerOkręgu
                        && wyborca.czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)) {
                    całkowitaZmiana += policzPojedyncząZmianę(getWektorDziałania(numerDziałania),
                            wyborca, kandydat);
                }
            }
        }

        return całkowitaZmiana;
    }

    private static int policzPojedyncząZmianę(int[] wektorDziałania, Wszechstronny wyborca,
            Kandydat kandydat) {
        int pojedynczaZmiana = 0, pomocnicza = 0, zmiana = 0;

        for (int i = 0; i < wektorDziałania.length; i++) {
            pomocnicza = wyborca.getWagiCech(i) + wektorDziałania[i];
            pomocnicza = Math.max(pomocnicza, -100);
            pomocnicza = Math.min(pomocnicza, 100);
            zmiana = pomocnicza - wyborca.getWagiCech(i);
            pojedynczaZmiana += kandydat.getWartośćCechy(i + 1) * zmiana;
        }

        return pojedynczaZmiana;
    }

}
