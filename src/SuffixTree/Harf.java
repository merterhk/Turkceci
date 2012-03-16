package SuffixTree;

import java.util.HashMap;

public class Harf {

    char c;
    boolean durum = false;
    boolean kaynastirmaAlabilir = false;
    boolean sonHarf = false;
    boolean sonDurum = false;
    String ekAdi = "";
    private HashMap<Character, Harf> onceki = new HashMap<Character, Harf>();

    public Harf(char c) {
        this.c = c;
    }

    public Harf(char c, boolean durum) {
        this.c = c;
        this.durum = durum;
    }

    public Harf(char c, boolean kaynastirmaAlabilir, boolean sonDurum) {
        this.c = c;
        this.kaynastirmaAlabilir = kaynastirmaAlabilir;
        this.sonDurum = sonDurum;
    }

    public void setEkAdi(String ekAdi) {
        this.ekAdi = ekAdi;
    }

    public void setSonDurum(boolean sonDurum) {
        this.sonDurum = sonDurum;
    }

    public Harf onceki(char c) {
        return onceki.get(c);
    }

    public Harf gecis(String ekDizisi, Harf durum) { // mUş
        if ("".equals(ekDizisi)) {
            return null;
        }

        String[] ekler = ekDizisi.split(",");
        for (String ek : ekler) {
            if (!"".equals(ek)) {

                Harf oncekiHarf = this;
                for (int i = ek.length() - 1; i >= 0; i--) {
                    if (oncekiHarf.onceki(ek.charAt(i)) == null) {
                        oncekiHarf.onceki.put(ek.charAt(i), new Harf(ek.charAt(i)));
                    }
                    oncekiHarf = oncekiHarf.onceki(ek.charAt(i));
                }

                for (char ch : durum.onceki.keySet()) {
                    if (oncekiHarf.onceki(ch) == null) {
                        oncekiHarf.onceki.put(ch, durum.onceki(ch));
                    }
                }


            }
        }
        return durum;
    }

    public HashMap<Character, Harf> getOnceki() {
        return onceki;
    }
}
