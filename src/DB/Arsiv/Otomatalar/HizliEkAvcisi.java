package DB.Arsiv.Otomatalar;

import DB.Vt;
import DB.Arsiv.Otomatalar.tablo1;
import DB.Arsiv.Otomatalar.tablo2;
import DB.Arsiv.Otomatalar.tablo4;
import DB.Arsiv.Otomatalar.tablo5;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.misc.Queue;

public class HizliEkAvcisi extends Thread {

    // <editor-fold defaultstate="collapsed" desc="Harfler ve türleri">
//Ünlü harfler ve turleri
    char[] unluler = {'a', 'e', 'ı', 'i', 'o', 'ö', 'u', 'ü'};
    char[] kalinUnluler = {'a', 'ı', 'o', 'u'};
    char[] inceUnluler = {'e', 'i', 'ö', 'ü'};
    char[] genisUnluler = {'a', 'e', 'o', 'ö'};
    char[] darUnluler = {'ı', 'i', 'u', 'ü'};
    char[] yuvarlakUnluler = {'o', 'u', 'ö', 'ü'};
    char[] duzUnluler = {'a', 'e', 'ı', 'i'};
    // Ünsüz harfler ve turleri
    char[] unsuzler = {'b', 'c', 'ç', 'd', 'f', 'g', 'ğ', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'r', 's', 'ş', 't', 'v', 'y', 'z'};
    char[] sertUnsuzler = {'f', 's', 't', 'k', 'ç', 'ş', 'h', 'p'};
    char[] kaynaştırma = {'y', 'ş', 's', 'n'};// </editor-fold>
    //**************************************
    // <editor-fold defaultstate="collapsed" desc="Ekler ve türleri">
// Ek-eylem Kümesi Ek Tablosu (isim'e uygula)
    String[] tablo1 = {"ım", "im", "um", "üm", "sın", "sin", "sun", "sün", "ız", "iz", "uz", "üz", "sınız", "siniz", "sunuz", "sünüz", "lar", "ler", "m", "n", "k", "nız", "niz", "nuz", "nüz", "dır", "dir", "dur", "dür", "tır", "tir", "tur", "tür", "casına", "cesine", "dı", "di", "du", "dü", "tı", "ti", "tu", "tü", "sa", "se", "mış", "miş", "muş", "müş", "ken"};
    String reg1 = "y?[ıiuü]m|s[ıiuü]n|y?[ıiuü]z|s[ıiuü]n[ıiuü]z|l[ae]r|m|n|k|n[ıiuü]z|[dt][ıiuü]r|c[ae]s[ıi]n[ae]|y?[dt][ıiuü]|y?s[ae]|y?m[ıiuü]ş|y?ken";
    // İsim Çekim Ekleri Kümesi Ek Tablosu (isim'e uygula)
    String[] tablo2 = {"lar", "ler", "m", "mız", "miz", "muz", "müz", "n", "nız", "niz", "nuz", "nüz", "ı", "i", "u", "ü", "ları", "leri ", "nı", "ni", "nu", "nü", "ın", "in", "un", "ün", "a", "e", "na", "ne", "da", "de", "ta", "te", "nda", "nde", "nta", "nte", "dan", "den", "tan", "ten", "ndan", "nden", "ntan", "nten", "la", "le", "ki", "ca", "ce"};
    String reg2 = "[ıiuü]|l[ae]r|[ıiuü]?m|[ıiuü]?m[ıiuü]z|[ıiuü]?n|[ıiuü]?n[ıiuü]z|s|[ıiuü]|l[ae]r[ıi]|n[ıiuü]|n?[ıiuü]n|y?[ae]|n[ae]|[dt][ea]|n[dt][ae]|[dt][ae]n|n[dt][ae]n|y?l[ae]|ki|n?c[ae]";
    // Yapım Ekleri Kümesi Ek Tablosu (isim'e uygula)
    String[] tablo3 = {"lık", "lik", "luk", "lük", "cı", "ci", "cu", "cü", "çı", "çi", "çu", "çü", "cık", "cik", "cuk", "cük", "çık", "çik", "çuk", "çük", "laş", "leş", "la", "le", "lan", "len", "ca", "ce", "ça", "çe", "lı", "li", "lu", "lü", "sız", "siz", "suz", "süz"};
    String reg3 = "l[ıiuü][kğ]|[cç][ıiuü][k]?|l[ae][şn]?|[cç][ae]|l[ıiuü]|s[ıiuü]z";
    // Eylem Zaman Ekleri Kümesi Ek Tablosu (fiil'e uygula)
    String[] tablo4 = {"ım", "im", "um", "üm", "sın", "sin", "sun", "sün", "ız", "iz", "uz", "üz", "sınız", "siniz", "sunuz", "sünüz", "lar", "ler", "mış", "miş", "muş", "müş", "acak", "ecek", "r", "ar", "er", "yor", "makta", "mekte", "malı", "meli", "m", "n", "k", "nız", "niz", "nuz", "nüz", "dı", "di", "du", "dü", "tı", "ti", "tu", "tü", "sa", "se", "lım", "lim", "a", "e", "ınız", "iniz", "unuz", "ünüz", "ın", "in", "un", "ün", "sınlar", "sinler", "sunlar", "sünler", "dır", "dir", "dur", "dür", "tır", "tir", "tur", "tür", "dı", "di", "du", "dü", "tı", "ti", "tu", "tü", "sa", "se", "mış", "miş", "muş", "müş", "casına", "cesine", "ken"};
    String reg4 = "y?[ıiuü]m|s[ıiuü]n|y?[ıiuü]z|s[ıiuü]n[ıiuü]z|l[ae]r|m[ıiuü]ş|y?[ae]?c[ae][kğ]|[ıiuü]?r|[ae]?r|[ıiuü]?yor|m[ae]kt[ae]|m[ae]l[ıi]|[mnk]|n[ıiuü]z|[dt][ıiuü]|s[ae]|l[ıi]m|y?[ae]|y?[ıiuü]n[ıiuü]z|y?[ıiuü]n|s[ıiuü]nl[ar]r|[dt][ıiuü]r|y?[dt][ıiuü]|y?s[ae]|y?m[ıiuü]ş|c[ae]s[ıi]n[ae]|y?ken";
    // Eylem Çekim Ekleri Kümesi Ek Tablosu (fiil'e uygula)
    String[] tablo5 = {"m", "zsın", "zsin", "zsun", "zsün", "z", "yız", "yiz", "zsınız", "zsiniz", "zlar", "zler", "ma", "me", "ama", "eme", "adur", "edur", "ıver", "iver", "uver", "üver", "agel", "egel", "agör", "egör", "abil", "ebil", "ayaz", "eyaz", "akal", "ekal", "akoy", "ekoy", "mak", "mek", "ıcı", "ici", "ucu", "ücü", "ıp", "ip", "up", "üp", "alı", "eli", "dıkça", "dikçe", "dukça", "dükçe", "tıkça", "tikçe", "tukça", "tükçe", "arak", "erek", "ınca", "ince", "unca", "ünce", "dan", "den", "tan", "ten", "ya", "ye", "an", "en", "acak", "ecek", "ası", "esi", "dık", "dik", "duk", "dük", "tık", "tik", "tuk", "tük", "mış", "miş", "muş", "müş", "mazlık", "mezlik", "ma", "me", "ış", "iş", "uş", "üş", "dan", "tan", "da", "de", "ta", "te", "la", "le", "a", "e", "maksızın", "meksiniz", "madan", "meden", "n", "ş", "l", "dur", "tur", "t"};
    String reg5 = "m|ns[ıiuü]n|z|y[ıi]z|zs[ıi]n[ıi]z|zl[ae]r|m[ae]|y?[ae]m[ae]|y?[ae]dur|y?[ıiuü]ver|y?[ae]gel|y?[ae]gör|y?[ae]bil|y?[ae]yaz|y?[ae]kal|y?[ae]koy|m[ae][kğ]|y?[ıiuü]c[ıiuü]|y?[ıiuü]p|y?[ae]l[ıi]|[dt][ıiuü]kç[ae]|y?[ae]r[ae][kğ]|y?[ıiuü]nc[ae]|[dt][ae]n|y[ae]|y?[ae]n|y?[ae]c[ae][kğ]|y?[ae]s[ıi]|[dt][ıi][kğ]|m[ıiuü]ş|m[ae]zl[ıi][kğ]|m[ae]|y?[ıiuü]ş|[dt][ae]n?|y?l[ae]|y?[ae]|m[ae]ks[ıi]z[ıi]n|m[ae]d[ae]n|[ıiuü]?n|[ıiuü]?ş|[ıiuü]?l|[dt]ur|[ıiuü]?t";
    //**************************************
    /* isme gelen çekim ekleri regex */
    String cogulCekimEki = "l[ae]r"; // -ler -lar
    String tamlananCekimkEki = "[ıiuü]m|n?[ıiuü]n|[ıiuü]|[ıiuü]?m[ıiuü]z|[ıiuü]?n[ıiuü]z|l[ae]r[ıi]"; // -ım -ın -ı -mız -nız -ları
    String isimDurumCekimEki = "[ıiuü]|y?[ae]|[dt][ae]|[dt][ae]n";// -i -e -de -den
    String tamlayanCekimEki = "n?[ıiuü]n"; // -ın -in -un -ün
    /* fiile gelen çekim ekleri regex*/
    String kisiCekimEki = "m|n|k|n[ıiuü]z|l[ae]r";// geldi--m geldi-n geldi geldi-k geldi-niz geldi-ler
    String kipZamanCekimEki = "[dt][ıiuü]|m[ıi]ş|yor|[ae]c[ae]k|r|[ae]|s[ae]|m[ae]l[ıi]";// geldi,gelmiş,geliyor,gelecek,gelir,gele,gelse,gelmeli
    /* Yapım Ekleri */
    // Addan Ad Türeten Yapım Ekleri
    String isimdenIsim = "(a)|(e)|(ca)|(ce)|(ça)|(çe)|(cağız)|(ceğiz)|(cak)|(cek)|(çak)|(çek)|(cı)|(ci)|(cu)|(cü)|(çı)|(çi)|(çu)|(çü)|(cık)|(cik)|(cuk)|(cük)|(çık)|(çik)|(çuk)|(çük)|(cıl)|(cil)|(cul)|(cül)|(çıl)|(çil)|(çul)|(çül)|(cın)|(cin)|(cun)|(cün)|(çın)|(çin)|(çun)|(çün)|(da)|(de)|(ta)|(te)|(dak)|(dek)|(tak)|(tek)|(dam)|(dem)|(tam)|(tem)|(dan)|(den)|(tan)|(ten)|(daş)|(deş)|(taş)|(teş)|(dırık)|(dirik)|(duruk)|(dürük)|(aç)|(eç)|(ağı)|(eği)|(ak)|(ek)|(alak)|(elek)|(an)|(en)|(arı)|(eri)|(az)|(ez)|(ız)|(iz)|(uz)|(üz)|(ı)|(i)|(u)|(ü)|(ncı)|(inci)|(uncu)|(üncü)|(ıl)|(il)|(ul)|(ül)|(ımsı)|(imsi)|(umsu)|(ümsü)|(mtırak)|(imtırak)|(umtırak)|(ıt)|(it)|(ut)|(üt)|(ki)|(kü)|(la)|(le)|(lak)|(lek)|(lam)|(lem)|(lum)|(lüm)|(lama)|(leme)|(layın)|(leyin)|(laz)|(lez)|(lı)|(li)|(lu)|(lü)|(lık)|(lik)|(luk)|(lük)|(man)|(men)|(ra)|(re)|(sak)|(sek)|(sal)|(sel)|(sı)|(si)|(su)|(sü)|(sız)|(siz)|(suz)|(süz)|(sın)|(sin)|(tı)|(ti)|(tu)|(tü)";
    // Addan Eylem Türeten Yapım Ekleri
    String isimdenFiil = "(a)|(e)|(al)|(el)|(an)|(en)|(ar)|(er)|(at)|(et)|(ık)|(ik)|(uk)|(ük)|(msa)|(imse)|(umsa)|(ümse)|(ırga)|(irge)|(kır)|(kir)|(kur)|(kür)|(la)|(le)|(lan)|(len)|(laş)|(leş)|(ra)|(re)|(sa)|(se)|(sı)|(si)|(su)|(sü)";
    // Eylemden Ad Türeten Yapım Ekleri
    String fiildenIsim = "(a)|(e)|(acak)|(ecek)|(aç)|(eç)|(ıç)|(iç)|(uç)|(üç)|(ağan)|(eğen)|(ak)|(al)|(el)|(alak)|(elek)|(am)|(em)|(ım)|(im)|(um)|(üm)|(amaç)|(emeç)|(amak)|(emek)|(an)|(en)|(anak)|(enek)|(arak)|(erek)|(arı)|(eri)|(ası)|(esi)|(ca)|(ce)|(ça)|(çe)|(dı)|(di)|(du)|(dü)|(tı)|(ti)|(tu)|(tü)|(dık)|(dik)|(duk)|(dük)|(tık)|(tik)|(tuk)|(tük)|(dıkça)|(dikçe)|(dukça)|(dükçe)|(ga)|(ge)|(gaç)|(geç)|(kaç)|(keç)|(gan)|(gen)|(kan)|(ken)|(gı)|(gi)|(gu)|(gü)|(kı)|(ki)|(ku)|(kü)|(gıç)|(giç)|(guç)|(güç)|(gın)|(gun)|(gün)|(kın)|(kin)|(kun)|(kün)|(ı)|(i)|(u)|(ü)|(ıcı)|(ici)|(ucu)|(ücü)|(ıç)|(iç)|(uç)|(üç)|(ık)|(ik)|(uk)|(ük)|(ıl)|(il)|(ul)|(ül)|(ılı)|(ili)|(ulu)|(ülü)|(ım)|(im)|(um)|(üm)|(ın)|(in)|(un)|(ün)|(anç)|(enç)|(ınç)|(inç)|(unç)|(ünç)|(ıntı)|(inti)|(untu)|(üntü)|(ar)|(er)|(ır)|(ir)|(ur)|(ür)|(ış)|(iş)|(uş)|(üş)|(ıt)|(it)|(ut)|(üt)|(ma)|(me)|(maca)|(mece)|(maç)|(meç)|(mak)|(mek)|(maz)|(mez)|(mık)|(mik)|(muk)|(mük)|(mış)|(miş)|(muş)|(müş)|(naz)|(nez)|(tı)|(ti)|(tu)|(tü)";
    // Eylemden Eylem Türeten Yapım Ekleri
    String fiildenFiil = "(akla)|(ekle)|(ala)|(ele)|(ar)|(er)|(ır)|(ir)|(ur)|(ür)|(dar)|(der)|(dır)|(dir)|(dur)|(dür)|(tır)|(tir)|(tür)|(tür)|(ı)|(i)|(u)|(ü)|(ık)|(uk)|(uk)|(ük)|(ıksa)|(ikse)|(ıl)|(il)|(ul)|(ül)|(ın)|(in)|(un)|(ün)|(ar)|(er)|(ır)|(ur)|(ür)|(ış)|(iş)|(uş)|(üş)|(ışla)|(işle)|(uşla)|(üşle)|(ıştır)|(iştir)|(ustur)|(üştür)|(ıt)|(it)|(ut)|(üt)";
    // Tüm ekler için
    HashMap<String, String> cekimEkleri = new HashMap<String, String>();
    HashMap<String, String> yapimEkleri = new HashMap<String, String>();
    // Yapim Eki Turleri için
//    HashMap<String, String> isimdenIsimYapimEki = new HashMap<String, String>();
//    HashMap<String, String> isimdenFiilYapimEki = new HashMap<String, String>();
//    HashMap<String, String> fiildenIsimYapimEki = new HashMap<String, String>();
//    HashMap<String, String> fiildenFiilYapimEki = new HashMap<String, String>();// </editor-fold>

