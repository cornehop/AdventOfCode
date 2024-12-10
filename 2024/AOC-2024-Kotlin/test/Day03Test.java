import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day03Test {
    @Test
    public void Day03GetMulResultTest() {
        // Arrange
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";

        // Act
        int result = Day03.getMulResult(input, false);

        // Assert
        Assertions.assertEquals(161, result);
    }

    @Test
    public void Day03GetSecondMulResultTest() {
        // Arrange
        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";

        // Act
        int result = Day03.getMulResult(input, true);

        // Assert
        Assertions.assertEquals(48, result);
    }

    @Test
    public void Day03GetSecondMulResultWithMultipleTest() {
        // Arrange
        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";

        // Act
        int result = Day03.getMulResult(input, true);

        // Assert
        Assertions.assertEquals(96, result);
    }
}
