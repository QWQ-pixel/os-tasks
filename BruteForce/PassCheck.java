package com.BruteForce;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import static com.BruteForce.Main.start;

import static com.BruteForce.Main.password;

public class PassCheck extends Thread{
    public static Instant start1= Instant.now();
    private static int count=0;
    @Override
    public void run(){
        try {
            checkPass();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static void checkPass() throws NoSuchAlgorithmException {
        ArrayList<String> hash=new ArrayList<>();
        hash.add("1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad");
        hash.add("3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b");
        hash.add("74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f");
       
        for(int i=0;i<password.size();i++) {
           CheckPass(password.get(i), hash);
              if (count == 3) {
                    Instant finish = Instant.now();
                    System.out.println("All password are matched");
                    long elapsed = Duration.between(start, finish).toMillis();
                    System.out.println("Time passed: " + (elapsed/1000)+" sec");
                    break;
                }
            }
        }
 
    public static void CheckPass(String password, ArrayList<String> hash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
            for (Object h : hash) {
                if (h.equals(sb.toString())) {
                    count++;
                     Instant finish1 = Instant.now();
                    long elapsed1 = Duration.between(start1, finish1).toMillis();
                    System.out.println("Password: " + password + " Hashcode: " + sb.toString());
                    System.out.println("Time for check password: " + (elapsed1 / 1000) + " sec");
                }
            }
        }
    }
}
