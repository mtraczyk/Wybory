package wyborca;

import kandydat.Kandydat;

public class MinimalizującyJednocechowyWybierającySpośródJednejPartii
        extends MinimalizującyJednocechowy {

    private final String nazwaPartii;

    public MinimalizującyJednocechowyWybierającySpośródJednejPartii(String imię, String nazwisko,
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
