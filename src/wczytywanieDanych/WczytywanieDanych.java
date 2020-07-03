package wczytywanieDanych;

import java.util.Scanner;

import kandydat.Kandydat;
import partia.Partia;
import wyborca.Wszechstronny;
import wyborca.Wyborca;

public class WczytywanieDanych {

    private int liczbaOkręgów;
    private int liczbaPartii;
    private int liczbaDziałań;
    private int liczbaCech;

    private int liczbaOkręgówDoScalenia;
    private int[] numeryKolejnychOkręgówPoScaleniu;
    private int[] liczbaWyborcówWOkręgach;

    private Wyborca[] wyborcy;
    private Wszechstronny[] wszechstronniWyborcy;
    private Kandydat[] kandydaci;
    private Partia[] partie;

    private void wczytajParametry(Scanner dane) {
        Scanner wiersz = new Scanner(dane.nextLine());
        liczbaOkręgów = wiersz.nextInt();
        liczbaPartii = wiersz.nextInt();
        liczbaDziałań = wiersz.nextInt();
        liczbaCech = wiersz.nextInt();
        wiersz.close();
    }

    public void przetwórzDane(Scanner dane) {
        wczytajParametry(dane);
        WczytajPrzetwórzPartie wczytajPrzetwórzPartie = new WczytajPrzetwórzPartie();
        WczytajPrzetwórzKandydatów wczytajPrzetwórzKandydatów = new WczytajPrzetwórzKandydatów();
        WczytajPrzetwórzWyborców wczytajPrzetwórzWyborców = new WczytajPrzetwórzWyborców();

        przetwórzNumeryOkręgów(dane);
        wczytajPrzetwórzPartie.wczytajDaneDotyczącePartii(dane, liczbaPartii);
        wczytajLiczbęWyborcówWOkręgach(dane);
        String ostatniWczytanyWiersz = wczytajPrzetwórzKandydatów.wczytajKandydatów(dane,
                liczbaCech, numeryKolejnychOkręgówPoScaleniu);
        wczytajPrzetwórzWyborców.wczytajWyborców(dane, ostatniWczytanyWiersz,
                liczbaWyborcówWOkręgach, numeryKolejnychOkręgówPoScaleniu, liczbaCech);
        wczytajPrzetwórzPartie.wczytajMożliweDziałania(dane, liczbaDziałań, liczbaCech);
        wczytajPrzetwórzPartie.utwórzPartie(liczbaPartii, liczbaDziałań, liczbaOkręgów);
        wczytajPrzetwórzKandydatów.ustawPartieKandydatom(wczytajPrzetwórzPartie.getPartie());

        zgromadźInformacje(wczytajPrzetwórzPartie, wczytajPrzetwórzKandydatów,
                wczytajPrzetwórzWyborców);
    }

    private void zgromadźInformacje(WczytajPrzetwórzPartie wczytajPrzetwórzPartie,
            WczytajPrzetwórzKandydatów wczytajPrzetwórzKandydatów,
            WczytajPrzetwórzWyborców wczytajPrzetwórzWyborców) {
        partie = wczytajPrzetwórzPartie.getPartie();
        kandydaci = wczytajPrzetwórzKandydatów.getKandydaci();
        wyborcy = wczytajPrzetwórzWyborców.getWyborcy();
        wszechstronniWyborcy = wczytajPrzetwórzWyborców.getWszechstronniWyborcy();
    }

    private void wczytajLiczbęWyborcówWOkręgach(Scanner dane) {
        liczbaWyborcówWOkręgach = new int[liczbaOkręgów + 1];
        Scanner wiersz = new Scanner(dane.nextLine());

        for (int i = 1; i <= liczbaOkręgów; i++) {
            liczbaWyborcówWOkręgach[numeryKolejnychOkręgówPoScaleniu[i]] += wiersz.nextInt();
        }
        wiersz.close();
    }

    private void przetwórzNumeryOkręgów(Scanner dane) {
        String napis = dane.nextLine();
        Scanner pierwszaLiczba = new Scanner(napis);
        liczbaOkręgówDoScalenia = pierwszaLiczba.nextInt();
        pierwszaLiczba.close();
        numeryKolejnychOkręgówPoScaleniu = new int[liczbaOkręgów + 1];
        Scanner wiersz = przygotujCzytnik(napis, liczbaOkręgówDoScalenia);

        for (int i = 0; i < numeryKolejnychOkręgówPoScaleniu.length; i++) {
            numeryKolejnychOkręgówPoScaleniu[i] = i;
        }

        for (int i = 1; i <= liczbaOkręgówDoScalenia; i++) {
            int okręgPodstawowy = wiersz.nextInt(), okręgDoScalenia = wiersz.nextInt();
            numeryKolejnychOkręgówPoScaleniu[okręgDoScalenia] = okręgPodstawowy;
        }

        if (wiersz != null) {
            wiersz.close();
        }
    }

    private Scanner przygotujCzytnik(String napis, int liczabOkręgówDoScalenia) {
        if (liczbaOkręgówDoScalenia == 0) {
            return null;
        }
        napis = napis.substring(napis.indexOf(' '));
        napis = napis.replace(',', ' ');
        napis = napis.replace("(", "");
        napis = napis.replace(")", "");

        return new Scanner(napis);
    }

    public Wyborca[] getWyborcy() {
        return wyborcy;
    }

    public Wszechstronny[] getWszechstronniWyborcy() {
        return wszechstronniWyborcy;
    }

    public Partia[] getPartie() {
        return partie;
    }

    public Kandydat[] getKandydaci() {
        return kandydaci;
    }

    public int getLiczbaOkręgów() {
        return liczbaOkręgów;
    }

    public int[] getNumeryKolejnychOkręgówPoScaleniu() {
        return numeryKolejnychOkręgówPoScaleniu;
    }

    public int[] getLiczbaWyborcówWOkręgach() {
        return liczbaWyborcówWOkręgach;
    }

}
