package sortowanie;

import java.util.Random;

import para.Para;

public class KomparatorParLiczbCałkowitych {

    public int porównaj(Para<Integer, Integer> a, Para<Integer, Integer> b) {
        if (a.getPierwszy() * b.getDrugi() == a.getDrugi() * b.getPierwszy()) {
            Random generator = new Random();
            int pomocnicza = generator.nextInt(2);

            if (pomocnicza == 0) {
                return -1;
            } else {
                return 1;
            }
        } else if (a.getPierwszy() * b.getDrugi() > a.getDrugi() * b.getPierwszy()) {
            return -1;
        } else {
            return 1;
        }
    }

}
