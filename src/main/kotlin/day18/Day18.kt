package day18

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

fun partOne(input: List<String>): Long =
    input.map { calculateExpression(it) }.sum()

fun partTwo(input: List<String>): Long =
    input.map { calcPart2(it.iterator()) }.sum()

val operations: Map<Char, (Long, Long) -> Long> = mapOf(
    '+' to { a, b -> (a + b) },
    '*' to { a, b -> (a * b) }
)

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day18.txt")
        .reader()
        .readLines().map {
            it.replace(" ", "")
        }

fun calculateExpression(input: String): Long {

    var processedString = if (input.contains(")")) processParentheses(input)
    else input

    val numbers = processedString.split("+", "*").map(String::toLong)
    val operators = processedString.filter { it in listOf('+', '*') }.iterator()

    val startValue = numbers.first()
    return numbers.drop(1).fold(
        startValue
    ) { acc, number ->
        operations[operators.next()]?.invoke(acc, number) ?: acc
    }
}

fun processParentheses(input: String, withPrecedence: Boolean = false): String {
    if (!input.contains(")")) return input

    val end = input.indexOf(")")
    val begin = input.substring(0, end).lastIndexOf("(")
    val expr = input.substring(begin + 1, end)
    val res = calculateExpression(expr)
    return processParentheses(input.substring(0, begin) + res + input.substring(end + 1), withPrecedence)
}

fun calcPart2(input: CharIterator): Long {
    val multiplyList = mutableListOf<Long>()
    var partialSum = 0L
    while (input.hasNext()) {
        val char = input.nextChar()
        when {
            char == '(' -> partialSum += calcPart2(input)
            char == ')' -> break
            char == '*' -> {
                multiplyList += partialSum
                partialSum = 0L
            }
            char.isDigit() -> partialSum += char.asLong()
        }
    }
    return (multiplyList + partialSum).product()
}

fun Char.asLong(): Long =
    this.toString().toLong()

fun Iterable<Long>.product(): Long =
    this.reduce { a, b -> a * b }
