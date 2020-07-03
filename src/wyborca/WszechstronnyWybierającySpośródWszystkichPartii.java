package wyborca;

import kandydat.Kandydat;

public class WszechstronnyWybierającySpośródWszystkichPartii extends Wszechstronny {

    public WszechstronnyWybierającySpośródWszystkichPartii(String imię, String nazwisko,
            int numerOkręgu, int[] wagiCech) {
        super(imię, nazwisko, numerOkręgu, wagiCech);
    }

    @Override
    public boolean czyWyborcaMożeOddaćGłosNaTegoKandydata(Kandydat kandydat) {
        if (kandydat.getNumerOkręgu() == getNumerOkręgu()) {
            return true;
        }

        return false;
    }

}
