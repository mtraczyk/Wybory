package wypiszInformacje;

import kandydat.Kandydat;
import metodaLiczeniaMandatów.MetodaDHondta;
import metodaLiczeniaMandatów.MetodaHareNiemeyera;
import metodaLiczeniaMandatów.MetodaIlorazowa;
import metodaLiczeniaMandatów.MetodaLiczeniaMandatów;
import metodaLiczeniaMandatów.MetodaSainteLague;
import partia.Partia;
import wyborca.Wyborca;

public class WypiszInformacje {

    public void wypiszInformacje(Partia[] partie, Wyborca[] wyborcy, Kandydat[] kandydaci,
            int[] numeryKolejnychOkręgówPoScaleniu, int[] liczbaWyborcówWOkręgu) {
        MetodaIlorazowa metodaDHondta = new MetodaDHondta(liczbaWyborcówWOkręgu);
        MetodaIlorazowa metodaSainteLague = new MetodaSainteLague(liczbaWyborcówWOkręgu);
        MetodaLiczeniaMandatów metodaHareNiemeyera = new MetodaHareNiemeyera(liczbaWyborcówWOkręgu);

        metodaDHondta.przeliczMandaty(partie);
        System.out.println("Metoda D`Hondta");
        wypisz(partie, wyborcy, kandydaci, numeryKolejnychOkręgówPoScaleniu);

        metodaSainteLague.przeliczMandaty(partie);
        System.out.println("Metoda Sainte-Lague");
        wypisz(partie, wyborcy, kandydaci, numeryKolejnychOkręgówPoScaleniu);

        metodaHareNiemeyera.przeliczMandaty(partie);
        System.out.println("Metoda Hare`a-Niemeyera");
        wypisz(partie, wyborcy, kandydaci, numeryKolejnychOkręgówPoScaleniu);
    }

    private void wypisz(Partia[] partie, Wyborca[] wyborcy, Kandydat[] kandydaci,
            int[] numeryKolejnychOkręgówPoScaleniu) {
        for (int i = 1; i < numeryKolejnychOkręgówPoScaleniu.length; i++) {
            if (numeryKolejnychOkręgówPoScaleniu[i] == i) {
                System.out.println("Okręg : " + i);
                wypiszWyborców(wyborcy, i);
                wypiszKandydatów(kandydaci, i);
                wypiszPartie(partie, i);
            }
        }

        wypiszŁączneIlościMandatówPartii(partie);
    }

    private static void wypiszŁączneIlościMandatówPartii(Partia[] partie) {
        for (Partia partia : partie) {
            System.out.println(partia.getNazwaPartii() + " " + partia.getCałkowitaLiczbaMandatów());
        }
    }

    private static void wypiszPartie(Partia[] partie, int numerOkręgu) {
        for (Partia partia : partie) {
            System.out.println(
                    partia.getNazwaPartii() + " " + partia.getLiczbaMandatówWOkręgu(numerOkręgu));
        }
    }

    private static void wypiszKandydatów(Kandydat[] kandydaci, int numerOkręgu) {
        for (Kandydat kandydat : kandydaci) {
            if (kandydat.getNumerOkręgu() == numerOkręgu) {
                System.out.println(kandydat.getImię() + " " + kandydat.getNazwisko() + " "
                        + kandydat.getNazwaPartii() + " " + kandydat.getPozycjaNaLiście() + " "
                        + kandydat.getLiczbaZebranychGłosów());
            }
        }
    }

    private static void wypiszWyborców(Wyborca[] wyborcy, int numerOkręgu) {
        for (Wyborca wyborca : wyborcy) {
            if (wyborca.getNumerOkręgu() == numerOkręgu) {
                System.out.println(wyborca.getImię() + " " + wyborca.getNazwisko() + " "
                        + wyborca.getGłosujeNaKandydata().getImię() + " "
                        + wyborca.getGłosujeNaKandydata().getNazwisko());
            }
        }
    }

}
