import enums.MovingDirection
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day06Test {
    @Test
    fun getDataFromInputTest() {
        // Arrange
        val input = listOf(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#...",
        )

        // Act
        val result = Day06().getDataFromInput(input);

        // Assert
        Assertions.assertEquals(10, result.size);
        Assertions.assertEquals(10, result[4].size);
        Assertions.assertEquals('.', result[0][0]);
        Assertions.assertEquals('#', result[0][4]);
        Assertions.assertEquals('#', result[3][2]);
        Assertions.assertEquals('^', result[6][4]);
        Assertions.assertEquals('.', result[6][5]);
    }

    @Test
    fun getStartPositionTest() {
        // Arrange
        val input = listOf(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#...",
        )
        val map = Day06().getDataFromInput(input);

        // Act
        val result = Day06().getStartPosition(map);

        // Assert
        Assertions.assertEquals(Pair(6,4), result)
    }

    @Test
    fun getNextPositionTest() {
        // Arrange
        val position = Pair(4, 3)

        // Act
        val upResult = Day06().getNextPosition(position, MovingDirection.Up)
        val downResult = Day06().getNextPosition(position, MovingDirection.Down)
        val leftResult = Day06().getNextPosition(position, MovingDirection.Left)
        val rightResult = Day06().getNextPosition(position, MovingDirection.Right)

        // Assert
        Assertions.assertEquals(Pair(3,3), upResult);
        Assertions.assertEquals(Pair(5,3), downResult);
        Assertions.assertEquals(Pair(4,2), leftResult);
        Assertions.assertEquals(Pair(4,4), rightResult);
    }

    @Test
    fun getRouteBlocksTest() {
        // Arrange
        val input = listOf(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#...",
        )
        val map = Day06().getDataFromInput(input)

        // Act
        val result = Day06().getRouteBlocks(map)

        // Assert
        Assertions.assertEquals(41, result.size)
    }

    @Test
    fun routeLoops_False_Test() {
        // Arrange
        val input = listOf(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#...",
        )
        val map = Day06().getDataFromInput(input)

        // Act
        val result = Day06().routeLoops(map)

        // Assert
        Assertions.assertFalse(result)
    }

    @Test
    fun routeLoops_True_Test() {
        // Arrange
        val input = listOf(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......##..",
        )
        val map = Day06().getDataFromInput(input)

        // Act
        val result = Day06().routeLoops(map)

        // Assert
        Assertions.assertTrue(result)
    }

    @Test
    fun getObstacleCountTest() {
        // Arrange
        val input = listOf(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#...",
        )
        val map = Day06().getDataFromInput(input)
        val route = Day06().getRouteBlocks(map)

        // Act
        val result = Day06().getObstacleCount(map, route)

        // Assert
        Assertions.assertEquals(6, result)
    }
}