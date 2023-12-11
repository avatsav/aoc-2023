/**
 * Day7 - Camel Cards
 * https://adventofcode.com/2023/day/7/input
 */

fun main() {
    val day07 = Day07(readInput("Day07"))
    println(day07.part1())
    println(day07.part2())
}

class Day07(private val input: List<String>) {

    fun part1(): Int {
        val hands: List<Hand> = input.map { Hand.from(it) }
        return hands.sortedWith(Hand.comparator)
            .withIndex()
            .sumOf { (index, hand) -> (index + 1) * hand.bid }
    }

    fun part2(): Int {
        val hands: List<Hand> = input.map { Hand.from(it, true) }
        return hands.sortedWith(Hand.comparator)
            .withIndex()
            .sumOf { (index, hand) -> (index + 1) * hand.bid }
    }

    private data class Hand(val cards: List<Int>, val bid: Int, val type: Int) {
        companion object {
            private const val RANK_STANDARD = "23456789TJQKA"
            private const val RANK_WITH_JOKER = "23456789TQKA" // Joker gets rank of -1 as indexOf('J') = -1

            fun from(line: String, withJoker: Boolean = false): Hand {
                return line.splitNonEmpty().run {
                    val rankOrder = if (withJoker) RANK_WITH_JOKER else RANK_STANDARD
                    val cards = first().map { card -> rankOrder.indexOf(card) }
                    val bid = last().toInt()
                    val jokerCount = cards.count { it == -1 } // joker count contributes to the max count
                    val counts = cards.filter { it != -1 } // filtering out jokers.
                        .groupingBy { it }
                        .eachCount().values
                        .sortedDescending()
                        .toMutableList()
                    if (counts.isEmpty()) { // only jokers! ಠ_ಠ
                        counts.add(0)
                    }
                    val sizeToMax = counts.size to (counts.max() + jokerCount)
                    val type = when (sizeToMax) {
                        1 to 5 -> 7 // five of a kind (5x)
                        2 to 4 -> 6 // four of a kind (4x, 1y)
                        2 to 3 -> 5 // full house (3x + 2y)
                        3 to 3 -> 4 // three of a kind (3x + 1y + 1z)
                        3 to 2 -> 3 // two pair (2x + 2y + 1z)
                        4 to 2 -> 2 // one pair (2x + 1y + 1z + 1a)
                        5 to 1 -> 1 // high card
                        else -> error("not reachable")
                    }
                    Hand(cards, bid, type)
                }
            }

            val comparator = compareBy<Hand> { it.type }
                .thenComparator { handA, handB ->
                    handA.cards.zip(handB.cards).forEach {
                        it.first.compareTo(it.second).let { comparison ->
                            if (comparison != 0) return@thenComparator comparison
                        }
                    }
                    0
                }
        }
    }
}
