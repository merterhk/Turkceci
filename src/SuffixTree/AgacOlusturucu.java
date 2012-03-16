package SuffixTree;

/**
 * @author merter
 */
public class AgacOlusturucu {

    public void olustur() {
        Harf A = new Harf(' ', true);
        Harf B = new Harf(' ', true);
        Harf C = new Harf(' ', true);
        Harf D = new Harf(' ', true);
        Harf E = new Harf(' ', true);
        Harf F = new Harf(' ', true);
        Harf G = new Harf(' ', true);
        Harf H = new Harf(' ', true);

        G.gecis(turet("mUş,ymUş"), F);
        H.gecis(turet("Um,sUn,Uz,yUz,sUnUz,lAr"), G);
        H.gecis(turet("mUş,ymUş"), F);
        E.gecis(turet("Um,yUm,sUn,Uz,yUz,sUnUz,lAr"), G);
        E.gecis(turet("mUş,ymUş"), F);
        D.gecis(turet("DU,yDU,sA,ysA"), F);
        C.gecis(turet("DUr,DU,yDU,sA,ysA,mUş,ymUş"), F);
        B.gecis(turet("mUş,ymUş"), F);
        A.gecis(turet("Um,yUm,sUn,Uz,yUz,sUnUz"), B);
        A.gecis(turet("lAr"), C);
        A.gecis(turet("DU,yDU,sA,ysA,mUş,ymUş,ken,yken"), F);
        A.gecis(turet("m,n,k,nUz"), D);
        A.gecis(turet("DUr"), E);
        A.gecis(turet("cAsInA"), H);

        long a = System.nanoTime();
        long b = System.currentTimeMillis();
        Harf h = A, temp = null;

//        String str = "osmanlılaştıramadıklarımızdanmışsınızdır";
        String str = "gelmişsinizcesine";
        System.out.println("Kelime: '" + str+"'");
        for (int i = str.length() - 1; i >= 0; i--) {
            temp = h.onceki(str.charAt(i));
            if (temp != null) {
                h = temp;
                continue;
            }
            System.out.println("Kök: '" + str.substring(0, i + 1)+"'");
            break;
        }
        System.out.println("Süre: ");
        System.out.println((System.nanoTime() - a) + "ns");
        System.out.println((System.currentTimeMillis() - b) + "ms");

    }

    public void isimCekimEkiAgaci() {
        Harf A = new Harf(' ', true);
        Harf B = new Harf(' ', true);
        Harf C = new Harf(' ', true);
        Harf D = new Harf(' ', true);
        Harf E = new Harf(' ', true);
        Harf F = new Harf(' ', true);
        Harf G = new Harf(' ', true);
        Harf H = new Harf(' ', true);
        Harf K = new Harf(' ', true);
        Harf L = new Harf(' ', true);
        Harf M = new Harf(' ', true);

        M.gecis(turet("2,3,4,5,6"), H);
        M.gecis(turet("7"), K);
        M.gecis(turet("1"), L);

        L.gecis(turet("18"), D);

        H.gecis(turet("1"), L);

        G.gecis(turet("2,3,4,5"), H);
        G.gecis(turet("1"), L);
        G.gecis(turet("18"), D);

        F.gecis(turet("7"), K);
        F.gecis(turet("6"), H);
        F.gecis(turet("18"), D);

        E.gecis(turet("7"), K);
        E.gecis(turet("2,3,4,5,6"), H);
        E.gecis(turet("18"), D);
        E.gecis(turet("1"), L);

        D.gecis(turet("14"), F);
        D.gecis(turet("10"), E);
        D.gecis(turet("13"), B);

        C.gecis(turet("7"), K);
        C.gecis(turet("6"), H);

        B.gecis(turet("2,3,4,5"), H);
        B.gecis(turet("1"), L);

        A.gecis(turet("8,11,13"), B);
        A.gecis(turet("9,16"), C);
        A.gecis(turet("18"), D);
        A.gecis(turet("10,17"), E);
        A.gecis(turet("12,14"), F);
        A.gecis(turet("15"), G);
        A.gecis(turet("2,3,4,5,6"), H);
        A.gecis(turet("7"), K);
        A.gecis(turet("1"), L);
        A.gecis(turet("19"), M);
    }

    public String turet(String ek) { // sAnUz
        String s = ek;
//U: ı,i,u,ü
//A: a,e
//D: d,t
//C: c,ç
//I : ı,i
        if (ek.contains("U")) {
            s = s.replaceAll("U", "ı") + "," + s.replaceAll("U", "i") + "," + s.replaceAll("U", "u") + "," + s.replaceAll("U", "ü");
        }
        if (ek.contains("A")) {
            s = s.replaceAll("A", "a") + "," + s.replaceAll("A", "e");
        }
        if (ek.contains("D")) {
            s = s.replaceAll("D", "d") + "," + s.replaceAll("D", "t");
        }
        if (ek.contains("C")) {
            s = s.replaceAll("C", "c") + "," + s.replaceAll("C", "ç");
        }
        if (ek.contains("I")) {
            s = s.replaceAll("I", "ı") + "," + s.replaceAll("I", "i");
        }

        return s;
    }

    public static void main(String[] args) {
        AgacOlusturucu ao = new AgacOlusturucu();
        ao.olustur();
    }
}
