/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Starting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author user
 */
public class referensi {
     /**
     * @param args the command line arguments
     */
    static double PGo_out;
    static double PStay_home;

    static double PSunnyGo_out;
    static double PRainyGo_out;
    static double PSunnyStay_home;
    static double PRainyStay_home;

    static double PWorkingGo_out;
    static double PBrokenGo_out;
    static double PWorkingStay_home;
    static double PBrokenStay_home;

    static String[] Wheater = new String[10];
    static String[] Car = new String[10];
    static String[] Class = new String[10];
          
    static Map Wheater_Temp = new HashMap<>();
    static Map Car_Temp = new HashMap<>();
    static Map Class_Temp = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        String[] Wheater = {"sunny", "rainy", "sunny",
            "sunny", "sunny", "rainy",
            "rainy", "sunny", "sunny", "rainy"};
        String[] Car = {"working", "broken", "working",
            "working", "working", "broken",
            "broken", "working", "broken", "broken"};
        String[] Class = {"go-out", "go-out", "go-out", "go-out",
            "go-out", "stay-home", "stay-home", "stay-home",
            "stay-home", "stay-home"};
       // readFromExcel("D://data.xls");
        System.out.println("PRINT MAP");
       konvert2arr(Wheater_Temp, Wheater);
        konvert2arr(Car_Temp, Car);
       konvert2arr(Class_Temp, Class);

        System.out.println("===== DATA TRAINING =====");
        for (int a = 0; a < Class.length; a++) {
            System.out.println(Wheater[a] + "  " + Car[a] + "   " + Class[a]);
        }

        System.out.println("===== PROBABILITAS KELAS =====");
        int jumlahGo_out = hitungJumlah(Class, "go-out");
        int jumlahStay_home = hitungJumlah(Class, "stay-home");
        System.out.println("Jumlah Kelas go-out : " + jumlahGo_out);
        System.out.println("Jumlah Kelas stay-home : " + jumlahStay_home);

        PGo_out = P_class(jumlahGo_out, jumlahStay_home);
        PStay_home = P_class(jumlahStay_home, jumlahGo_out);

        System.out.println("Probabilitas Kelas go-out : " + PGo_out);
        System.out.println("Probabilitas Kelas stay-home : " + PStay_home);

        System.out.println("===== PROBABILITAS WHEATER =====");
        int jumlahSunnyGo_out = hitungJumlahWheater(Wheater, Class, "sunny", "go-out");
        int jumlahRainyGo_out = hitungJumlahWheater(Wheater, Class, "rainy", "go-out");
        int jumlahRainyStay_home = hitungJumlahWheater(Wheater, Class, "rainy", "stay-home");
        int jumlahSunnyStay_home = hitungJumlahWheater(Wheater, Class, "sunny", "stay-home");

        System.out.println("Jumlah Wheater Sunny Go-Out : " + jumlahSunnyGo_out);
        System.out.println("Jumlah Wheater Rainy Go-Out : " + jumlahRainyGo_out);
        System.out.println("Jumlah Wheater Rainy Stay-Home : " + jumlahRainyStay_home);
        System.out.println("Jumlah Wheater Sunny Stay-Home : " + jumlahSunnyStay_home);

        PSunnyGo_out = P_atribut(jumlahSunnyGo_out, jumlahGo_out);
        PRainyGo_out = P_atribut(jumlahRainyGo_out, jumlahGo_out);
        PSunnyStay_home = P_atribut(jumlahSunnyStay_home, jumlahStay_home);
        PRainyStay_home = P_atribut(jumlahRainyStay_home, jumlahStay_home);
        System.out.println("Probabilitas weather=sunny|class=go-out     : " + PSunnyGo_out);
        System.out.println("Probabilitas weather=rainy|class=go-out     : " + PRainyGo_out);
        System.out.println("Probabilitas weather=sunny|class=stay-home  : " + PSunnyStay_home);
        System.out.println("Probabilitas weather=rainy|class=stay-home  : " + PRainyStay_home);

        System.out.println("===== PROBABILITAS CAR =====");
        int jumlahWorkingGo_out = hitungJumlahCar(Car, Class, "working", "go-out");
        int jumlahBrokenGo_out = hitungJumlahCar(Car, Class, "broken", "go-out");
        int jumlahWorkingStay_home = hitungJumlahCar(Car, Class, "working", "stay-home");
        int jumlahBrokenStay_home = hitungJumlahCar(Car, Class, "broken", "stay-home");

