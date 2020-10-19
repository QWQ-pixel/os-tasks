package com.BruteForce;

import java.time.Instant;
import java.util.ArrayList;

/**
 * Переборщик паролей
 * Найдите с помощью алгоритма брут форс пятибуквенные пароли, соответствующие следующим хэшам SHA-256:
 *
 * 1. 1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad
 * 2. 3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b
 * 3. 74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f
 */

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
