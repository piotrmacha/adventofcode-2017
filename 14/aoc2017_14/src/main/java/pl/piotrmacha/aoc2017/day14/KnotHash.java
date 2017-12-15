package pl.piotrmacha.aoc2017.day14;

import java.util.ArrayList;
import java.util.List;

public class KnotHash {
    public int[] hash(String input) {
        int[] vector = prepareVector(256);
        int[] lengths = prepareInput(input);

        int pos = 0;
        int skip = 0;
        for (int r = 0; r < 64; ++r) {
            for (int length : lengths) {
                for (int i = 0; i < length / 2; ++i) {
                    int current = Math.floorMod(i + pos, vector.length);
                    int opposite = Math.floorMod(length - i - 1 + pos, vector.length);

                    int tmp = vector[opposite];
                    vector[opposite] = vector[current];
                    vector[current] = tmp;
                }
                pos += length + skip;
                skip++;
            }
        }

        int[] denseVector = prepareVector(16);
        for (int i = 0; i < 16; ++i) {
            int xor = vector[i * 16];
            for (int j = 1; j < 16; ++j) {
                xor ^= vector[i * 16 + j];
            }
            denseVector[i] = xor;
        }

        return denseVector;
    }

    private int[] prepareInput(String input) {
        int[] inputChars = input.chars().toArray();
        int[] append = {17, 31, 73, 47, 23};
        List<Integer> output = new ArrayList<>(inputChars.length + append.length);
        for (int x : inputChars) {
            output.add(x);
        }
        for (int x : append) {
            output.add(x);
        }
        return output.stream().mapToInt(i -> i).toArray();
    }

    private int[] prepareVector(int size) {
        int[] list = new int[size];
        for (int i = 0; i < size; ++i) {
            list[i] = i;
        }
        return list;
    }

    public static String asString(int[] vector) {
        StringBuilder builder = new StringBuilder();
        for (int c : vector) {
            builder.append(String.format("%02x", c));
        }
        return builder.toString();
    }
}
