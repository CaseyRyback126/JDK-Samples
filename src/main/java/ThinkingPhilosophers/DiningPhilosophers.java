package ThinkingPhilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    public static void main(String[] args) {
        Lock[] forks = new ReentrantLock[5];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new ReentrantLock();
        }

        PhilosopherN[] philosophers = new PhilosopherN[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new PhilosopherN("Философ " + (i + 1), forks[i], forks[(i + 1) % 5]);
            philosophers[i].start();
        }

        for (PhilosopherN philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("Все философы пообедали");

    }
}
