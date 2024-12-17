import helpers.FileHelpers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static void main(String[] args) throws FileNotFoundException {
        // Read resource
        FileHelpers fileHelpers = new FileHelpers();
        Scanner reader = fileHelpers.getResourceFile("Day03-Input.txt");

        List<String> lines = new ArrayList<>();
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
        reader.close();

        StringBuilder input = new StringBuilder();
        for (String line : lines) {
            input.append(line);
        }

        int total = getMulResult(input.toString(), false);
        System.out.println("Result part 1: " + total);

        int total2 = getMulResult(input.toString(), true);
        System.out.println("Result part 2: " + total2);
    }

    public static int getMulResult(String mul, boolean isSecondCalculation) {
        boolean isBlocked = false;
        int total = 0;
        String regex = isSecondCalculation ? "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)" : "mul\\((\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mul);

        while (matcher.find()) {
            if (matcher.group(0).equals("do()")) {
                isBlocked = false;
                continue;
            }

            if (matcher.group(0).equals("don't()")) {
                isBlocked = true;
                continue;
            }

            if (!isBlocked) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                total += x*y;
            }
        }

        return total;
    }
}