    /* Kelime Haznesi*/
    HashMap<String, String> kelimeHazne;
    Vt vt;
    // <editor-fold defaultstate="collapsed" desc="TBD Gülşen Cebiroğlu Türkçe Ekler Otomatası, Tablolar ve Regex">
// Ekler Regexi
    tablo1 t1 = new tablo1(); // ek-eylem
    tablo2 t2 = new tablo2(); // isim cekim
    tablo4 t4 = new tablo4(); // eylem-zaman
    tablo5 t5 = new tablo5(); // eylem-cekim
    String yapim = "l[ıiuü][kğ]|[cç][ıiuü][k]?|l[ae][şn]?|[cç][ae]|l[ıiuü]|s[ıiuü]z";
    String ekEylem = t1.regex;
    String isimCekim = t2.regex;
    String eylemZaman = t4.regex;
    String eylemCekim = t5.regex;
//    String rgx = "((" + reg3 + ")|(" + reg5 + ")*|(" + reg2 + ")*|(" + reg1 + ")*|(" + reg4 + ")*)";
    String rgx = "((" + yapim + ")?(" + eylemCekim + ")?((" + isimCekim + ")?(" + ekEylem + ")?|(" + eylemZaman + ")?)?)$";// </editor-fold>
    Pattern p;
    private Matcher m;
    //Thread değişkenleri
    Queue kuyruk;

