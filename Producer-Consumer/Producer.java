package com.pr3OS;

import java.util.Random;

public class Producer extends Thread
{
    private PCQueue queue;

    public Producer(PCQueue queue){
        this.queue = queue;
    }

    @Override
    public void run()
    {
        Random random = new Random();
        while (!Main.exit.equals("q")) {
            queue.addElement(random.nextInt(100) + 1); //добавляем случайный элемент от 1 до 100
            try {
                Thread.sleep(random.nextInt(1000)+500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Thread.currentThread().isInterrupted()){
                break;
            }
        }
    }
}

