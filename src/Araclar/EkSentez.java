/*
 * Verilen kelimeyi kök+ek(ler) olarak ayrıştırır ve türüne göre geri döndürür.
 * Static bir sınıftır..
 */
package Araclar;

import Ekler.CekimEkleri.FiileGelen.*;
import Ekler.CekimEkleri.IsimeGelen.*;
import Ekler.YapimEkleri.*;
import Kelimeler.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author merter
 */
public class EkSentez {
    /* Çekim Ekleri */
    // <editor-fold defaultstate="collapsed" desc="Çekim Ekleri Regex">
    /* isme gelen çekim ekleri regex */

    private static final String cogulCekimEki = "lar|ler"; // -ler -lar
    private static final String iyelikCekimEki = "m|n|[ıiuü]m|[ıiuü]n|s?[ıiuü]|[ıiuü]?m[ıiuü]z|[ıiuü]?n[ıiuü]z|ları|leri"; // -ım -ın -ı -mız -nız -ları
    private static final String tamlayanCekimEki = "ın|in|un|ün"; // -ın -in -un -ün
    private static final String halCekimEki = "y?[ıiuü]|y?[ae]|da|de|ta|te|[dt][ae]|[dt][ae]n";// -i -e -de -den
    private static final String ekfiil = "(dır|dir|dur|dür|tır|tir|tur|tür|idi|dı|di|du|dü|tı|ti|tu|tü|imiş|mış|miş|muş|müş|sa|se)(m|n|k|nız|sınız)?"; // ım sın dır ız sınız dırlar
    /* fiile gelen çekim ekleri regex */
    private static final String bilesikFiilCekimEki = "(y?[ae]m[ae]|y?[ae]dur|y?[ıiuü]ver|y?[ae]gel|y?[ae]gör|y?[ae]bil|y?[ae]yaz|y?[ae]kal)";// geldigeliver düşeyaz
    private static final String kisiCekimEki = "([iıuü]?m|(s[iıuü])?n|k|z|n?[ıiuü]z|l[ae]r)";// geldi--m geldi-n geldi geldi-k geldi-niz geldi-ler
    private static final String kipZamanCekimEki = "(y?[dt][ıiuü]|y?m[ıiuü]ş|[ıiuü]?yor|y?[ae]c[ae]k(l[ae]r)?|[ıiuüae]?r|m[ae]z|[ae](l|y|ler)?|s[ae](l[ae]r)?|m[ae]l[ıi](l[ae]r)?)";// geldi,gelmiş,geliyor,gelecek,gelir,gele,gelse,gelmeli//
    private static final String olumsuzlukEki = "(a?ma|e?me|a?mı|e?mi)";
    private static final String catiEki = "([ıiuü]?[rşln]|dir|tir|t){0,2}";// </editor-fold>
    /* Yapım Ekleri */
    // <editor-fold defaultstate="collapsed" desc="Yapım Ekleri Regex">
// Addan Ad Türeten Yapım Ekleri
    private static final String isimdenIsim = "(a|e|ca|ce|ça|çe|cağız|ceğiz|cak|cek|çak|çek|cı|ci|cu|cü|çı|çi|çu|çü|cık|cik|cuk|cük|çık|çik|çuk|çük|cıl|cil|cul|cül|çıl|çil|çul|çül|cın|cin|cun|cün|çın|çin|çun|çün|da|de|ta|te|dak|dek|tak|tek|dam|dem|tam|tem|dan|den|tan|ten|daş|deş|taş|teş|dırık|dirik|duruk|dürük|aç|eç|ağı|eği|ak|ek|alak|elek|an|en|arı|eri|az|ez|ız|iz|uz|üz|ı|i|u|ü|ncı|inci|uncu|üncü|ıl|il|ul|ül|ımsı|imsi|umsu|ümsü|mtırak|imtırak|umtırak|ıt|it|ut|üt|ki|kü|la|le|lak|lek|lam|lem|lum|lüm|lama|leme|layın|leyin|laz|lez|lı|li|lu|lü|lık|lik|luk|lük|man|men|ra|re|sak|sek|sal|sel|sı|si|su|sü|sız|siz|suz|süz|sın|sin|tı|ti|tu|tü|daki|deki|taki|teki)";
    // Addan Eylem Türeten Yapım Ekleri
    private static final String isimdenFiil = "(a|e|al|el|an|en|ar|er|at|et|ık|ik|uk|ük|msa|imse|umsa|ümse|ırga|irge|kır|kir|kur|kür|la|le|lan|len|laş|leş|ra|re|sa|se|sı|si|su|sü)";
    // Eylemden Ad Türeten Yapım Ekleri
    private static final String fiildenIsim = "(a|e|acak|ecek|aç|eç|ıç|iç|uç|üç|ağan|eğen|ak|al|el|alak|elek|am|em|ım|im|um|üm|amaç|emeç|amak|emek|an|en|anak|enek|arak|erek|arı|eri|ası|esi|ca|ce|ça|çe|dı|di|du|dü|tı|ti|tu|tü|dık|dik|duk|dük|tık|tik|tuk|tük|dıkça|dikçe|dukça|dükçe|ga|ge|gaç|geç|kaç|keç|gan|gen|kan|ken|gı|gi|gu|gü|kı|ki|ku|kü|gıç|giç|guç|güç|gın|gun|gün|kın|kin|kun|kün|ı|i|u|ü|ıcı|ici|ucu|ücü|ıç|iç|uç|üç|ık|ik|uk|ük|ıl|il|ul|ül|ılı|ili|ulu|ülü|ım|im|um|üm|ın|in|un|ün|anç|enç|ınç|inç|unç|ünç|ıntı|inti|untu|üntü|ar|er|ır|ir|ur|ür|ış|iş|uş|üş|ıt|it|ut|üt|ma|me|maca|mece|maç|meç|mak|mek|mık|mik|muk|mük|mış|miş|muş|müş|naz|nez|tı|ti|tu|tü|[ıiuü]?p)";
    // Eylemden Eylem Türeten Yapım Ekleri
    private static final String fiildenFiil = "(akla|ekle|ala|ele|ar|er|ır|ir|ur|ür|dar|der|dır|dir|dur|dür|tır|tir|tür|tür|ı|i|u|ü|ık|uk|uk|ük|ıksa|ikse|ıl|il|ul|ül|ın|in|un|ün|ar|er|ır|ur|ür|ış|iş|uş|üş|ışla|işle|uşla|üşle|ıştır|iştir|ustur|üştür|ıt|it|ut|üt)";// </editor-fold>
    /* Ek Patternleri */
    // <editor-fold defaultstate="collapsed" desc="Ek Patternleri">
    // Yapım ekleri
    static Pattern isimdenIsime = Pattern.compile("(.+?)(" + isimdenIsim + ")$");
    static Pattern isimdenFiile = Pattern.compile("(.+?)(" + isimdenFiil + ")$");
    static Pattern fiildenIsime = Pattern.compile("(.+?)(" + fiildenIsim + ")$");
    static Pattern fiildenFiile = Pattern.compile("(.+?)(" + fiildenFiil + ")$");
    // Çekim ekleri
    static Pattern isimCEPattern = Pattern.compile("(.+?)((" + cogulCekimEki + ")?(" + iyelikCekimEki + ")?(" + halCekimEki + ")?(" + tamlayanCekimEki + ")?(" + ekfiil + ")?(ki)?)$");
    static Pattern fiilCEPattern = Pattern.compile("(.+?)((" + catiEki + ")?(" + olumsuzlukEki + ")?(" + bilesikFiilCekimEki + ")?((" + kipZamanCekimEki + ")((l[ae]r)?" + kipZamanCekimEki + ")?)?(" + kisiCekimEki + ")?)$");// </editor-fold>

