package day18

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day10.txt")
        .reader()
        .readLines()

fun partOne(input: List<String>): Int = 0

fun partTwo(input: List<String>): Int = 0
