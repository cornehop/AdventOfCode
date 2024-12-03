package puzzles.day01;

import org.junit.Assert;
import org.junit.Test;

public class Day1Test {
    @Test
    public void calculateDistanceTest() {
        // Arrange
        int[] testArray1 = { 3,4,2,1,3,3 };
        int[] testArray2 = { 4,3,5,3,9,3 };

        // Act
        int result = Day1.calculateDistance(testArray1, testArray2);

        // Assert
        Assert.assertEquals(11, result);
    }

    @Test
    public void calculateSimilarityTest() {
        // Arrange
        int[] testArray1 = { 3,4,2,1,3,3 };
        int[] testArray2 = { 4,3,5,3,9,3 };

        // Act
        int result = Day1.calculateSimilarity(testArray1, testArray2);

        // Assert
        Assert.assertEquals(31, result);
    }
}
