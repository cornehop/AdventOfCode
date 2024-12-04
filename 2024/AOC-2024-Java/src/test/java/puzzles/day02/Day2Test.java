package puzzles.day02;

import core.Enums.reportDirection;

import org.junit.Assert;
import org.junit.Test;

public class Day2Test {
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
        int result = Day2.getSafeReportCount(testInput,false);

        // Assert
        Assert.assertEquals(2, result);
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
        int result = Day2.getSafeReportCount(testInput, true);

        // Assert
        Assert.assertEquals(5, result);
    }

    @Test
    public void reportIsSafe_Returns_True_When_Safe() {
        // Arrange
        int[] report = {7,6,4,2,1};

        // Act
        boolean result = Day2.reportIsSafe(report, false);

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void reportIsSafe_Returns_False_When_Not_Safe() {
        // Arrange
        int[] report = {1,2,7,8,9};

        // Act
        boolean result = Day2.reportIsSafe(report, false);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void reportIsSafe_Returns_False_When_No_Increase_Or_Decrease() {
        // Arrange
        int[] report = {8,6,4,4,1};

        // Act
        boolean result = Day2.reportIsSafe(report, false);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void getReportDirection() {
        // Arrange
        int[] report = {1,2,3,4,5};

        // Act
        reportDirection result = Day2.getReportDirection(report);

        // Assert
        Assert.assertEquals(reportDirection.increasing, result);
    }

    @Test
    public void getReportDirection_Should_Return_Decreasing() {
        // Arrange
        int[] report = {5,4,3,2,1};

        // Act
        reportDirection result = Day2.getReportDirection(report);

        // Assert
        Assert.assertEquals(reportDirection.decreasing, result);
    }

    @Test
    public void getReportDirection_Should_Return_Non() {
        // Arrange
        int[] report = {1,1,2,3,4};

        // Act
        reportDirection result = Day2.getReportDirection(report);

        // Assert
        Assert.assertEquals(reportDirection.non, result);
    }
}
