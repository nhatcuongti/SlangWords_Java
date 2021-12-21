package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Directionary {
    String fileName = "slang.txt";
    ArrayList<String> rawData = new ArrayList<>();
    HashMap<String, String> directionary = new HashMap<String, String>();

    public Directionary(){
        int maxLength = 0;
        long startTime = System.currentTimeMillis();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while(true){
                String data = br.readLine();
                if (data == null)
                    break;

                String[] slangAndDefi = data.split("`");
                if (slangAndDefi.length < 2)
                    continue;
                else
                    directionary.put(slangAndDefi[0], slangAndDefi[1]);

            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis() - startTime;


    }

    HashMap<String, String> findByDefinition(String keyWords){
        HashMap<String, String> subDirectionary = new HashMap<>();
        Set<String > keySet = directionary.keySet();

        for (String key : keySet) {
            String definition = directionary.get(key);
            definition = definition.toUpperCase();
            keyWords = keyWords.toUpperCase();

            if (definition.contains(keyWords))
                subDirectionary.put(key, directionary.get(key));
        }

        if (subDirectionary.size() == 0)
            return null;
        return subDirectionary;
    }

    HashMap<String, String> findByWords(String keyWords){
        HashMap<String, String> subDirectionary = new HashMap<>();
        if (directionary.get(keyWords) == null)
            return null;

        subDirectionary.put(keyWords, directionary.get(keyWords));
        return subDirectionary;
    }

    void get4RandomWords(){
        String[] slangWords = directionary.keySet().toArray(new String[0]);
        Random random = new Random();
        int index = random.nextInt(3, slangWords.length - 1);
        int index1 = index - 1;
        int index2 = index - 2;
        int index3 = index + 1;
        System.out.println(slangWords[index]);
        System.out.println(slangWords[index1]);
        System.out.println(slangWords[index2]);
        System.out.println(slangWords[index3]);
    }

    public static void main(String[] args){
        Directionary directionary = new Directionary();
        directionary.findByDefinition("B");

    }
}
