package wczytywanieDanych;

import java.util.Scanner;

import partia.Partia;
import partia.Skromna;
import partia.Własna;
import partia.ZRozmachem;
import partia.Zachłanna;

public class WczytajPrzetwórzPartie {

    private String[] nazwaPartii;
    private int[] budżetPartii;
    private String[] strategiaPartii;
    private int[][] działania;
    private Partia[] partie;

    public void wczytajDaneDotyczącePartii(Scanner dane, int liczbaPartii) {
        zaalokujTablicePrzechowująceDaneNaTematPartii(liczbaPartii);

        Scanner wiersz = new Scanner(dane.nextLine());
        for (int i = 0; i < liczbaPartii; i++) {
            nazwaPartii[i] = wiersz.next();
        }
        wiersz.close();

        wiersz = new Scanner(dane.nextLine());
        for (int i = 0; i < liczbaPartii; i++) {
            budżetPartii[i] = wiersz.nextInt();
        }
        wiersz.close();

        wiersz = new Scanner(dane.nextLine());
        for (int i = 0; i < liczbaPartii; i++) {
            strategiaPartii[i] = wiersz.next();
        }
        wiersz.close();
    }

    private void zaalokujTablicePrzechowująceDaneNaTematPartii(int liczbaPartii) {
        nazwaPartii = new String[liczbaPartii];
        budżetPartii = new int[liczbaPartii];
        strategiaPartii = new String[liczbaPartii];
        partie = new Partia[liczbaPartii];
    }

    public void wczytajMożliweDziałania(Scanner dane, int liczbaDziałań, int liczbaCech) {
        działania = new int[liczbaDziałań][liczbaCech];

        for (int i = 0; i < liczbaDziałań; i++) {
            String napis = dane.nextLine();
            Scanner wiersz = new Scanner(napis);

            for (int j = 0; j < liczbaCech; j++) {
                działania[i][j] = wiersz.nextInt();
            }

            wiersz.close();
        }
    }

    public void utwórzPartie(int liczbaPartii, int liczbaDziałań, int liczbaOkręgów) {
        for (int i = 0; i < liczbaPartii; i++) {
            switch (strategiaPartii[i]) {
            case "R":
                partie[i] = new ZRozmachem(nazwaPartii[i], budżetPartii[i], liczbaDziałań,
                        liczbaOkręgów, działania);
                break;
            case "S":
                partie[i] = new Skromna(nazwaPartii[i], budżetPartii[i], liczbaDziałań,
                        liczbaOkręgów, działania);
                break;
            case "Z":
                partie[i] = new Zachłanna(nazwaPartii[i], budżetPartii[i], liczbaDziałań,
                        liczbaOkręgów, działania);
                break;
            case "W":
                partie[i] = new Własna(nazwaPartii[i], budżetPartii[i], liczbaDziałań,
                        liczbaOkręgów, działania);
            }
        }
    }

    protected Partia[] getPartie() {
        return partie;
    }

}
