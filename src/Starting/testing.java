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
public class testing {
    /**
     * @param args the command line arguments
     */
    static double PSinusitis_Akut;
    static double PSinusitis_Subakut;
    static double PSinusitis_Kronis;
   

    static double PKentalSinusitis_akut;
    static double PCairSinusitis_akut;
    static double PKentalSinusitis_subakut;
    static double PCairSinusitis_subakut;
    static double PKentalSinusitis_kronis;
    static double PCairSinusitis_kronis;
    
    static double P1_2Sinusitis_akut;
    static double P4_12Sinusitis_akut;
    static double PL_12Sinusitis_akut;
    static double P1_2Sinusitis_subakut;
    static double P4_12Sinusitis_subakut;
    static double PL_12Sinusitis_subakut;
    static double P1_2Sinusitis_kronis;
    static double P4_12Sinusitis_kronis;
    static double PL_12Sinusitis_kronis;
    

    
    static String[] Tekstur = new String[10];
    static String[] Durasi = new String[10];
    static String[] Penyakit = new String[20];
   
          
    static Map Tekstur_Temp = new HashMap<>();
    static Map Durasi_Temp = new HashMap<>();
    static Map Penyakit_Temp = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        String[] Tekstur = {"Kental","Cair","Kental","Kental","Kental","Cair",
                            "Kental","Kental","Kental","Kental"};
        String[] Durasi = {"1-2 Minggu","4-12 Minggu","Lebih Dari 12 Minggu",
                            "1-2 Minggu","1-2 Minggu","4-12 Minggu","4-12 Minggu",
                            "Lebih Dari 12 Minggu","Lebih Dari 12 Minggu",
                            "Lebih Dari 12 Minggu"};
        String[] Penyakit = {"Sinusitis-Akut","Sinusitis-Subakut","Sinusitis-Kronis",
                            "Sinusitis-Akut","Sinusitis-Akut","Sinusitis-Subakut",
                            "Sinusitis-Subakut","Sinusitis-Kronis","Sinusitis-Kronis",
                            "Sinusitis-Kronis"};
        
       // readFromExcel("D://data.xls");
        System.out.println("PRINT MAP");
       konvert2arr(Tekstur_Temp, Tekstur);
       konvert2arr(Durasi_Temp, Durasi);
       konvert2arr(Penyakit_Temp, Penyakit);

        
        
        System.out.println("======Train set 2=========");
         System.out.println("Tekstur  Durasi	PENYAKIT");
        for (int a = 0; a < Penyakit.length; a++){
            System.out.println(Tekstur[a] + "     " + Durasi[a]
            + "     " + Penyakit[a]);
        }

        System.out.println("===== PROBABILITAS Penyakit =====");
        int jumlahSinusitis_Akut = hitungJumlah(Penyakit, "Sinusitis-Akut");
        int jumlahSinusitis_Subakut = hitungJumlah(Penyakit, "Sinusitis-Subakut");
        int jumlahSinusitis_Kronis = hitungJumlah(Penyakit, "Sinusitis-Kronis");
        System.out.println("Jumlah Penyakit Sinusitis Akut : " + jumlahSinusitis_Akut);
        System.out.println("Jumlah Penyakit Sinusitis Sub akut : " + jumlahSinusitis_Subakut);
        System.out.println("Jumlah Penyakit Kronis : " + jumlahSinusitis_Kronis);

        PSinusitis_Akut = P_class(jumlahSinusitis_Akut, jumlahSinusitis_Subakut, jumlahSinusitis_Kronis);
        PSinusitis_Subakut = P_class(jumlahSinusitis_Subakut, jumlahSinusitis_Kronis, jumlahSinusitis_Akut);
        PSinusitis_Kronis = P_class(jumlahSinusitis_Kronis, jumlahSinusitis_Akut,jumlahSinusitis_Subakut);

        System.out.println("Probabilitas Penyakit Sinusitis Akut : " + PSinusitis_Akut);
        System.out.println("Probabilitas Penyakit Sinusitis Subakut : " + PSinusitis_Subakut);
        System.out.println("Probabilitas Penyakit Sinusitis Kronis : " + PSinusitis_Kronis);

