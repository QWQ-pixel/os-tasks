package com.BruteForce;

import java.time.Instant;
import java.util.ArrayList;

public class Main extends Thread {
    public static Instant start = Instant.now();
    public static ArrayList<String> password=new ArrayList<>();
    public static ArrayList<Character> letters=new ArrayList();

    public static void main(String[] args) {

        PassGenerate pass=new PassGenerate();
        pass.start();

        PassCheck check=new PassCheck();
        check.start();

    }
}
