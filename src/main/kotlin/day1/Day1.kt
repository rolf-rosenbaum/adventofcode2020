package day1

fun main(args: Array<String>) {

    val result1 = partOne()
    println("Result1: $result1")

    val result2 = partTwo()
    println("Result2: $result2")
}

private fun readInputData(): List<Int> =

    object {}.javaClass.classLoader.getResourceAsStream("day1.txt")
        .reader()
        .readLines()
        .map { it.toInt() }


fun partOne(): Int {
    val numbers = readInputData()

    for (i in numbers.indices) {
        for (j in numbers.indices) {
            if (i != j && numbers[i] + numbers[j] == 2020)
                return numbers[i] * numbers[j]
        }
    }
    return 0
}

fun partTwo(): Int {
    val numbers = readInputData()

    for (i in 0..numbers.size - 3) {
        for (j in i + 1..numbers.size - 2) {
            for (l in j + 1 until numbers.size) {
                if (i != j && j != l && numbers[i] + numbers[j] + numbers[l] == 2020)
                    return numbers[i] * numbers[j] * numbers[l]
            }
        }
    }
    return 0
}
