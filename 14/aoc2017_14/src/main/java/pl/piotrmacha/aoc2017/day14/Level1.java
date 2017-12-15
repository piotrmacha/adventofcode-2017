package pl.piotrmacha.aoc2017.day14;


public class Level1
{
    public static void main( String[] args )
    {
        KnotHash knotHasher = new KnotHash();
        String input = "vbqugkhl";

        int usedSquares = 0;
        for (int i = 0; i < 128; ++i) {
            String rowInput = input + "-" + String.valueOf(i);
            int[] rowHash = knotHasher.hash(rowInput);
            for (int b : rowHash) {
                for (int j = 0; j < 8; ++j) {
                    int shifted = b >>> j;
                    if ((shifted % 2) != 0) {
                        usedSquares++;
                    }
                }
            }
        }
        System.out.println(usedSquares);
    }
}
