package metodaLiczeniaMandatów;

import partia.Partia;

public abstract class MetodaLiczeniaMandatów {

    public abstract void przeliczMandaty(Partia[] partie);

    protected static void przygotujPartieNaLiczenieMandatów(Partia[] partie) {
        for (Partia partia : partie) {
            partia.przygotujPartięNaLiczenieMandatów();
        }
    }

}
