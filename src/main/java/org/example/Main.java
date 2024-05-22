package org.example;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

//        Sukurti klasę kuri turi failo pavadinimą ir int masyvą.
//        Klasėje sukurti funkciją kuri masyvą surikiuoja pasinaudojant funkcija auksčiau.
//        Sukurti surikiuoto masyvo atspausdinimo į failą funkciją.
//        Sukurti funkciją kuri gauna visus txt ir csv failus nurodytoje direktorijoje

        //String fileReadCSV = "C:\\JavaTest\\kartojimas_samples\\Skaiciai.csv";
        //String fileWriteCSV = "C:\\JavaTest\\kartojimas_samples\\Skaiciai_sort.csv";

        String fileReadCSV = "C:\\JavaTest\\kartojimas_samples\\Tekstas.csv";
        String fileWriteCSV = "C:\\JavaTest\\kartojimas_samples\\Tekstas_sort.csv";

        Failas failas = new Failas();
        int[] parametrai = new int[]{0, 0};  // [0]- nuskaityto masyvo dydis, [1]- reikšmė: 1- skaičių failas; 0- tekstinis failas
        parametrai = failas.skaitytiCSV(fileReadCSV); // nuskaito duomenis iš csv failo, grąžina parametrų masyvą
        if(parametrai[1] == 1){ //skaičių failas
            int[] temp = new int[parametrai[0]];
            for(int j = 0; j < parametrai[0]; j++){ //perrašome tik nuskaitytus duomenis
                temp[j] = failas.array[j];
            }
            failas.sort(temp); //rūšiuojame masyvą
            failas.array = temp;
        } else {    //tekstinis failas
            String[] temp = new String[parametrai[0]];
            for(int j = 0; j < parametrai[0]; j++){ //perrašome tik nuskaitytus duomenis
                temp[j] = failas.textArray[j];
            }
            failas.sort(temp); //rūšiuojame masyvą
            failas.textArray = temp;
        }
        failas.rasytiCSV(parametrai, fileWriteCSV); //įrašome į csv failą rūšiuotą masyvą

        //Funkcija gauti failus is direktorijos. (Failų sąrašą reikia gražinti string masyvu)
        //nuskaityti visus txt ir csv failus direktorijoje kurią duodame.
        // Duomenis priskirti mūsų sukurtai klasei - Failo pavadinimas ir int masyvas
        // Sukurti klasę failobazineinformacija, kuri turi failo pavadinimą, failo vietą ir failo dydį (string, string, string)
        // Iš mūsų jau sukurtos klasės ištrinti pavadinimo narį ir mūsų klasė turi paveldėti failobazineinformacija klasę.

        String directoryPath = "C:\\JavaTest\\kartojimas_samples\\";
        String[] fileList = failas.listTextAndCsvFiles(directoryPath);  //nuskaitome failų pavadinimus
        Failas[] failuArray = new Failas[fileList.length];
        System.out.println();
        for(int i = 0; i < fileList.length; i++){
            parametrai = failas.skaitytiCSV("C:\\JavaTest\\kartojimas_samples\\" + fileList[i]);
            if(parametrai[1] == 1){
                int[] temp = new int[parametrai[0]];
                for(int j = 0; j < parametrai[0]; j++){ //perrašome tik nuskaitytus duomenis
                    temp[j] = failas.array[j];
                }
                failuArray[i] = new Failas(fileList[i],
                        "C:\\JavaTest\\kartojimas_samples\\",
                        failas.getFileSizeInKB(Paths.get("C:\\JavaTest\\kartojimas_samples\\" + fileList[i])),
                        temp);
            } else {
                String[] temp = new String[parametrai[0]];
                for(int j = 0; j < parametrai[0]; j++){ //perrašome tik nuskaitytus duomenis
                    temp[j] = failas.textArray[j];
                }
                failuArray[i] = new Failas(fileList[i],
                        "C:\\JavaTest\\kartojimas_samples\\",
                        failas.getFileSizeInKB(Paths.get("C:\\JavaTest\\kartojimas_samples\\" + fileList[i])),
                        temp);
            }
        }
        System.out.println("Išvedame failų informaciją: ");
        for(Failas item: failuArray){
            if(item.textArray[0] == null){  //neturi String masyvo, vadinasi turi int masyvą; reikėtų tikrinti ar visi lygūs null
                System.out.println(item.failoPavadinimas + ", vieta: " + item.failoVieta +
                        ", dydis: " + item.failoDydis + "B, " + item.array);
            } else {
                System.out.println(item.failoPavadinimas + ", vieta: " + item.failoVieta +
                        ", dydis: " + item.failoDydis + "B, " + item.textArray);
            }
        }

    }
}