package day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun `line can be read`() {
        val line1 = "...."
        val line2 = "...#"
        assertThat(countTrees(listOf(line1, line2))).isEqualTo(1)
    }

    @Test
    fun `line with no tree`() {
        val line1 = "...."
        val line2 = "...."
        assertThat(countTrees(listOf(line1, line2))).isEqualTo(0)
    }

    @Test
    fun `multiple lines`() {
        val line1 = "......."
        val line2 = "......."
        val line3 = "......#"
        assertThat(countTrees(listOf(line1, line2, line3))).isEqualTo(1)
    }

    @Test
    fun `line can be read and pattern is extended`() {
        val line1 = ".."
        val line2 = ".#"
        assertThat(countTrees(listOf(line1, line2))).isEqualTo(1)
    }
}