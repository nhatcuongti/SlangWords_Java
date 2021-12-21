package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Directionary_TreeMap {
    String fileName = "slang.txt";
    ArrayList<String> rawData = new ArrayList<>();
    TreeMap<String, String> directionary = new TreeMap<String, String >();

    public Directionary_TreeMap(){
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

    void findByDefinition(String keyWords){
        long startTime = System.currentTimeMillis();

        Set<String > keySet = directionary.keySet();

        for (String key : keySet) {
            String definition = directionary.get(key);
            definition = definition.toUpperCase();
            keyWords = keyWords.toUpperCase();

            if (definition.contains(keyWords))
                System.out.println(key + " : " + directionary.get(key) );
        }

        long endTime = System.currentTimeMillis() - startTime;
        double duration = endTime / 1000.0;
        System.out.println( "Time : " + duration + " ms");

    }

    void findByWords(String keyWords){
        System.out.println(directionary.get(keyWords));
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
        Directionary_TreeMap directionaryTreeMap = new Directionary_TreeMap();
         directionaryTreeMap.findByDefinition("C");


    }
}
