import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day01Test {
    @Test
    public void calculateDistanceTest() {
        // Arrange
        int[] testArray1 = { 3,4,2,1,3,3 };
        int[] testArray2 = { 4,3,5,3,9,3 };

        // Act
        int result = Day01.calculateDistance(testArray1, testArray2);

        // Assert
        Assertions.assertEquals(11, result);
    }

    @Test
    public void calculateSimilarityTest() {
        // Arrange
        int[] testArray1 = { 3,4,2,1,3,3 };
        int[] testArray2 = { 4,3,5,3,9,3 };

        // Act
        int result = Day01.calculateSimilarity(testArray1, testArray2);

        // Assert
        Assertions.assertEquals(31, result);
    }
}