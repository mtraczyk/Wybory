package wyborca;

import java.util.Random;

import kandydat.Kandydat;

public abstract class MaksymalizującyJednocechowy extends Wyborca {

    private final int numerCechy;

    public MaksymalizującyJednocechowy(String imię, String nazwisko, int numerOkręgu,
            int numerCechy) {
        super(imię, nazwisko, numerOkręgu);
        this.numerCechy = numerCechy;
    }

    protected abstract boolean czyWyborcaMożeOddaćGłosNaTegoKandydata(Kandydat kandydat);

    @Override
    public void oddajGłos(Kandydat[] kandydaci) {
        int maksymalnaWartośćCechy = Integer.MIN_VALUE, iluMaksymalnych = 0;

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)) {
                maksymalnaWartośćCechy = Math.max(kandydat.getWartośćCechy(numerCechy),
                        maksymalnaWartośćCechy);
            }
        }

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)
                    && kandydat.getWartośćCechy(numerCechy) == maksymalnaWartośćCechy) {
                iluMaksymalnych++;
            }
        }

        oddajGłosNaLosowegoMinimalnego(maksymalnaWartośćCechy, iluMaksymalnych, kandydaci);
    }

    private void oddajGłosNaLosowegoMinimalnego(int maksymalnaWartośćCechy, int iluMaksymalnych,
            Kandydat[] kandydaci) {
        Random generator = new Random();
        int któryKandydat = generator.nextInt(iluMaksymalnych), pomocnicza = 0;

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)
                    && kandydat.getWartośćCechy(numerCechy) == maksymalnaWartośćCechy) {
                if (pomocnicza == któryKandydat) {
                    setGłosujeNaKandydata(kandydat);
                    break;
                }

                pomocnicza++;
            }
        }
    }

}
