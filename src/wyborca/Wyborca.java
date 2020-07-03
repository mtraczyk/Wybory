package wyborca;

import kandydat.Kandydat;

public abstract class Wyborca {

    private final String imię;
    private final String nazwisko;
    private final int numerOkręgu;
    private Kandydat głosujeNaKandydata;

    public Wyborca(String imię, String nazwisko, int numerOkręgu) {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.numerOkręgu = numerOkręgu;
    }

    public abstract void oddajGłos(Kandydat[] kandydaci);

    public String getImię() {
        return imię;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getNumerOkręgu() {
        return numerOkręgu;
    }

    public Kandydat getGłosujeNaKandydata() {
        return głosujeNaKandydata;
    }

    protected void setGłosujeNaKandydata(Kandydat kandydat) {
        głosujeNaKandydata = kandydat;
    }

    @Override
    public String toString() {
        return getImię() + " " + getNazwisko() + " " + głosujeNaKandydata.getImię() + " "
                + głosujeNaKandydata.getNazwisko();
    }

}
