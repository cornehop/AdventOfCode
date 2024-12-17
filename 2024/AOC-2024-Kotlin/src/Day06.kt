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

        val part2 = getObstacleCount(map, defaultRoute)
        println("Result for part 2: $part2")
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

    fun getObstacleCount(map: List<List<Char>>, originalRoute: List<Pair<Int,Int>>): Int {
        var total = 0;
        for (position in originalRoute) {
            val newMap = replaceCurrentPosition(map, position)
            if (routeLoops(newMap)) {
                total++
            }
        }
        return total
    }

    fun routeLoops(map: List<List<Char>>): Boolean {
        val visitedPositions = mutableListOf<Pair<Pair<Int,Int>,Pair<Int,Int>>>()

        var finished = false
        var movingDirection = MovingDirection.Up
        var currentPosition = getStartPosition(map)

        while (!finished) {
            // Take new position and add to collection (if not already exists)
            currentPosition = getNextPosition(currentPosition, movingDirection)

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

            // Check if the guard has already visited this step
            if (visitedPositions.any { it.first == currentPosition && it.second == nextPosition }) {
                return true;
            } else {
                visitedPositions.add(Pair(currentPosition, nextPosition))
            }
        }

        return false
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

    private fun replaceCurrentPosition(map: List<List<Char>>, position: Pair<Int,Int>): List<List<Char>> {
        val existingChar = map[position.first][position.second];
        if (existingChar == '^') {
            return map;
        }

        val newLine = map[position.first].replaceItem(position.second, '#')
        return map.replaceItem(position.first, newLine)
    }

    private fun <E> Iterable<E>.replaceItem(index: Int, elem: E) = mapIndexed { i, existing ->  if (i == index) elem else existing }
}