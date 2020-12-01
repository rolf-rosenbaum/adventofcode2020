package day1

fun main(args: Array<String>) {

    val numbers = readInputData()

    val result1 = partOne(numbers)
    println("Result1: $result1")

    val result2 = partTwo(numbers)
    println("Result2: $result2")
}

private fun readInputData(): List<Int> =
    object {}.javaClass.classLoader.getResourceAsStream("day1.txt")
        .reader()
        .readLines()
        .map { it.toInt() }

fun partOne(numbers: List<Int>): Int {
    for (a in numbers) {
        for (b in numbers) {
            if (a + b == 2020) {
                return a * b
            }
        }
    }

    return 0
}

fun partTwo(numbers: List<Int>): Int {
    for (a in numbers) {
        for (b in numbers) {
            for (c in numbers) {
                if (a + b + c == 2020) {
                    return a * b * c
                }
            }
        }
    }
    return 0
}
