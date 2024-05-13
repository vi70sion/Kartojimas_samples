package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Skaiciai {
    public String fileReadCSV = "C:\\JavaTest\\Skaiciai.csv";
    public String fileWriteCSV = "C:\\JavaTest\\Skaiciai_sort.csv";
    public int[] array = new int[100] ;

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
            System.err.println("Error occurred: " + ex.getMessage());
        }
        String[] fileArray = new String[fileList.size()];
        for(int i = 0; i < fileList.size(); i++ ){
            fileArray[i] = fileList.toString();
        }
        return fileArray;
    }
}
