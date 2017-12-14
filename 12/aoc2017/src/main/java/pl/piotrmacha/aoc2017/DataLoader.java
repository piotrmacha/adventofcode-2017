package pl.piotrmacha.aoc2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class DataLoader {
    static Map<Integer, Program> load(String name) throws IOException {
        InputStream file = DataLoader.class.getResourceAsStream("/" + name + ".txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        String line;
        Map<Integer, Program> programs = new HashMap<>();
        while (null != (line = reader.readLine())) {
            String[] parts1 = line.split(" <-> ");

            Integer id = Integer.parseInt(parts1[0]);
            List<Integer> pipes = Arrays.stream(parts1[1].split(", "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            Program program = new Program();
            program.nodeId = id;
            program.connections = pipes;

            programs.put(id, program);
        }
        return programs;
    }
}
