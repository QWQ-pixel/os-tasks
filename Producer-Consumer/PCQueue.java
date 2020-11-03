package com.pr3OS;

import java.util.LinkedList;
import java.util.Queue;

public class PCQueue
{
    private Queue<Integer> queue;
    private int storageSize;

    private int elementsCount;

    public PCQueue(int maxSize, int elementsCount){ //макс размер 200, счет элементов 100
        queue = new LinkedList<Integer>();
        this.storageSize = maxSize;
        this.elementsCount = elementsCount;
    }
    public synchronized void addElement(int x){ //добавляем элементы
        while (queue.size() >= elementsCount){   //если в очереди элементов больше или равно 100, то ждем
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

            queue.add(x); //добавляем элемент
            System.out.println(Thread.currentThread().getName() + " add " + x); //выводим поток и элемент
            notifyAll(); //сообщаем остальным, что добавили элемент
        try {
            wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void getElement(){  //берем элементы

        while (queue.size() == 0){  //если в очереди нет элементов, ждем
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                System.out.println("wait exception...");
            }
        }

        System.out.println(Thread.currentThread().getName() + " get " + queue.remove()); //удаляем элемент, выводим поток и элемент
        notifyAll(); //сообщаем остальным потокам, что забрали элемент

        try {
            wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public synchronized int getQueueSize(){  //возвращает размер очереди
        return queue.size();
    }

}

