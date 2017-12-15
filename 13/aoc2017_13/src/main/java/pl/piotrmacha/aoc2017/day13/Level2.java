package pl.piotrmacha.aoc2017.day13;

import java.util.Collection;
import java.util.Map;

public class Level2
{
    public static void main( String[] args )
    {
        try {

            Map<Integer, Scanner> scanners = DataLoader.load("data");

            int max = 0;
            for (Scanner scanner : scanners.values()) {
                max = Math.max(max, scanner.layer + 2);
            }

            int delay = 0;
            boolean caught = true;
            int playerPos;
            for (; caught; delay++) {
                caught = false;

                for (Scanner scanner : scanners.values()) {
                    scanner.reset();

                    if (delay == 0) {
                        break;
                    }

                    // setup scanner starting position using Mathematics™
                    int position;
                    int directionIndicator = Math.floorDiv(delay, scanner.range - 1);
                    int reminder = Math.floorMod(delay, scanner.range - 1);

                    if ((directionIndicator % 2) == 0) {
                        position = reminder + 1;
                    } else {
                        position = scanner.range - reminder;
                        scanner.direction = Scanner.Direction.UP;
                    }

                    scanner.currentPos = position;
                }
                playerPos = 0;

                for (; playerPos < max;) {
                    for (Scanner scanner : scanners.values()) {
                        if (scanner.detected(playerPos)) {
                            caught = true;
                            break;
                        }
                        scanner.updatePos();
                    }


                    if (caught) {
                        break;
                    }
                    playerPos++;
                }

                System.out.println("delay = " + delay);
            }

            System.out.println(delay - 1);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printState(int playerPos, Map<Integer, Scanner> scannerList, int delay) {
        System.out.println(String.format("=== delay = %d, position = %d", delay, playerPos));
        int max = 0;
        int maxDepth = 0;
        for (Scanner scanner : scannerList.values()) {
            max = Math.max(max, scanner.layer + 1);
            maxDepth = Math.max(maxDepth, scanner.range);
        }

        for (int i = 0; i < max; ++i) {
            System.out.print(" " + i + "  ");
        }
        System.out.println();
        for (int j = 0; j < maxDepth; ++j) {
            for (int i = 0; i < max; ++i) {
                if (!scannerList.containsKey(i)) {
                    System.out.print("... ");
                    continue;
                }
                Scanner scanner = scannerList.get(i);

                if (scanner.layer.equals(playerPos)) {
                    System.out.print("(");
                } else {
                    System.out.print("[");
                }

                if (scanner.currentPos.equals(j + 1)) {
                    System.out.print("S");
                } else {
                    System.out.print(" ");
                }

                if (scanner.layer.equals(playerPos)) {
                    System.out.print(")");
                } else {
                    System.out.print("]");
                }

                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
