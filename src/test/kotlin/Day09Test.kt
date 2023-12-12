import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day09Test : FunSpec({

    test("Mirage Maintenance") {
        val input = listOf(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45"
        )
        val day09 = Day09(input)
        day09.part1() shouldBe 114
        day09.part2() shouldBe 2
    }

})
