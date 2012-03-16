package Arsiv;

import Araclar.Dagarcik;
import Kelimeler.Kelime;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author merter
 */
public class EkAvcisi {

    /* Çekim Ekleri */
    // <editor-fold defaultstate="collapsed" desc="Çekim Ekleri Regex">
/* isme gelen çekim ekleri regex */
    String cogulCekimEki = "lar|ler"; // -ler -lar
    String iyelikCekimEki = "m|n|[ıiuü]m|[ıiuü]n|s?[ıiuü]|[ıiuü]?m[ıiuü]z|[ıiuü]?n[ıiuü]z|ları|leri"; // -ım -ın -ı -mız -nız -ları
    String tamlayanCekimEki = "ın|in|un|ün"; // -ın -in -un -ün
    String halCekimEki = "y?[ıiuü]|y?[ae]|da|de|ta|te|[dt][ae]|[dt][ae]n";// -i -e -de -den
    String ekfiil = "(dır|dir|dur|dür|tır|tir|tur|tür|idi|dı|di|du|dü|tı|ti|tu|tü|imiş|mış|miş|muş|müş|sa|se)(m|n|k|nız|sınız)?"; // ım sın dır ız sınız dırlar
    /* fiile gelen çekim ekleri regex*/
    String bilesikFiilCekimEki = "(y?[ae]m[ae]|y?[ae]dur|y?[ıiuü]ver|y?[ae]gel|y?[ae]gör|y?[ae]bil|y?[ae]yaz|y?[ae]kal)";// geldigeliver düşeyaz
    String kisiCekimEki = "([iıuü]?m|(s[iıuü])?n|k|z|n?[ıiuü]z|l[ae]r)";// geldi--m geldi-n geldi geldi-k geldi-niz geldi-ler
    String kipZamanCekimEki = "(y?[dt][ıiuü]|y?m[ıiuü]ş|[ıiuü]?yor|y?[ae]c[ae]k(l[ae]r)?|r|m[ae]z|[ae](l|y|ler)?|s[ae](l[ae]r)?|m[ae]l[ıi](l[ae]r)?)";// geldi,gelmiş,geliyor,gelecek,gelir,gele,gelse,gelmeli// 
    String olumsuzlukEki = "(y?[dt][ıiuü]|y?m[ıiuü]ş|[ıiuü]?yor|y?[ae]c[ae]k(l[ae]r)?|r|m[ae]z|[ae](l|y|ler)?|s[ae](l[ae]r)?|m[ae]l[ıi](l[ae]r)?)";// geldi,gelmiş,geliyor,gelecek,gelir,gele,gelse,gelmeli
    String catiEki = "(([ıiuü]?[lşndt])?){0,2}";// </editor-fold>
    /* Yapım Ekleri */
    // <editor-fold defaultstate="collapsed" desc="Yapım Ekleri Regex">
// Addan Ad Türeten Yapım Ekleri
    String isimdenIsim = "(a|e|ca|ce|ça|çe|cağız|ceğiz|cak|cek|çak|çek|cı|ci|cu|cü|çı|çi|çu|çü|cık|cik|cuk|cük|çık|çik|çuk|çük|cıl|cil|cul|cül|çıl|çil|çul|çül|cın|cin|cun|cün|çın|çin|çun|çün|da|de|ta|te|dak|dek|tak|tek|dam|dem|tam|tem|dan|den|tan|ten|daş|deş|taş|teş|dırık|dirik|duruk|dürük|aç|eç|ağı|eği|ak|ek|alak|elek|an|en|arı|eri|az|ez|ız|iz|uz|üz|ı|i|u|ü|ncı|inci|uncu|üncü|ıl|il|ul|ül|ımsı|imsi|umsu|ümsü|mtırak|imtırak|umtırak|ıt|it|ut|üt|ki|kü|la|le|lak|lek|lam|lem|lum|lüm|lama|leme|layın|leyin|laz|lez|lı|li|lu|lü|lık|lik|luk|lük|man|men|ra|re|sak|sek|sal|sel|sı|si|su|sü|sız|siz|suz|süz|sın|sin|tı|ti|tu|tü|daki|deki|taki|teki)";
    // Addan Eylem Türeten Yapım Ekleri
    String isimdenFiil = "(a|e|al|el|an|en|ar|er|at|et|ık|ik|uk|ük|msa|imse|umsa|ümse|ırga|irge|kır|kir|kur|kür|la|le|lan|len|laş|leş|ra|re|sa|se|sı|si|su|sü)";
    // Eylemden Ad Türeten Yapım Ekleri
    String fiildenIsim = "(a|e|acak|ecek|aç|eç|ıç|iç|uç|üç|ağan|eğen|ak|al|el|alak|elek|am|em|ım|im|um|üm|amaç|emeç|amak|emek|an|en|anak|enek|arak|erek|arı|eri|ası|esi|ca|ce|ça|çe|dı|di|du|dü|tı|ti|tu|tü|dık|dik|duk|dük|tık|tik|tuk|tük|dıkça|dikçe|dukça|dükçe|ga|ge|gaç|geç|kaç|keç|gan|gen|kan|ken|gı|gi|gu|gü|kı|ki|ku|kü|gıç|giç|guç|güç|gın|gun|gün|kın|kin|kun|kün|ı|i|u|ü|ıcı|ici|ucu|ücü|ıç|iç|uç|üç|ık|ik|uk|ük|ıl|il|ul|ül|ılı|ili|ulu|ülü|ım|im|um|üm|ın|in|un|ün|anç|enç|ınç|inç|unç|ünç|ıntı|inti|untu|üntü|ar|er|ır|ir|ur|ür|ış|iş|uş|üş|ıt|it|ut|üt|ma|me|maca|mece|maç|meç|mak|mek|mık|mik|muk|mük|mış|miş|muş|müş|naz|nez|tı|ti|tu|tü)";
    // Eylemden Eylem Türeten Yapım Ekleri
    String fiildenFiil = "(akla|ekle|ala|ele|ar|er|ır|ir|ur|ür|dar|der|dır|dir|dur|dür|tır|tir|tür|tür|ı|i|u|ü|ık|uk|uk|ük|ıksa|ikse|ıl|il|ul|ül|ın|in|un|ün|ar|er|ır|ur|ür|ış|iş|uş|üş|ışla|işle|uşla|üşle|ıştır|iştir|ustur|üştür|ıt|it|ut|üt)";// </editor-fold>
    /* Ek Patternleri */
    // <editor-fold defaultstate="collapsed" desc="Ek Patternleri">
// Çekim ekleri
    // isme gelen
    Pattern cogul = Pattern.compile("(.*?)(" + cogulCekimEki + ")$");
    Pattern tamlanan = Pattern.compile("(.*?)(" + iyelikCekimEki + ")$");
    Pattern tamlayan = Pattern.compile("(.*?)(" + tamlayanCekimEki + ")$");
    Pattern durum = Pattern.compile("(.*?)(" + halCekimEki + ")$");
    // fiile gelen
    Pattern kisi = Pattern.compile("(.*?)(" + kisiCekimEki + ")$");
    Pattern kip = Pattern.compile("(.*?)(" + kipZamanCekimEki + ")$");
    // Yapım ekleri
    Pattern isimdenIsime = Pattern.compile("(.+?)(" + isimdenIsim + ")$");
    Pattern isimdenFiile = Pattern.compile("(.+?)(" + isimdenFiil + ")$");
    Pattern fiildenIsime = Pattern.compile("(.+?)(" + fiildenIsim + ")$");
    Pattern fiildenFiile = Pattern.compile("(.+?)(" + fiildenFiil + ")$");
//    Pattern fiilYapan = Pattern.compile("(.+?)(("++")|())+");
    // İsme gelen ekler
    Pattern isimCEPattern = Pattern.compile("(.+?)(" + cogulCekimEki + ")?(" + iyelikCekimEki + ")?(" + halCekimEki + ")?(" + tamlayanCekimEki + ")?(" + ekfiil + ")?(ki)?$");
    Pattern fiilCEPattern = Pattern.compile("(.+?)((" + catiEki + ")?(" + olumsuzlukEki + ")?(" + bilesikFiilCekimEki + "(([ıiuü]?[lşndt]?){0,2})?)?((" + kipZamanCekimEki + ")?(l[ae]r)?(" + kipZamanCekimEki + ")?)?(" + kisiCekimEki + ")?)?$");// </editor-fold>

