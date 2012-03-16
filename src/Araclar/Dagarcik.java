/* Static Kelime haznesidir. Oluşturulurken tüm kelimeleri hash table'a türleriyle biriktirir. */
package Araclar;

import DB.Vt;
import Kelimeler.Kelime;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class Dagarcik {

    public static final String fiil = "fiil";
    public static final String isim = "isim";
    public static final String sifat = "sifat";
    public static final String zarf = "zarf";
    public static final String edat = "edat";
    public static final String baglac = "baglac";
    public static final String unlem = "unlem";
    private static Vt vt;
    private static HashMap<String, Integer> kelimeHazne;

    // <editor-fold defaultstate="collapsed" desc="Kelime Hazne Hazırlama">
    public static void initKelimeHaznesi() {
        vt = new Vt();
        kelimeHazne = new HashMap<String, Integer>();

        System.out.println("Kelime haznesi hazırlanıyor...");
        try {
            int tur = 0;
            ResultSet rs = vt.executeQuery("select * from kelimeHazne"), rs2; // speed hack
            while (rs.next()) {
// <editor-fold defaultstate="collapsed" desc="Diğer türlerini ekle">
//                tur = 0;
//                rs2 = vt.executeQuery("select * from kelimeTurleri where kelime like '" + rs.getString("kelime") + "'"); // speed hack
//                while (rs2.next()) {
//                    tur |= kelimeTurKodu(rs2.getString("tur"));
//                }
// </editor-fold>
                kelimeHazne.put(rs.getString("kelime"), rs.getInt("tur"));
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

    public Dagarcik() {
    }

    public static int getir(String kelime) {
        return kelimeHazne.get(kelime) != null ? kelimeHazne.get(kelime) : 0;
    }

    public static boolean varMi(String kelime) {
        return kelimeHazne.containsKey(kelime);
    }

    public static boolean varMi(String kelime, String tur) {
        return kelimeHazne.containsKey(kelime) && kelimeHazne.get(kelime).equals(tur);
    }

    public static int tur(String kelime) {
        if (varMi(kelime)) {
            return getir(kelime);
        }
        return 0;
    }

    public static boolean ekle(String kelime, int tur) {
        kelimeHazne.put(kelime, tur);
        return true;
    }

    public static ArrayList<String> turler(String kelime) {
        ArrayList<String> ali = new ArrayList<String>();
        return ali;
    }

    public static int kelimeTurKodu(String tur) {
        if ("isim".equals(tur)) {
            return Kelime.isim;
        } else if ("fiil".equals(tur)) {
            return Kelime.fiil;
        } else if ("sifat".equals(tur)) {
            return Kelime.sifat;
        } else if ("zarf".equals(tur)) {
            return Kelime.zarf;
        } else if ("zamir".equals(tur)) {
            return Kelime.zamir;
        } else if ("edat".equals(tur)) {
            return Kelime.edat;
        } else if ("baglac".equals(tur)) {
            return Kelime.baglac;
        } else if ("unlem".equals(tur)) {
            return Kelime.unlem;
        }
        return 0;
    }
}
