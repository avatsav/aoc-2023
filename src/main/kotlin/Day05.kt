import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

/**
 * Day 5 - If You Give A Seed A Fertilizer
 * https://adventofcode.com/2023/day/5
 */

fun main() {
    val day05 = Day05(readInput("Day05"))
    println(day05.part1())
    println(day05.part2())
}


class Day05(input: List<String>) {

    private val almanac = Almanac.from(input)

    fun part1(): Long {
        return almanac.seeds.minOf { seed -> almanac.manual.findLocation(seed) }
    }

    // Brute-force! :(
    fun part2(): Long {
        return runBlocking(Dispatchers.Default) {
            almanac.seedRanges.map { seedRange ->
                async {
                    seedRange.minOf { seed -> almanac.manual.findLocation(seed) }
                }
            }.awaitAll().min()
        }
    }

}

private data class Almanac(val seeds: Set<Long>, val seedRanges: Set<LongRange>, val manual: List<CategoryMap>) {
    companion object {
        fun from(input: List<String>): Almanac {
            val seeds =
                input.first().substringAfter(":").trim().split(" ").filter { it.isNotBlank() }.map { it.toLong() }
                    .toSet()

            val seedRange = seeds.chunked(2).map { LongRange(it.first(), it.first() + it.last() - 1) }.toSet()

            val manual = input.drop(2) // don't need the first 2 lines anymore
                .fold(mutableListOf(mutableListOf<RangeMap>())) { listOfLists, line ->
                    when {
                        line.isBlank() -> listOfLists.add(mutableListOf())
                        !line.contains("map") -> listOfLists.last().add(RangeMap.from(line))
                    }
                    listOfLists
                }
            return Almanac(seeds, seedRange, manual)
        }
    }
}

private data class RangeMap(val sourceRange: LongRange, val destinationRange: LongRange) {

    fun findDestination(seed: Long): Long {
        val destination = destinationRange.first + (seed - sourceRange.first)
        return destination
    }

    companion object {
        fun from(line: String): RangeMap {
            var rangeLength = 0L
            var sourceRange = LongRange(0, 0)
            var destinationRange = LongRange(0, 0)
            line.split(" ").reversed().forEachIndexed { index, s ->
                val value = s.toLong()
                when (index) {
                    0 -> rangeLength = value
                    1 -> sourceRange = LongRange(value, value + rangeLength - 1)
                    2 -> destinationRange = LongRange(value, value + rangeLength - 1)
                }
            }
            return RangeMap(sourceRange, destinationRange)
        }
    }
}

private typealias CategoryMap = List<RangeMap>

private fun List<CategoryMap>.findLocation(seed: Long): Long {
    return this.fold(seed) { acc, map ->
        map.findDestinationOrNull(acc) ?: acc
    }
}

// Any source numbers that aren't mapped correspond to the same destination number.
// (╯°□°)╯︵ ┻━┻
private fun CategoryMap.findDestinationOrNull(seed: Long): Long? {
    return this.firstOrNull { seed in it.sourceRange }?.findDestination(seed)
}




