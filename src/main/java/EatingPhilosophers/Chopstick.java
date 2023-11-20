package EatingPhilosophers;

import java.util.concurrent.Semaphore;

public class Chopstick {
    private final Semaphore semaphore;

    public Chopstick() {
        semaphore = new Semaphore(1);
    }

    public void acquire() throws InterruptedException {
        semaphore.acquire();
    }

    public void release() {
        semaphore.release();
    }
}
