import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day08Test : FunSpec({

    test("Haunted Wasteland - Part1 - Eg1") {
        val input = listOf(
            "RL",
            "",
            "AAA = (BBB, CCC)",
            "BBB = (DDD, EEE)",
            "CCC = (ZZZ, GGG)",
            "DDD = (DDD, DDD)",
            "EEE = (EEE, EEE)",
            "GGG = (GGG, GGG)",
            "ZZZ = (ZZZ, ZZZ)"
        )
        val day08 = Day08(input)
        day08.part1() shouldBe 2
    }

    test("Haunted Wasteland - Part1 - Eg2") {
        val input = listOf(
            "LLR",
            "",
            "AAA = (BBB, BBB)",
            "BBB = (AAA, ZZZ)",
            "ZZZ = (ZZZ, ZZZ)"
        )
        val day08 = Day08(input)
        day08.part1() shouldBe 6
    }

    test("Haunted Wasteland - Part2") {
        val input = listOf(
            "LR",
            "",
            "11A = (11B, XXX),",
            "11B = (XXX, 11Z),",
            "11Z = (11B, XXX),",
            "22A = (22B, XXX),",
            "22B = (22C, 22C),",
            "22C = (22Z, 22Z),",
            "22Z = (22B, 22B),",
            "XXX = (XXX, XXX)"
        )
        val day08 = Day08(input)
        day08.part2() shouldBe 6
    }
})
