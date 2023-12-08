/**
 * Day 2 - Cube Conundrum
 * https://adventofcode.com/2023/day/2
 */

fun main() {
    val day02 = Day02(readInput("Day02"))
    println(day02.part1(12, 13, 14))
    println(day02.part2())
}


class Day02(input: List<String>) {

    private val games = input.map { Game.from(it) }

    fun part1(red: Int, green: Int, blue: Int): Int {
        return games
            .filter { it.isPossible(red, green, blue) }
            .sumOf { it.id }
    }

    fun part2(): Int {
        return games.sumOf { it.power() }
    }

}


private data class Game(val id: Int, val maxRedsPulled: Int, val maxGreensPulled: Int, val maxBluesPulled: Int) {

    fun isPossible(red: Int, green: Int, blue: Int): Boolean =
        maxRedsPulled <= red && maxGreensPulled <= green && maxBluesPulled <= blue

    fun power() = maxRedsPulled * maxGreensPulled * maxBluesPulled

    companion object {
        // Eg: Game 1: 7 red, 14 blue; 2 blue, 3 red, 3 green; 4 green, 12 blue, 15 red; 3 green, 12 blue, 3 red; 11 red, 2 green
        fun from(line: String): Game {
            val id = line.substringAfter(" ").substringBefore(":").toInt()
            var maxRedsPulled = 0
            var maxGreensPulled = 0
            var maxBluesPulled = 0
            line.substringAfter(":").split(";").forEach { turn ->
                turn.split(",").map { it.trim() }.forEach { hand ->
                    val count = hand.substringBefore(" ").toInt()
                    val color = hand.substringAfter(" ").lowercase()
                    when (color) {
                        "red" -> maxRedsPulled = maxOf(count, maxRedsPulled)
                        "green" -> maxGreensPulled = maxOf(count, maxGreensPulled)
                        "blue" -> maxBluesPulled = maxOf(count, maxBluesPulled)
                    }
                }
            }
            return Game(id, maxRedsPulled, maxGreensPulled, maxBluesPulled)
        }
    }
}
