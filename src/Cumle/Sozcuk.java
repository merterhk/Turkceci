    package Cumle;

import Araclar.Dagarcik;
import Araclar.EkSentez;
import Kelimeler.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author merter
 */
public class Sozcuk {
// <editor-fold defaultstate="collapsed" desc="Kelime türü sabitleri">

    public static final byte kelime_isim = 1;
    public static final byte kelime_fiil = 2;
    public static final byte kelime_sifat = 4;
    public static final byte kelime_zarf = 8;
    public static final byte kelime_edat = 16;
    public static final byte kelime_unlem = 32;// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Sahip sabitleri">
    public static final int sahip_benim = 1;
    public static final int sahip_senin = 2;
    public static final int sahip_onun = 3;
    public static final int sahip_bizim = 4;
    public static final int sahip_sizin = 5;
    public static final int sahip_onların = 6;// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Kişi sabitleri">
    public static final int kisi_ben = 1;
    public static final int kisi_sen = 2;
    public static final int kisi_o = 3;
    public static final int kisi_biz = 4;
    public static final int kisi_siz = 5;
    public static final int kisi_onlar = 6;// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Fiil Kipleri">
    public static final int kip_di = 1;
    public static final int kip_mis = 2;
    public static final int kip_yor = 4;
    public static final int kip_ecek = 8;
    public static final int kip_r = 16;
    public static final int kip_mez = 32; // olumsuzu
    public static final int kip_se = 64;
    public static final int kip_meli = 128;
    // birleşik kipler
    public static final int kip_di_se = kip_di + kip_se;
    public static final int kip_mis_se = kip_mis + kip_se;
    public static final int kip_yor_sa = kip_yor + kip_se;
    public static final int kip_r_se = kip_r + kip_se;
    public static final int kip_mez_se = kip_mez + kip_se;
    public static final int kip_ecek_se = kip_ecek + kip_se;
    public static final int kip_meli_se = kip_meli + kip_se;
    public static final int kip_meli_di = kip_meli + kip_di;
    public static final int kip_meli_mis = kip_meli + kip_mis;
    public static final int kip_di_di = kip_di + kip_di;
    public static final int kip_mis_di = kip_mis + kip_di;
    public static final int kip_mis_mis = kip_mis + kip_mis;
    public static final int kip_yor_mis = kip_yor + kip_mis;
    public static final int kip_ecek_mis = kip_ecek + kip_mis;
    public static final int kip_r_mis = kip_r + kip_mis;
    public static final int kip_mez_mis = kip_mez + kip_mis;// </editor-fold>
    Sozcuk kok;
    String kelime;
    byte kelimeTur;
    byte sahip;
    byte kisi;
    int kip;
    /* Kelimenin diğer türleri için oluşturulmuş bağlı liste */
    Sozcuk diger;

    public void sentezle() {
        Sozcuk diger = this.diger;
        int tur;
        String yapayKelime = "xXxyapayxXx";
        Kelime k;
        Dagarcik.ekle(yapayKelime, Kelime.isim);

        for (int i = 2; i < kelime.length(); i++) {

            if (Dagarcik.varMi(kelime.substring(0, i)) && (tur = Dagarcik.getir(kelime.substring(0, i))) != 0) {
                k = EkSentez.kelimeSentez(yapayKelime + kelime.substring(i));
                if (!(k instanceof Tanimsiz)) {
                    
                    System.out.println(k.getKelime());
                }
            }
        }
    }

    public static int sentezle(String Kelime) {
        return Dagarcik.getir(Kelime);
    }

    public void setFiilKipi(int kip) {
    }

    public void setCekimEki(int kip) {
    }

    public static void main(String[] args) {
        Sozcuk s = new Sozcuk();
        Dagarcik.initKelimeHaznesi();
        s.kelime = "atalarımızdan";
        s.sentezle();
        System.out.println();
    }
}
