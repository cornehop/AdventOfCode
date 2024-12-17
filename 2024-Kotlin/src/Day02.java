import enums.reportDirection;
import helpers.FileHelpers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day02 {
    public static void main(String[] args) throws FileNotFoundException {
        // Read resource
        FileHelpers fileHelpers = new FileHelpers();
        Scanner reader = fileHelpers.getResourceFile("Day02-Input.txt");

        List<String> lines = new ArrayList<>();
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
        reader.close();

        String[] reportArray = lines.toArray(new String[0]);

        int safeReports = getSafeReportCount(reportArray, false);
        System.out.println("Total safe reports: " + safeReports);

        int safeReportsWithSafetySystem = getSafeReportCount(reportArray, true);
        System.out.println("Total safe reports with safety system: " + safeReportsWithSafetySystem);
    }

    public static int getSafeReportCount(String[] input, boolean safetySystemAvailable) {
        int count = 0;

        for (String row : input) {
            List<String> stringValues = Arrays.asList(row.split(" "));
            int[] values = stringValues.stream().flatMapToInt(num -> IntStream.of(Integer.parseInt(num))).toArray();
            boolean isSafe = reportIsSafe(values, safetySystemAvailable);
            if (isSafe) {
                count++;
            }
        }

        return count;
    }

    public static boolean reportIsSafe(int[] values, boolean safetySystemAvailable) {
        reportDirection direction = getReportDirection(values);
        boolean reportIsSafe = true;
        for (int i = 0; i < values.length - 1; i++) {
            if (!isRightDirection(values[i], values[i + 1], direction)) {
                reportIsSafe = false;
                break;
            }

            if (!isDifferenceIsNotTooBig(values[i], values[i + 1], direction)) {
                reportIsSafe = false;
                break;
            }
        }

        if (!reportIsSafe && safetySystemAvailable) {
            for (int i = 0; i < values.length; i++) {
                int[] modifiedArray = removeValueAtIndex(values, i);
                if (reportIsSafe(modifiedArray, false)) {
                    reportIsSafe = true;
                    break;
                }
            }
        }

        return reportIsSafe;
    }

    public static boolean isRightDirection(int level1, int level2, reportDirection direction) {
        if (direction == reportDirection.increasing) {
            return level1 < level2;
        }

        return level1 > level2;
    }

    public static boolean isDifferenceIsNotTooBig(int level1, int level2, reportDirection direction) {
        int difference;
        if (direction == reportDirection.increasing) {
            difference = level2 - level1;
        } else {
            difference = level1 - level2;
        }
        return difference <= 3;
    }

    public static reportDirection getReportDirection(int[] values) {
        if (values[0] == values[1]) {
            return reportDirection.non;
        }

        if (values[0] < values[1]) {
            return reportDirection.increasing;
        }

        return reportDirection.decreasing;
    }

    public static int[] removeValueAtIndex(int[] array, int index) {
        int[] returnArray = new int[array.length - 1];
        for (int i = 0, k = 0; i < array.length; i++) {
            if (i == index) {
                continue;
            }

            returnArray[k++] = array[i];
        }

        return returnArray;
    }
}
