/* Sayılar TBD Gülşen Cebiroğlunun makalesindeki eklere karşılık gelmektedir. */
package DB.Arsiv.Otomatalar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tablo5 {

    public String regex = // <editor-fold defaultstate="collapsed" desc="regex">
            "((([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t)?(((m[ae]|(y)?[ae]m[ae])z)|(m[ae]|(y)?[ae]m[ae])(m|zs[ıi]n|z|y[ıi]z|zs[ıi]n[ıi]z|zl[ae]r)|(m[ae]ks[ıi]z[ıi]n|m[ae]d[ae]n)))"
            + "|"
            + "(((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t))?(((y)?[ıiuü]p|(y)?[ae]l[ıi]|[dt][ıiuü]kç[ae]|(y)?[ae]r[ae]k|(y)?[ıiuü]nc[ae])|((y)?[ıiuü]nc[ae]|y[ae])|((y)?[ae]r[ae]k|[dt][ae]n)))"
            + "|"
            + "((([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t)?((m[ae]|(y)?[ae]m[ae])zz|(y)?[ıiuü]c[ıiuü]))"
            + "|"
            + "(((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t))?(((y)?[ae]n|(y)?[ae]c[ae]k|(y)?[ae]s[ıi]|[dt][ıiuü]k|m[ıiuü]ş|m[ae]zl[ıi]k|m[ae]|(y)?[ıiuü]ş)|(m[ae]k)([dt]an|[dt][ae]|(y)?l[ae]|(y)?[ae])|(m[ae]k)))"
            + "|"
            + "(((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t)))" // </editor-fold>
            ;

    //// <editor-fold defaultstate="collapsed" desc="Uzun hali">
    // O girişi için
    //            // <editor-fold defaultstate="collapsed" desc="Q Girişi">
    //            "((([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t)?(m[ae]|(y)?[ae]m[ae])z)"
    //            + "|"// </editor-fold>
    //            // A girişi için
    //            // <editor-fold defaultstate="collapsed" desc="A Girişi">
    //            + "((([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t)?(m[ae]|(y)?[ae]m[ae])(m|zs[ıi]n|z|y[ıi]z|zs[ıi]n[ıi]z|zl[ae]r))"
    //            + "|"
    //            + "((([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t)?(m[ae]ks[ıi]z[ıi]n|m[ae]d[ae]n))"
    //            + "|"
    //            + "(((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t))?((y)?[ıiuü]p|(y)?[ae]l[ıi]|[dt][ıiuü]kç[ae]|(y)?[ae]r[ae]k|(y)?[ıiuü]nc[ae]))"
    //            + "|"
    //            + "(((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t))?((y)?[ıiuü]nc[ae]y[ae]))"
    //            + "|"
    //            + "(((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t))?((y)?[ae]r[ae]k[dt][ae]n))"
    //            + "|"// </editor-fold>
    //            // P girişi için
    //            // <editor-fold defaultstate="collapsed" desc="P Girişi">
    //            + "((([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t)?(m[ae]|(y)?[ae]m[ae])z)"
    //            + "|"
    //            + "((([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t)(y)?[ıiuü]c[ıiuü])"
    //            + "|"
    //            + "(((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t))?((y)?[ae]n|(y)?[ae]c[ae]k|(y)?[ae]s[ıi]|[dt][ıiuü]k|m[ıiuü]ş|m[ae]zl[ıi]k|m[ae]|(y)?[ıiuü]ş))"
    //            + "|"// </editor-fold>
    //            // Q girişi için
    //            // <editor-fold defaultstate="collapsed" desc="Q Girişi">
    //            + "((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t))?(m[ae]k)([dt]an|[dt][ae]|(y)?l[ae]|(y)?[ae])"
    //            + "|"// </editor-fold>
    //            // R girişi için
    //            // <editor-fold defaultstate="collapsed" desc="R Girişi">
    //            + "(((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t))?(m[ae]k))"
    //            + "|"// </editor-fold>
    //            // C girişi için
    //            // <editor-fold defaultstate="collapsed" desc="C Girişi">
    //            + "(((m[ae]|(y)?[ae]m[ae]|(y)?[ae]yaz|(y)?[ae]kal|(y)?[ae]koy)|(y)?[ae]bil|((y)?[ae]dur|(y)?[ıiuü]ver|(y)?[ae]gel|(y)?[ae]gör)|(([ıiuü])?n|([ıiuü])?ş|([ıiuü])?l|[dt]ur|([ıiuü])?t)))"// </editor-fold>
    //            + ""
    //// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Numaralı Ek listesi">
/*
    1–m
    2–zs[ıi]n
    3–z
    4–y[ıi]z
    5–zs[ıi]n[ıi]z
    6–zl[ae]r
    7–m[ae]
    8–(y)?[ae]m[ae]
    9–(y)?[ae]dur
    10–(y)?[ıiuü]ver
    11–(y)?[ae]gel
    12–(y)?[ae]gör
    13–(y)?[ae]bil
    14–(y)?[ae]yaz
    15–(y)?[ae]kal
    16–(y)?[ae]koy
    17–m[ae]k
    18–(y)?[ıiuü]c[ıiuü]
    19–(y)?[ıiuü]p
    20–(y)?[ae]l[ıi]
    21–[dt][ıiuü]kç[ae]
    22–(y)?[ae]r[ae]k
    23–(y)?[ıiuü]nc[ae]
    24–[dt][ae]n
    25–y[ae]
    26–(y)?[ae]n
    27–(y)?[ae]c[ae]k
    28–(y)?[ae]s[ıi]
    29–[dt][ıiuü]k
    30–m[ıiuü]ş
    31–m[ae]zl[ıi]k
    32–m[ae]
    33–(y)?[ıiuü]ş
    34–[dt]an
    35–[dt][ae]
    36–(y)?l[ae]
    37–(y)?[ae]
    38–m[ae]ks[ıi]z[ıi]n
    39–m[ae]d[ae]n
    40–([ıiuü])?n
    41–([ıiuü])?ş
    42–([ıiuü])?l
    43–[dt]ur
    44-([ıiuü])?t
     */
// </editor-fold>
    public static void main(String[] args) {
        tablo5 t = new tablo5();
        Pattern p = Pattern.compile("([\\wüğışçö]+?)((" + t.regex + ")+)$");
        long a2 = System.currentTimeMillis();
        long a = System.nanoTime();
        Matcher m = p.matcher("görüşebilişmek");
        while (m.find()) {
            System.out.println("Kabul");

            System.out.println(m.group(0));
            System.out.println(m.group(1));
        }
        System.out.println((System.nanoTime() - a) / 1000 + "us");
        System.out.println((System.currentTimeMillis() - a2) + "ms");


    }
}
