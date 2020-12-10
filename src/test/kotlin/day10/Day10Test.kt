package day10

import org.assertj.core.api.Assertions
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun testPartOne() {

        val result = partOne(mutableListOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4))

        Assertions.assertThat(result).isEqualTo(35)
    }
    @Test
    fun testPartOneLargerList() {

        val result = partOne(mutableListOf(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3))

        Assertions.assertThat(result).isEqualTo(220)

    }

    @Test
    fun testPartTwo() {

        val result = partTwo(mutableListOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4))

        Assertions.assertThat(result).isEqualTo(8)
    }
    @Test
    fun testPartTwoLargerList() {

        val result = partTwo(mutableListOf(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3))

        Assertions.assertThat(result).isEqualTo(19208)

    }
}

