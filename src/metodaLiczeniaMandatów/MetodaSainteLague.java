package metodaLiczeniaMandatów;

public class MetodaSainteLague extends MetodaIlorazowa {

    public MetodaSainteLague(int[] liczbaWyborcówWOkręgu) {
        super(liczbaWyborcówWOkręgu);
    }

    @Override
    protected int mianownikIlorazu(int numerMianownika) {
        return 2 * numerMianownika - 1;
    }

}
