package pl.piotrmacha.aoc2017.day16;

import java.util.Collection;

class Level1 {
    public static void main(String[] args) throws Exception {
        new Level1().run();
    }

    private void run() throws Exception {
        DancingCircularList list = new DancingCircularList(16);
        fillList(list);

        DataLoader dataLoader = new DataLoader();
        Collection<Move> moves = dataLoader.load("data");

        for (Move move : moves) {
            move.execute(list);
        }

        System.out.println(list.asString());
    }

    private void fillList(DancingCircularList list) {
        for (char c = 'a'; c <= 'p'; ++c) {
            list.set(c - 'a', c);
        }
    }
}