    public HizliEkAvcisi() {
        //   initCekimEkleri();
        p = Pattern.compile("([\\wüğışçö]+?)" + rgx);
        vt = new Vt();
        kuyruk = new Queue();
        initKelimeHaznesi();
    }

    public HizliEkAvcisi(HashMap<String, String> kelimeHazne, Queue kuyruk, Pattern p) {
        this.kelimeHazne = (HashMap<String, String>) kelimeHazne;
        this.kuyruk = kuyruk;
        this.p = p;
    }

    // <editor-fold defaultstate="collapsed" desc="initCekimEkleri">
    public void initCekimEkleri() {
        for (String ek : tablo1) {
            cekimEkleri.put(ek, "");
        }
        for (String ek : tablo2) {
            cekimEkleri.put(ek, "");
        }
        for (String ek : tablo3) { // Yapım ekleri
            cekimEkleri.put(ek, "");
        }
        for (String ek : tablo4) {
            cekimEkleri.put(ek, "");
        }
        for (String ek : tablo5) {
            cekimEkleri.put(ek, "");
        }

//        for (String s : cekimEkleri.keySet()) {
//            System.out.println(s);
//        }
        System.out.println(cekimEkleri.size() + "ek atandı...");
    }
//    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Kelime Hazne Hazırlama">
    private void initKelimeHaznesi() {

        kelimeHazne = new HashMap<String, String>();

        System.out.println("Kelime haznesi hazırlanıyor...");
        try {
            String tur = "";
            ResultSet rs = vt.executeQuery("select * from kelimeTurleri"), rs2; // speed hack
            while (rs.next()) {
                tur = rs.getString("tur");
// <editor-fold defaultstate="collapsed" desc="Diğer türlerini ekle">
//                tur = "";
//                rs2 = vt.executeQuery("select * from kelimeTurleri where kelime like '" + rs.getString("kelime") + "'"); // speed hack
//                while (rs2.next()) {
//                    tur += rs2.getString("tur") + ((!rs2.isLast()) ? "|" : "");
//                }// </editor-fold>
//                kelimeHazne.put(rs.getString("kelime"), tur);
// <editor-fold defaultstate="collapsed" desc="Ünsüz yumuşamasını dahil et">
//                if (rs.getString("kelime").endsWith("p")) {
//                    kelimeHazne.put(rs.getString("kelime").substring(0, rs.getString("kelime").length() - 1) + "b", tur);
//                } else if (rs.getString("kelime").endsWith("ç")) {
//                    kelimeHazne.put(rs.getString("kelime").substring(0, rs.getString("kelime").length() - 1) + "c", tur);
//                } else if (rs.getString("kelime").endsWith("t")) {
//                    kelimeHazne.put(rs.getString("kelime").substring(0, rs.getString("kelime").length() - 1) + "d", tur);
//                } else if (rs.getString("kelime").endsWith("k")) {
//                    kelimeHazne.put(rs.getString("kelime").substring(0, rs.getString("kelime").length() - 1) + "g", tur);
//                    kelimeHazne.put(rs.getString("kelime").substring(0, rs.getString("kelime").length() - 1) + "ğ", tur);
//                }// </editor-fold>
            }
        } catch (Exception e) {
            System.out.println("Hata oluştu : " + e.getLocalizedMessage());
        }
        System.out.println("Kelime haznesinde " + kelimeHazne.size() + " kelime var...");

    }// </editor-fold>

