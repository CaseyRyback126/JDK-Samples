package EatingPhilosophers;

public class Main {
    public static void main(String[] args) {
        Chopstick[] chopsticks = new Chopstick[5];

        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Chopstick();
        }

        for (int i = 0; i < 5; i++) {
            new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % 5]).start();
        }
    }
}
