package `2018`.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun `simple adding`() {
        assertThat(partOne(listOf("+1", "+1"))).isEqualTo(2)
    }

    @Test
    fun `simple subtracting`() {
        assertThat(partOne(listOf("-1", "-1"))).isEqualTo(-2)
    }

    @Test
    fun `adding and subtracting`() {
        assertThat(partOne(listOf("-1", "-1", "+5"))).isEqualTo(3)
    }

    @Test
    fun `part two`() {
        assertThat(partTwo(listOf("+3", "+3", "+4", "-2", "-4"))).isEqualTo(10)
    }

}