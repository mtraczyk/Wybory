package sortowanie;

import java.util.List;

import para.Para;
import partia.Partia;

public class SortowaniePar {

    public static void sortuj(List<Para<Partia, Para<Integer, Integer>>> dane) {
        KomparatorParLiczbCałkowitych komparator = new KomparatorParLiczbCałkowitych();

        for (int i = 0; i < dane.size(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (komparator.porównaj(dane.get(j + 1).getDrugi(), dane.get(j).getDrugi()) < 0) {
                    dane.get(j + 1).zamień(dane.get(j));
                } else {
                    break;
                }
            }
        }
    }

}
