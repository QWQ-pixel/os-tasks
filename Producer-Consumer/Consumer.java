package com.pr3OS;
import java.util.Random;

public class Consumer extends Thread
{
    private PCQueue queue;

    public Consumer(PCQueue queue){
        this.queue = queue;
    }

    @Override
    public void run()
    {
        Random random = new Random();
        while (queue.getQueueSize()!=0) { //пока размер очереди не станет равен 0
                queue.getElement(); //забираем элемент
            try {
               sleep(random.nextInt(1000)+500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(queue.getQueueSize()==0){
                ThreadPool.shutdown(); //если все элементы забрали, завершаем пул потоков
            }

        }

    }
}


