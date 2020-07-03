package wyborca;

import kandydat.Kandydat;

public class MinimalizującyJednocechowyWybierającySpośródWszystkichPartii
        extends MinimalizującyJednocechowy {

    public MinimalizującyJednocechowyWybierającySpośródWszystkichPartii(String imię,
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
