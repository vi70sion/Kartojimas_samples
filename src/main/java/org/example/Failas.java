package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Failas extends Failobazineinformacija{
    public int[] array = new int[100] ;
    public String[] textArray = new String[100];

    public Failas(String failoPavadinimas, String failoVieta, String failoDydis, int[] array) {
        super(failoPavadinimas, failoVieta, failoDydis );
        this.array = array;
    }
    public Failas(String failoPavadinimas, String failoVieta, String failoDydis, String[] textArray) {
        super(failoPavadinimas, failoVieta, failoDydis );
        this.textArray = textArray;
    }

    public Failas() {
        super();
    }

    public int[] skaitytiCSV(String filePath){
        int[] parametrai = new int[] {0, 1};    //nuskaityto failo dydis [0]- 0; tikimasi nuskaityti skaičių failą [1]- 1
        int i = 0;  //skaičiuosim failo dydį
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = bufferedReader.readLine()) != null){
                try{
                    array[i] = Integer.parseInt(line);  //nebus gerai, prarasime eilutę, jei tekstinio failo atveju bus eilutė vien iš skaičių
                } catch (NumberFormatException e){
                    parametrai[1] = 0;  //randame tekstinį failą
                    textArray[i] = line;
                }
                i++;
            }
            bufferedReader.close();
            System.out.println("Nuskaityta sėkmingai.");
        } catch (IOException e){
            System.err.println("Nepavyko skaityti failo: " + e.getMessage());
        }
        parametrai[0] = i;
        return parametrai;
    }

    public void rasytiCSV(int[] param, String filePath){
        try{
            FileWriter fileWriter = new FileWriter(filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int j = 0; j < param[0]; j++){
                if(param[1] == 1) bufferedWriter.write(Integer.toString(array[j])); //1- skaičių ar 0- tekstinis masyvas
                    else bufferedWriter.write(textArray[j]);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Eksportuota sėkmingai.");
        } catch (IOException e){
            System.err.println("Nepavyko įrašyti į failą: " + e.getMessage());
        }
    }

    public void sort(int[] array) {
        boolean swapped;
        int n = array.length;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1] > array[i]) {
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    public void sort(String[] array) {
        Arrays.sort(array);
    }

    public String[] listTextAndCsvFiles(String directoryPath) {
        List<Path> fileList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.{txt,csv}")) {
            for (Path entry : stream) {
                fileList.add(entry.getFileName());
            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println("Gauta klaida: " + ex.getMessage());
        }
        String[] fileArray = new String[fileList.size()];
        for(int i = 0; i < fileList.size(); i++ ){
            fileArray[i] = String.valueOf(fileList.get(i));
        }
        return fileArray;
    }

    public String getFileSizeInKB(Path path) {
        try {
            long sizeInBytes = Files.size(path);
            //long sizeInKB = sizeInBytes / 1024;
            long sizeInKB = sizeInBytes;
            return sizeInKB + " B";
        } catch (IOException e) {
            System.err.println("Unable to determine file size: " + e.getMessage());
            return "Error";
        }
    }

}
