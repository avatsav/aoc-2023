/**
 *  Day 8 - Haunted Wasteland
 *  https://adventofcode.com/2023/day/8
 */

fun main() {
    val day08 = Day08(readInput("Day08"))
    println(day08.part1())
    println(day08.part2())
}

class Day08(input: List<String>) {

    private val instructions: String = input.first()

    // Example: AAA = (BBB, CCC)
    private val map: Map<String, Pair<String, String>> = input.asSequence().drop(2)
        .associate { line ->
            val (from, destinations) = line.split(" = ")
            val (left, right) = destinations.substringAfter("(")
                .substringBefore(")")
                .split(", ")
            from to (left to right)
        }

    fun part1(): Int {
        return navigate(source = "AAA", destination = { it == "ZZZ" })
    }

    // Wow. LCM! First time since high school!
    // Had to look this one up.
    fun part2(): Long {
        val startingPoints = map.filterKeys { it.endsWith("A") }.keys
        return startingPoints.map { start -> navigate(source = start, destination = { it.endsWith("Z") }) }
            .map { it.toLong() }
            .reduce { acc, i -> acc.lcm(i) }
    }

    private fun navigate(source: String, destination: (String) -> Boolean): Int {
        var current = source
        return instructions.asSequence().repeatInfinitely().map { instruction ->
            current = if (instruction == 'L') map[current]!!.first else map[current]!!.second
            current
        }.takeWhile { !destination(it) }.count() + 1
    }

}







