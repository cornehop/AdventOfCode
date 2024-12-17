import enums.reportDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day02Test {
    @Test
    public void getSafeReportCountTest() {
        // Arrange
        String[] testInput = {
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9"
        };

        // Act
        int result = Day02.getSafeReportCount(testInput,false);

        // Assert
        Assertions.assertEquals(2, result);
    }

    @Test
    public void getSafeReportCountWithSafetySystemTest() {
        // Arrange
        String[] testInput = {
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9",
                "1 1 3 4 5"
        };

        // Act
        int result = Day02.getSafeReportCount(testInput, true);

        // Assert
        Assertions.assertEquals(5, result);
    }

    @Test
    public void reportIsSafe_Returns_True_When_Safe() {
        // Arrange
        int[] report = {7,6,4,2,1};

        // Act
        boolean result = Day02.reportIsSafe(report, false);

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    public void reportIsSafe_Returns_False_When_Not_Safe() {
        // Arrange
        int[] report = {1,2,7,8,9};

        // Act
        boolean result = Day02.reportIsSafe(report, false);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void reportIsSafe_Returns_False_When_No_Increase_Or_Decrease() {
        // Arrange
        int[] report = {8,6,4,4,1};

        // Act
        boolean result = Day02.reportIsSafe(report, false);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void getReportDirection() {
        // Arrange
        int[] report = {1,2,3,4,5};

        // Act
        reportDirection result = Day02.getReportDirection(report);

        // Assert
        Assertions.assertEquals(reportDirection.increasing, result);
    }

    @Test
    public void getReportDirection_Should_Return_Decreasing() {
        // Arrange
        int[] report = {5,4,3,2,1};

        // Act
        reportDirection result = Day02.getReportDirection(report);

        // Assert
        Assertions.assertEquals(reportDirection.decreasing, result);
    }

    @Test
    public void getReportDirection_Should_Return_Non() {
        // Arrange
        int[] report = {1,1,2,3,4};

        // Act
        reportDirection result = Day02.getReportDirection(report);

        // Assert
        Assertions.assertEquals(reportDirection.non, result);
    }
}
