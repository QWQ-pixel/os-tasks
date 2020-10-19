package com.BruteForce;

import java.util.ArrayList;

import static com.BruteForce.Main.letters;
import static com.BruteForce.Main.password;

public class PassGenerate extends Thread {
    @Override
    public void run(){

        addLetters(letters);
        createPass(letters,5,password,"");

    }
    static void addLetters(ArrayList<Character> list){

            for (char i='a';i<='z';i++)
                list.add(i);
    }

    static void createPass(ArrayList<Character> letters, int length, ArrayList<String> password, String pr){

    if(pr.length()==length){
        password.add(pr);
        return;
    }
    for(int i=0;i<letters.size();i++){

        createPass(letters,length,password,pr+letters.get(i));
     }
    }
}
