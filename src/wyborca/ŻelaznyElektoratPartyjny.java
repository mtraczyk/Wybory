package wyborca;

import java.util.Random;

import kandydat.Kandydat;

public class ŻelaznyElektoratPartyjny extends Wyborca {

    private final String nazwaPartii;

    public ŻelaznyElektoratPartyjny(String imię, String nazwisko, int numerOkręgu,
            String nazwaPartii) {
        super(imię, nazwisko, numerOkręgu);
        this.nazwaPartii = nazwaPartii;
    }

    private boolean czyWyborcaMożeOddaćGłosNaTegoKandydata(Kandydat kandydat) {
        if (kandydat.getNumerOkręgu() == getNumerOkręgu()
                && kandydat.getNazwaPartii().equals(nazwaPartii)) {
            return true;
        }

        return false;
    }

    @Override
    public void oddajGłos(Kandydat[] kandydaci) {
        int liczbaMożliwychWyborów = 0, któryKandydat = 0;
        Random generator = new Random();

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)) {
                liczbaMożliwychWyborów++;
            }
        }

        któryKandydat = generator.nextInt(liczbaMożliwychWyborów);
        int pomocnicza = 0;

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)) {
                if (pomocnicza == któryKandydat) {
                    setGłosujeNaKandydata(kandydat);
                    break;
                }

                pomocnicza++;
            }
        }
    }

}
