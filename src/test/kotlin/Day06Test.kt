import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day06Test : FunSpec({

    test("Wait For It") {
        val input = listOf(
            "Time:      7  15   30",
            "Distance:  9  40  200"
        )
        val day06 = Day06(input)
        day06.part1() shouldBe 288
        day06.part2() shouldBe 71503
    }

})
