package pl.piotrmacha.aoc2017.day16;

public class PartnerMove implements Move {
    private final char a;
    private final char b;

    PartnerMove(char a, char b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute(DancingCircularList list) {
        try {
            list.partner(a, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
