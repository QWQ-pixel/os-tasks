package com.pr3OS;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Дано 3 производителя и 2 потребителя, все разные потоки и работают все одновременно.
 * Есть очередь с 200 элементами. Производители добавляют случайное число от 1..100, а потребители берут эти числа.
 * Если в очереди элементов >= 100 производители спят, если нет элементов в очереди - потребители спят.
 * Все это работает до тех пор пока пользователь не нажал на кнопку "q",
 * после чего производители останавливаются а потребители берут все элементы, только потом программа завершается.
 *
 *
 * все же учтите, что более правильно это специально "закрывать" очередь.
 * Иметь в ней флаг on/off и действовать в соответствии с ним в методах queue.add() и queue.remove()
 * (не забывая о notifyAll).
 *
 *
 * Производители и потребители.
 * Есть несколько производителей и несколько потребителей, все разные потоки и работают все одновременно.
 * Есть очередь с N элементами.
 * Производители добавляют случайное число от 1..100, а потребители берут эти числа.
 * Если в очереди элементов >= N производители спят, если нет элементов в очереди - потребители спят.
 * Если элементов стало <= 0 производители просыпаются.
 */

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
