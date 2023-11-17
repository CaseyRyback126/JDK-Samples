package TreadExs;

import java.util.concurrent.CountDownLatch;

public class Runner implements Runnable{
    private final int id;
    private final CountDownLatch startSignal;
    private final CountDownLatch finishSignal;

    public Runner(int id, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.id = id;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await(); // ждем сигнала старта
            System.out.println("Бегун " + id + " начал гонку");
            Thread.sleep((long) (Math.random() * 5000 + 1000)); // имитация бега
            System.out.println("Бегун " + id + " закончил гонку");
            finishSignal.countDown(); // сообщаем о финише
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
