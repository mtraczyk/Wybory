package main;

import java.io.File;
import java.util.Scanner;

import przeprowadźWybory.PrzeprowadźWybory;
import wczytywanieDanych.WczytywanieDanych;
import wypiszInformacje.WypiszInformacje;

public class Main {

    public static void main(String[] args) {
        try (Scanner dane = new Scanner(new File(args[0]))) {
            WczytywanieDanych wczytajPrzetwórzDane = new WczytywanieDanych();
            PrzeprowadźWybory przeprowadźWybory = new PrzeprowadźWybory();
            WypiszInformacje wypiszInformacje = new WypiszInformacje();

            wczytajPrzetwórzDane.przetwórzDane(dane);
            przeprowadźWybory.przeprowadźWybory(wczytajPrzetwórzDane.getLiczbaOkręgów(),
                    wczytajPrzetwórzDane.getPartie(), wczytajPrzetwórzDane.getKandydaci(),
                    wczytajPrzetwórzDane.getWyborcy(),
                    wczytajPrzetwórzDane.getWszechstronniWyborcy());

            wypiszInformacje.wypiszInformacje(wczytajPrzetwórzDane.getPartie(),
                    wczytajPrzetwórzDane.getWyborcy(), wczytajPrzetwórzDane.getKandydaci(),
                    wczytajPrzetwórzDane.getNumeryKolejnychOkręgówPoScaleniu(),
                    wczytajPrzetwórzDane.getLiczbaWyborcówWOkręgach());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
