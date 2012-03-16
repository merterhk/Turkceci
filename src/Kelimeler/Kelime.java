/* Kelime*/
package Kelimeler;

import Ekler.YapimEki;

public abstract class Kelime {
// <editor-fold defaultstate="collapsed" desc="Kelime türü sabitleri">

    public static final int isim = 1;
    public static final int fiil = 2;
    public static final int sifat = 4;
    public static final int zarf = 8;
    public static final int zamir = 16;
    public static final int edat = 32;
    public static final int baglac = 64;
    public static final int unlem = 128;
    // </editor-fold>
    private Kelime diger;
    int tur = 0;
    private String kelime;
    boolean turemis = false;
    boolean buyukUnluUyumu = false, kucukUnluUyumu = false;

    public Kelime(String kelime) {
        this.kelime = kelime;
    }

    public void addYapimEki(YapimEki ye) {
    }

    public void dokum() {
    }

    public void dokumAyrintili() {
    }

    public void setKelime(String kelime) {
        this.kelime = kelime;
    }

    public String getKelime() {
        return kelime;
    }

    public void setBuyukUnluUyumu(boolean buyukUnluUyumu) {
        this.buyukUnluUyumu = buyukUnluUyumu;
    }

    public void setKucukUnluUyumu(boolean kucukUnluUyumu) {
        this.kucukUnluUyumu = kucukUnluUyumu;
    }

    public void setTuremis(boolean turemis) {
        this.turemis = turemis;
    }

    public Kelime getDiger() {
        return diger;
    }

    public void setDiger(Kelime diger) {
        this.diger = diger;
    }

    public void setTur(int tur) {
        this.tur = tur;
    }

    public int getTur() {
        return tur;
    }
}
