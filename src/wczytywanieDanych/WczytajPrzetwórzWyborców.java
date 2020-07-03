package wczytywanieDanych;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import wyborca.MaksymalizującyJednocechowyWybierającySpośródJednejPartii;
import wyborca.MaksymalizującyJednocechowyWybierającySpośródWszystkichPartii;
import wyborca.MinimalizującyJednocechowyWybierającySpośródJednejPartii;
import wyborca.MinimalizującyJednocechowyWybierającySpośródWszystkichPartii;
import wyborca.Wszechstronny;
import wyborca.WszechstronnyWybierającySpośródJednejPartii;
import wyborca.WszechstronnyWybierającySpośródWszystkichPartii;
import wyborca.Wyborca;
import wyborca.ŻelaznyElektoratKandydata;
import wyborca.ŻelaznyElektoratPartyjny;

public class WczytajPrzetwórzWyborców {

    private Wyborca[] wyborcy;
    private Wszechstronny[] wszechstronniWyborcy;
    private List<Wyborca> listaWyborców = new ArrayList<Wyborca>();
    private List<Wszechstronny> listaWszechstronnychWyborców = new ArrayList<Wszechstronny>();

    public void wczytajWyborców(Scanner dane, String ostatniWczytanyWiersz,
            int[] liczbaWyborcówWOkręgach, int[] numeryKolejnychOkręgówPoScaleniu, int liczbaCech) {
        int liczbaWszystkichWyborców = iluWszystkichWyborców(liczbaWyborcówWOkręgach);
        Scanner wiersz = new Scanner(ostatniWczytanyWiersz);
        String imię, nazwisko;
        Wszechstronny pomocnicza;
        int numerOkręgu, typWyborcy;

        for (int i = 1; i <= liczbaWszystkichWyborców; i++) {
            imię = wiersz.next();
            nazwisko = wiersz.next();
            numerOkręgu = numeryKolejnychOkręgówPoScaleniu[wiersz.nextInt()];
            typWyborcy = wiersz.nextInt();

            switch (typWyborcy) {
            case 1:
                listaWyborców
                        .add(wczytajŻelaznyElektoratPartyjny(imię, nazwisko, numerOkręgu, wiersz));
                break;
            case 2:
                listaWyborców
                        .add(wczytajŻelaznyElektoratKandydata(imię, nazwisko, numerOkręgu, wiersz));
                break;
            case 3:
                listaWyborców.add(wczytajMinimalizującyJednocechowyZeWszystkichPartii(imię,
                        nazwisko, numerOkręgu, wiersz));
                break;
            case 4:
                listaWyborców.add(wczytajMaksymalizującyJednocechowyZeWszystkichPartii(imię,
                        nazwisko, numerOkręgu, wiersz));
                break;
            case 5:
                pomocnicza = wczytajWszechstronnyZeWszystkichPartii(imię, nazwisko, numerOkręgu,
                        wiersz, liczbaCech);
                listaWyborców.add(pomocnicza);
                listaWszechstronnychWyborców.add(pomocnicza);
                break;
            case 6:
                listaWyborców.add(wczytajMinimalizującyJednocechowyZJednejPartii(imię, nazwisko,
                        numerOkręgu, wiersz));
                break;
            case 7:
                listaWyborców.add(wczytajMaksymalizującyJednocechowyZJednejPartii(imię, nazwisko,
                        numerOkręgu, wiersz));
                break;
            case 8:
                pomocnicza = wczytajWszechstronnyZJednejPartii(imię, nazwisko, numerOkręgu, wiersz,
                        liczbaCech);
                listaWyborców.add(pomocnicza);
                listaWszechstronnychWyborców.add(pomocnicza);
            }

            if (i != liczbaWszystkichWyborców) {
                wiersz.close();
                String napis = dane.nextLine();
                wiersz = new Scanner(napis);
            }
        }

        wiersz.close();
        zamieńListyNaTablice();
    }

