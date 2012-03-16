package Ekler;

import java.util.ArrayList;

/**
 * @author merter
 */
public abstract class CekimEki {

    public ArrayList<YapimEki> yapimEkleri = new ArrayList<YapimEki>();
    public String ek;

    public CekimEki(String ek) {
        this.ek = ek;
    }
}
