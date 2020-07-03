package wczytywanieDanych;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kandydat.Kandydat;
import partia.Partia;

public class WczytajPrzetwórzKandydatów {

    private Kandydat[] kandydaci;
    private String[] nazwaPartiiKandydata;
    List<Kandydat> listaKandydatów = new ArrayList<Kandydat>();
    List<String> listaNazwPartiiKandydatów = new ArrayList<String>();

    public String wczytajKandydatów(Scanner dane, int liczbaCech,
            int[] numeryKolejnychOkręgówPoScaleniu) {
        String imię, nazwisko, kolejnaLiniaWejścia = dane.nextLine();
        int numerOkręgu, pozycjaNaLiście;
        int[] wartościCech = new int[liczbaCech + 1];

        while (czyWczytujęKandydata(new String(kolejnaLiniaWejścia))) {
            Scanner wiersz = new Scanner(kolejnaLiniaWejścia);
            imię = wiersz.next();
            nazwisko = wiersz.next();
            numerOkręgu = numeryKolejnychOkręgówPoScaleniu[wiersz.nextInt()];
            listaNazwPartiiKandydatów.add(wiersz.next());
            pozycjaNaLiście = wiersz.nextInt();
            wczytajCechyKandydata(wartościCech, wiersz, liczbaCech);
            listaKandydatów
                    .add(new Kandydat(imię, nazwisko, numerOkręgu, pozycjaNaLiście, wartościCech));
            wiersz.close();
            kolejnaLiniaWejścia = dane.nextLine();
        }

        zamieńListyNaTablice();
        return kolejnaLiniaWejścia;
    }

    private void zamieńListyNaTablice() {
        kandydaci = new Kandydat[listaKandydatów.size()];
        listaKandydatów.toArray(kandydaci);
        nazwaPartiiKandydata = new String[listaNazwPartiiKandydatów.size()];
        listaNazwPartiiKandydatów.toArray(nazwaPartiiKandydata);
    }

    private static boolean czyWczytujęKandydata(String napis) {
        Scanner wiersz = new Scanner(napis);
        wiersz.next();
        wiersz.next();
        wiersz.next();

        if (wiersz.hasNextBigInteger()) {
            wiersz.close();
            return false;
        }

        wiersz.close();
        return true;
    }

    private void wczytajCechyKandydata(int[] wartościCech, Scanner wiersz, int liczbaCech) {
        for (int i = 1; i <= liczbaCech; i++) {
            wartościCech[i] = wiersz.nextInt();
        }
    }

    public void ustawPartieKandydatom(Partia[] partie) {
        for (int i = 0; i < kandydaci.length; i++) {
            for (Partia partia : partie) {
                if (partia.getNazwaPartii().equals(nazwaPartiiKandydata[i])) {
                    kandydaci[i].setPartia(partia);
                    break;
                }
            }
        }
    }

    protected Kandydat[] getKandydaci() {
        return kandydaci;
    }

}
