import helpers.FileHelpers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day04 {
    public static void main(String[] args) throws FileNotFoundException {
        FileHelpers fileHelpers = new FileHelpers();
        Scanner reader = fileHelpers.getResourceFile("Day04-input.txt");

        List<String> lines = new ArrayList<>();
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
        reader.close();

        String[] linesArray = lines.toArray(new String[0]);
        char[][] charMap = getCharMap(linesArray);

        int firstResult = getXmasCount(charMap);
        System.out.println("Result part 1: " + firstResult);

        int secondResult = getMasCount(charMap);
        System.out.println("Result part 2: " + secondResult);
    }

    public static int getXmasCount(char[][] charMap) {
        int total = 0;
        for (int i = 0; i < charMap.length; i++) {
            total += getXmasCountForLine(charMap, charMap[i], i);
        }
        return total;
    }

    public static int getMasCount(char[][] charMap) {
        int total = 0;
        for (int i = 1; i < charMap.length - 1; i++) {
            total += getMasCountForLine(charMap, charMap[i], i);
        }
        return total;
    }

    public static int getXmasCountForLine(char[][] charMap, char[] currentLine, int currentLineIndex) {
        int total = 0;
        for(int i = 0; i < currentLine.length; i++) {
            char currentChar = currentLine[i];
            if (currentChar != 'X') {
                continue;
            }

            total += getXmasCountForChar(charMap, currentLine, currentLineIndex, i);
        }
        return total;
    }

    public static int getMasCountForLine(char[][] charMap, char[] currentLine, int currentLineIndex) {
        int total = 0;
        for(int i = 1; i < currentLine.length - 1; i++) {
            char currentChar = currentLine[i];
            if (currentChar != 'A') {
                continue;
            }

            if(getCrossForChar(charMap, currentLine, currentLineIndex, i)) {
                total++;
            }
        }
        return total;
    }

    /// li = the current line index, ci = the current char index (shortened to keep the code readable)
    public static int getXmasCountForChar(char[][] map, char[] line, int li, int ci) {
        int total = 0;
        if (li > 2) {
            // Search up
            if (charsFormXmas(map[li - 1][ci], map[li - 2][ci], map[li - 3][ci])) {
                total++;
            }

            // Search up left
            if (ci > 2 && charsFormXmas(map[li - 1][ci - 1], map[li - 2][ci - 2], map[li - 3][ci - 3])) {
                total++;
            }

            // Search up right
            if (ci < line.length - 3 && charsFormXmas(map[li - 1][ci + 1], map[li - 2][ci + 2], map[li - 3][ci + 3])) {
                total++;
            }
        }

        if (li < map.length - 3) {
            // Search down
            if (charsFormXmas(map[li + 1][ci], map[li + 2][ci], map[li + 3][ci])) {
                total++;
            }

            // Search down left
            if (ci > 2 && charsFormXmas(map[li + 1][ci - 1], map[li + 2][ci - 2], map[li + 3][ci - 3])) {
                total++;
            }

            // Search down right
            if (ci < line.length - 3 && charsFormXmas(map[li + 1][ci + 1], map[li + 2][ci + 2], map[li + 3][ci + 3])) {
                total++;
            }
        }

        // Search left
        if (ci > 2 && charsFormXmas(map[li][ci - 1], map[li][ci - 2], map[li][ci - 3])) {
            total++;
        }

        // Search right
        if (ci < line.length - 3 && charsFormXmas(map[li][ci + 1], map[li][ci + 2], map[li][ci + 3])) {
            total++;
        }
        return total;
    }

    /// li = the current line index, ci = the current char index (shortened to keep the code readable)
    public static boolean getCrossForChar(char[][] map, char[] line, int li, int ci) {
        char topLeft = map[li - 1][ci - 1];
        char topRight = map[li - 1][ci + 1];
        char bottomLeft = map[li + 1][ci - 1];
        char bottomRight = map[li + 1][ci + 1];

        boolean spellsLeftToRight = (topLeft == 'M' && bottomRight == 'S') || (topLeft == 'S' && bottomRight == 'M');
        boolean spellsRightToLeft = (topRight == 'M' && bottomLeft == 'S') || (topRight == 'S' && bottomLeft == 'M');

        return spellsLeftToRight && spellsRightToLeft;
    }

    public static boolean charsFormXmas(char second, char third, char fourth) {
        return second == 'M' && third == 'A' && fourth == 'S';
    }

    public static char[][] getCharMap(String[] lines) {
        char[][] charMap = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            charMap[i] = lines[i].toCharArray();
        }
        return charMap;
    }
}
