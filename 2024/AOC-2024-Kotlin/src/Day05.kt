import helpers.FileHelpers

fun main() {
    val day5 = Day05()
    day5.main();
}

class Day05 {
    fun main() {
        // Read resource
        val fileHelpers = FileHelpers()
        val lines = fileHelpers.getLinesFromFile("Day05-Input.txt");
        val manualData = getDataFromInput(lines)

        val part1 = getCorrectManualsScore(manualData);
        println("Result for part 1: $part1");

        val part2 = getFaultyManualsScore(manualData);
        println("Result for part 2: $part2");
    }

    fun getCorrectManualsScore(data: ManualData): Int {
        var total = 0;
        for (manual in data.manuals) {
            if (isOrdered(manual, data.pageOrders)) {
                total += getMiddlePage(manual);
            }
        }
        return total;
    }

    fun getFaultyManualsScore(data: ManualData): Int {
        var total = 0
        for (manual in data.manuals) {
            if (!isOrdered(manual, data.pageOrders)) {
                val orderedManual = manual.sortedWith { page1, page2 ->
                    when {
                        data.pageOrders.any { order -> order.first == page1 && order.second == page2 } -> -1
                        data.pageOrders.any { order -> order.first == page2 && order.second == page1 } -> 1
                        else -> 0
                    }
                }
                total += getMiddlePage(orderedManual);
            }
        }
        return total
    }

    fun getMiddlePage(pages: List<Int>): Int {
        return pages[pages.count() / 2];
    }

    private fun isOrdered(page: List<Int>, orderedPages: List<Pair<Int,Int>>): Boolean {
        for (index in page.indices) {
            if (index == page.count() - 1) {
                continue;
            }

            if (orderedPages.any { it.second == page[index] && it.first == page[index + 1] }) {
                return false;
            }
        }
        return true;
    }

    private fun getDataFromInput(lines: List<String>): ManualData {
        val pageOrders = mutableListOf<Pair<Int, Int>>()
        val manuals = mutableListOf<List<Int>>()

        lines.forEach {
            when {
                it.contains('|') -> pageOrders.add(Pair(it.split('|')[0].toInt(), it.split('|')[1].toInt()))
                it.contains(',') -> manuals.add(it.split(',').map { page -> page.toInt() })
            }
        }

        return ManualData(pageOrders, manuals)
    }
}
class ManualData(val pageOrders: List<Pair<Int, Int>>, val manuals: List<List<Int>>)