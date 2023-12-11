import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day07Test : FunSpec({

    test("Camel Cards") {
        val input = listOf(
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483"
        )
        val day07 = Day07(input)
        day07.part1() shouldBe 6440
        day07.part2() shouldBe 5905
    }

})
