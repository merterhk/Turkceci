package Ekler;

import java.util.ArrayList;

/**
 * @author merter
 */
public abstract class YapimEki {

    boolean isimKokenli = false, fiilKokenli = false;
    public ArrayList<YapimEki> yapimEkleri = new ArrayList<YapimEki>();
    public String ek;

    public YapimEki(String ek) {
        this.ek = ek;
    }
}
