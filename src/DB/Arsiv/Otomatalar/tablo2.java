/* Sayılar TBD Gülşen Cebiroğlunun makalesindeki eklere karşılık gelmektedir. */
package DB.Arsiv.Otomatalar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tablo2 {

    public String regex = // <editor-fold defaultstate="collapsed" desc="regex">
            "(((((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)?l[ae]r)?(([ıiuü])?m|([ıiuü])?m[ıiuü]z|([ıiuü])?n|([ıiuü])?n[ıiuü]z|(s)?[ıiuü])|l[ae]r[ıi]|((((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)l[ae]r?))?(n)?c[ae]"
            + "|"
            + "(((((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)|((((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)l[ae]r)?)|([([ıiuü])?m([ıiuü])?m[ıiuü]z([ıiuü])?n([ıiuü])?n[ıiuü]z]((((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)?l[ae]r)?))?[dt][ae]n"
            + "|"
            + "(([([ıiuü])?m([ıiuü])?m[ıiuü]z([ıiuü])?n([ıiuü])?n[ıiuü]z]((((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)?l[ae]r)?)|((((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)?l[ae]r)?)?((y)?[ıiuü]|(y)?[ae]|[dt][ae])"
            + "|"
            + "(((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)?l[ae]r"
            + "|"
            + "((((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)?l[ae]r)?(([ıiuü])?m|([ıiuü])?m[ıiuü]z|([ıiuü])?n|([ıiuü])?n[ıiuü]z|(s)?[ıiuü])"
            + "|"
            + "((((((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)?l[ae]r)?(s)?[ıiuü])|l[ae]r[ıi])(n[ıiuü]|n[dt][ae]n)"
            + "|"
            + "(((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)"
            + "|"
            + "(l[ae]r[ıi]|((l[ae]r[ıi])?(n)?[ıiuü]n|[dt][ae])ki)?((n)?[ıiuü]n|(y)?l[ae])"
            + "|"
            + "l[ae]r[ıi]|"
            + "(n[ae]|n[dt][ae])"// </editor-fold>
            ;

    public static void main(String[] args) {
        tablo2 t = new tablo2();
        Pattern p = Pattern.compile("([\\wüğışçö]+?)(" + t.regex + ")$");
        long a2 = System.currentTimeMillis();
        long a = System.nanoTime();
        Matcher m = p.matcher("sarımsaklamadan");
        while (m.find()) {
            System.out.println("Kabul");

            System.out.println(m.group(0));
            System.out.println(m.group(1));
        }
        System.out.println((System.nanoTime() - a) / 1000 + "us");
        System.out.println((System.currentTimeMillis() - a2) + "ms");


    }
}
