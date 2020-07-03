package metodaLiczeniaMandatów;

import java.util.ArrayList;
import java.util.List;

import para.Para;
import partia.Partia;
import sortowanie.SortowaniePar;

public abstract class MetodaIlorazowa extends MetodaLiczeniaMandatów {

    private int[] liczbaMandatówWOkręgu;

    public MetodaIlorazowa(int[] liczbaWyborcówWOkręgu) {
        liczbaMandatówWOkręgu = new int[liczbaWyborcówWOkręgu.length];

        for (int i = 0; i < liczbaWyborcówWOkręgu.length; i++) {
            liczbaMandatówWOkręgu[i] = liczbaWyborcówWOkręgu[i] / 10;
        }
    }

    protected abstract int mianownikIlorazu(int numerMianownika);

    @Override
    public void przeliczMandaty(Partia[] partie) {
        przygotujPartieNaLiczenieMandatów(partie);

        for (int i = 1; i < liczbaMandatówWOkręgu.length; i++) {
            List<Para<Partia, Para<Integer, Integer>>> kolejnośćPrzyznaniaMandatów = new ArrayList<>();

            for (Partia partia : partie) {
                for (int k = 1; k <= liczbaMandatówWOkręgu[i]; k++) {
                    kolejnośćPrzyznaniaMandatów.add(new Para<Partia, Para<Integer, Integer>>(partia,
                            new Para<Integer, Integer>(partia.getLiczbaGłosówWOkręgu(i),
                                    mianownikIlorazu(k))));
                }
            }

            przyznajMandaty(kolejnośćPrzyznaniaMandatów, liczbaMandatówWOkręgu[i], i);
        }
    }

    private static void przyznajMandaty(
            List<Para<Partia, Para<Integer, Integer>>> kolejnośćPrzyznaniaMandatów,
            int liczbaMandatówDoPrzyznania, int numerOkręgu) {
        SortowaniePar.sortuj(kolejnośćPrzyznaniaMandatów);

        for (Para<Partia, Para<Integer, Integer>> para : kolejnośćPrzyznaniaMandatów) {
            if (liczbaMandatówDoPrzyznania == 0) {
                break;
            }
            liczbaMandatówDoPrzyznania--;

            para.getPierwszy().zliczMandaty(numerOkręgu, 1);
        }
    }

}
