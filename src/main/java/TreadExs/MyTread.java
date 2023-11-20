package TreadExs;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyTread {
    public static void main(String[] args) {
        AtomicBoolean switcher = new AtomicBoolean(true);

        Thread threadA = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Задержка 1000 миллисекунд (1 секунда)
                    switcher.set(!switcher.get()); // Меняем значение переменной switcher на противоположное
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                if (switcher.get()) { // Проверяем значение переменной switcher
                    for (int i = 100; i >= 0; i--) {
                        System.out.println(i);
                        try {
                            Thread.sleep(100); // Задержка 100 миллисекунд
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break; // Выходим из цикла, как только switcher станет false
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}

