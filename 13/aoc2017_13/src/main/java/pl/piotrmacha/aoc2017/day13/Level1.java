package pl.piotrmacha.aoc2017.day13;

import java.util.Map;

public class Level1
{
    public static void main( String[] args )
    {
        try {

            Map<Integer, Scanner> scanners = DataLoader.load("data");

            int playerPos = 0;
            int max = 0;
            for (Scanner scanner : scanners.values()) {
                max = Math.max(max, scanner.layer);
            }
            int severity = 0;

            for (int i = 0; i < max; ++i) {
                for (Scanner scanner : scanners.values()) {
                    if (scanner.detected(playerPos)) {
                        severity += scanner.layer * scanner.range;
                    }
                    scanner.updatePos();
                }
                playerPos++;
            }

            System.out.println(severity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
