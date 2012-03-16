package Cumle;

import Araclar.Dagarcik;
import Araclar.EkSentez;
import Kelimeler.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class CumleAnaliz {

    String strCumle;
    StringTokenizer sTokenizer;
    Stack stack = new Stack();

    public CumleAnaliz(String strCumle) {
        Dagarcik.initKelimeHaznesi();
        this.strCumle = strCumle;
        sTokenizer = new StringTokenizer(strCumle);
    }

    public void analizEt() {
        while (sTokenizer.hasMoreTokens()) {
            Kelime kelime = EkSentez.sentezle(sTokenizer.nextToken());
            kelime.dokum();
            System.out.println(" (" + kelimeTur(kelime) + ") --?" + kelime.getTur());
            Kelime diger = kelime.getDiger();

//            while (diger != null) {
//                System.out.println("--> " + diger.getKelime() + " (" + kelimeTur(diger) + ")" + diger.getTur());
//                diger = diger.getDiger();
//            }
        }
    }

    private String kelimeTur(Kelime kelime) {
        String tur = "";

        if ((kelime.getTur()) == 0) {
            return ("Tanımsız");
        }

        if ((kelime.getTur() & Kelime.sifat) == Kelime.sifat) {
            tur += ("Sıfat") + " | ";
        }
        if ((kelime.getTur() & Kelime.fiil) == Kelime.fiil) {
            tur += ("Fiil") + " | ";
        }
        if ((kelime.getTur() & Kelime.zarf) == Kelime.zarf) {
            tur += ("Zarf") + " | ";
        }
        if ((kelime.getTur() & Kelime.edat) == Kelime.edat) {
            tur += ("Edat") + " | ";
        }
        if ((kelime.getTur() & Kelime.zamir) == Kelime.zamir) {
            tur += ("Zamir") + " | ";
        }
        if ((kelime.getTur() & Kelime.baglac) == Kelime.baglac) {
            tur += ("Baglac") + " | ";
        }
        if ((kelime.getTur() & Kelime.unlem) == Kelime.unlem) {
            tur += ("Unlem") + " | ";
        }

        if ((kelime.getTur() & Kelime.isim) == Kelime.isim) {
            tur += ("İsim");
        }
        return tur;
    }

    public static void main(String[] args) {
        CumleAnaliz ca = new CumleAnaliz("ali masayı kırdın geliyorum geldim");
        Dagarcik.ekle("ali", Kelime.isim);
        ca.analizEt();
    }
}
