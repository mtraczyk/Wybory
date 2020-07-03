package wyborca;

import kandydat.Kandydat;

public class MaksymalizującyJednocechowyWybierającySpośródJednejPartii
        extends MaksymalizującyJednocechowy {

    private final String nazwaPartii;

    public MaksymalizującyJednocechowyWybierającySpośródJednejPartii(String imię, String nazwisko,
            int numerOkręgu, int numerCechy, String nazwaPartii) {
        super(imię, nazwisko, numerOkręgu, numerCechy);
        this.nazwaPartii = nazwaPartii;
    }

    @Override
    public boolean czyWyborcaMożeOddaćGłosNaTegoKandydata(Kandydat kandydat) {
        if (kandydat.getNumerOkręgu() == getNumerOkręgu()
                && kandydat.getNazwaPartii().equals(nazwaPartii)) {
            return true;
        }

        return false;
    }

}
