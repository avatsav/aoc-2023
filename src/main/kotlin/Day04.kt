import kotlin.math.pow

/**
 * Day 4 - Scratch Cards
 * https://adventofcode.com/2023/day/4
 */
fun main() {
    val day04 = Day04(readInput("Day04"))
    println(day04.part1())
    println(day04.part2())
}

class Day04(input: List<String>) {

    private val cards = input.map { Card.from(it) }

    fun part1(): Int {
        return cards.sumOf { it.score }
    }

    fun part2(): Int {
        val scratchCards = IntArray(cards.size) { 1 }
        cards.forEachIndexed { originalCardIndex, card ->
            repeat(card.winningMatches.size) { copyCardIndex ->
                scratchCards[originalCardIndex + copyCardIndex + 1] += scratchCards[originalCardIndex]
            }
        }
        return scratchCards.sum()
    }
}

private data class Card(val winningMatches: Set<Int>, val score: Int) {
    companion object {
        // Example - Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
        fun from(line: String): Card {
            val winningNumbers = line.substringAfter(":")
                .substringBefore("|")
                .splitNonEmpty()
                .map { it.toInt() }.toSet()
            val allNumbers = line.substringAfter("|")
                .splitNonEmpty()
                .map { it.toInt() }.toSet()

            val winningMatches = winningNumbers.intersect(allNumbers)
            val score = 2.0.pow(winningMatches.size - 1).toInt()
            return Card(winningMatches, score)
        }
    }
}
