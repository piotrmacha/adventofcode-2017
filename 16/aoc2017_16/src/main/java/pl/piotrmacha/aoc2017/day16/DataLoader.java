package pl.piotrmacha.aoc2017.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class DataLoader {
    Collection<Move> load(String dataSet) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(DataLoader.class.getResourceAsStream("/" + dataSet + ".txt"))
        );

        StringBuilder buffer = new StringBuilder();
        List<Move> list = new ArrayList<>();
        int c;
        while ((c = reader.read()) != -1) {
            if (c == ',') {
                parseBuffer(list, buffer);
                buffer.delete(0, buffer.length());
                continue;
            }
            buffer.append(String.valueOf((char)c));
        }
        parseBuffer(list, buffer);

        return list;
    }

    private void parseBuffer(List<Move> list, StringBuilder buffer) throws Exception {
        String rawMove = buffer.toString();

        Move move;
        switch (rawMove.charAt(0)) {
            case 's':
                move = new SpinMove(Integer.parseInt(rawMove.substring(1, rawMove.length())));
                break;
            case 'x':
                String[] positions = rawMove.substring(1, rawMove.length()).split("/");
                move = new ExchangeMove(
                        Integer.parseInt(positions[0]),
                        Integer.parseInt(positions[1])
                );
                break;
            case 'p':
                String[] programs = rawMove.substring(1, rawMove.length()).split("/");
                move = new PartnerMove(
                        programs[0].charAt(0),
                        programs[1].charAt(0)
                );
                break;
            default:
                throw new Exception();
        }
        list.add(move);
    }
}