    public static Kelime sentezle(String str) {
        Kelime kelime = null, diger = null;
        int tur = 0;
        String yapayKelime = "xXxyapayxXx";
        Dagarcik.ekle(yapayKelime, Kelime.isim);
        kelime = kelimeSentez(str);
        kelime.setTur(Dagarcik.getir(kelime.getKelime()));
        diger = kelime;
        for (int i = 2; i < str.length(); i++) {
            if (Dagarcik.varMi(str.substring(0, i)) && (tur = Dagarcik.getir(str.substring(0, i))) != 0) {
                diger.setDiger(kelimeSentez(str.substring(0, i)));
                diger = diger.getDiger();
                diger.setTur(tur);
            }
        }
        return kelime;
    }

    public static Kelime kelimeSentez(String str) {
        int tur;
        if (Dagarcik.varMi(str) && (tur = Dagarcik.getir(str)) != 0) {
            if ((tur & Kelime.isim) == Kelime.isim) {
                return new Isim(str);
            } else if ((tur & Kelime.fiil) == Kelime.fiil) {
                return new Fiil(str);
            } else if ((tur & Kelime.sifat) == Kelime.sifat) {
                return new Sifat(str);
            } else if ((tur & Kelime.zarf) == Kelime.zarf) {
                return new Zarf(str);
            } else if ((tur & Kelime.edat) == Kelime.edat) {
                return new Edat(str);
            } else if ((tur & Kelime.zamir) == Kelime.zamir) {
                return new Zamir(str);
            } else if ((tur & Kelime.baglac) == Kelime.baglac) {
                return new Baglac(str);
            } else if ((tur & Kelime.unlem) == Kelime.unlem) {
                return new Unlem(str);
            } else {
                return new Tanimsiz(str);
            }
        }
        Kelime isim = isimCESentez(str);
        Kelime fiil = fiilCESentez(str);
        if (isim != null && fiil != null) {
            System.out.println("Çift anlamlı kelime olabilir!");
        }

        if (fiil != null && Dagarcik.varMi(fiil.getKelime(), Dagarcik.fiil)) {
            return fiil;
        } else if (isim != null) {
            return isim;
        }

        Tanimsiz tanimsiz = new Tanimsiz();
        tanimsiz.setKelime(str);
        return tanimsiz;
    }

