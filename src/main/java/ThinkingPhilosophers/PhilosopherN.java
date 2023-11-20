package ThinkingPhilosophers;

import java.util.concurrent.locks.Lock;

public class PhilosopherN extends Thread{
    private final String name;
    private final Lock leftFork;
    private final Lock rightFork;

    private int mealsEaten;

    public PhilosopherN(String name, Lock leftFork, Lock rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void run() {
        while(mealsEaten < 3) {
            think();
            eat();
        }
    }

    private void think() {
        System.out.println(name + " думает");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void eat() {
        if (name.equals("Философ 1")) {
            acquireLocks(rightFork, leftFork);
        } else {
            acquireLocks(leftFork, rightFork);
        }
        System.out.println(name + " ест");
        mealsEaten++;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        rightFork.unlock();
        leftFork.unlock();
    }

    private void acquireLocks(Lock first, Lock second) {
        first.lock();
        second.lock();
    }
}
