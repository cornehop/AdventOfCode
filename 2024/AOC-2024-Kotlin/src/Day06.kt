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

        val defaultRoute = getRouteBlocks(map)
        val part1 = defaultRoute.size;
        println("Result for part 1: $part1")
    }

    fun getRouteBlocks(map: List<List<Char>>): List<Pair<Int,Int>> {
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

        return visitedBlocks
    }

    fun predictNextObstacle(map: List<List<Char>>) {
        // TODO:
        // make sure the map consists of "+" on every corner where the guard changes route
        // for every "+", search the next "+" in the same row (do this for every "+")
        // if the second "+" exists, find a "+" in the same vertical row of the first item
        // if the third exists, place an obstacle below the fourth block
        // So if the first is [1][3], the second [1][7] and the third [5][3], then the obstacle should be BELOW [5][7]
        // OR find the third in the same vertical row as the second item and try te obstacle below the first item
        // So in that case, if the first is [1][3], the second [1][7] and the third [5][7], then the obstacle should be LEFT of [5][3]
        // Possibly run the map with the new obstacle, but if it works we should be able to predict this method without even testing it?
        // Make a version with every test run and a version without the test run and check if they show the same result
    }

    fun createSquaresForBlock(map: List<List<Char>>, start: Pair<Int,Int>) {
        val possibleObstacles = mutableListOf<Pair<Int,Int>>()
        val line = map[start.first]
        for (index in line.indices) {
            val secondBlock = map[start.first][index];
            if (index <= start.second || secondBlock != '+') {
                continue;
            }

        }
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