        System.out.println("===== PROBABILITAS Tekstur =====");
        
        
        int jumlahCairSinusitisAkut = hitungJumlahTekstur(Tekstur, Penyakit, "Kental", "Sinusitis-Akut");
        int jumlahKentalSinusitisAkut = hitungJumlahTekstur(Tekstur, Penyakit, "Cair", "Sinusitis-Akut");
        int jumlahCairSinusitisSubakut = hitungJumlahTekstur(Tekstur, Penyakit, "Kental", "Sinusitis-Subakut");
        int jumlahKentalSinusitisSubakut = hitungJumlahTekstur(Tekstur, Penyakit, "Cair", "Sinusitis-Subakut");
        int jumlahCairSinusitisKronis = hitungJumlahTekstur(Tekstur, Penyakit, "Kental", "Sinusitis-Kronis");
        int jumlahKentalSinusitsKronis = hitungJumlahTekstur(Tekstur, Penyakit, "Cair", "Sinusitis-Kronis");
        

        System.out.println("Jumlah Tekstur Kental Akut : " + jumlahCairSinusitisAkut);
        System.out.println("Jumlah Tekstur Cair Akut : " + jumlahKentalSinusitisAkut);
        System.out.println("Jumlah Tekstur Kental Sub akut : " + jumlahCairSinusitisSubakut);
        System.out.println("Jumlah Tekstur Cair Sub akut : " + jumlahKentalSinusitisSubakut);
        System.out.println("Jumlah Tekstur Kental Kronis : " + jumlahCairSinusitisKronis);
        System.out.println("Jumlah Tekstur Cair Kronis : " + jumlahKentalSinusitsKronis);

        PKentalSinusitis_akut = P_atribut(jumlahCairSinusitisAkut, jumlahSinusitis_Akut);
        PCairSinusitis_akut = P_atribut(jumlahKentalSinusitisAkut, jumlahSinusitis_Akut);
        PKentalSinusitis_subakut = P_atribut(jumlahCairSinusitisSubakut, jumlahSinusitis_Subakut);
        PCairSinusitis_subakut = P_atribut(jumlahKentalSinusitisSubakut, jumlahSinusitis_Subakut);
        PKentalSinusitis_kronis = P_atribut(jumlahCairSinusitisKronis, jumlahSinusitis_Kronis);
        PCairSinusitis_kronis = P_atribut(jumlahKentalSinusitsKronis, jumlahSinusitis_Kronis);
        
        System.out.println("Probabilitas Tekstur=kental|penyakit=sinusitis-akut     : " + PKentalSinusitis_akut);
        System.out.println("Probabilitas Tekstur=cair|penyakit=sinusitis-akut     : " + PCairSinusitis_akut);
        System.out.println("Probabilitas Tekstur=kental|penyakit=sinusitis-subakut  : " + PKentalSinusitis_subakut);
        System.out.println("Probabilitas Tekstur=cair|penyakitsinusitis-subakut  : " + PCairSinusitis_subakut);
        System.out.println("Probabilitas Tekstur=kental|penyakit=sinusitis-kronis  : " + PKentalSinusitis_kronis);
        System.out.println("Probabilitas Tekstur=cair|penyakit=sinusitis-kronis  : " + PCairSinusitis_kronis);

        System.out.println("===== PROBABILITAS Durasi =====");
        
        int jumlah1_2SinustisAkut = hitungJumlahDurasi(Durasi, Penyakit, "1-2 Minggu", "Sinusitis-Akut");
        int jumlah4_12SinustisAkut = hitungJumlahDurasi(Durasi, Penyakit, "4-12 Minggu", "Sinusitis-Akut");
        int jumlahL_12SinustisAkut = hitungJumlahDurasi(Durasi, Penyakit, "Lebih Dari 12 Minggu", "Sinusitis-Akut");
        int jumlah1_2SinustisSubakut = hitungJumlahDurasi(Durasi, Penyakit, "1-2 Minggu", "Sinusitis-Subakut");
        int jumlah4_12SinustisSubakut = hitungJumlahDurasi(Durasi, Penyakit, "4-12 Minggu", "Sinusitis-Subakut");
        int jumlahL_12SinustisSubakut = hitungJumlahDurasi(Durasi, Penyakit, "Lebih Dari 12 Minggu", "Sinusitis-Subakut");
        int jumlah1_2SinustisKronis = hitungJumlahDurasi(Durasi, Penyakit, "1-2 Minggu", "Sinusitis-Kronis");
        int jumlah4_12SinustisKronis = hitungJumlahDurasi(Durasi, Penyakit, "4-12 Minggu", "Sinusitis-Kronis");
        int jumlahL_12SinustisKronis = hitungJumlahDurasi(Durasi, Penyakit, "Lebih Dari 12 Minggu", "Sinusitis-Kronis");

