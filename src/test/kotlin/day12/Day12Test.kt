package day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun testPartTwo() {
        val input = listOf("F10", "N3", "F7", "R90", "F11")

        val commands = input.map(::toCommand)

        val result = partTwo(commands)

        assertThat(result).isEqualTo(286)
    }

    @Test
    fun testRotateClockwise() {
        assertThat(Vector(4,10).rotateClockwise(90)).isEqualTo(Vector(10,-4))
        assertThat(Vector(12,34)
            .rotateClockwise(90)
            .rotateClockwise(90)
            .rotateClockwise(90)
            .rotateClockwise(90))
            .isEqualTo(Vector(12,34))
    }
}