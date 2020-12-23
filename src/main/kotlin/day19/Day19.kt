package day19

fun main() {


    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}
val input = readInputData()

val rules: Map<Int, Rule> = readRules(input)

fun readRules(input: List<String>): Map<Int, Rule> {
    val result = mutableMapOf<Int, Rule>()

    for (i in 0..138) {
        val tmp = input[i].split(": ")
        val index = tmp.first()
        val ruleStr = tmp.component2()
        if (ruleStr.contains("\"")) {
//            result[i] = Rule(ruleStr.filter { it.isLetter() }.toString())

        } else {
            val forwardingRuleStr = ruleStr.split(" | ")
            val leftList = forwardingRuleStr.first().split(" ").map(String::toInt)
            val rightList = forwardingRuleStr.component2().split(" ").map(String::toInt)
            result[i] = Rule(leftRules = leftList, rightRules = rightList)
        }
    }
    return result
}

data class Rule(val validString: String? = null, val leftRules: List<Int> = emptyList(), val rightRules: List<Int>)

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day19.txt")
        .reader()
        .readLines()

fun partOne(input: List<String>): Int = 0

fun partTwo(input: List<String>): Int = 0
