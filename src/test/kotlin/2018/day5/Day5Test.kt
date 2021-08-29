package `2018`.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Test {
    val testPolymer = "dabAcCaCBAcCcaDA"

    @Test
    fun `Aa is completely removed`() {
        assertThat(react("Aa".toCharArray())).isEqualTo("")
    }

    @Test
    fun `BaB remains unchanged`() {
        assertThat(react("BaB".toCharArray())).isEqualTo("BaB")
    }

    @Test
    fun `BbA becomes A`() {
        assertThat(react("BbA".toCharArray())).isEqualTo("A")
    }

    @Test
    fun `BbAa is completely removed`() {
        assertThat(react("BbAa".toCharArray())).isEqualTo("")
    }

    @Test
    fun `CBbAac is completely removed`() {
        assertThat(react("CBbAac".toCharArray())).isEqualTo("")
    }

    @Test
    fun `dabAcCaCBAcCcaDA becomes`() {
        assertThat(react("dabAcCaCBAcCcaDA".toCharArray())).isEqualTo("dabCBAcaDA")
    }


}