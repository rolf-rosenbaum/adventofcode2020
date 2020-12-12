package day11

import org.junit.jupiter.api.Test

class Day11Test {

    private fun readInputData(): List<String> =
        {}.javaClass.classLoader.getResourceAsStream("test.txt")
            .reader()
            .readLines()


    @Test
    fun testPartTwo() {
        partTwo()
    }
}