    public int fiilYESentez(String kelime) {
        Matcher m;
        m = fiildenFiile.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1)) || fiilYESentez(m.group(1)) != 0) {
                System.out.println("Fiilden fiile yapım eki : -" + m.group(2));
                return 1;
            }
            return fiilYESentez(m.group(1));
        }

        m = isimdenFiile.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1)) || isimYESentez(m.group(1)) != 0) {
                System.out.println("İsimden fiile yapım eki : -" + m.group(2));
                return 2;
            }
            return isimYESentez(m.group(1));
        }
        return 0;
    }

    public int isimYESentez(String kelime) {

        Matcher m;
        m = fiildenIsime.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1)) || fiilYESentez(m.group(1)) != 0) {
                System.out.println("Fiilden isime yapım eki : -" + m.group(2));
                return 1;
            }
        }

        m = isimdenIsime.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1)) || isimYESentez(m.group(1)) != 0) {
                System.out.println("İsimden isime yapım eki : -" + m.group(2));
                return 2;
            }
        }

        return 0;
    }

    public int fiilCESentez(String kelime) {
        Matcher m;

        m = fiilCEPattern.matcher(kelime);
        if (m.matches()) {
            if (Dagarcik.varMi(m.group(1), Dagarcik.fiil) || fiilYESentez(m.group(1)) != 0) {
                if (m.group(3) != null) {
                    System.out.println("Çatı eki : -" + m.group(3));
                }
                if (m.group(5) != null) {
                    System.out.println("Olumsuzluk eki : -" + m.group(5));
                }
                if (m.group(11) != null) {
                    System.out.println("Birleşik fiil eki : -" + m.group(11));
                }
                if (m.group(15) != null) {
                    System.out.println("Kip-Zaman eki : -" + m.group(15));
                }
                if (m.group(22) != null) {
                    System.out.println("Kip-Zaman çoğul eki : -" + m.group(22));
                }
                if (m.group(29) != null) {
                    System.out.println("Kisi eki : -" + m.group(29));
                }
//                for (int i = 0; i < m.groupCount(); i++) {
//                    System.out.println(i + "->" + m.group(i));
//                }
                System.out.println("Fiil : " + m.group(1));
                return 1;
            }
            Stack stack = new Stack();
            char[] chars = m.group(2).toCharArray();
            for (int i = chars.length - 1; i > -1; i--) {
                stack.push(chars[i]);
            }
            String s = m.group(1);
            while (!stack.isEmpty()) {
                s += stack.pop();
                if (Dagarcik.varMi(s, Dagarcik.fiil)) {
                    System.out.println("Fiil : " + s);
                    return 1;
                }
            }

        }
        return 0;
    }

    public int isimCESentez(String kelime) {
        Matcher m;

        m = isimCEPattern.matcher(kelime);
        if (m.matches()) {
            System.out.println(m.group(1));
            if (Dagarcik.varMi(m.group(1)) || isimYESentez(m.group(1)) != 0) {
                if (m.group(2) != null) {
                    System.out.println("Çoğul eki : -" + m.group(2));
                }
                if (m.group(3) != null) {
                    System.out.println("İyelik eki : -" + m.group(3));
                }
                if (m.group(4) != null) {
                    System.out.println("Hal eki : -" + m.group(4));
                }
                if (m.group(5) != null) {
                    System.out.println("Tamlayan eki : -" + m.group(5));
                }
                if (m.group(6) != null) {
                    System.out.println("Ek-fiil : -" + m.group(6));
                }
                if (m.group(7) != null) {
                    System.out.println("ki : -" + m.group(7));
                }
                System.out.println("İsim : " + m.group(1));
                return 2;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        EkAvcisi ea2 = new EkAvcisi();
        Dagarcik.initKelimeHaznesi();
        Dagarcik.ekle("gel", Kelime.fiil);
        String kelime = "elmalarımızdakilerinkinden";

        long nano = System.nanoTime();
        long mili = System.currentTimeMillis();
        if (ea2.isimCESentez(kelime) == 0) {
            System.out.println("İsim Çekim eki bulunamadı...");
        }
        System.out.println("------------");
        if (ea2.fiilCESentez(kelime) == 0) {
            System.out.println("Fiil Çekim eki bulunamadı...");
        }
        System.out.println((System.nanoTime() - nano) + "us");
        System.out.println((System.currentTimeMillis() - mili) + "ms");
    }
}
