/**
 * Day 6 - Wait For It
 * https://adventofcode.com/2023/day/6
 */

fun main() {
    val day06 = Day06(readInput("Day06"))
    println(day06.part1())
    println(day06.part2())
}

class Day06(input: List<String>) {

    private val races: List<Race>
    private val bigRace: Race

    init {
        val (times, distances) = input.map { line -> line.substringAfter(":")
            .splitNonEmpty()
            .map { it.toLong() } }
        races = times.zip(distances) { time, distance -> Race(time, distance) }
        bigRace = Race(
            times.joinToString("").toLong(),
            distances.joinToString("").toLong()
        )
    }

    fun part1(): Long {
        return races.productOf { it.winCount().toLong() }
    }

    // brute-force because I don't know the math formula
    fun part2(): Int {
        return bigRace.winCount()
    }

}


private class Race(val time: Long, val distance: Long) {

    fun winCount(): Int {
        return LongRange(1, time - 1).count { it * (time - it) > distance }
    }

}
