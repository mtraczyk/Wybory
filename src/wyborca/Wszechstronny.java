package wyborca;

import java.util.Random;

import kandydat.Kandydat;

public abstract class Wszechstronny extends Wyborca {

    private int[] wagiCech;

    public Wszechstronny(String imię, String nazwisko, int numerOkręgu, int[] wagiCech) {
        super(imię, nazwisko, numerOkręgu);
        this.wagiCech = wagiCech;
    }

    public abstract boolean czyWyborcaMożeOddaćGłosNaTegoKandydata(Kandydat kandydat);

    public int getWagiCech(int numerWagi) {
        return wagiCech[numerWagi];
    }

    public void setWagiCech(int numerWagi, int zmiana) {
        wagiCech[numerWagi] += zmiana;

        if (wagiCech[numerWagi] > 100) {
            wagiCech[numerWagi] = 100;
        } else if (wagiCech[numerWagi] < -100) {
            wagiCech[numerWagi] = -100;
        }
    }

    @Override
    public void oddajGłos(Kandydat[] kandydaci) {
        int najlepszaŚredniaWażona = Integer.MIN_VALUE, iluZNajlepsząŚrednią = 0;

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)) {
                najlepszaŚredniaWażona = Math.max(policzŚredniąWażoną(kandydat),
                        najlepszaŚredniaWażona);
            }
        }

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)
                    && policzŚredniąWażoną(kandydat) == najlepszaŚredniaWażona) {
                iluZNajlepsząŚrednią++;
            }
        }

        oddajGłosNaLosowegoZNajlepsząŚrednią(najlepszaŚredniaWażona, iluZNajlepsząŚrednią,
                kandydaci);
    }

    private void oddajGłosNaLosowegoZNajlepsząŚrednią(int najlepszaŚredniaWażona,
            int iluZNajlepsząŚredniąWażoną, Kandydat[] kandydaci) {
        Random generator = new Random();
        int któryKandydat = generator.nextInt(iluZNajlepsząŚredniąWażoną), pomocnicza = 0;

        for (Kandydat kandydat : kandydaci) {
            if (czyWyborcaMożeOddaćGłosNaTegoKandydata(kandydat)
                    && policzŚredniąWażoną(kandydat) == najlepszaŚredniaWażona) {
                if (pomocnicza == któryKandydat) {
                    setGłosujeNaKandydata(kandydat);
                    break;
                }

                pomocnicza++;
            }
        }
    }

    private int policzŚredniąWażoną(Kandydat kandydat) {
        int wartośćŚredniej = 0;

        for (int i = 0; i < wagiCech.length; i++) {
            wartośćŚredniej += wagiCech[i] * kandydat.getWartośćCechy(i + 1);
        }

        return wartośćŚredniej;
    }

}
