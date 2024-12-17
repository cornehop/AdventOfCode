import helpers.FileHelpers
import java.math.BigInteger

fun main() {
    Day07().main()
}

class Day07 {
    fun main() {
        val fileHelpers = FileHelpers()
        val lines = fileHelpers.getLinesFromFile("Day07-Input.txt")
        val input = parseInput(lines)

        val result1 = getTotalPart1(input)
        println("Result for part 1: $result1")

        val result2 = getTotalPart2(input)
        println("Result for part 1: $result2")
    }

    private fun getTotalPart1(input: List<Pair<BigInteger,List<BigInteger>>>): BigInteger {
        var total = BigInteger.valueOf(0)
        for (line in input) {
            if (lineIsCorrect(line.first, line.second, false)) {
                total += line.first
            }
        }
        return total;
    }

    private fun getTotalPart2(input: List<Pair<BigInteger,List<BigInteger>>>): BigInteger {
        var total = BigInteger.valueOf(0)
        for (line in input) {
            if (lineIsCorrect(line.first, line.second, true)) {
                total += line.first
            }
        }
        return total;
    }

    fun lineIsCorrect(expectedResult: BigInteger, values: List<BigInteger>, useExtraOperator: Boolean): Boolean {
        var previous = mutableListOf<BigInteger>()
        for (index in values.indices) {
            if (index == 0) {
                previous = mutableListOf(values[index])
                continue
            }

            val newPrevious = mutableListOf<BigInteger>()
            for (valueSoFar in previous) {
                newPrevious.add(valueSoFar + values[index])
                newPrevious.add(valueSoFar * values[index])

                if (useExtraOperator) {
                    val combinedValues = valueSoFar.toString() + values[index].toString()
                    val combinedInteger = combinedValues.toBigInteger()
                    newPrevious.add(combinedInteger)
                }

                previous = newPrevious;
            }
        }

        return previous.any { it == expectedResult }
    }

    private fun parseInput(input: List<String>): List<Pair<BigInteger,List<BigInteger>>> {
        val list = mutableListOf<Pair<BigInteger,List<BigInteger>>>()
        for (line in input) {
            val parts = line.split(": ")
            val result = parts[0].toBigInteger()
            val numbers = parts[1].split(' ')
            val integers = numbers.map { it.toBigInteger() }
            list.add(Pair(result, integers))
        }
        return list
    }
}