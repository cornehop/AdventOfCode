import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day04Test {
    @Test
    public void getCharMapTest() {
        // Arrange
        String[] lines = {
                "MMMSXXMASM",
                "MSAMXMSMSA",
                "AMXSXMAAMM",
                "MSAMASMSMX",
                "XMASAMXAMM",
                "XXAMMXXAMA",
                "SMSMSASXSS",
                "SAXAMASAAA",
                "MAMMMXMMMM",
                "MXMXAXMASX"
        };

        // Act
        char[][] charMap = Day04.getCharMap(lines);

        // Assert
        Assertions.assertEquals('M', charMap[0][0]);
    }

    @Test
    public void getXmasCountTest() {
        // Arrange
        String[] lines = {
                "MMMSXXMASM",
                "MSAMXMSMSA",
                "AMXSXMAAMM",
                "MSAMASMSMX",
                "XMASAMXAMM",
                "XXAMMXXAMA",
                "SMSMSASXSS",
                "SAXAMASAAA",
                "MAMMMXMMMM",
                "MXMXAXMASX"
        };
        char[][] charMap = Day04.getCharMap(lines);

        // Act
        int result = Day04.getXmasCount(charMap);

        // Assert
        Assertions.assertEquals(18, result);
    }

    @Test
    public void getMasCountTest() {
        // Arrange
        String[] lines = {
                "MMMSXXMASM",
                "MSAMXMSMSA",
                "AMXSXMAAMM",
                "MSAMASMSMX",
                "XMASAMXAMM",
                "XXAMMXXAMA",
                "SMSMSASXSS",
                "SAXAMASAAA",
                "MAMMMXMMMM",
                "MXMXAXMASX"
        };
        char[][] charMap = Day04.getCharMap(lines);

        // Act
        int result = Day04.getMasCount(charMap);

        // Assert
        Assertions.assertEquals(9, result);
    }
}