        System.out.println("Jumlah Durasi 1-2 Minggu Sinusitis Akut : " + jumlah1_2SinustisAkut);
        System.out.println("Jumlah Durasi 4-12 Minggu Sinusitis Akut : " + jumlah4_12SinustisAkut);
        System.out.println("Jumlah Durasi Lebih dari 12 Minggu Sinusitis Akut : " + jumlahL_12SinustisAkut);
        System.out.println("Jumlah Durasi 1-2 Minggu Sinusitis Subakut : " + jumlah1_2SinustisSubakut);
        System.out.println("Jumlah Durasi 4-12 Minggu Sinusitis Subakut : " + jumlah4_12SinustisSubakut);
        System.out.println("Jumlah Durasi Lebih dari 12 Minggu Sinusitis Subakut : " + jumlahL_12SinustisSubakut);
        System.out.println("Jumlah Durasi 1-2 Minggu Sinusitis Subakut : " + jumlah1_2SinustisKronis);
        System.out.println("Jumlah Durasi 4-12 Minggu Sinusitis Subakut : " + jumlah4_12SinustisKronis);
        System.out.println("Jumlah Durasi Lebih dari 12 Minggu Sinusitis Subakut : " + jumlahL_12SinustisKronis);
        
        
        

        P1_2Sinusitis_akut = P_atribut(jumlah1_2SinustisAkut, jumlahSinusitis_Akut);
        P4_12Sinusitis_akut = P_atribut(jumlah4_12SinustisAkut, jumlahSinusitis_Akut);
        PL_12Sinusitis_akut = P_atribut(jumlahL_12SinustisAkut, jumlahSinusitis_Akut);
        P1_2Sinusitis_subakut = P_atribut(jumlah1_2SinustisSubakut, jumlahSinusitis_Subakut);
        P4_12Sinusitis_subakut = P_atribut(jumlah4_12SinustisSubakut, jumlahSinusitis_Subakut);
        PL_12Sinusitis_subakut = P_atribut(jumlahL_12SinustisSubakut, jumlahSinusitis_Subakut);
        P1_2Sinusitis_kronis = P_atribut(jumlah1_2SinustisKronis, jumlahSinusitis_Kronis);
        P4_12Sinusitis_kronis = P_atribut(jumlah4_12SinustisKronis, jumlahSinusitis_Kronis);
        PL_12Sinusitis_kronis = P_atribut(jumlahL_12SinustisKronis, jumlahSinusitis_Kronis);
        
        System.out.println("Probabilitas Durasi=1-2 Minggu|Penyakit=Sinusitis Akut     : " + P1_2Sinusitis_akut);
        System.out.println("Probabilitas Durasi=2-12 Minggu|Penyakit=Sinusitis Akut     : " + P4_12Sinusitis_akut);
        System.out.println("Probabilitas Durasi=Lebih dari 12 Minggu|Penyakit=Sinusitis Akut     : " + PL_12Sinusitis_akut);
        System.out.println("Probabilitas Durasi=1-2 Minggu|Penyakit=Sinusitis Subakut     : " + P1_2Sinusitis_subakut);
        System.out.println("Probabilitas Durasi=2-12 Minggu|Penyakit=Sinusitis Subakut     : " + P4_12Sinusitis_subakut);
        System.out.println("Probabilitas Durasi=Lebih dari 12 Minggu|Penyakit=Subakut     : " + PL_12Sinusitis_subakut);
        System.out.println("Probabilitas Durasi=1-2 Minggu|Penyakit=Sinusitis Kronis     : " + P1_2Sinusitis_kronis);
        System.out.println("Probabilitas Durasi=2-12 Minggu|Penyakit=Sinusitis Kronis     : " + P4_12Sinusitis_kronis);
        System.out.println("Probabilitas Durasi=Lebih dari 12 Minggu|Penyakit=Sinusitis Kronis     : " + PL_12Sinusitis_kronis);
        
 
        System.out.println(" ===== DATA TESTING ===== ");
        String tekstur="Kental";
        String durasi="1-2 Minggu";
        System.out.println("Tekstur = " + tekstur);
        System.out.println("Durasi = " + durasi);
        
        System.out.println("Klasifikasi Naive Bayes");
        System.out.println("Best Solution = "+ PrediksiNaiveBayes(tekstur, durasi));
       
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

    public static int hitungJumlahTekstur(String[] Tekstur, String[] Penyakit, String nilaiTekstur, String nilaiPenyakit) {
        int hitung = 0;
        for (int a = 0; a < Penyakit.length; a++) {
            if (Tekstur[a].equals(nilaiTekstur) && Penyakit[a].equals(nilaiPenyakit)) {
                hitung++;
            }
        }
        return hitung;
    }
    public static int hitungJumlahDurasi(String[] Durasi, String[] Penyakit, String nilaiDurasi, String nilaiPenyakit) {
        int hitung = 0;
        for (int a = 0; a < Penyakit.length; a++) {
            if (Durasi[a].equals(nilaiDurasi) && Penyakit[a].equals(nilaiPenyakit)) {
                hitung++;
            }
        }
        return hitung;
    }
    

