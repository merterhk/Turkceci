package Arsiv;

import Araclar.Dagarcik;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArsivKelime {

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
// <editor-fold defaultstate="collapsed" desc="Çekim Ekleri Regex">
    /* Çekim Ekleri */
    /* isme gelen çekim ekleri regex */
    String cogulCekimEki = "(lar|ler)"; // -ler -lar
    String tamlananCekimEki = "([ıiuü]m|n?[ıiuü]n|[ıiuü]|[ıiuü]?m[ıiuü]z|[ıiuü]?n[ıiuü]z|l[ae]r[ıi])"; // -ım -ın -ı -mız -nız -ları
    String tamlayanCekimEki = "(n?[ıiuü]n)"; // -ın -in -un -ün
    String isimDurumCekimEki = "([ıiuü]|y?[ae]|[dt][ae]|[dt][ae]n)";// -i -e -de -den
    /* fiile gelen çekim ekleri regex*/
    String kisiCekimEki = "([iıuü]?m|(s[iıuü])?n|k|n?[ıiuü]z|l[ae]r)";// geldi--m geldi-n geldi geldi-k geldi-niz geldi-ler
    String kipZamanCekimEki = "(y?[dt][ıiuü]|y?m[ıiuü]ş|[ıiuü]?yor|y?[ae]c[ae]k(l[ae]r)?|r|m[ae]z|[ae](l|y|ler)?|s[ae](l[ae]r)?|m[ae]l[ıi](l[ae]r)?)";// geldi,gelmiş,geliyor,gelecek,gelir,gele,gelse,gelmeli// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Yapım Ekleri Regex">
    /* Yapım Ekleri */
    // Addan Ad Türeten Yapım Ekleri
    String isimdenIsim = "(a|e|ca|ce|ça|çe|cağız|ceğiz|cak|cek|çak|çek|cı|ci|cu|cü|çı|çi|çu|çü|cık|cik|cuk|cük|çık|çik|çuk|çük|cıl|cil|cul|cül|çıl|çil|çul|çül|cın|cin|cun|cün|çın|çin|çun|çün|da|de|ta|te|dak|dek|tak|tek|dam|dem|tam|tem|dan|den|tan|ten|daş|deş|taş|teş|dırık|dirik|duruk|dürük|aç|eç|ağı|eği|ak|ek|alak|elek|an|en|arı|eri|az|ez|ız|iz|uz|üz|ı|i|u|ü|ncı|inci|uncu|üncü|ıl|il|ul|ül|ımsı|imsi|umsu|ümsü|mtırak|imtırak|umtırak|ıt|it|ut|üt|ki|kü|la|le|lak|lek|lam|lem|lum|lüm|lama|leme|layın|leyin|laz|lez|lı|li|lu|lü|lık|lik|luk|lük|man|men|ra|re|sak|sek|sal|sel|sı|si|su|sü|sız|siz|suz|süz|sın|sin|tı|ti|tu|tü)";
    // Addan Eylem Türeten Yapım Ekleri
    String isimdenFiil = "(a|e|al|el|an|en|ar|er|at|et|ık|ik|uk|ük|msa|imse|umsa|ümse|ırga|irge|kır|kir|kur|kür|la|le|lan|len|laş|leş|ra|re|sa|se|sı|si|su|sü)";
    // Eylemden Ad Türeten Yapım Ekleri
    String fiildenIsim = "(a|e|acak|ecek|aç|eç|ıç|iç|uç|üç|ağan|eğen|ak|al|el|alak|elek|am|em|ım|im|um|üm|amaç|emeç|amak|emek|an|en|anak|enek|arak|erek|arı|eri|ası|esi|ca|ce|ça|çe|dı|di|du|dü|tı|ti|tu|tü|dık|dik|duk|dük|tık|tik|tuk|tük|dıkça|dikçe|dukça|dükçe|ga|ge|gaç|geç|kaç|keç|gan|gen|kan|ken|gı|gi|gu|gü|kı|ki|ku|kü|gıç|giç|guç|güç|gın|gun|gün|kın|kin|kun|kün|ı|i|u|ü|ıcı|ici|ucu|ücü|ıç|iç|uç|üç|ık|ik|uk|ük|ıl|il|ul|ül|ılı|ili|ulu|ülü|ım|im|um|üm|ın|in|un|ün|anç|enç|ınç|inç|unç|ünç|ıntı|inti|untu|üntü|ar|er|ır|ir|ur|ür|ış|iş|uş|üş|ıt|it|ut|üt|ma|me|maca|mece|maç|meç|mak|mek|maz|mez|mık|mik|muk|mük|mış|miş|muş|müş|naz|nez|tı|ti|tu|tü)";
    // Eylemden Eylem Türeten Yapım Ekleri
    String fiildenFiil = "(akla|ekle|ala|ele|ar|er|ır|ir|ur|ür|dar|der|dır|dir|dur|dür|tır|tir|tür|tür|ı|i|u|ü|ık|uk|uk|ük|ıksa|ikse|ıl|il|ul|ül|ın|in|un|ün|ar|er|ır|ur|ür|ış|iş|uş|üş|ışla|işle|uşla|üşle|ıştır|iştir|ustur|üştür|ıt|it|ut|üt)";// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Ek Patternleri">
    // Ek Patternleri //
// Çekim ekleri
    // isme gelen
    Pattern cogul = Pattern.compile("(.*?)(" + cogulCekimEki + ")$");
    Pattern tamlanan = Pattern.compile("(.*?)(" + tamlananCekimEki + ")$");
    Pattern tamlayan = Pattern.compile("(.*?)(" + tamlayanCekimEki + ")$");
    Pattern durum = Pattern.compile("(.*?)(" + isimDurumCekimEki + ")$");
    // fiile gelen
    Pattern kisi = Pattern.compile("(.*?)(" + kisiCekimEki + ")$");
    Pattern kip = Pattern.compile("(.*?)(" + kipZamanCekimEki + ")$");
    // Yapım ekleri
    Pattern isimdenIsime = Pattern.compile("(.*?)(" + isimdenIsim + ")$");
    Pattern isimdenFiile = Pattern.compile("(.*?)(" + isimdenFiil + ")$");
    Pattern fiildenIsime = Pattern.compile("(.*?)(" + fiildenIsim + ")$");
    Pattern fiildenFiile = Pattern.compile("(.*?)(" + fiildenFiil + ")$");
    // İsme gelen ekler
    Pattern isimPattern = Pattern.compile("(.+?)(" + cogulCekimEki + ")?(" + tamlananCekimEki + ")?(" + isimDurumCekimEki + ")?(" + tamlayanCekimEki + ")?$");
    Pattern fiilPattern = Pattern.compile("(.+?)(m[ae]z?)?((" + kipZamanCekimEki + ")?(l[ae]r)?(" + kipZamanCekimEki + ")?)(" + kisiCekimEki + ")?$");// </editor-fold>
    /* Kelime özellikleri */
    public boolean turemis = false;
    public String kok;
    public byte tur;

    public int cekimEkiSentez(String kelime) {
        Matcher m;

        m = fiilPattern.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1))) {
                System.out.println("Fiildir... : " + m.group(1));
                if (m.group(2) != null) {
                    System.out.println("Olumsuzluk eki : -" + m.group(2));
                }
                if (m.group(3) != null) {
                    System.out.println("Kip-Zaman eki : -" + m.group(3));
                }
                if (m.group(17) != null) {
                    System.out.println("Kisi eki : -" + m.group(17));
                }

                return 1;
            }
        }

        m = isimPattern.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1))) {
                System.out.println("isimdir... : " + m.group(1));
                if (m.group(2) != null) {
                    System.out.println("Çoğul eki : -" + m.group(2));
                }
                if (m.group(3) != null) {
                    System.out.println("Tamlanan eki : -" + m.group(3));
                }
                if (m.group(4) != null) {
                    System.out.println("Tamlayan eki : -" + m.group(4));
                }
                if (m.group(5) != null) {
                    System.out.println("Hal eki : -" + m.group(5));
                }
                return 2;
            }
        }

        return 0;
    }
}
