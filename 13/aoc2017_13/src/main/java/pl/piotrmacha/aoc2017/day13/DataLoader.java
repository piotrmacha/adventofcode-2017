package pl.piotrmacha.aoc2017.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

class DataLoader {
    static Map<Integer, Scanner> load(String name) throws IOException {
        InputStream file = DataLoader.class.getResourceAsStream("/" + name + ".txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        String line;
        Map<Integer, Scanner> programs = new HashMap<>();
        while (null != (line = reader.readLine())) {
            String[] parts1 = line.split(": ");

            Integer layer = Integer.parseInt(parts1[0]);
            Integer range = Integer.parseInt(parts1[1]);

            Scanner scanner = new Scanner();
            scanner.layer = layer;
            scanner.range = range;

            programs.put(layer, scanner);
        }
        return programs;
    }
}
