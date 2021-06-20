package `2018`.day3

import org.assertj.core.api.Assertions.`in`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Test {

val claims = listOf(
    "#1 @ 1,3: 4x4",
    "#2 @ 3,1: 4x4",
    "#3 @ 5,5: 2x2",
    "#4 @ 3,3: 2x2"
).map {it.toClaim()}

    @Test
    fun readInput() {
        assertThat(claims).hasSize(4)
        assertThat(partOne(claims)).isEqualTo(4)
    }


}

