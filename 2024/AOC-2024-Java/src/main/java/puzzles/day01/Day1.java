package puzzles.day01;

import core.FileHelpers;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        // Read resource
        FileHelpers fileHelpers = new FileHelpers();
        Scanner reader = fileHelpers.getResourceFile("Day01-Input1.txt");

        List<String> lines = new ArrayList<>();
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
        reader.close();

        int[] array1 = new int[lines.size()];
        int[] array2 = new int[lines.size()];

        for (int index = 0; index < lines.size(); index++) {
            String[] items = lines.get(index).split(" {3}");
            array1[index] = Integer.parseInt(items[0]);
            array2[index] = Integer.parseInt(items[1]);
        }

        int result = calculateDistance(array1, array2);
        System.out.println("Total distance: " + result);

        int secondResult = calculateSimilarity(array1, array2);
        System.out.println("Total similarity: " + secondResult);
    }

    public static int calculateSimilarity(int[] array1, int[] array2) {
        int similarity = 0;
        for (int value : array1) {
            int occurrences = getOccurrences(value, array2);
            similarity += (value * occurrences);
        }

        return similarity;
    }

    public static int getOccurrences(int value, int[] array) {
        int occurrances = 0;
        for (int j : array) {
            if (j == value) {
                occurrances++;
            }
        }
        return occurrances;
    }

    public static int calculateDistance(int[] array1, int[] array2) {
        int totalDistance = 0;
        Arrays.sort(array1);
        Arrays.sort(array2);

        for (int index = 0; index < array1.length; index++) {
            int firstItem = array1[index];
            int secondItem = array2[index];

            if (firstItem == secondItem) {
                continue;
            }

            if (firstItem < secondItem) {
                totalDistance += (secondItem - firstItem);
            } else {
                totalDistance += (firstItem - secondItem);
            }
        }

        return totalDistance;
    }
}
