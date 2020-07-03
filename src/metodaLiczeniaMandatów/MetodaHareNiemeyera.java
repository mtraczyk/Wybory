package metodaLiczeniaMandatów;

import java.util.ArrayList;
import java.util.List;

import para.Para;
import partia.Partia;
import sortowanie.SortowaniePar;

public class MetodaHareNiemeyera extends MetodaLiczeniaMandatów {

    private int[] liczbaMandatówWOkręgu;

    public MetodaHareNiemeyera(int[] liczbaWyborcówWOkręgu) {
        liczbaMandatówWOkręgu = new int[liczbaWyborcówWOkręgu.length];

        for (int i = 0; i < liczbaWyborcówWOkręgu.length; i++) {
            liczbaMandatówWOkręgu[i] = liczbaWyborcówWOkręgu[i] / 10;
        }
    }

    @Override
    public void przeliczMandaty(Partia[] partie) {
        przygotujPartieNaLiczenieMandatów(partie);

        for (int i = 1; i < liczbaMandatówWOkręgu.length; i++) {
            int ileMandatów = 0;
            int iluWyborcówWOkręgu = iluWyborcówWOkręgu(partie, i);

            if (iluWyborcówWOkręgu != 0) {
                int ileMandatówZostałoDoPrzyznania = liczbaMandatówWOkręgu[i];
                List<Para<Partia, Para<Integer, Integer>>> kolejnośćPrzyznaniaMandatów = new ArrayList<>();

                for (Partia partia : partie) {
                    int a = partia.getLiczbaGłosówWOkręgu(i) * liczbaMandatówWOkręgu[i];
                    ileMandatów = a / iluWyborcówWOkręgu;
                    partia.zliczMandaty(i, ileMandatów);
                    ileMandatówZostałoDoPrzyznania -= ileMandatów;
                    kolejnośćPrzyznaniaMandatów.add(new Para<Partia, Para<Integer, Integer>>(partia,
                            new Para<Integer, Integer>(a - ileMandatów * iluWyborcówWOkręgu, 1)));
                }

                przyznajPozostałeMandaty(kolejnośćPrzyznaniaMandatów,
                        ileMandatówZostałoDoPrzyznania, i);
            }
        }
    }

    private static int iluWyborcówWOkręgu(Partia[] partie, int numerOkręgu) {
        int pomocnicza = 0;
        for (Partia partia : partie) {
            pomocnicza += partia.getLiczbaGłosówWOkręgu(numerOkręgu);
        }

        return pomocnicza;
    }

    private static void przyznajPozostałeMandaty(
            List<Para<Partia, Para<Integer, Integer>>> kolejnośćPrzyznaniaMandatów,
            int ileMandatówZostałoDoPrzyznania, int numerOkręgu) {
        SortowaniePar.sortuj(kolejnośćPrzyznaniaMandatów);

        for (Para<Partia, Para<Integer, Integer>> para : kolejnośćPrzyznaniaMandatów) {
            if (ileMandatówZostałoDoPrzyznania == 0) {
                break;
            } else {
                ileMandatówZostałoDoPrzyznania--;
            }

            para.getPierwszy().zliczMandaty(numerOkręgu, 1);
        }
    }

}
