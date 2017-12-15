package pl.piotrmacha.aoc2017.day15;

import java.util.concurrent.ArrayBlockingQueue;

public class Level2 {
    public static void main(String[] args) {
        new Level2().run();
    }

    private void run() {
        Generator A = new Generator(116, 16807);
        Generator B = new Generator(299, 48271);

        int judgeCount = 0;
        ArrayBlockingQueue<Integer> queueA = new ArrayBlockingQueue<>(10_000_000);
        for (int i = 0; i < 5_000_000; ) {
            int a = A.next();
            int b = B.next();

            boolean shouldCheckQueue = false;

            if (a % 4 == 0) {
                queueA.add(a);
            }
            if (b % 8 == 0) {
                shouldCheckQueue = true;
            }

            if (shouldCheckQueue) {
                if (queueA.size() == 0) {
                    continue;
                }
                i++;
                a = queueA.poll();

                if (((a ^ b) & 0x0000FFFF) == 0) {
                    judgeCount++;
                }
            }
        }

        System.out.println(judgeCount);
    }

    private class Generator {
        // value is long because it can go over INT_MAX after multiplying
        private long value;
        private final int factor;

        Generator(int value, int factor) {
            this.value = value;
            this.factor = factor;
        }

        int next() {
            value = (value * factor) % 2147483647;
            return (int)value;
        }
    }
}
