/**
 * Day 1 - Trebuchet?!
 * https://adventofcode.com/2023/day/1
 */

fun main() {
    val day01 = Day01(readInput("Day01"))
    println(day01.part1())
    println(day01.part2())
}

private class Day01(private val input: List<String>) {

    private val wordedDigits = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    fun part1(): Int {
        return input.sumOf { calibrationValue(it) }
    }

    private fun calibrationValue(line: String): Int {
        val first = line.firstDigit() * 10
        val last = line.lastDigit()
        return first + last
    }

    fun part2(): Int {
        return input.sumOf { calibrationAdvanced((it)) }
    }


    private fun calibrationAdvanced(line: String): Int {
        val first = line.firstDigitWithWords() * 10
        val last = line.lastDigitWithWords()
        return first + last
    }

    private fun String.firstDigit(): Int =
        first { it.isDigit() }.digitToInt()

    private fun String.lastDigit(): Int =
        last { it.isDigit() }.digitToInt()


    private fun String.firstDigitWithWords(): Int = verbalisedDigit(indices)

    private fun String.lastDigitWithWords(): Int = verbalisedDigit(indices.reversed())

    private fun String.verbalisedDigit(range: IntProgression): Int {
        for (index in range) {
            this[index].digitToIntOrNull()?.let { return it }
            wordedDigits.forEach { (word, value) ->
                if (this.substring(index).startsWith(word)) return value
            }
        }
        error("unreachable code")
    }
}




