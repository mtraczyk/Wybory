package wyborca;

import java.util.Random;

import kandydat.Kandydat;

public abstract class MinimalizującyJednocechowy extends Wyborca {

    private final int numerCechy;

    public MinimalizującyJednocechowy(String imię, String nazwisko, int numerOkręgu,
            int numerCechy) {
        super(imię, nazwisko, numerOkręgu);
        this.numerCechy = numerCechy;
    }

    protected abstract boolean czyWyborcaMożeOddaćGłosNaTegoKandydata(Kandydat kandydat);

    @Override
    public void oddajGłos(Kandydat[] kandydaci) {
        int minimalnaWartośćCechy = Integer.MAX_VALUE, iluMinimalnych = 0;

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)) {
                minimalnaWartośćCechy = Math.min(kandydat.getWartośćCechy(numerCechy),
                        minimalnaWartośćCechy);
            }
        }

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)
                    && kandydat.getWartośćCechy(numerCechy) == minimalnaWartośćCechy) {
                iluMinimalnych++;
            }
        }

        oddajGłosNaLosowegoMinimalnego(minimalnaWartośćCechy, iluMinimalnych, kandydaci);
    }

    private void oddajGłosNaLosowegoMinimalnego(int minimalnaWartośćCechy, int iluMinimalnych,
            Kandydat[] kandydaci) {
        Random generator = new Random();
        int któryKandydat = generator.nextInt(iluMinimalnych), pomocnicza = 0;

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)
                    && kandydat.getWartośćCechy(numerCechy) == minimalnaWartośćCechy) {
                if (pomocnicza == któryKandydat) {
                    setGłosujeNaKandydata(kandydat);
                    break;
                }

                pomocnicza++;
            }
        }
    }

}
