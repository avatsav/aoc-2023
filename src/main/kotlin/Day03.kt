/**
 * Day 3 - Gear Ratios
 * https://adventofcode.com/2023/day/3
 */

fun main() {
    val day03 = Day03(readInput("Day03"))
    println(day03.part1())
    println(day03.part2())
}

class Day03(private val input: List<String>) {

    fun part1(): Int {
        val (partNumbers, symbols) = EngineSchematic.from(input) { it != '.' }
        return partNumbers
            .filter { it.isAdjacentToAnyOf(symbols) }
            .sumOf { it.number }
    }

    fun part2(): Int {
        val (partNumbers, symbols) = EngineSchematic.from(input) { it == '*' }
        return symbols.sumOf { symbol ->
            val cogs = partNumbers.filter { it.isAdjacentTo(symbol) }
            when {
                cogs.count() == 2 -> cogs[0].number * cogs[1].number
                else -> 0
            }
        }
    }

}

private data class EngineSchematic(val partNumbers: Set<PartNumber>, val symbols: Set<Symbol>) {
    companion object {
        fun from(input: List<String>, isSymbol: (Char) -> Boolean): EngineSchematic {
            val partNumbers = mutableSetOf<PartNumber>()
            val symbols = mutableSetOf<Symbol>()
            val partNumberBuilder = PartNumberBuilder()
            input.forEachIndexed { yIndex, line ->
                line.forEachIndexed { xIndex, character ->
                    when {
                        character.isDigit() -> partNumberBuilder.add(character, Point(xIndex, yIndex))
                        else -> {
                            if (partNumberBuilder.inProgress()) partNumbers.add(partNumberBuilder.build())
                            if (isSymbol(character)) symbols.add(Point(xIndex, yIndex))
                        }
                    }
                }
            }
            return EngineSchematic(partNumbers, symbols)
        }
    }
}

private class PartNumberBuilder {
    private val number = mutableListOf<Char>()
    private val points = mutableSetOf<Point>()

    fun add(c: Char, point: Point) {
        number.add(c)
        points.add(point)
    }

    fun inProgress(): Boolean {
        return number.isNotEmpty()
    }

    fun build(): PartNumber {
        val partNumber = PartNumber(
            number = number.joinToString("").toInt(),
            neighbours = points.flatMap { it.neighbours }.toSet()
        )
        clear()
        return partNumber
    }

    private fun clear() {
        number.clear()
        points.clear()
    }
}

data class PartNumber(val number: Int, val neighbours: Set<Point>) {
    fun isAdjacentToAnyOf(symbols: Set<Symbol>) = neighbours.intersect(symbols).isNotEmpty()
    fun isAdjacentTo(symbol: Symbol) = neighbours.contains(symbol)
}

typealias Symbol = Point

data class Point(val x: Int, val y: Int) {
    /**
     * (x-1, y-1) ( x , y-1) (x+1, y-1)
     * (x-1,  y ) ( x ,  y ) (x+1,  y )
     * (x-1, y+1) ( x , y+1) (x+1, y+1)
     */
    val neighbours: Set<Point> by lazy {
        setOf(
            Point(x - 1, y - 1),
            Point(x, y - 1),
            Point(x + 1, y - 1),

            Point(x - 1, y),
            Point(x + 1, y),

            Point(x - 1, y + 1),
            Point(x, y + 1),
            Point(x + 1, y + 1)
        )
    }
}


