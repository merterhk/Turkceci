package Kelimeler;

import Ekler.CekimEkleri.FiileGelen.*;
import Ekler.YapimEki;
import java.util.ArrayList;

public class Fiil extends Kelime {

    private Kelime kok;
    private ArrayList<YapimEki> yapimEkleri = new ArrayList<YapimEki>();
    private CatiEki catiEki;
    private OlumsuzlukEki olumsuzlukEki;
    private BilesikFiilEki bilesikFiilEki;
    private KipZamanEki kipZamanEki;
    private KisiEki kisiEki;

    public Fiil() {
        super(null);
    }

    public Fiil(String kelime) {
        super(kelime);
    }

    public Fiil(Kelime kok, CatiEki catiEki, OlumsuzlukEki olumsuzlukEki, BilesikFiilEki bilesikFiilEki, KipZamanEki kipZamanEki, KisiEki kisiEki) {
        super(null);
        this.kok = kok;
        this.catiEki = catiEki;
        this.olumsuzlukEki = olumsuzlukEki;
        this.bilesikFiilEki = bilesikFiilEki;
        this.kipZamanEki = kipZamanEki;
        this.kisiEki = kisiEki;
    }

    public void addYapimEki(YapimEki ye) {
        yapimEkleri.add(ye);
    }

    public void dokum() {
        System.out.print(getKelime());
        for (YapimEki yapimEki : yapimEkleri) {
            System.out.print((yapimEki != null) ? " + " + yapimEki.ek : "!");
        }
        System.out.print((catiEki != null) ? " - " + catiEki.ek : "");
        System.out.print((olumsuzlukEki != null) ? " - " + olumsuzlukEki.ek : "");
        System.out.print((bilesikFiilEki != null) ? " - " + bilesikFiilEki.ek : "");
        System.out.print((kipZamanEki != null) ? " - " + kipZamanEki.ek : "");
        System.out.print((kisiEki != null) ? " - " + kisiEki.ek : "");
        System.out.println("");

    }

    public void dokumAyrintili() {
        System.out.println("\tKök              : " + getKelime());
        for (YapimEki yapimEki : yapimEkleri) {
            System.out.println("\tYapım Eki        : " + ((yapimEki != null) ? yapimEki.ek : ""));
        }
        System.out.println("\tÇatı Eki         : " + ((catiEki != null) ? catiEki.ek : ""));
        System.out.println("\tOlumsuzluk Eki   : " + ((olumsuzlukEki != null) ? olumsuzlukEki.ek : ""));
        System.out.println("\tBileşik Fiil Eki : " + ((bilesikFiilEki != null) ? bilesikFiilEki.ek : ""));
        System.out.println("\tKipZaman Eki     : " + ((kipZamanEki != null) ? kipZamanEki.ek : ""));
        System.out.println("\tKişi Eki         : " + ((kisiEki != null) ? kisiEki.ek : ""));
        System.out.println("\t-------------------");
    }

    /**
     * @return the kok
     */
    public Kelime getKok() {
        return kok;
    }

    /**
     * @param kok the kok to set
     */
    public void setKok(Kelime kok) {
        this.kok = kok;
    }

    /**
     * @return the yapimEkleri
     */
    public ArrayList<YapimEki> getYapimEkleri() {
        return yapimEkleri;
    }

    /**
     * @param yapimEkleri the yapimEkleri to set
     */
    public void setYapimEkleri(ArrayList<YapimEki> yapimEkleri) {
        this.yapimEkleri = yapimEkleri;
    }

    /**
     * @return the catiEki
     */
    public CatiEki getCatiEki() {
        return catiEki;
    }

    /**
     * @param catiEki the catiEki to set
     */
    public void setCatiEki(CatiEki catiEki) {
        this.catiEki = catiEki;
    }

    /**
     * @return the olumsuzlukEki
     */
    public OlumsuzlukEki getOlumsuzlukEki() {
        return olumsuzlukEki;
    }

    /**
     * @param olumsuzlukEki the olumsuzlukEki to set
     */
    public void setOlumsuzlukEki(OlumsuzlukEki olumsuzlukEki) {
        this.olumsuzlukEki = olumsuzlukEki;
    }

    /**
     * @return the bilesikFiilEki
     */
    public BilesikFiilEki getBilesikFiilEki() {
        return bilesikFiilEki;
    }

    /**
     * @param bilesikFiilEki the bilesikFiilEki to set
     */
    public void setBilesikFiilEki(BilesikFiilEki bilesikFiilEki) {
        this.bilesikFiilEki = bilesikFiilEki;
    }

    /**
     * @return the kipZamanEki
     */
    public KipZamanEki getKipZamanEki() {
        return kipZamanEki;
    }

    /**
     * @param kipZamanEki the kipZamanEki to set
     */
    public void setKipZamanEki(KipZamanEki kipZamanEki) {
        this.kipZamanEki = kipZamanEki;
    }

    /**
     * @return the kisiEki
     */
    public KisiEki getKisiEki() {
        return kisiEki;
    }

    /**
     * @param kisiEki the kisiEki to set
     */
    public void setKisiEki(KisiEki kisiEki) {
        this.kisiEki = kisiEki;
    }
}
