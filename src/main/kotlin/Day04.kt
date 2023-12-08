import kotlin.math.pow

/**
 * Day 3 - Scratch Cards
 * https://adventofcode.com/2023/day/4
 */
fun main() {
    val day04 = Day04(readInput("Day04").map { Card.from(it) })
    println(day04.part1())
    println(day04.part2())
}

class Day04(private val input: List<Card>) {

    fun part1(): Int {
        return input.sumOf { it.score }
    }

    fun part2(): Int {
        val scratchCards = IntArray(input.size) { 1 }
        input.forEachIndexed { originalCardIndex, card ->
            repeat(card.winningMatches.size) { copyCardIndex ->
                scratchCards[originalCardIndex + copyCardIndex + 1] += scratchCards[originalCardIndex]
            }
        }
        return scratchCards.sum()
    }
}

data class Card(val winningMatches: Set<Int>, val score: Int) {

    companion object {
        // Example - Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
        fun from(line: String): Card {
            val winningNumbers = line.substringAfter(":")
                .substringBefore("|")
                .split(" ")
                .filter { it.isNotBlank() }
                .map { it.toInt() }.toSet()
            val allNumbers = line.substringAfter("|")
                .split(" ")
                .filter { it.isNotBlank() }
                .map { it.toInt() }.toSet()

            val winningMatches = winningNumbers.intersect(allNumbers)
            val score = 2.0.pow(winningMatches.size - 1).toInt()
            return Card(winningMatches, score)
        }
    }
}
