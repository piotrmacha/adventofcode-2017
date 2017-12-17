package pl.piotrmacha.aoc2017.day16;

import java.util.HashMap;
import java.util.Map;

class DancingCircularList {
    private final int capacity;
    private final char[] list;
    private final Map<Character, Integer> reverseIndex;

    DancingCircularList(int capacity) {
        this.capacity = capacity;
        list = new char[capacity];
        reverseIndex = new HashMap<>(capacity);
    }

    void set(int index, char element) {
        int i = index % capacity;
        reverseIndex.put(element, i);
        list[i] = element;
    }

    char get(int index) {
        return list[index % capacity];
    }

    void spin(int moves) {
        char[] copy = new char[capacity];
        System.arraycopy(list, 0, copy, 0, capacity);
        for (int i = 0; i < copy.length; i++) {
            set(i + moves, copy[i]);
        }
    }

    void exchange(int i, int j) {
        char tmp = get(i);
        set(i, get(j));
        set(j, tmp);
    }

    void partner(char a, char b) {
        exchange(reverseIndex.get(a), reverseIndex.get(b));
    }

    String asString() {
        StringBuilder buffer = new StringBuilder();
        for (char c : list) {
            buffer.append(String.valueOf(c));
        }
        return buffer.toString();
    }
}
