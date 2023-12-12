/**
 * Day09 - Mirage Maintenance
 * https://adventofcode.com/2023/day/9
 */

fun main() {
    val dayO9 = Day09(readInput("Day09"))
    println(dayO9.part1())
    println(dayO9.part2())
}

class Day09(input: List<String>) {

    private val readings: List<List<Long>> = input.map { line -> line.splitNonEmpty().map { it.toLong() } }

    fun part1(): Long {
        return readings.sumOf { it.predictNext() }
    }

    fun part2(): Long {
        return readings.sumOf { it.predictPrevious() }
    }

    private fun List<Long>.predictNext(): Long {
        if (all { it == 0L }) return 0L
        val diff = windowed(2) { it.last() - it.first() }
        return last() + diff.predictNext()
    }

    private fun List<Long>.predictPrevious() = reversed().predictNext()
}
