package org.example;

import java.io.*;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Skaiciai skaiciai = new Skaiciai();
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(skaiciai.fileReadCSV));
            String line;
            while((line = bufferedReader.readLine()) != null){
                skaiciai.array[i] = Integer.parseInt(line);
                i++;
            }
            bufferedReader.close();
        } catch (IOException e){
            System.err.println("Nepavyko skaityti failo: " + e.getMessage());
        }
        int[] array = new int[i];
        for(int j = 0; j < i; j++){
            array[j] = skaiciai.array[j];
        }
        skaiciai.sort(array);
        try{
            FileWriter fileWriter = new FileWriter(skaiciai.fileWriteCSV, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int j = 0; j < i; j++){
                bufferedWriter.write(Integer.toString(array[j]));
                bufferedWriter.newLine();
            }
            System.out.println("Eksportuota sėkmingai.");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e){
            System.err.println("Nepavyko įrašyti į failą: " + e.getMessage());
        }

        //Funkcija gauti failus is direktorijos. (Failų sąrašą reikia gražinti string masyvu)
        String directoryPath = "C:\\JavaTest";
        String[] fileList = skaiciai.listTextAndCsvFiles(directoryPath);

//        for(Path item: fileList){
//            System.out.println(item.toString());
//        }
        System.out.println();




        //Paleidus programą, ji turi nuskaityti visus txt ir csv failus direktorijoje kurią duodame.
        // Duomenis priskirti mūsų sukurtai klasei - Failo pavadinimas ir int masyvas




    }
}