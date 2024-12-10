import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day05Test {
    @Test
    fun getMiddlePageTest() {
        // Arrange
        val pages = listOf(1, 2, 3, 4, 5, 6, 7)

        // Act
        val result = Day05().getMiddlePage(pages);

        // Assert
        Assertions.assertEquals(4, result);
    }

    @Test
    fun getCorrectManualsScoreTest() {
        // Arrange
        val pageOrders = listOf(
            Pair(47,53),
            Pair(97,13),
            Pair(97,61),
            Pair(97,47),
            Pair(75,29),
            Pair(61,13),
            Pair(75,53),
            Pair(29,13),
            Pair(97,29),
            Pair(53,29),
            Pair(61,53),
            Pair(97,53),
            Pair(61,29),
            Pair(47,13),
            Pair(75,47),
            Pair(97,75),
            Pair(47,61),
            Pair(75,61),
            Pair(47,29),
            Pair(75,13),
            Pair(53,13)
        )
        val manuals = listOf(
            listOf(75,47,61,53,29),
            listOf(97,61,53,29,13),
            listOf(75,29,13),
            listOf(75,97,47,61,53),
            listOf(61,13,29),
            listOf(97,13,75,29,47)
        )
        val data = ManualData(pageOrders, manuals)

        // Act
        val result = Day05().getCorrectManualsScore(data)

        // Assert
        Assertions.assertEquals(143, result);
    }

    @Test
    fun getFaultyManualsScoreTest() {
        // Arrange
        val pageOrders = listOf(
            Pair(47,53),
            Pair(97,13),
            Pair(97,61),
            Pair(97,47),
            Pair(75,29),
            Pair(61,13),
            Pair(75,53),
            Pair(29,13),
            Pair(97,29),
            Pair(53,29),
            Pair(61,53),
            Pair(97,53),
            Pair(61,29),
            Pair(47,13),
            Pair(75,47),
            Pair(97,75),
            Pair(47,61),
            Pair(75,61),
            Pair(47,29),
            Pair(75,13),
            Pair(53,13)
        )
        val manuals = listOf(
            listOf(75,47,61,53,29),
            listOf(97,61,53,29,13),
            listOf(75,29,13),
            listOf(75,97,47,61,53),
            listOf(61,13,29),
            listOf(97,13,75,29,47)
        )
        val data = ManualData(pageOrders, manuals)

        // Act
        val result = Day05().getFaultyManualsScore(data)

        // Assert
        Assertions.assertEquals(123, result);
    }
}