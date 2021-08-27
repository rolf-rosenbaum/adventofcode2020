package `2018`.day4

import org.assertj.core.api.Assertions.`in`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4Test {

    private val input = readInputData("day4.txt")

    @Test
    fun `time asleep for guard #99 should be 30`() {
        assertThat(input.timeAsleep(99)).isEqualTo(30)

    }

    @Test
    fun `time asleep for guard #10 should be 50`() {
        assertThat(input.timeAsleep(10)).isEqualTo(50)

    }

    @Test
    fun testPartOne() {
        val result = partOne(input)
        assertThat(result).isEqualTo(240)
    }

    @Test
    fun testPartTwo() {
        val result = partTwo(input)
        assertThat(result).isEqualTo(4455)
    }



}