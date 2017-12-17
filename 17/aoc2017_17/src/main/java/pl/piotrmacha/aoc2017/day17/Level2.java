package pl.piotrmacha.aoc2017.day17;

public class Level2 {
    public static void main(String[] args) {
        new Level2().run();
    }

    void run() {
        int step = 335;

        int pointer = 0;
        int result = 0;
        for (int i = 0; i <= 50_000_000; ++i) {
            pointer = (pointer + step + 1) % (i + 1);
            if (pointer == 0) {
                result = i + 1;
            }
        }

        System.out.println(result);
    }
}
