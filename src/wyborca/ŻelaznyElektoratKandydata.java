package wyborca;

import kandydat.Kandydat;

public class ŻelaznyElektoratKandydata extends Wyborca {

    private final String nazwaPartii;
    private final int pozycjaNaLiście;

    public ŻelaznyElektoratKandydata(String imię, String nazwisko, int numerOkręgu,
            String nazwaPartii, int pozycjaNaLiście) {
        super(imię, nazwisko, numerOkręgu);
        this.nazwaPartii = nazwaPartii;
        this.pozycjaNaLiście = pozycjaNaLiście;
    }

    private boolean czyWyborcaMożeOddaćGłosNaTegoKandydata(Kandydat kandydat) {
        if (kandydat.getNumerOkręgu() == getNumerOkręgu()
                && kandydat.getNazwaPartii().equals(nazwaPartii)
                && kandydat.getPozycjaNaLiście() == pozycjaNaLiście) {
            return true;
        }

        return false;
    }

    @Override
    public void oddajGłos(Kandydat[] kandydaci) {
        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)) {
                setGłosujeNaKandydata(kandydat);
                break;
            }
        }
    }

}
