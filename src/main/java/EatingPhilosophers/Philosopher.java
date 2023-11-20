package EatingPhilosophers;

public class Philosopher extends Thread {
    private final int id;
    private final Chopstick left;
    private final Chopstick right;

    public Philosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            think();
            eat();
        }
    }

    private void think() {
        System.out.println("Философ " + (id + 1) + " думает");
    }

    private void eat() {
        try {
            left.acquire();
            right.acquire();
        } catch (InterruptedException ignored) {
        }

        System.out.println("Философ " + (id + 1) + " ест");

        left.release();
        right.release();
    }
}
