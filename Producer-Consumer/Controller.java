package com.pr3OS;

import java.io.IOException;


import static com.pr3OS.Main.*;

public class Controller extends Thread{

    private String name;
    private volatile boolean isRunning = true; //флаг

    public Controller(String name){
        this.name=name;
    }

    @Override
    public void run(){
        while (isRunning()) {
            try {
                exit = Main.reader.readLine();
                while (exit.equals("q")){
                    if(!Main.producer.isRunning()&&!Main.consumer.isRunning()&& queue.getQueueSize()==0){ //если все условия выполнены
                        System.out.println(" END "); //сообщит об окончании
                        setRunning(false); //смена флага
                        exit=" ";
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