    private static Kelime fiilYESentez(String kelime, Kelime k) {
        Matcher m;
        Kelime fiilYE = null;

        m = fiildenFiile.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1)) || (fiilYE = fiilYESentez(m.group(1), k)) != null) {
//                System.out.println("Fiilden fiile yapım eki : -" + m.group(2));
//                Fiil fiil = new Fiil();
                k.setKelime((fiilYE == null) ? m.group(1) : fiilYE.getKelime());
                k.addYapimEki(new FiildenFiilEki(m.group(2)));
//                k.setKok(fiilYE);
//                k.addYapimEki(new FiildenFiilEki(m.group(2)));
                return k;
            }
            return fiilYESentez(m.group(1), k);
        }

        m = isimdenFiile.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1)) || (fiilYE = isimYESentez(m.group(1), k)) != null) {
//                System.out.println("İsimden fiile yapım eki : -" + m.group(2));
//                Isim isim = new Isim();
                k.setKelime((fiilYE == null) ? m.group(1) : fiilYE.getKelime());
                k.addYapimEki(new IsimdenFiilEki(m.group(2)));
//                k.setKok(isim);
//                k.setKelime((fiilYE == null) ? m.group(1) : fiilYE.getKelime());
//                k.addYapimEki(new IsimdenFiilEki(m.group(2)));
                return k;
            }
            return isimYESentez(m.group(1), k);
        }
        return null;
    }

    private static Kelime isimYESentez(String kelime, Kelime k) {
        Matcher m;
        Kelime isimYE = null;

        m = fiildenIsime.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1)) || (isimYE = fiilYESentez(m.group(1), k)) != null) {
//                System.out.println("Fiilden isime yapım eki : -" + m.group(2));
//                Fiil fiil = new Fiil();
                k.setKelime((isimYE == null) ? m.group(1) : isimYE.getKelime());
                k.addYapimEki(new FiildenIsimEki(m.group(2)));
//                k.setKok(fiil);

//                k.setKelime((isimYE == null) ? m.group(1) : isimYE.getKelime());
//                k.addYapimEki(new FiildenIsimEki(m.group(2)));
                return k;
            }
        }

        m = isimdenIsime.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1)) || (isimYE = isimYESentez(m.group(1), k)) != null) {
//                System.out.println("İsimden isime yapım eki : -" + m.group(2));
//                Isim isim = new Isim();
                k.setKelime((isimYE == null) ? m.group(1) : isimYE.getKelime());
                k.addYapimEki(new IsimdenIsimEki(m.group(2)));
//                k.setKok(isim);
//
//                k.setKelime((isimYE == null) ? m.group(1) : isimYE.getKelime());
//                k.addYapimEki(new IsimdenIsimEki(m.group(2)));
                return k;
            }
        }

        return null;
    }

    private static Kelime fiilCESentez(String kelime) {
        Matcher m;
        Kelime fiilYE = null;
        Fiil fiil = new Fiil();

        m = fiilCEPattern.matcher(kelime);
        if (m.matches()) {
//            for (int i = 0; i < m.groupCount(); i++) {
//                System.out.println(i + ":" + m.group(i));
//            }
            if (Dagarcik.varMi(m.group(1), Dagarcik.fiil) || (fiilYE = fiilYESentez(m.group(1), fiil)) != null) {

                fiil.setKok(fiilYE);
                fiil.setKelime((fiilYE == null) ? m.group(1) : fiilYE.getKelime());
                fiil.setCatiEki((m.group(3) == null) ? null : new CatiEki(m.group(3)));
                fiil.setOlumsuzlukEki((m.group(5) == null) ? null : new OlumsuzlukEki(m.group(5)));
                fiil.setBilesikFiilEki((m.group(7) == null) ? null : new BilesikFiilEki(m.group(7)));
                fiil.setKipZamanEki((m.group(9) == null) ? null : new KipZamanEki(m.group(9)));
//                fiil.setKipZamanEki((m.group(22) == null) ? null : null);
                fiil.setKisiEki((m.group(23) == null) ? null : new KisiEki(m.group(23)));

                return fiil;
            }
            String s = m.group(1);
            char[] chars = m.group(2).toCharArray();
            for (int i = 0; i < chars.length; i++) {
                s += chars[i];
                if (Dagarcik.varMi(s, Dagarcik.fiil)) {
                    String yapayFiil = "xXxyapayFiilxXx";
                    Dagarcik.ekle(yapayFiil, Kelime.fiil);
                    for (int j = i + 1; j < chars.length; j++) {
                        yapayFiil += chars[j];
                    }
                    fiil = (Fiil) fiilCESentez(yapayFiil);
                    fiil.setKelime(s);
                    return fiil;
                }
            }

        }
        return null;
    }

    private static Kelime isimCESentez(String kelime) {
        Matcher m;
        Kelime isimYE = null, isimCE = null;
        Isim isim = new Isim();

        m = isimCEPattern.matcher(kelime);
        if (m.matches()) {
//            System.out.println(m.group(1));
//            System.out.println(m.group(9));
//            for (int i = 0; i < m.groupCount(); i++) {
//                System.out.println(i + ":" + m.group(i));
//            }
            if (Dagarcik.varMi(m.group(1)) || (isimYE = isimYESentez(m.group(1), isim)) != null) {
                isim.setKok((isimCE == null) ? isim.getKok() : isimCE);
//                isimYE = (isimYE == null) ? isimCE : isimYE;
                isim.setKelime((isimYE == null) ? m.group(1) : isimYE.getKelime());
                isim.setCogulEki(((m.group(3) == null)) ? null : new CogulEki(m.group(3)));
                isim.setIyelikEki(((m.group(4) == null)) ? null : new IyelikEki(m.group(4)));
                isim.setHalEki(((m.group(5) == null)) ? null : new HalEki(m.group(5)));
                isim.setTamlayanEki(((m.group(6) == null)) ? null : new TamlayanEki(m.group(6)));
                isim.setEkFiil(((m.group(7) == null)) ? null : new EkFiilEki(m.group(7)));
                isim.setKiEki(((m.group(9) == null)) ? null : new KiEki(m.group(9)));

                return isim;
            }
            if (m.group(1).endsWith("ki")) {
                Isim isimKi = null;
                if ((isimKi = (Isim) isimCESentez(m.group(1))) != null) {
                    return isimKi;
                }
            }
            if (m.group(2) != null) {
                String s = m.group(1);
                char[] chars = m.group(2).toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    s += chars[i];
                    if (Dagarcik.varMi(s)) {
                        String yapayIsim = "xXxyapayIsimxXx";
                        Dagarcik.ekle(yapayIsim, Kelime.isim);
                        for (int j = i + 1; j < chars.length; j++) {
                            yapayIsim += chars[j];
                        }
                        isim = (Isim) isimCESentez(yapayIsim);
                        isim.setKelime(s);
                        return isim;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Dagarcik.initKelimeHaznesi();
        Dagarcik.ekle("sağla", Kelime.fiil);
        long b = System.currentTimeMillis();
        long a = System.nanoTime();

        Kelime k = kelimeSentez("geliyor    ");

        System.out.println((System.nanoTime() - a) / 1000 + "us");
        System.out.println((System.currentTimeMillis() - b) + "ms");
    }
}
