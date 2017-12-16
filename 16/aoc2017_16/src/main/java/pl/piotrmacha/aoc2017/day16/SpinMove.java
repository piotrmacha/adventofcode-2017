package pl.piotrmacha.aoc2017.day16;

class SpinMove implements Move {
    private int spin;

    SpinMove(int spin) {
        this.spin = spin;
    }

    @Override
    public void execute(DancingCircularList list) {
        list.spin(spin);
    }
}
