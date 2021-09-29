package `2018`.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Test {
    val testPolymer = "dabAcCaCBAcCcaDA"

    @Test
    fun `Aa is completely removed`() {
        assertThat("Aa".react()).isEqualTo("")
    }

    @Test
    fun `BaB remains unchanged`() {
        assertThat("BaB".react()).isEqualTo("BaB")
    }

    @Test
    fun `BbA becomes A`() {
        assertThat("BbA".react()).isEqualTo("A")
    }

    @Test
    fun `BbAa is completely removed`() {
        assertThat("BbAa".react()).isEqualTo("")
    }

    @Test
    fun `CBbAac is completely removed`() {
        assertThat("CBbAac".react()).isEqualTo("")
    }

    @Test
    fun `dabAcCaCBAcCcaDA becomes dabCBAcaDA`() {
        assertThat("dabAcCaCBAcCcaDA".react()).isEqualTo("dabCBAcaDA")
    }


}