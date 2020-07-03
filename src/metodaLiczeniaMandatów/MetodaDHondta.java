package metodaLiczeniaMandatów;

public class MetodaDHondta extends MetodaIlorazowa {

    public MetodaDHondta(int[] liczbaWyborcówWOkręgu) {
        super(liczbaWyborcówWOkręgu);
    }

    @Override
    protected int mianownikIlorazu(int numerMianownika) {
        return numerMianownika;
    }

}
