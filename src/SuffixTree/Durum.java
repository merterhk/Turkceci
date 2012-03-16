package SuffixTree;

import java.util.HashMap;

/**
 * @author merter
 */
public class Durum {

    char c;
    boolean sonDurum;
    private HashMap<Character, Durum> cikislar = new HashMap<Character, Durum>();

    public Durum cikisEkle(String ekDizisi) {
        if ("".equals(ekDizisi)) {
            return null;
        }

        String[] ekler = ekDizisi.split(",");
        for (String ek : ekler) {
            if (!"".equals(ek)) {

            }
        }
        return this;
    }
}
