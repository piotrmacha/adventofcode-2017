package pl.piotrmacha.aoc2017.day16;

public class ExchangeMove implements Move {
    private final int i;
    private final int j;

    ExchangeMove(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public void execute(DancingCircularList list) {
        list.exchange(i, j);
    }
}
