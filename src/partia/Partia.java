package partia;

import kandydat.Kandydat;
import wyborca.Wszechstronny;
import wyborca.Wyborca;

public abstract class Partia {

    private final String nazwaPartii;
    private int budżet;
    private final int liczbaDziałań;
    private final int[][] działania;

    private boolean czyMożnaWykonaćNastępneDziałanie = false;
    private int następneDziałanie = Integer.MAX_VALUE;
    private int wJakimOkręguWykonaćNastępneDziałanie = Integer.MAX_VALUE;
    private int kosztAktualnegoDziałania = ustawStartowyKosztDziałania();

    private int[] liczbaGłosówWOkręgu;
    private int[] liczbaMandatówWOkręgu;
    private int całkowitaLiczbaMandatów = 0;

    public Partia(String nazwaPartii, int budżet, int liczbaDziałań, int liczbaOkręgów,
            int[][] działania) {
        this.nazwaPartii = nazwaPartii;
        this.budżet = budżet;
        this.liczbaDziałań = liczbaDziałań;
        this.liczbaGłosówWOkręgu = new int[liczbaOkręgów + 1];
        this.liczbaMandatówWOkręgu = new int[liczbaOkręgów + 1];
        this.działania = działania;
    }

    public String getNazwaPartii() {
        return nazwaPartii;
    }

    protected boolean getCzyMożnaWykonaćNastępneDziałanie() {
        return czyMożnaWykonaćNastępneDziałanie;
    }

    protected int getNastępneDziałanie() {
        return następneDziałanie;
    }

    protected int getWJakimOkręguWykonaćNastępneDziałanie() {
        return wJakimOkręguWykonaćNastępneDziałanie;
    }

    protected int[] getWektorDziałania(int numerDziałania) {
        return działania[numerDziałania];
    }

    public int getLiczbaGłosówWOkręgu(int numerOkręgu) {
        return liczbaGłosówWOkręgu[numerOkręgu];
    }

    public int getLiczbaMandatówWOkręgu(int numerOkręgu) {
        return liczbaMandatówWOkręgu[numerOkręgu];
    }

    public int getCałkowitaLiczbaMandatów() {
        return całkowitaLiczbaMandatów;
    }

    private void przygotujKlasęNaNastępneDziałanie() {
        czyMożnaWykonaćNastępneDziałanie = false;
        następneDziałanie = Integer.MAX_VALUE;
        wJakimOkręguWykonaćNastępneDziałanie = Integer.MAX_VALUE;
        kosztAktualnegoDziałania = ustawStartowyKosztDziałania();
    }

    public void przygotujPartięNaLiczenieMandatów() {
        for (int i = 0; i < liczbaMandatówWOkręgu.length; i++) {
            liczbaMandatówWOkręgu[i] = 0;
        }

        całkowitaLiczbaMandatów = 0;
    }

    public void zliczGłos(int numerOkręgu) {
        liczbaGłosówWOkręgu[numerOkręgu]++;
    }

    public void zliczMandaty(int numerOkręgu, int liczbaPrzyznanychMandatów) {
        liczbaMandatówWOkręgu[numerOkręgu] += liczbaPrzyznanychMandatów;
        całkowitaLiczbaMandatów += liczbaPrzyznanychMandatów;
    }

    protected abstract boolean Strategia(int numerDziałania, int numerOkręgu,
            Wszechstronny[] wszechstronniWyborcy, Kandydat[] kandydaci);

    protected abstract int ustawStartowyKosztDziałania();

    public void kampaniaWyborcza(int liczbaOkręgów, Wszechstronny[] wszechstronniWyborcy,
            Kandydat[] kandydaci) {
        while (budżet > 0 && następneDziałanie(liczbaOkręgów, wszechstronniWyborcy, kandydaci)) {
            wykonajNastępneDziałanie(wszechstronniWyborcy);
            przygotujKlasęNaNastępneDziałanie();
        }
    }

    private boolean następneDziałanie(int liczbaOkręgów, Wszechstronny[] wszechstronniWyborcy,
            Kandydat[] kandydaci) {
        for (int i = 1; i <= liczbaOkręgów; i++) {
            for (int j = 0; j < liczbaDziałań; j++) {
                if (budżet >= kosztDziałania(i, j, wszechstronniWyborcy)
                        && Strategia(j, i, wszechstronniWyborcy, kandydaci)) {
                    if (kosztDziałania(i, j, wszechstronniWyborcy) != 0) {
                        czyMożnaWykonaćNastępneDziałanie = true;
                        następneDziałanie = j;
                        wJakimOkręguWykonaćNastępneDziałanie = i;
                        kosztAktualnegoDziałania = kosztDziałania(i, j, wszechstronniWyborcy);
                    }
                }
            }
        }

        return czyMożnaWykonaćNastępneDziałanie;
    }

    private void wykonajNastępneDziałanie(Wszechstronny[] wszechstronniWyborcy) {
        budżet -= kosztAktualnegoDziałania;

        for (Wszechstronny wyborca : wszechstronniWyborcy) {
            if (wyborca.getNumerOkręgu() == wJakimOkręguWykonaćNastępneDziałanie) {
                for (int i = 0; i < działania[następneDziałanie].length; i++) {
                    wyborca.setWagiCech(i, działania[następneDziałanie][i]);
                }
            }
        }
    }

    protected int kosztDziałania(int numerOkręgu, int numerDziałania,
            Wyborca[] wszechstronniWyborcy) {
        int ileOsóbWOkręgu = 0, sumaWektoraWag = 0;
        for (Wyborca wyborca : wszechstronniWyborcy) {
            if (wyborca.getNumerOkręgu() == numerOkręgu) {
                ileOsóbWOkręgu++;
            }
        }

        for (int wartość : działania[numerDziałania]) {
            sumaWektoraWag += Math.abs(wartość);
        }

        return ileOsóbWOkręgu * sumaWektoraWag;
    }

}
