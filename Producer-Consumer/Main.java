package com.pr3OS;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private final static int SIZE_QUEUE = 200;
    private final static int ELEMENTS_COUNT = 100;

    public static PCQueue queue = new PCQueue(SIZE_QUEUE, ELEMENTS_COUNT);
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static ThreadPool producer;
    public static ThreadPool consumer;
    public static Controller controller;
    public static String exit=" ";

    public static void main(String[] args) {

        System.out.println(" Start! ");
        System.out.println(" If you want to exit press 'q' ");

        controller=new Controller("controller");  //создаем поток, который считывает строку
        controller.start();

        producer = new ThreadPool(3, "producer", queue); //создаем пул потоков производителя
        consumer = new ThreadPool("consumer", queue, 2); //создаем пул потоков потребителя

    }
}
