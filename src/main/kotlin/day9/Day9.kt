package day9

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day9.txt")
        .reader()
        .readLines()
        .map(String::toLong)

fun partOne(input: List<Long>): Long {
    for (index in 25 until input.size) {
        val candidate = input[index]
        val subList = input.subList(index - 25, index)
        var found = false
        for (i in subList.indices) {
            for (j in subList.indices) {
                if (subList[i] + subList[j] == candidate && subList[i] != subList[j]) {
                    found = true
                    break
                }
            }
        }
        if (!found) return candidate
    }
    return -1
}

fun partTwo(input: List<Long>): Long {

    val candidate: Long = partOne(input)

    for (window in 2 until input.size) {
        val index = input.windowed(window).indexOfFirst { it.sum() == candidate }
        if (index != -1) {
            val list = input.windowed(window)[index]
            return list.minOf { it } + list.maxOf { it }
        }
    }
    return -1
}
