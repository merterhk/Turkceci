/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SuffixTree;

import java.io.File;
import java.util.HashMap;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author merter
 */
public class zember_ek {

    public static HashMap<String, String> eklerYigiti = new HashMap<String, String>(); // Ek adi , ek şeklinde ekleri barındırır.
    public static HashMap<String, Stack> ekKumeleri = new HashMap<String, Stack>(); // Ek kümesinin adı ve kümenin ekler stack'ını barındırır.
    public static HashMap<String, Stack> ekDizimi = new HashMap<String, Stack>(); // Bir ekten sonra gelebilecek eklerin stack'ini barındırır.
    public static HashMap<String, Stack> onceGelenEkler = new HashMap<String, Stack>(); // Bir ekten !önce! gelebilecek eklerin stack'ini barındırır.

    public static String turet(String ek) { // sAnUz
        String s = ek;
//U: ı,i,u,ü
//A: a,e
//D: d,t
//C: c,ç
//I : ı,i


        if (ek.contains("U")) {
            if (ek.contains("A")) {
                s = (s.replaceAll("U", "ı") + "," + s.replaceAll("U", "u")).replaceAll("A", "a") + "," + (s.replaceAll("U", "i") + "," + s.replaceAll("U", "ü")).replaceAll("A", "e");
            } else {
                s = s.replaceAll("U", "ı") + "," + s.replaceAll("U", "i") + "," + s.replaceAll("U", "u") + "," + s.replaceAll("U", "ü");
            }
        }
        if (ek.contains("I")) {
            if (ek.contains("A")) {
                s = (s.replaceAll("I", "ı") + "," + s.replaceAll("I", "u")).replaceAll("A", "a") + "," + (s.replaceAll("I", "i") + "," + s.replaceAll("I", "ü")).replaceAll("A", "e");
            } else {
                s = s.replaceAll("I", "ı") + "," + s.replaceAll("I", "i") + "," + s.replaceAll("I", "u") + "," + s.replaceAll("I", "ü");
            }
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

        if (ek.contains("~k")) {
            s = s.replaceAll("~k", "k") + "," + s.replaceAll("~k", "ğ");
        }
        if (ek.contains("+s")) {
            s = s.replaceAll("\\+s", "") + "," + s.replaceAll("\\+s", "s");
        }
        if (ek.contains("+y")) {
            s = s.replaceAll("\\+y", "") + "," + s.replaceAll("\\+y", "y");
        }
        if (ek.contains("+n")) {
            s = s.replaceAll("\\+n", "") + "," + s.replaceAll("\\+n", "n");
        }
        if (ek.contains("+m")) {
            s = s.replaceAll("\\+m", "") + "," + s.replaceAll("\\+m", "m");
        }
        if (ek.contains("+ş")) {
            s = s.replaceAll("\\+ş", "") + "," + s.replaceAll("\\+ş", "ş");
        }
        if (ek.contains(">c")) {
            s = s.replaceAll("\\>c", "c") + "," + s.replaceAll("\\>c", "ç");
        }
        if (ek.contains(">d")) {
            s = s.replaceAll("\\>d", "d") + "," + s.replaceAll("\\>d", "t");
        }
        if (ek.contains(">b")) {
            s = s.replaceAll("\\>b", "b") + "," + s.replaceAll("\\>b", "p");
        }


        return s;
    }

    public static void onceGelenEkleriOlustur() {
        for (String key : eklerYigiti.keySet()) {
            onceGelenEkler.put(key, new Stack());
        }

        for (String string : eklerYigiti.keySet()) {
            Stack sonrakiler = ekDizimi.get(string);
            while (sonrakiler != null && !sonrakiler.isEmpty() && !sonrakiler.contains(string)) {
                String pop = (String) sonrakiler.pop();
//                System.out.println(string);
                onceGelenEkler.get(pop).push(string);
            }
        }

//        for (String string : onceGelenEkler.keySet()) {
//            Stack oncekiler = onceGelenEkler.get(string);
//            while (oncekiler != null && !oncekiler.isEmpty()) {
//                String s = (String) oncekiler.pop();
////                System.out.println("-->" + s);
//            }
//
//        }


    }

    public static void onceGelenEklerDokum(String ek, String tre) {
        if (ek == null) {
            return;
        }
        Stack oncekiler = onceGelenEkler.get(ek);
        System.out.println(tre + " " + ek);
        while (oncekiler != null && !oncekiler.isEmpty()) {
            String s = "" + (String) oncekiler.pop();
            onceGelenEklerDokum(s, tre + " - ");
        }
    }

    public static void main(String[] args) {
        try {
            long a = System.nanoTime();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("ek_tr.xml"));
            doc.getDocumentElement().normalize();

            // <editor-fold defaultstate="collapsed" desc="Ek kümeleri okunur.">
/* Ek kümeleri okunur */
            NodeList kumeList = doc.getElementsByTagName("ek-kumesi");
            for (int i = 0; i < kumeList.getLength(); i++) {
                Element el = (Element) kumeList.item(i);
                Stack s = new Stack();

                NodeList kList = el.getElementsByTagName("ek");
                for (int j = 0; j < kList.getLength(); j++) {
                    Element k = (Element) kList.item(j);
                    s.add(k.getTextContent());
                }
                ekKumeleri.put(el.getAttribute("ad"), s);
            }
            System.out.println("---> Kümeler okundu...");// </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Ekler okunur.">
            /* Ekler okunur */
            NodeList ekOkuList = doc.getElementsByTagName("ek");
            for (int i = 0; i < ekOkuList.getLength(); i++) {
                Node ek = ekOkuList.item(i);
                if (ek.getAttributes().getNamedItem("uretim") != null) {
                    Element el = (Element) ek;
                    eklerYigiti.put(el.getAttribute("ad"), turet(el.getAttribute("uretim")));
                }
            }
            System.out.println("---> Ekler okundu...");// </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Dizilim yığıtları oluşturulur.">
                        /* Dizim yığıtları oluşturulur */

            Element ekler = (Element) doc.getElementsByTagName("ekler").item(0);
            NodeList ekDizimList = ekler.getElementsByTagName("ek");
            for (int i = 0; i < ekDizimList.getLength(); i++) {
                Stack ekStack = new Stack();

                Element el = (Element) ekDizimList.item(i);

                NodeList kList = el.getElementsByTagName("kume");
                for (int j = 0; j < kList.getLength(); j++) {
                    Element k = (Element) kList.item(j);
                    ekStack.addAll(ekKumeleri.get(k.getTextContent()));
//                    System.out.println("\t kume-->" + k.getTextContent());
                }

                NodeList aekList = el.getElementsByTagName("aek");
                for (int j = 0; j < aekList.getLength(); j++) {
                    Element aek = (Element) aekList.item(j);
                    ekStack.push(aek.getTextContent());
//                    System.out.println("\t ardisil-->" + aek.getTextContent());
                }

                NodeList oekList = el.getElementsByTagName("oek");
                for (int j = 0; j < oekList.getLength(); j++) {
                    Element oek = (Element) oekList.item(j);
                    ekStack.push(oek.getTextContent());
//                    System.out.println("\t oncelikli-->" + oek.getTextContent());
                }

                ekDizimi.put(el.getAttribute("ad"), ekStack);
//                System.out.println(el.getAttribute("ad"));
            }
            System.out.println("---> Dizilimler belirlendi...");// </editor-fold>

            onceGelenEkleriOlustur();

            for (String string : eklerYigiti.keySet()) {
                onceGelenEklerDokum(string, "+");
            }

            System.out.println(eklerYigiti.size());

            System.out.println((System.nanoTime() - a) / 1000 + " mikrosaniye");
        } catch (Exception ex) {
            System.out.println("hata virdi" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