        System.out.println("Jumlah Car Working Go-Out : " + jumlahWorkingGo_out);
        System.out.println("Jumlah Car Broken Go-Out : " + jumlahBrokenGo_out);
        System.out.println("Jumlah Car Working Stay-Home : " + jumlahWorkingStay_home);
        System.out.println("Jumlah Car Broken Stay-Home : " + jumlahBrokenStay_home);

        PWorkingGo_out = P_atribut(jumlahWorkingGo_out, jumlahGo_out);
        PBrokenGo_out = P_atribut(jumlahBrokenGo_out, jumlahGo_out);
        PWorkingStay_home = P_atribut(jumlahWorkingStay_home, jumlahStay_home);
        PBrokenStay_home = P_atribut(jumlahBrokenStay_home, jumlahStay_home);
        System.out.println("Probabilitas car=working|class=go-out     : " + PWorkingGo_out);
        System.out.println("Probabilitas car=broken|class=go-out     : " + PBrokenGo_out);
        System.out.println("Probabilitas car=working|class=stay-home  : " + PWorkingStay_home);
        System.out.println("Probabilitas car=broken|class=stay-home  : " + PBrokenStay_home);
 
        System.out.println(" ===== DATA TESTING ===== ");
        String wheater="sunny";
        String car="working";
        System.out.println("wheater = " + wheater);
        System.out.println("car = " + car);
        
        System.out.println("Hasil Klasifikasi Naive Bayes");
        System.out.println("Kesimpulan = "+ PrediksiNaiveBayes(wheater, car));
       
    }

    public static int hitungJumlah(String[] arrVariabel, String nilaiVar) {
        int hitung = 0;
        for (int a = 0; a < arrVariabel.length; a++) {
            if (arrVariabel[a].equals(nilaiVar)) {
                hitung++;
            }
        }
        return hitung;
    }

    public static int hitungJumlahWheater(String[] wheater, String[] Class, String nilaiWheater, String nilaiClass) {
        int hitung = 0;
        for (int a = 0; a < Class.length; a++) {
            if (wheater[a].equals(nilaiWheater) && Class[a].equals(nilaiClass)) {
                hitung++;
            }
        }
        return hitung;
    }

    public static int hitungJumlahCar(String[] car, String[] Class, String nilaiCar, String nilaiClass) {
        int hitung = 0;
        for (int a = 0; a < Class.length; a++) {
            if (car[a].equals(nilaiCar) && Class[a].equals(nilaiClass)) {
                hitung++;
            }
        }
        return hitung;
    }

    public static double P_class(int jumlah_a, int jumlah_b) {
        double Pclass = (double) jumlah_a / (jumlah_a + jumlah_b);
        return Pclass;
    }

    public static double P_atribut(int jumlah_a, int jumlah_b) {
        double Pclass = (double) jumlah_a / (jumlah_b);
        return Pclass;
    }

    public static String PrediksiNaiveBayes(String wheater, String car) {
        double hasilGo_Out = 0;
        double hasilStay_Home = 0;
        if (wheater.equals("sunny") && car.equals("working")) {
            hasilGo_Out = PSunnyGo_out * PWorkingGo_out * PGo_out;
            hasilStay_Home = PSunnyStay_home * PWorkingStay_home * PStay_home;
        } else if (wheater.equals("sunny") && car.equals("broken")) {
            hasilGo_Out = PSunnyGo_out * PBrokenGo_out * PGo_out;
            hasilStay_Home = PSunnyStay_home * PBrokenStay_home * PStay_home;
        } else if (wheater.equals("rainy") && car.equals("working")) {
            hasilGo_Out = PRainyGo_out * PWorkingGo_out * PGo_out;
            hasilStay_Home = PRainyStay_home * PWorkingStay_home * PStay_home;
        } else if (wheater.equals("rainy") && car.equals("broken")) {
            hasilGo_Out = PRainyGo_out * PBrokenGo_out * PGo_out;
            hasilStay_Home = PRainyStay_home * PBrokenStay_home * PStay_home;
        }
        System.out.println("hasil Go-Out = " + hasilGo_Out);
        System.out.println("hasil Stay-Home = " + hasilStay_Home);

        if (hasilGo_Out > hasilStay_Home) {
            return "Go-Out";
        } else {
            return "Stay-Home";
        }
    }

    

    public static void konvert2arr(Map map, String[] arr) {
        Set s = map.entrySet();
        Iterator it = s.iterator();
        int a = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Integer key = (Integer) entry.getKey();
            String value = (String) entry.getValue();
            arr[a] = value;
            System.out.println("Arr " + arr[a]);
            a++;
        }//while
        System.out.println("========================");
    }//printMap
}
