import enums.MovingDirection
import helpers.FileHelpers

fun main() {
    Day06().main()
}

class Day06 {
    fun main() {
        val fileHelpers = FileHelpers()
        val lines = fileHelpers.getLinesFromFile("Day06-Input.txt")
        val map = getDataFromInput(lines)

        val result1 = getRouteCount(map)
        println("Result for part 1: $result1")
    }

    fun getRouteCount(map: List<List<Char>>): Int {
        val visitedBlocks = mutableListOf<Pair<Int,Int>>()
        var finished = false
        var movingDirection = MovingDirection.Up
        var currentPosition = getStartPosition(map)

        while (!finished) {
            // Take new position and add to collection (if not already exists)
            currentPosition = getNextPosition(currentPosition, movingDirection)
            if (!visitedBlocks.any { it.first == currentPosition.first && it.second == currentPosition.second }) {
                visitedBlocks.add(currentPosition);
            }

            // Determine the next position
            var nextPosition = getNextPosition(currentPosition, movingDirection)

            // Check if the guard leaves the map with the next step
            if (getIsFinished(map, nextPosition)) {
                finished = true
                continue
            }

            // Check if the guard changes his direction with the next step
            var nextChar = map[nextPosition.first][nextPosition.second]
            var isBlocked = nextChar == '#'
            while (isBlocked) {
                movingDirection = getNextDirection(movingDirection);
                nextPosition = getNextPosition(currentPosition, movingDirection)
                nextChar = map[nextPosition.first][nextPosition.second]
                isBlocked = nextChar == '#'
            }
        }

        return visitedBlocks.size
    }

    fun getNextPosition(currentPosition: Pair<Int,Int>, direction: MovingDirection): Pair<Int, Int> {
        return when (direction) {
            MovingDirection.Up -> {
                Pair(currentPosition.first - 1, currentPosition.second)
            }
            MovingDirection.Down -> {
                Pair(currentPosition.first + 1, currentPosition.second)
            }
            MovingDirection.Left -> {
                Pair(currentPosition.first, currentPosition.second - 1)
            }
            else -> {
                Pair(currentPosition.first, currentPosition.second + 1)
            }
        }
    }

    fun getStartPosition(map: List<List<Char>>): Pair<Int, Int> {
        for (index in map.indices) {
            val charIndex = map[index].indexOf('^')
            if (charIndex > 0) {
                return Pair(index, charIndex)
            }
        }
        return Pair(0, 0)
    }

    fun getDataFromInput(lines: List<String>): List<List<Char>> {
        val map = mutableListOf<List<Char>>()
        lines.forEach {
            map.add(it.toList())
        }
        return map
    }

    private fun getNextDirection(currentDirection: MovingDirection): MovingDirection {
        return when (currentDirection) {
            MovingDirection.Up -> {
                MovingDirection.Right
            }
            MovingDirection.Right -> {
                MovingDirection.Down
            }
            MovingDirection.Down -> {
                MovingDirection.Left
            }
            MovingDirection.Left -> {
                MovingDirection.Up
            }
        }
    }

    private fun getIsFinished(map: List<List<Char>>, newPosition: Pair<Int,Int>): Boolean {
        return newPosition.first < 0 ||
               newPosition.first > map.size - 1 ||
               newPosition.second < 0 ||
               newPosition.second > map[0].size - 1
    }

    private fun getNewDirectionChar(current: Char, direction: MovingDirection): Char {
        return when (direction) {
            MovingDirection.Up, MovingDirection.Down -> {
                when (current) {
                    '-' -> '+'
                    else -> '|'
                }
            }
            MovingDirection.Right, MovingDirection.Left -> {
                when (current) {
                    '|' -> '+'
                    else -> '-'
                }
            }
        }
    }

    private fun replaceCurrentPosition(map: List<List<Char>>, position: Pair<Int,Int>, direction: MovingDirection): List<List<Char>> {
        val newChar = getNewDirectionChar(map[position.first][position.second], direction)
        val newLine = map[position.first].replaceItem(position.second, newChar)
        map.replaceItem(position.first, newLine)
        return map
    }

    private fun <E> Iterable<E>.replaceItem(index: Int, elem: E) = mapIndexed { i, existing ->  if (i == index) elem else existing }
}