/* Sayılar TBD Gülşen Cebiroğlunun makalesindeki eklere karşılık gelmektedir. */
package DB.Arsiv.Otomatalar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// <editor-fold defaultstate="collapsed" desc="Numaralı Ek listesi">
/**
 *
 * @author merter
1–(y)?[ıiuü]m
2–s[ıiuü]n
3–(y)?[ıiuü]z
4–s[ıiuü]n[ıiuü]z
5–l[ae]r
6–m[ıiuü]ş
7–(y)?[ae]c[ae]k
8–([ıiuü])?r
9-[ae]r
10–([ıiuü])?yor
11–m[ae]kt[ae]
12–m[ae]l[ıi]
13–m
14–n
15–k
16–n[ıiuü]z
17–[dt][ıiuü]
18–s[ae]
19–l[ıi]m
20–(y)?[ae]
21–(y)?[ıiuü]n[ıiuü]z
22–(y)?[ıiuü]n
23–s[ıiuü]nl[ae]r
24–[dt][ıiuü]r
25–(y)?[dt][ıiuü]
26–(y)?s[ae]
27–(y)?m[ıiuü]ş
28–c[ae]s[ıi]n[ae]
29–(y)?ken

 */// </editor-fold>
public class tablo4 {

    public String regex = // <editor-fold defaultstate="collapsed" desc="regex">
            "(((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi]|[dt][ıiuü]|s[ae]|(y)?[ae])?((y)?m[ıiuü]ş)|(m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi]))(y)?[ıiuü]z)"
            + "|"
            + "(((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi]|[dt][ıiuü]|s[ae]|(y)?[ae])?((y)?m[ıiuü]ş)|(m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi]|(y)?[ae]))?s[ıiuü]n)"
            + "|"
            + "(((y)?m[ıiuü]ş|(m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi]|(y)?[ae]))((y)?[ıiuü]m|s[ıiuü]n[ıiuü]z))"
            + "|"
            + "((([dt][ıiuü]|s[ae])|((y)?[dt][ıiuü]|(y)?s[ae]))(m|n|k|n[ıiuü]z))"
            + "|"
            + "(((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi]|[dt][ıiuü]|s[ae]|(y)?[ae])|((y)?[dt][ıiuü]|(y)?s[ae]|(y)?m[ıiuü]ş))l[ae]r)"
            + "|"
            + "((y)?[ae]|l[ıi]m)"
            + "|"
            + "(m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi]|[dt][ıiuü]|s[ae]|(y)?[ae]|(y)?[ıiuü]n[ıiuü]z|(y)?[ıiuü]n|s[ıiuü]nl[ae]r)"
            + "|"
            + "(((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi])|((((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi])?l[ae]r)|(m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi]|[dt][ıiuü]|s[ae]|(y)?[ae]))?(y)?m[ıiuü]ş)|(((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi])l[ae]r)))c[ae]s[ıi]n[ae])"
            + "|"
            + "(((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi])|(m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi])l[ae]r)(y)?ken)"
            + "|"
            + "(((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi]|[dt][ıiuü]|s[ae]|(y)?[ae])|((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi])?l[ae]r))?((y)?[dt][ıiuü]|(y)?s[ae]|(y)?m[ıiuü]ş))"
            + "|"
            + "(((m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi])|(m[ıiuü]ş|(y)?[ae]c[ae]k|([ıiuü])?r|[ae]r|([ıiuü])?yor|m[ae]kt[ae]|m[ae]l[ıi])((y)?[ıiuü]ms[ıiuü]n(y)?[ıiuü]zs[ıiuü]n[ıiuü]zl[ae]r))[dt][ıiuü]r)"// </editor-fold>
            ;

    public static void main(String[] args) {
        tablo4 t = new tablo4();
        Pattern p = Pattern.compile("([\\wüğışçö]+?)(" + t.regex + ")$");
        long a2 = System.currentTimeMillis();
        long a = System.nanoTime();
        Matcher m = p.matcher("yor");
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
