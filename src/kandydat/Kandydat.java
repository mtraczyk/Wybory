package kandydat;

import partia.Partia;

public class Kandydat {

    private final String imię;
    private final String nazwisko;
    private final int numerOkręgu;
    private Partia partia;
    private final int pozycjaNaLiście;
    private final int[] wartościCech;
    private int liczbaZebranychGłosów;

    public Kandydat(String imię, String nazwisko, int numerOkręgu, int pozycjaNaLiście,
            int[] wartościCech) {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.numerOkręgu = numerOkręgu;
        this.pozycjaNaLiście = pozycjaNaLiście;
        this.wartościCech = wartościCech;
        this.liczbaZebranychGłosów = 0;
    }

    public String getImię() {
        return imię;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setPartia(Partia a) {
        partia = a;
    }

    public int getNumerOkręgu() {
        return numerOkręgu;
    }

    public String getNazwaPartii() {
        return partia.getNazwaPartii();
    }

    public int getPozycjaNaLiście() {
        return pozycjaNaLiście;
    }

    public int getWartośćCechy(int numerCechy) {
        return wartościCech[numerCechy];
    }

    public int getLiczbaZebranychGłosów() {
        return liczbaZebranychGłosów;
    }

    public void setLiczbaZebranychGłosów() {
        liczbaZebranychGłosów++;
        partia.zliczGłos(numerOkręgu);
    }

    @Override
    public String toString() {
        return getImię() + " " + getNazwisko() + " " + getNazwaPartii() + " "
                + Integer.toString(getPozycjaNaLiście()) + " "
                + Integer.toString(getLiczbaZebranychGłosów());
    }
}
