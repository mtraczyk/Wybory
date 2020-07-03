package wyborca;

import kandydat.Kandydat;

public class MaksymalizującyJednocechowyWybierającySpośródWszystkichPartii
        extends MaksymalizującyJednocechowy {

    public MaksymalizującyJednocechowyWybierającySpośródWszystkichPartii(String imię,
            String nazwisko, int numerOkręgu, int numerCechy) {
        super(imię, nazwisko, numerOkręgu, numerCechy);
    }

    @Override
    public boolean czyWyborcaMożeOddaćGłosNaTegoKandydata(Kandydat kandydat) {
        if (kandydat.getNumerOkręgu() == getNumerOkręgu()) {
            return true;
        }

        return false;
    }

}
