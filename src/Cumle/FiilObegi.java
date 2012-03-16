package Cumle;

import Kelimeler.Fiil;
import Kelimeler.Zarf;

/**
 * @author merter
 */
public class FiilObegi {

    Zarf zarf;
    AdObegi adObegi;
    Fiil fiil;

    public FiilObegi(Zarf zarf, Fiil fiil) {
        this.zarf = zarf;
        this.fiil = fiil;
    }

    public FiilObegi(AdObegi adObegi, Fiil fiil) {
        this.adObegi = adObegi;
        this.fiil = fiil;
    }

    public FiilObegi(Fiil fiil) {
        this.fiil = fiil;
    }
}