    @Override
    public void run() {
        while (!false) {
            try {
                System.out.println(ekAvcisi((String) kuyruk.dequeue()));

            } catch (Exception e) {
                System.out.println("hata virdi..");
            }
        }
    }

    public synchronized ArrayList<String> ekAvcisi(String s) {
        String kelime = s;
        ArrayList<String> ali = new ArrayList<String>();

        /* Yalın hal kontrol*/
        if (kelimeHazne.containsKey(kelime)) {
            ali.add(kelime);
            ali.add(kelimeHazne.get(kelime));
            return ali;
        }

        /* kök döngüsü*/
        while (!false) {
            m = p.matcher(kelime);
            if (m.matches()) {//.find()
                if (kelime.equals(m.group(1))) {
                    return null;
                }

                kelime = m.group(1);
                ali.add(m.group(2));

                if (kelimeHazne.containsKey(kelime)) {
                    ali.add(kelime);
                    ali.add(kelimeHazne.get(kelime));
                    return ali;
                }
                continue;
            }
            return null;
        }
    }

    public boolean isEkAvcisi(String s) {
        String kelime = s;
        ArrayList<String> ali = new ArrayList<String>();

        /* Yalın hal kontrol*/
        if (kelimeHazne.containsKey(kelime)) {
            ali.add(kelime);
            ali.add(kelimeHazne.get(kelime));
            return true;
        }

        /* kök döngüsü*/
        while (!false) {
            m = p.matcher(kelime);
            if (m.matches()) {//.find()
                if (kelime.equals(m.group(1))) {
                    return false;
                }

                kelime = m.group(1);
                ali.add(m.group(2));

                if (kelimeHazne.containsKey(kelime)) {
                    ali.add(kelime);
                    ali.add(kelimeHazne.get(kelime));
                    return true;
                }
                continue;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        HizliEkAvcisi ea = new HizliEkAvcisi();

//        ea.initCekimEkleri();
        String cumle = // <editor-fold defaultstate="collapsed" desc="5.000 kelime">
                "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir"
                + "Buradaki amaç dil bilgisi düşük olabilecek daha az eğitimli kişilerin kolayca anlamasını sağlamak veya acil durumlarda panik halinde olabilecek kişilerin"
                + "anlama güçlüklerinin ve hatalarının en aza indirgenmesidir";// </editor-fold>
        cumle += " " + cumle; // 10.002
//        cumle += " " + cumle; // 20.004
//        cumle += " " + cumle; // 40.008
//        cumle += " " + cumle; // 80.016
//        cumle += " " + cumle; // 160.032
//        cumle += " " + cumle; // 320.064
//        cumle += " " + cumle; // 640.128
//        cumle += " " + cumle; // 1.280.256
//        cumle += " " + cumle; // 2.500.512
//        cumle += " " + cumle; // 5.001.024

        cumle = "Babasıyla kavga ettiğinden eve uğramıyor";//Kapıyı arkamdan kapattım ve evden çıktım
        String token = "";
        StringTokenizer st = new StringTokenizer(cumle);
        HizliEkAvcisi[] avcilar = new HizliEkAvcisi[(st.countTokens() / 1000) + 1]; // her 2000 kelime için 1 thread başlat
        System.out.println(st.countTokens() + "kelime.. / " + avcilar.length + " thread..");

        System.out.println(avcilar.length + " thread oluşturuldu.");
        for (int a = 0; a < avcilar.length; a++) {
            avcilar[a] = new HizliEkAvcisi((HashMap<String, String>) ea.kelimeHazne, ea.kuyruk, ea.p);
            avcilar[a].setPriority(Thread.MAX_PRIORITY);
        }

        while (st.hasMoreTokens()) {
            token = st.nextToken();
            ea.kuyruk.enqueue((String) token);
        }

        for (HizliEkAvcisi ekAvcisi : avcilar) {
            ekAvcisi.start();
        }
        long a2 = System.currentTimeMillis();
        long a = System.nanoTime();
        while (!ea.kuyruk.isEmpty()) {
        }

        System.out.println((System.nanoTime() - a) / 1000 + "us");
        System.out.println((System.currentTimeMillis() - a2) + "ms");
//        System.exit(0);
        System.gc();
    }

//// <editor-fold defaultstate="collapsed" desc="eski psvmain()">
//    public static void main(String[] args) {
//
////        Scanner s = new Scanner(System.in);
////        System.out.println("Kelime girin : ");
////        String kelime = s.next();
//
//        EkAvcisi ea = new EkAvcisi();
////        ea.initCekimEkleri();
//        long a, b;
//        a = System.currentTimeMillis();
////        ea.kokKontrol("deneme"); // Hızlandırma hilesi..
//        System.out.println("Başladık..");
////        for (String s : args) {
//        a = System.currentTimeMillis();
//        String s = "gerilemişlerdir";
////        for (int i = 0; i < 5000; i++) {
////            String string = args[i];
////            String s = "gönderemedik";
//            System.out.println(ea.ekAvcisi(s));
////        }
////        System.out.println("Backtrack : '" + s.substring(0, ea.cekimEkiAvcisi(s, (byte) s.length())) + "' ");
////            ea.ekAvcisi(s);
////            System.out.println(ea.ekAvcisi(s));
////            String rgx = "((" + ea.reg1 + "|" + ea.reg2 + "|" + ea.reg4 + "|" + ea.reg5 + ")?$)";//+ ea.reg3 + "|" // Yapım ekleri
//////            System.out.print("'" + s.substring(0, ea.cekimEkiAvcisi(s, (byte) s.length())) + "' ");
//////            if (s.matches(s)) {
//////                System.out.println(s.replaceAll(rgx, ""));
//////            }
////            Pattern p = Pattern.compile("([\\wüğışçö]+?)" + rgx);
////            Matcher m = p.matcher(s);
////            while (m.find()) {
////                System.out.println(m.group(1));
////        }
////            }
////        StringTokenizer st = new StringTokenizer("çalışkan mışsınız");
////        System.out.println(st.nextToken("(\\wüğışçö)+"));
////        Pattern p = Pattern.compile("([\\wüğışçö]+)(" + ea.reg1 + "|" + ea.reg2 + "|" + ea.reg3 + "|" + ea.reg4 + "|" + ea.reg5 + ")*$");
////        Matcher m = p.matcher(s);
////        if (m.find()) {
////            System.out.println(m.group());
////            System.out.println(m.group(0));
////            System.out.println(m.group(1));
////            System.out.println(m.group(2));
////        }
////StringBuilder sb = new StringBuilder(s);
//
//
////        System.out.println(s.replaceAll("(m|ns[ıiuü]n|z|y[ıi]z|zs[ıi]n[ıi]z|zl[ae]r|[ae]?m[ae]|[ae]dur|[ıiuü]ver|[ae]gel|[ae]gör|[ae]bil|[ae]yaz|[ae]kal|[ae]koy|m[ae]k|[ıiuü]c[ıiuü]|[ıiuü]p|[ae]l[ıi]|[dt][ıiuü]kç[ae]|[ae]r[ae]k|[ıiuü]nc[ae]|[dt][ae]n|y[ae]|[ae]n|[ae]c[ae]k|[ae]s[ıi]|[dt][ıi]k|m[ıiuü]ş|m[ae]zl[ıi]k|m[ae]|[ıiuü]ş|[dt][ae]n?|l[ae]|a|e|m[ae]ks[ıi]z[ıi]n|m[ae]d[ae]n|n|ş|l|[dt]ur|t)*$", ""));
////        System.out.println(s.replaceAll("("+ea.reg1+"|"+ea.reg2+"|"+ea.reg3+"|"+ea.reg4+"|"+ea.reg5+")*$", ""));
////        System.out.println(s.replaceAll("("+ea.reg1+")*$", ""));
////        System.out.println(s.replaceAll("("+ea.reg2+")*$", ""));
////        System.out.println(s.replaceAll("("+ea.reg3+")*$", ""));
////        System.out.println(s.replaceAll("("+ea.reg4+")*$", ""));
////        System.out.println(s.replaceAll("("+ea.reg5+")*$", ""));
////        if (m.find()) {
////            System.out.println("var dedi : " + m.group(1));
////        }
//        b = System.currentTimeMillis();
//        System.out.println("Süre : " + (b - a) + "ms");
////        ea.calis();
////        ea.milyonKelime();
//
////        }
//    }
//}// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Milyon Kelime">
//    public void milyonKelime() {
//        try {
//            Scanner s = new Scanner(new File("sozluk.mhk"), "UTF-8");
//            initCekimEkleri();
//
//            int limit = 0;
//            while (s.hasNext()) {
////                if (limit-- < 0) {
////                    break;
////                }
//                String str = s.next().trim();
//                System.out.println("Kelime : " + str);
////                String kok = ekAvcisi(str);
//                if (kok.equals("")) {
////                    System.out.println("Kelime kökü bulunamadı..");
//                    try {
//                        vt.execute("insert ignore into dictionary (word) values ('" + str + "');");
//                    } catch (Exception e) {
//                    }
//                }
////                System.out.println("Kök    : " + kok);
////
//
//                System.out.println("Kelime : " + limit++ + ": " + str);
//            }
////            System.gc();
//        } catch (Exception e) {
//        }
//
//    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="cekimEkiAvcisi">
    public int cekimEkiAvcisi(String str, byte subLen) {
        if (subLen <= 1) {
            return -1;
        }
        ResultSet rs;
        try {
            rs = vt.executeQuery("select kelime,tur from kelimeTurleri where kelime like '" + str + "'");
            if (rs.next()) {
//                        ali.add(kelime);
//                        ali.add(rs.getString("tur"));
                return str.length();
            }
        } catch (Exception e) {
            System.out.println("Ek avlarken veritabanı hatası : " + e.getLocalizedMessage());
        }
        for (byte i = subLen; i > 0; i--) {
            String s = str.substring(i, subLen);
            if (cekimEkleri.containsKey(s)) {


                try {
                    rs = vt.executeQuery("select kelime,tur from kelimeTurleri where kelime like '" + str.substring(0, i) + "'");
                    if (rs.next()) {
//                        ali.add(kelime);
//                        ali.add(rs.getString("tur"));
                        return i;
                    }
                } catch (Exception e) {
                    System.out.println("Ek avlarken veritabanı hatası : " + e.getLocalizedMessage());
                }

                int subCEA = cekimEkiAvcisi(str, i);
                if (subCEA > 1) {
//                    System.out.println("Ek : " + s);
                    return subCEA;
                }
                if (subCEA == -1) {
                    return 0;
                }
            }
        }
        return 0;
    }
//
//    public int cekimEkiAvcisiRegex(String str, byte subLen) {
//        if (subLen <= 2) {
//            return -1;
//        }
//        for (byte i = subLen; i > 0; i--) {
//            String s = str.substring(i, subLen);
//            if (cekimEkleri.containsKey(s)) {
//
//                if (kokKontrol(str.substring(0, i))) {
////                    System.out.println("Ek : " + s);
////                    System.out.println("Olası kelime : " + str.substring(0, i));
//                    return i;
//                }
//                int subCEA = cekimEkiAvcisi(str, i);
//                if (subCEA > 1) {
////                    System.out.println("Ek : " + s);
//                    return subCEA;
//                }
//                if (subCEA == -1) {
//                    return 0;
//                }
//            }
//        }
//        return 0;
//    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="ekAvcisiRecursive">
//    public String ekAvcisiRecursive(String s) {
//        if (s.isEmpty() || s.length() < 3) {
//            return s;
//        }
//        if (kokKontrol(s)) {
//            return s;
//        }
//        m = p.matcher(s);
//        if (m.find()) {
////            System.out.println(m.group(2));
//            return ekAvcisiRecursive(m.group(1));
//        }
//        return "";
//    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="kokKontrol">
//public String
//
//kokKontrol(String s) {
//        sorguSayisi++;
//
//
//try {
////            System.out.println(s);
////             return s.equals("montaj") || s.equals("nokta");
//            ResultSet rs = vt.executeQuery("select kelime,tur from kelimeTurleri where kelime like '" + s + "'");
//
//
//return rs.next() ? rs.getString("kelime") : null;
//
//
//} catch (Exception e) {
//            System.out.println("Kök kontrol hata oluştu : " + e.getLocalizedMessage());
//
//
//return null;
//
//}
//
//    }
// </editor-fold>
//// <editor-fold defaultstate="collapsed" desc="calis()">
//    public void calis() {
//        long a = System.currentTimeMillis();
//        String s = "meteliksizler";
//        String[] dizi = s.split(" ");
////        initCekimEkleri();
//        for (String string : dizi) {
//            System.out.print("'" + ekAvcisi(string) + "' ");
//        }
//        System.out.println("");
////        System.out.println("Kök : " + s.substring(0, cekimEkiAvcisi(s, (byte) s.length())));
//        System.out.println("Sorgu sayısı : " + sorguSayisi);
//
//        long b = System.currentTimeMillis();
//        System.out.println("Süre : " + (b - a) + "ms");
//    }// </editor-fold>
}
