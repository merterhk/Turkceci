/* Sayılar TBD Gülşen Cebiroğlunun makalesindeki eklere karşılık gelmektedir. */
package DB.Arsiv.Otomatalar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// <editor-fold defaultstate="collapsed" desc="Numaralı Ek listesi">
/*
1–(y)?[ıiuü]m
2–s[ıiuü]n
3–(y)?[ıiuü]z
4–s[ıiuü]n[ıiuü]z
5–l[ae]r
6–m
7–n
8–k
9–n[ıiuü]z
10–[dt][ıiuü]r
11–c[ae]s[ıi]n[ae]
12–(y)?[dt][ıiuü]
13–(y)?s[ae]
14–(y)?m[ıiuü]ş
15–(y)?ken
 */// </editor-fold>
public class tablo1 {

    public String regex = // <editor-fold defaultstate="collapsed" desc="regex">
            "((y)?m[ıiuü]ş)?((y)?[ıiuü]m|s[ıiuü]n|(y)?[ıiuü]z|s[ıiuü]n[ıiuü]z)"
            + "|"
            + "([dt][ıiuü]r|(y)?[dt][ıiuü]|(y)?s[ae]|(y)?m[ıiuü]ş)?(l[ae]r)"
            + "|"
            + "((y)?[dt][ıiuü]|(y)?s[ae]|(y)?m[ıiuü]ş|(y)?ken)"
            + "|"
            + "((y)?[dt][ıiuü]|(y)?s[ae])(m|n|k|n[ıiuü]z)"
            + "|"
            + "((y)?m[ıiuü]ş((y)?[ıiuü]m|s[ıiuü]n|(y)?[ıiuü]z|s[ıiuü]n[ıiuü]z|l[ae]r)?)?([dt][ıiuü]r)"
            + "|"
            + "((y)?m[ıiuü]ş((y)?[ıiuü]m|s[ıiuü]n|(y)?[ıiuü]z|s[ıiuü]n[ıiuü]z|l[ae]r)?)?(c[ae]s[ıi]n[ae])" // </editor-fold>
            ;

    public static void main(String[] args) {
        tablo1 t = new tablo1();
        Pattern p = Pattern.compile("([\\wüğışçö]+?)(" + t.regex + ")$");
        long a2 = System.currentTimeMillis();
        long a = System.nanoTime();
        Matcher m = p.matcher("çalışkanlar");
        while (m.find()) {
            System.out.println("Kabul");
            System.out.println(m.group(1));
//            for (int i = 0; i < m.groupCount(); i++) {
//                if (m.group(i)!=null) {
//                    System.out.println(m.group(i));
//                }
//            }
        }
        System.out.println((System.nanoTime() - a) / 1000 + "us");
        System.out.println((System.currentTimeMillis() - a2) + "ms");


    }
}
