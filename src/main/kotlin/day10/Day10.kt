package day10

fun main() {

    val input = readInputData()

    val result1 = partOne(input.toMutableList())
    println("Result1: $result1")

    val result2 = partTwo(input.toMutableList())
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day10.txt")
        .reader()
        .readLines().map(String::toInt)

fun partOne(input: MutableList<Int>): Int {

    val (ones, threes) = input
        .apply { add(0, 0) }
        .differenceList()
        .partition { it == 1 }
    return ones.size * (threes.size + 1)
}

fun partTwo(input: MutableList<Int>): Long {
    input.add(0, 0)

    val validLines = mutableListOf<Long>()
    val differences = input.differenceList()

    var index = 0
    while (index < differences.size) {
        var numberOfSizeOneSteps = 0
        while (index < differences.size && differences[index] == 1) {
            numberOfSizeOneSteps++
            index++
        }
        if (numberOfSizeOneSteps > 1) {
            val element = 2.pow(numberOfSizeOneSteps - 1) - 1
            validLines.add((0..element).map {
                it.toString(2).padStart(numberOfSizeOneSteps - 1, '0')
            }.count {
                !it.contains("000")
            }.toLong())
        }
        numberOfSizeOneSteps = 0
        index++
    }
    return validLines.reduce{ acc, i -> acc * i }
}

private fun MutableList<Int>.differenceList() = sorted()
    .zipWithNext { a, b ->
        b - a
    }

fun Int.pow(exp: Int): Long {
    return if (exp < 0) 0
    else (0 until exp).fold(1) { acc, _ -> acc * 2 }
}