    public static double P_class(int jumlah_a, int jumlah_b, int jumlah_c) {
        double Pclass = (double) jumlah_a / (jumlah_a + jumlah_b+jumlah_c);
        return Pclass;
    }

    public static double P_atribut(int jumlah_a, int jumlah_b) {
        double Pclass = (double) jumlah_a / (jumlah_b);
        return Pclass;
    }

    public static String PrediksiNaiveBayes(String Tekstur, String Durasi) {
        double hasilSinusitis_Akut = 0;
        double hasilSinusitis_Subakut = 0;
        double hasilSinusitis_Kronis = 0;
        if (Tekstur.equals("Kental") && Durasi.equals("1-2 Minggu")) {
            hasilSinusitis_Akut = PKentalSinusitis_akut * P1_2Sinusitis_akut * PSinusitis_Akut;
            hasilSinusitis_Subakut = PKentalSinusitis_subakut * P4_12Sinusitis_subakut * PSinusitis_Subakut;
            hasilSinusitis_Kronis = PKentalSinusitis_kronis * PL_12Sinusitis_kronis * PSinusitis_Kronis;
        } else if (Tekstur.equals("Kental") && Durasi.equals("4-12 Minggu")) {
            hasilSinusitis_Akut = PKentalSinusitis_akut * P1_2Sinusitis_akut * PSinusitis_Akut;
            hasilSinusitis_Subakut = PKentalSinusitis_subakut * P4_12Sinusitis_subakut * PSinusitis_Subakut;
            hasilSinusitis_Kronis = PKentalSinusitis_kronis * PL_12Sinusitis_kronis * PSinusitis_Kronis;
        }else if (Tekstur.equals("Kental") && Durasi.equals("Lebih Dari 12 Minggu")) {
            hasilSinusitis_Akut = PKentalSinusitis_akut * P1_2Sinusitis_akut * PSinusitis_Akut;
            hasilSinusitis_Subakut = PKentalSinusitis_subakut * P4_12Sinusitis_subakut * PSinusitis_Subakut;
            hasilSinusitis_Kronis = PKentalSinusitis_kronis * PL_12Sinusitis_kronis * PSinusitis_Kronis;} 
        else if (Tekstur.equals("Cair") && Durasi.equals("1-2 Minggu")) {
            hasilSinusitis_Akut = PCairSinusitis_akut * P1_2Sinusitis_akut * PSinusitis_Akut;
            hasilSinusitis_Subakut = PCairSinusitis_subakut * P4_12Sinusitis_subakut * PSinusitis_Subakut;
            hasilSinusitis_Kronis = PCairSinusitis_kronis * PL_12Sinusitis_kronis * PSinusitis_Kronis;
        } else if (Tekstur.equals("Cair") && Durasi.equals("4-12 Minggu")) {
            hasilSinusitis_Akut = PCairSinusitis_akut * P1_2Sinusitis_akut * PSinusitis_Akut;
            hasilSinusitis_Subakut = PCairSinusitis_subakut * P4_12Sinusitis_subakut * PSinusitis_Subakut;
            hasilSinusitis_Kronis = PCairSinusitis_kronis * PL_12Sinusitis_kronis * PSinusitis_Kronis;
        }else if (Tekstur.equals("Cair") && Durasi.equals("Lebih Dari 12 Minggu")) {
            hasilSinusitis_Akut = PCairSinusitis_akut * P1_2Sinusitis_akut * PSinusitis_Akut;
            hasilSinusitis_Subakut = PCairSinusitis_subakut * P4_12Sinusitis_subakut * PSinusitis_Subakut;
            hasilSinusitis_Kronis = PCairSinusitis_kronis * PL_12Sinusitis_kronis * PSinusitis_Kronis;} 
        System.out.println("Sinusitis Akut = " + hasilSinusitis_Akut);
        System.out.println("Sinusitis Subakut = " + hasilSinusitis_Subakut);
        System.out.println("Sinusitis Kronis = " + hasilSinusitis_Kronis);

        if (hasilSinusitis_Akut > hasilSinusitis_Subakut) {
            return "Sinusitis-Akut";
        } else 
        if (hasilSinusitis_Subakut > hasilSinusitis_Kronis){
            return "Sinusitis-Subakut";
        }
        else
            return "Sinusitis-Kronis";
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
