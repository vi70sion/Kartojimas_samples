package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Skaiciai extends Failobazineinformacija{
    public String fileReadCSV = "C:\\JavaTest\\kartojimas_samples\\Skaiciai.csv";
    public String fileWriteCSV = "C:\\JavaTest\\kartojimas_samples\\Skaiciai_sort.csv";
    public int[] array = new int[100] ;
    //public String fileName;

    public Skaiciai(String failoPavadinimas, String failoVieta, String failoDydis, int[] array) {
    //public Skaiciai(String fileName, int[] array) {
        super(failoPavadinimas, failoVieta, failoDydis );
        //this.fileName = fileName;
        this.array = array;
    }
    public Skaiciai() {
        super();
    }

    public int skaitytiCSV(String filePath){
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = bufferedReader.readLine()) != null){
                array[i] = Integer.parseInt(line);
                i++;
            }
            bufferedReader.close();
            System.out.println("Nuskaityta sėkmingai.");
        } catch (IOException e){
            System.err.println("Nepavyko skaityti failo: " + e.getMessage());
        }
        return i;
    }

    public void rasytiCSV(int i){
        try{
            FileWriter fileWriter = new FileWriter(fileWriteCSV, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int j = 0; j < i; j++){
                bufferedWriter.write(Integer.toString(array[j]));
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
