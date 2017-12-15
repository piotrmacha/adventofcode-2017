package pl.piotrmacha.aoc2017.day14;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level2 {
    private static boolean[] grid = new boolean[128 * 128];
    private static List<Integer> visited = new ArrayList<>(128 * 128);
    private static BufferedImage visualisation = new BufferedImage(128,128, BufferedImage.TYPE_4BYTE_ABGR);
    private static BufferedImage rawVisualisation = new BufferedImage(128,128, BufferedImage.TYPE_4BYTE_ABGR);
    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        KnotHash knotHasher = new KnotHash();
        String input = "vbqugkhl";

        for (int i = 0; i < 128; ++i) {
            String rowInput = input + "-" + String.valueOf(i);
            int[] rowHash = knotHasher.hash(rowInput);
            for (int k = 0; k < rowHash.length; k++) {
                int b = rowHash[k];
                for (int j = 0; j < 8; ++j) {
                    int shifted = b >>> j;
                    grid[i * 128 + k * 8 + (7 - j)] = ((shifted & 1) % 2) != 0;
                    if (grid[i * 128 + k * 8 + (7 - j)]) {
                        rawVisualisation.setRGB(k * 8 + (7 - j), i, 0xFF000000);
                    } else {
                        rawVisualisation.setRGB(k * 8 + (7 - j), i, 0xFFFFFFFF);
                    }
                }
            }
        }

        int regions = 0;
        for (int y = 0; y < 128; ++y) {
            for (int x = 0; x < 128; ++x) {
                int pos = y * 128 + x;

                if (visited.contains(pos)) {
                    continue;
                }

                if (grid[pos]) {
                    regions++;
                }
                visit(pos, random.nextInt() | 0xFF000000);

            }
        }

        File output = new File("./14/aoc2017_14/visualisation.png");
        ImageIO.write(visualisation, "png", output);
        File output2 = new File("./14/aoc2017_14/visualisation_raw.png");
        ImageIO.write(rawVisualisation, "png", output2);

        System.out.println(regions);
    }

    private static void visit(int pos, int color) {
        if (visited.contains(pos)) {
            return;
        }
        visited.add(pos);

        boolean square = grid[pos];
        if (!square) {
            visualisation.setRGB(pos % 128, pos / 128, 0xFFFFFFFF);
            return;
        }

        visualisation.setRGB(pos % 128, pos / 128, color);

        if ((pos + 1) % 128 != 0 && pos + 1 >= 0 && pos + 1 < 128 * 128) {
            visit(pos + 1, color);
        }
        if (pos % 128 != 0 && pos - 1 >= 0 && pos - 1 < 128 * 128) {
            visit(pos - 1, color);
        }
        if (pos + 128 >= 0 && pos + 128 < 128 * 128) {
            visit(pos + 128, color);
        }
        if (pos - 128 >= 0 && pos - 128 < 128 * 128) {
            visit(pos - 128, color);
        }
    }

}
