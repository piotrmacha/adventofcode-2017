package pl.piotrmacha.aoc2017.day13;

class Scanner {
    Integer layer;
    Integer range;

    Integer currentPos = 1;
    Direction direction = Direction.DOWN;

    void updatePos() {
        switch (direction) {
            case UP:
                if (currentPos <= 1) {
                    currentPos++;
                    direction = Direction.DOWN;
                    break;
                }
                currentPos--;
                break;
            case DOWN:
                if (currentPos >= range) {
                    currentPos--;
                    direction = Direction.UP;
                    break;
                }
                currentPos++;
                break;
        }
    }

    boolean detected(int pos) {
        return layer.equals(pos) && currentPos.equals(1);
    }

    void reset() {
        currentPos = 1;
        direction = Direction.DOWN;
    }

    enum Direction {
        UP, DOWN
    }
}
