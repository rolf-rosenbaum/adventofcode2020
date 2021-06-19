package `2018`.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun `one double, one triple`() {
        assertThat(partOne(listOf("aa", "bbb"))).isEqualTo(1)
    }

    @Test
    fun `one double, two triples`() {
        assertThat(partOne(listOf("aa", "bbb", "ccc"))).isEqualTo(2)
    }

    @Test
    fun `one double, no triple`() {
        assertThat(partOne(listOf("aa"))).isEqualTo(0)
    }

    @Test
    fun `differs by one character`() {
        assertThat("abcde".differsByOneCharacterFrom("fbcde")).isTrue
        assertThat("abcde".differsByOneCharacterFrom("fbcdg")).isFalse
    }

    @Test
    fun `find exactly one difference`() {
        assertThat(
            partTwo(
                listOf(
                    "abcde",
                    "fghij",
                    "klmno",
                    "pqrst",
                    "fguij",
                    "axcye",
                    "wvxyz"
                )
            )
        ).isEqualTo("fgij")
    }


}