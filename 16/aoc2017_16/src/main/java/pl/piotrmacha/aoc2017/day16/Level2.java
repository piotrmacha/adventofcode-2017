package pl.piotrmacha.aoc2017.day16;

import java.util.Collection;

class Level2 {
    public static void main(String[] args) throws Exception {
        new Level2().run();
    }

    private void run() throws Exception {
        DancingCircularList list = new DancingCircularList(16);
        fillList(list);

        DataLoader dataLoader = new DataLoader();
        Collection<Move> moves = dataLoader.load("data");

        for (int i = 0; i < 1_000_000_000; ++i) {
            for (Move move : moves) {
                move.execute(list);
            }

            System.out.print(String.format(
                    "\r%d / %d",
                    i,
                    1_000_000_000
            ));
        }

        System.out.println(list.asString());
    }

    private void fillList(DancingCircularList list) {
        for (char c = 'a'; c <= 'p'; ++c) {
            list.set(c - 'a', c);
        }
    }
}
