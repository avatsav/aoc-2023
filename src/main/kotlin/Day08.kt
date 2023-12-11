/**
 *  Day 8 - Haunted Wasteland
 *  https://adventofcode.com/2023/day/8
 */

fun main() {
    val day08 = Day08(readInput("Day08"))
    println(day08.part1())
}

class Day08(input: List<String>) {

    private val instructions: String = input.first()

    // Example: AAA = (BBB, CCC)
    private val map: Map<String, Destinations> = input.asSequence().drop(2)
        .associate { line ->
            val (from, destinations) = line.split(" = ")
            val (left, right) = destinations.substringAfter("(")
                .substringBefore(")")
                .split(", ")
            from to Destinations(left to right)
        }

    fun part1(): Int {
        var current = "AAA"
        return instructions.asSequence().repeatInfinitely().map { instruction ->
            current = if (instruction == 'L') map[current]!!.left() else map[current]!!.right()
            current
        }.takeWhile { it != "ZZZ" }.count() + 1
    }

}

private data class Destinations(private val pair: Pair<String, String>) {
    fun left() = pair.first
    fun right() = pair.second
}








