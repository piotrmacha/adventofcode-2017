package pl.piotrmacha.aoc2017.day17;

import java.util.LinkedList;

public class Level1 {
    public static void main(String[] args) {
        new Level1().run();
    }

    void run() {
        int step = 335;

        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(0, 0);

        int pointer = 0;
        for (int i = 1; i <= 2017; ++i) {
            pointer = (pointer + step) % list.size() + 1;
            list.add(pointer, i);
        }

        System.out.println(list.get(pointer + 1));
    }
}
