package para;

public class Para<T1, T2> {

    private T1 pierwszy;
    private T2 drugi;

    public Para(T1 pierwszy, T2 drugi) {
        this.pierwszy = pierwszy;
        this.drugi = drugi;
    }

    public T1 getPierwszy() {
        return pierwszy;
    }

    public T2 getDrugi() {
        return drugi;
    }

    public void setPierwszy(T1 a) {
        pierwszy = a;
    }

    public void setDrugi(T2 a) {
        drugi = a;
    }

    public void zamień(Para<T1, T2> b) {
        T1 pierwszyZDrugiejPary = b.getPierwszy();
        T2 drugiZDrugiejPary = b.getDrugi();

        b.setPierwszy(pierwszy);
        b.setDrugi(drugi);
        pierwszy = pierwszyZDrugiejPary;
        drugi = drugiZDrugiejPary;
    }

}