    private void zamieńListyNaTablice() {
        wyborcy = new Wyborca[listaWyborców.size()];
        wszechstronniWyborcy = new Wszechstronny[listaWszechstronnychWyborców.size()];
        listaWyborców.toArray(wyborcy);
        listaWszechstronnychWyborców.toArray(wszechstronniWyborcy);
    }

    private Wszechstronny wczytajWszechstronnyZJednejPartii(String imię, String nazwisko,
            int numerOkręgu, Scanner wiersz, int liczbaCech) {
        int[] wagiCech = new int[liczbaCech];
        String nazwaPartii;
        for (int i = 0; i < liczbaCech; i++) {
            wagiCech[i] = wiersz.nextInt();
        }
        nazwaPartii = wiersz.next();
        return new WszechstronnyWybierającySpośródJednejPartii(imię, nazwisko, numerOkręgu,
                wagiCech, nazwaPartii);
    }

    private Wyborca wczytajMaksymalizującyJednocechowyZJednejPartii(String imię, String nazwisko,
            int numerOkręgu, Scanner wiersz) {
        int numerCechy = wiersz.nextInt();
        String nazwaPartii = wiersz.next();
        return new MaksymalizującyJednocechowyWybierającySpośródJednejPartii(imię, nazwisko,
                numerOkręgu, numerCechy, nazwaPartii);
    }

    private Wyborca wczytajMinimalizującyJednocechowyZJednejPartii(String imię, String nazwisko,
            int numerOkręgu, Scanner wiersz) {
        int numerCechy = wiersz.nextInt();
        String nazwaPartii = wiersz.next();
        return new MinimalizującyJednocechowyWybierającySpośródJednejPartii(imię, nazwisko,
                numerOkręgu, numerCechy, nazwaPartii);
    }

    private Wszechstronny wczytajWszechstronnyZeWszystkichPartii(String imię, String nazwisko,
            int numerOkręgu, Scanner wiersz, int liczbaCech) {
        int[] wagiCech = new int[liczbaCech];
        for (int i = 0; i < liczbaCech; i++) {
            wagiCech[i] = wiersz.nextInt();
        }

        return new WszechstronnyWybierającySpośródWszystkichPartii(imię, nazwisko, numerOkręgu,
                wagiCech);
    }

    private Wyborca wczytajMaksymalizującyJednocechowyZeWszystkichPartii(String imię,
            String nazwisko, int numerOkręgu, Scanner wiersz) {
        int numerCechy = wiersz.nextInt();
        return new MaksymalizującyJednocechowyWybierającySpośródWszystkichPartii(imię, nazwisko,
                numerOkręgu, numerCechy);
    }

    private Wyborca wczytajMinimalizującyJednocechowyZeWszystkichPartii(String imię,
            String nazwisko, int numerOkręgu, Scanner wiersz) {
        int numerCechy = wiersz.nextInt();
        return new MinimalizującyJednocechowyWybierającySpośródWszystkichPartii(imię, nazwisko,
                numerOkręgu, numerCechy);
    }

    private Wyborca wczytajŻelaznyElektoratKandydata(String imię, String nazwisko, int numerOkręgu,
            Scanner wiersz) {
        String nazwaPartii = wiersz.next();
        int pozycjaNaLiście = wiersz.nextInt();
        return new ŻelaznyElektoratKandydata(imię, nazwisko, numerOkręgu, nazwaPartii,
                pozycjaNaLiście);
    }

    private Wyborca wczytajŻelaznyElektoratPartyjny(String imię, String nazwisko, int numerOkręgu,
            Scanner wiersz) {
        String nazwaPartii = wiersz.next();
        return new ŻelaznyElektoratPartyjny(imię, nazwisko, numerOkręgu, nazwaPartii);
    }

    private int iluWszystkichWyborców(int[] liczbaWyborcówWOkręgach) {
        int pomocnicza = 0;
        for (int a : liczbaWyborcówWOkręgach) {
            pomocnicza += a;
        }

        return pomocnicza;
    }

    protected Wyborca[] getWyborcy() {
        return wyborcy;
    }

    protected Wszechstronny[] getWszechstronniWyborcy() {
        return wszechstronniWyborcy;
    }

}
