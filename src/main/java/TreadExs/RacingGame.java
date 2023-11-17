package TreadExs;

import java.util.concurrent.CountDownLatch;

public class RacingGame {


    public static void main(String[] args) throws InterruptedException {
        int numRunners = 3;
        CountDownLatch startSignal = new CountDownLatch(1); // сигнал старта
        CountDownLatch finishSignal = new CountDownLatch(numRunners); // сигнал финиша

        // Создаем и запускаем потоки-бегунов
        for (int i = 1; i <= numRunners; i++) {
            Thread runner = new Thread(new Runner(i, startSignal, finishSignal));
            runner.start();
        }

        System.out.println("На старт!");
        Thread.sleep(1000); // задержка для имитации команды "Внимание"

        System.out.println("Внимание!");
        Thread.sleep(1000); // задержка для имитации команды "Марш"

        System.out.println("Марш!");
        startSignal.countDown();
        finishSignal.await(); // ждем, пока все потоки-бегуны не закончат гонку

        System.out.println("Гонка закончена!");
    }
}




