package org.example;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        Sukurti klasę kuri turi failo pavadinimą ir int masyvą.
//        Klasėje sukurti funkciją kuri masyvą surikiuoja pasinaudojant funkcija auksčiau.
//        Sukurti surikiuoto masyvo atspausdinimo į failą funkciją.
//        Sukurti funkciją kuri gauna visus txt ir csv failus nurodytoje direktorijoje

        Skaiciai skaiciai = new Skaiciai();
        int i = skaiciai.skaitytiCSV(skaiciai.fileReadCSV); // nuskaito duomenis iš csv failo, grąžina masyvo dydį
        int[] array = new int[i];
        for(int j = 0; j < i; j++){
            array[j] = skaiciai.array[j];
        }
        skaiciai.sort(array); //rūšiuojame masyvą
        for(int j = 0; j < i; j++){
            skaiciai.array[j] = array[j];
        }
        skaiciai.rasytiCSV(i); //įrašome į csv failą rūšiuotą masyvą

        //Funkcija gauti failus is direktorijos. (Failų sąrašą reikia gražinti string masyvu)
        String directoryPath = "C:\\JavaTest\\kartojimas_samples\\";
        String[] fileList = skaiciai.listTextAndCsvFiles(directoryPath);

        //nuskaityti visus txt ir csv failus direktorijoje kurią duodame.
        // Duomenis priskirti mūsų sukurtai klasei - Failo pavadinimas ir int masyvas
        // Sukurti klasę failobazineinformacija, kuri turi failo pavadinimą, failo vietą ir failo dydį (string, string, string)
        // Iš mūsų jau sukurtos klasės ištrinti pavadinimo narį ir mūsų klasė turi paveldėti failobazineinformacija klasę.
        Skaiciai[] skaiciaiArray = new Skaiciai[100];
        for(int j = 0; j < fileList.length; j++){
            int dydis = skaiciai.skaitytiCSV("C:\\JavaTest\\kartojimas_samples\\" + fileList[j]);
            array = new int[dydis];
            for(int k = 0; k < dydis; k++){
                array[k] = skaiciai.array[k];
            }
            //skaiciaiArray[j] = new Skaiciai(fileList[j], skaiciai.array);
            //skaiciaiArray[j] = new Skaiciai(fileList[j], array);
            skaiciaiArray[j] = new Skaiciai(fileList[j],
                    "C:\\JavaTest\\kartojimas_samples\\",
                    skaiciai.getFileSizeInKB(Paths.get("C:\\JavaTest\\kartojimas_samples\\" + fileList[j])),
                    array);
        }
        System.out.println();

//        Sukurti klasę kuri turi string masyva ir galėti jį surikiuoti.
//        Patobulinti nuskaitymą string masyvams iš failo






    }
}