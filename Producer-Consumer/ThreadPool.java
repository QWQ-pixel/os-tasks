package com.pr3OS;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;


public class ThreadPool implements Executor {

    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>(); //рабочая очередь
    private volatile static boolean isRunning = true;  //флаг который будет показывать состояние пула

    public ThreadPool(int nThreads, String name, PCQueue store) {
        int count = 1;
        for (int i = 0; i < nThreads; i++) {

            System.out.println((name+":"+ count)+" start...");
            new Thread(new Producer(store),(name+":"+ count++)).start(); //создает и запускает пул потоков производителя
        }
    }
    public ThreadPool(String name, PCQueue store, int nThreads) {

        int count = 1;
        for (int i = 0; i < nThreads; i++) {

            System.out.println((name+":"+ count)+" start...");
            new Thread(new Consumer(store),(name+":"+ count++)).start(); //создает и запускает пул потоков потребителя
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isRunning()) {
            workQueue.offer(command);
            if(Main.exit.equals("q")){
                for(int i=0;i<workQueue.size(); i++){  //проходим по очереди (надо остановить всех производителей)
                    if(command.equals(ThreadPool.this.equals(Main.producer))){ //если пул производителя и он выполняется
                        workQueue.remove(command); //убирает из очереди потоки пула производителя
                        ThreadPool.shutdown();  //завершает пул
                    }
                }
            }
        }
    }

    public static void shutdown() {    // смена флага
        isRunning = false;

    }
    public boolean isRunning(){  //вернет состояние пула
        return isRunning;
    }

}
