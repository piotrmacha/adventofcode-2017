package pl.piotrmacha.aoc2017.day16;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Level2 {
    public static void main(String[] args) throws Exception {
        new Level2().run();
    }

    private void run() throws Exception {
        DancingCircularList list = new DancingCircularList(16);
        fillList(list);

        DataLoader dataLoader = new DataLoader();
        Collection<Move> moves = dataLoader.load("data");
        String initial = list.asString();

        System.out.println(initial);
        List<String> history = new ArrayList<>();
        history.add(initial);
        for (int i = 0; i < 1_000_000_000; ++i) {
            for (Move move : moves) {
                move.execute(list);
            }

            System.out.println(String.format("%d - %s", i + 1, list.asString()));
            if (list.asString().equals(initial)) {
                history.add(list.asString());
                break;
            }
            history.add(list.asString());
        }

        System.out.println(history.get(1_000_000_000 % (history.size() + 1)));
    }

    private void fillList(DancingCircularList list) {
        for (char c = 'a'; c <= 'p'; ++c) {
            list.set(c - 'a', c);
        }
    }
}
