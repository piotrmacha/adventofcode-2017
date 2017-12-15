package pl.piotrmacha.aoc2017.day15;

public class Level1 {
    public static void main(String[] args) {
        new Level1().run();
    }

    private void run() {
        Generator A = new Generator(116, 16807);
        Generator B = new Generator(299, 48271);

        int judgeCount = 0;
        for (int i = 0; i < 40_000_000; ++i) {
            int a = A.next();
            int b = B.next();
            if (((a ^ b) & 0x0000FFFF) == 0) {
                judgeCount++;
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
