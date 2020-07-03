package przeprowadźWybory;

import kandydat.Kandydat;
import partia.Partia;
import wyborca.Wszechstronny;
import wyborca.Wyborca;

public class PrzeprowadźWybory {

    public void przeprowadźWybory(int liczbaOkręgów, Partia[] partie, Kandydat[] kandydaci,
            Wyborca[] wyborcy, Wszechstronny[] wszechstronniWyborcy) {
        for (Partia partia : partie) {
            partia.kampaniaWyborcza(liczbaOkręgów, wszechstronniWyborcy, kandydaci);
        }

        for (Wyborca wyborca : wyborcy) {
            wyborca.oddajGłos(kandydaci);
        }

        for (Wyborca wyborca : wyborcy) {
            wyborca.getGłosujeNaKandydata().setLiczbaZebranychGłosów();
        }
    }

}
