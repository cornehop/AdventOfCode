package puzzles;

import org.junit.Assert;
import org.junit.Test;

public class Day01Test {
    @Test
    public void calculateDistanceTest() {
        // Arrange
        int[] testArray1 = { 3,4,2,1,3,3 };
        int[] testArray2 = { 4,3,5,3,9,3 };

        // Act
        int result = Day01.calculateDistance(testArray1, testArray2);

        // Assert
        Assert.assertEquals(11, result);
    }

    @Test
    public void calculateSimilarityTest() {
        // Arrange
        int[] testArray1 = { 3,4,2,1,3,3 };
        int[] testArray2 = { 4,3,5,3,9,3 };

        // Act
        int result = Day01.calculateSimilarity(testArray1, testArray2);

        // Assert
        Assert.assertEquals(31, result);
    }
}
