package Kelimeler;

import Ekler.CekimEkleri.IsimeGelen.*;
import Ekler.YapimEki;
import java.util.ArrayList;

/**
 * @author merter
 */
public class Isim extends Kelime {

    private Kelime kok;
    private ArrayList<YapimEki> yapimEkleri = new ArrayList<YapimEki>();
    private CogulEki cogulEki;
    private IyelikEki iyelikEki;
    private HalEki halEki;
    private TamlayanEki tamlayanEki;
    private EkFiilEki ekFiil;
    private KiEki kiEki;

    public Isim() {
        super(null);
    }

    public Isim(String kelime) {
        super(kelime);
    }

    public void addYapimEki(YapimEki ye) {
        yapimEkleri.add(ye);
    }

    public Isim(Kelime kok, CogulEki cogulEki, IyelikEki iyelikEki, HalEki halEki, TamlayanEki tamlayanEki, EkFiilEki ekFiil) {
        super(null);
        this.kok = kok;
        this.cogulEki = cogulEki;
        this.iyelikEki = iyelikEki;
        this.halEki = halEki;
        this.tamlayanEki = tamlayanEki;
        this.ekFiil = ekFiil;

    }

    @Override
    public void dokum() {
        System.out.print(getKelime() + ":");
        for (YapimEki yapimEki : yapimEkleri) {
            System.out.print((yapimEki != null) ? " + " + yapimEki.ek : "");
        }
        System.out.print((cogulEki != null) ? " - " + cogulEki.ek : "");
        System.out.print((iyelikEki != null) ? " - " + iyelikEki.ek : "");
        System.out.print((halEki != null) ? " - " + halEki.ek : "");
        System.out.print((tamlayanEki != null) ? " - " + tamlayanEki.ek : "");
        System.out.print((ekFiil != null) ? " - " + ekFiil.ek : "");
        System.out.print((kiEki != null) ? " - " + kiEki.ek : "");
        System.out.println("");

    }

    @Override
    public void dokumAyrintili() {
        System.out.println("\tKök              : " + getKelime() + "");
        for (YapimEki yapimEki : yapimEkleri) {
            System.out.println("\tYapım Eki        : " + ((yapimEki != null) ? yapimEki.ek : ""));
        }
        System.out.println("\tÇoğul Eki        : " + ((cogulEki != null) ? cogulEki.ek : ""));
        System.out.println("\tİyelik Eki       : " + ((iyelikEki != null) ? iyelikEki.ek : ""));
        System.out.println("\tHal Eki          : " + ((halEki != null) ? halEki.ek : ""));
        System.out.println("\tTamlayan Eki     : " + ((tamlayanEki != null) ? tamlayanEki.ek : ""));
        System.out.println("\tEkFiil           : " + ((ekFiil != null) ? ekFiil.ek : ""));
        System.out.println("\tKi eki           : " + ((kiEki != null) ? kiEki.ek : ""));
        System.out.println("\t-------------------");
        Kelime diger = getKok();
        while (diger != null) {
            diger.dokumAyrintili();
            diger = (Kelime) diger.getDiger();
        }

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
     * @return the cogulEki
     */
    public CogulEki getCogulEki() {
        return cogulEki;
    }

    /**
     * @param cogulEki the cogulEki to set
     */
    public void setCogulEki(CogulEki cogulEki) {
        this.cogulEki = cogulEki;
    }

    /**
     * @return the iyelikEki
     */
    public IyelikEki getIyelikEki() {
        return iyelikEki;
    }

    /**
     * @param iyelikEki the iyelikEki to set
     */
    public void setIyelikEki(IyelikEki iyelikEki) {
        this.iyelikEki = iyelikEki;
    }

    /**
     * @return the halEki
     */
    public HalEki getHalEki() {
        return halEki;
    }

    /**
     * @param halEki the halEki to set
     */
    public void setHalEki(HalEki halEki) {
        this.halEki = halEki;
    }

    /**
     * @return the tamlayanEki
     */
    public TamlayanEki getTamlayanEki() {
        return tamlayanEki;
    }

    /**
     * @param tamlayanEki the tamlayanEki to set
     */
    public void setTamlayanEki(TamlayanEki tamlayanEki) {
        this.tamlayanEki = tamlayanEki;
    }

    /**
     * @return the ekFiil
     */
    public EkFiilEki getEkFiil() {
        return ekFiil;
    }

    /**
     * @param ekFiil the ekFiil to set
     */
    public void setEkFiil(EkFiilEki ekFiil) {
        this.ekFiil = ekFiil;
    }

    public void setKiEki(KiEki kiEki) {
        this.kiEki = kiEki;
    }

    public KiEki getKiEki() {
        return kiEki;
    }
}
