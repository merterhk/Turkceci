    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB.Arsiv.Otomatalar;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.misc.Queue;

public class AnaMakina {

    /**/
    String kelime = "([\\wüğışçö]+?)";
    String dolayliTumlec = "([yn]?(^[l])[ae]|[dt][ae]n|[dt][ae])$";

    /* isme gelen çekim ekleri regex */
    String cogulCekimEki = "l[ae]r"; // -ler -lar
    String tamlananCekimkEki = "[ıiuü]m|n?[ıiuü]n|[ıiuü]|[ıiuü]?m[ıiuü]z|[ıiuü]?n[ıiuü]z|l[ae]r[ıi]"; // -ım -ın -ı -mız -nız -ları
    String isimDurumCekimEki = "[ıiuü]|y?[ae]|[dt][ae]|[dt][ae]n";// -i -e -de -den
    String tamlayanCekimEki = "n?[ıiuü]n"; // -ın -in -un -ün
    /* fiile gelen çekim ekleri regex*/
    String kisiCekimEki = "m|n|k|n[ıiuü]z|l[ae]r";// geldi--m geldi-n geldi geldi-k geldi-niz geldi-ler
    String kipZamanCekimEki = "[dt][ıiuü]|m[ıi]ş|yor|[ae]c[ae]k|r|[ae]|s[ae]|m[ae]l[ıi]";// geldi,gelmiş,geliyor,gelecek,gelir,gele,gelse,gelmeli

    /* Patternler */
    Pattern pDolayliTumlec = Pattern.compile(kelime + dolayliTumlec);

    public ArrayList dolayliTumlecler(Queue kuyruk) {
        HizliEkAvcisi ea = new HizliEkAvcisi();

        ArrayList<String> kelimeler = new ArrayList<String>();
        Matcher m;
        while (!kuyruk.isEmpty()) {
            try {
                m = pDolayliTumlec.matcher((String) kuyruk.dequeue());
                if (m.find()) {

                    System.out.println(m.group(1));
                    if (ea.isEkAvcisi(m.group())) {
                        System.out.println(m.group());
                    }
                    kelimeler.add("kapıyı");
                }
            } catch (Exception e) {
                System.out.println("hata virdi..");
            }
        }
        return kelimeler;
    }

    public AnaMakina() {
        //initKelimeHaznesi();
    }

    public static void main(String[] args) {
        AnaMakina m = new AnaMakina();
        Queue q = new Queue();
        StringTokenizer st = new StringTokenizer("Biyoloji sınavına abisiyle çalıştı");
        while (st.hasMoreTokens()) {
            q.enqueue(st.nextToken());
        }
        m.dolayliTumlecler(q);
    }
}
