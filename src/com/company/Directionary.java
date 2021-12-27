package com.company;

import java.io.*;
import java.util.*;

public class Directionary {
    String fileName = "slang.txt";
    ArrayList<String> rawData = new ArrayList<>();
    HashMap<String, ArrayList<String>> directionary = new HashMap<>();
    HashMap<String, ArrayList<String>> directionaryTmp = new HashMap<>();

    public Directionary(){
        readDataFrom(directionaryTmp, "slang.txt");
        readDataFrom(directionary, "slangOriginal.txt");
    }

    public void readDataFrom(HashMap<String, ArrayList<String>> directionaryData, String fileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while(true){
                String data = br.readLine();
                if (data == null)
                    break;

                String[] slangAndDefi = data.split("`");
                if (slangAndDefi.length < 2)
                    continue;
                else {

                    String slangWord = slangAndDefi[0];
                    String[] list = slangAndDefi[1].split("\\| ");

                    ArrayList<String> listDefi = new ArrayList<>();
                    for (String element : list)
                        listDefi.add(element);

                    directionaryData.put(slangWord, listDefi);
                }

            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkWordExists(String keyCheck) {
        ArrayList<String> definition = directionaryTmp.get(keyCheck);
        if (definition == null)
            return false;

        return true;
    }

    HashMap<String, ArrayList<String>> findByDefinition(String keyWords){
        HashMap<String, ArrayList<String>> subDirectionary = new HashMap<>();
        Set<String > keySet = directionaryTmp.keySet();

        for (String key : keySet) {
            String definition = String.valueOf(directionaryTmp.get(key));
            definition = definition.toUpperCase();
            keyWords = keyWords.toUpperCase();

            if (definition.contains(keyWords))
                subDirectionary.put(key, directionaryTmp.get(key));
        }

        if (subDirectionary.size() == 0)
            return null;
        return subDirectionary;
    }

    HashMap<String, ArrayList<String>> findByWords(String keyWords){
        HashMap<String, ArrayList<String>> subDirectionary = new HashMap<>();
        if (directionaryTmp.get(keyWords) == null)
            return null;

        subDirectionary.put(keyWords, directionaryTmp.get(keyWords));
        return subDirectionary;
    }

    void get4RandomWords(){

    }

    public boolean checkDefiExists(String word, String definition) {
        ArrayList<String> defiList = directionaryTmp.get(word);

        for(String defi : defiList)
            if (defi.equals(definition))
                return true;

        return false;
    }

    public void deleteOneElement(String word, String definition) {
        ArrayList<String> defiList = directionaryTmp.get(word);

        defiList.remove(definition);
        if (defiList.size() == 0)
            directionaryTmp.remove(word);

        writeToFile();
    }

    void writeToFile(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("slang.txt"));
            Set<String> keySet = directionaryTmp.keySet();
            for (String key : keySet){
                //Get String
                ArrayList<String> defiList = directionaryTmp.get(key);
                String defiListStr = String.join("| ", defiList);
                String strToFile = key + "`" + defiListStr;

                //Write to file
                bw.write(strToFile);
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void insertNewElement(String word, String definition) {
        ArrayList<String> listDefi = new ArrayList<>();
        listDefi.add(definition);

        directionaryTmp.put(word, listDefi);
        writeToFile();
    }

    public void insertToExistsElement(String word, String definition) {
        Set<String> keySet = directionaryTmp.keySet();
        ArrayList<String> listDefi = directionaryTmp.get(word);
        listDefi.add(definition);

        writeToFile();
    }


    public void updateData(String word, String definition, String newWord, String newDefinition) {
        if (word.equals(newWord)){
            // Change on definition
            if (definition.equals(newDefinition))
                return;

            ArrayList<String> listDefi = directionaryTmp.get(word);
            int index = 0;
            int count = 0;
            for (String defi : listDefi) {
                if (defi.equals(definition)) {
                    index = count;
                    break;
                } else
                    count++;
            }

            listDefi.set(index, newDefinition);
            writeToFile();


        }
        else{
            deleteOneElement(word, definition);
            if (checkWordExists(newWord))
                insertToExistsElement(newWord, newDefinition);
            else
                insertNewElement(newWord, newDefinition);

            writeToFile();
        }
    }

    public void resetData() {
        directionaryTmp = new HashMap<>(directionary);
    }




    public void saveSlangWord(String keyWord) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("history.txt", true));
            bufferedWriter.write(keyWord);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getHistory() {
        ArrayList<String[]> data = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("history.txt"));
            while (true){
                String str = br.readLine();
                if (str == null)
                    break;

                // Continue on this
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args){
        Directionary directionary = new Directionary();
        HashMap<String, String> test = new HashMap<>();
        test.put("A", "Ahihi");
        HashMap<String, String> testTmp = new HashMap<>(test);
        testTmp.remove("A");

        System.out.println(testTmp.get("A"));
    }
}
