package wyborca;

import kandydat.Kandydat;

public class WszechstronnyWybierającySpośródJednejPartii extends Wszechstronny {

    private final String nazwaPartii;

    public WszechstronnyWybierającySpośródJednejPartii(String imię, String nazwisko,
            int numerOkręgu, int[] wagiCech, String nazwaPartii) {
        super(imię, nazwisko, numerOkręgu, wagiCech);
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
