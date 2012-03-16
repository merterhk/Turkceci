package Cumle;

import Kelimeler.Isim;
import Kelimeler.Sifat;

/**
 * @author merter
 */
public class AdObegi {

    Sifat sifat;
    AdObegi adObegi1, adObegi2;
    Isim isim;

    public AdObegi(AdObegi adObegi1, AdObegi adObegi2) {
        this.adObegi1 = adObegi1;
        this.adObegi2 = adObegi2;
    }

    public AdObegi(Isim isim) {
        this.isim = isim;
    }

    public AdObegi(Sifat sifat, AdObegi adObegi1) {
        this.sifat = sifat;
        this.adObegi1 = adObegi1;
    }
}
