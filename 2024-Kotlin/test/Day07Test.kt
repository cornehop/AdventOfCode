import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day07Test {
    @Test
    fun lineIsCorrect_True_Test() {
        // Arrange
        val total = BigInteger.valueOf(190);
        val input = listOf(BigInteger.valueOf(10), BigInteger.valueOf(19))

        // Act
        val result = Day07().lineIsCorrect(total, input, false)

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    fun lineIsCorrect_False_Test() {
        // Arrange
        val total = BigInteger.valueOf(7290);
        val input = listOf(BigInteger.valueOf(6), BigInteger.valueOf(8), BigInteger.valueOf(6), BigInteger.valueOf(15))

        // Act
        val result = Day07().lineIsCorrect(total, input, false)

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    fun lineIsCorrect_ExtraOperator_True_Test() {
        // Arrange
        val total = BigInteger.valueOf(7290);
        val input = listOf(BigInteger.valueOf(6), BigInteger.valueOf(8), BigInteger.valueOf(6), BigInteger.valueOf(15))

        // Act
        val result = Day07().lineIsCorrect(total, input, true)

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    fun lineIsCorrect_ExtraOperator_False_Test() {
        // Arrange
        val total = BigInteger.valueOf(83);
        val input = listOf(BigInteger.valueOf(15), BigInteger.valueOf(6))

        // Act
        val result = Day07().lineIsCorrect(total, input, true)

        // Assert
        Assertions.assertFalse(result);
    }
}