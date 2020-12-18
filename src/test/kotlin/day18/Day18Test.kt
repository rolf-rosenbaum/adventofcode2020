package day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun `1 + 1 is 2`() {
        assertThat(calculateExpression("1+1")).isEqualTo(2)
    }

    @Test
    fun `1 + 2 is 3`() {
        assertThat(calculateExpression("1+2")).isEqualTo(3)
    }

    @Test
    fun `2 * 3 is 6`() {
        assertThat(calculateExpression("2*3")).isEqualTo(6)
    }

    @Test
    fun `2 * 2 * 2 is 8`() {
        assertThat(calculateExpression("2*2*2")).isEqualTo(8)
    }

    @Test
    fun `2 + 2 + 2 is 6`() {
        assertThat(calculateExpression("2*2*2")).isEqualTo(8)
    }

    @Test
    fun `2 + 2 * 3 is 12`() {
        assertThat(calculateExpression("2+2*3")).isEqualTo(12)
    }

    @Test
    fun `0 + 1 * 3 + 5 * 2 is 16`() {
        assertThat(calculateExpression("0+1*3+5*2")).isEqualTo(16)
    }

    @Test
    fun `2 + 2 + 2 * 3 + 2`() {
        assertThat(calcPart2("2+2+2*3+2".iterator())).isEqualTo(30)
    }

    @Test
    fun `3 + (5 * 2) is 13`() {
        assertThat(calculateExpression("3+(5*2)")).isEqualTo(13)
    }

    @Test
    fun `examples`() {
        assertThat(calcPart2("2*3+(4*5)".iterator())).isEqualTo(46)
//        assertThat(calculateExpression("5+(8*3+9+3*4*3)")).isEqualTo(437)
//        assertThat(solvePart2("5 + (8 * 3 + 9 + 3 * 4 * 3)".iterator())).isEqualTo(1445)
//        assertThat(calculateExpression("((2+4*9)*(6+9*8+6)+6)+2+4*2")).isEqualTo(13632)
//        assertThat(solvePart2("5*9*(7*3*3+9*3+(8+6*4))".iterator())).isEqualTo(669060)
    }








}