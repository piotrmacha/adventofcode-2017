package pl.piotrmacha.aoc2017.day16;

import java.util.HashMap;
import java.util.Map;

class DancingCircularList {
    private final int capacity;
    private final char[] list;
    private final Map<Character, Integer> reverseIndex;

    private int spin = 0;

    DancingCircularList(int capacity) {
        this.capacity = capacity;
        list = new char[capacity];
        reverseIndex = new HashMap<>(capacity);
    }

    void set(int index, char element) {
        int i = unspinIndex(index);
        reverseIndex.put(element, i);
        list[i] = element;
    }

    char get(int index) {
        return list[unspinIndex(index)];
    }

    void spin(int moves) {
        spin += moves;
    }

    void exchange(int i, int j) {
        char tmp = get(i);
        set(i, get(j));
        set(j, tmp);
    }

    void partner(char a, char b) {
        exchange(reverseIndex.get(a), reverseIndex.get(b));
    }

    private int unspinIndex(int i) {
        return Math.floorMod(i - spin, capacity);
    }

    String asString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            char c = list[unspinIndex(i)];
            buffer.append(c);
        }
        return buffer.toString();
    }
}
