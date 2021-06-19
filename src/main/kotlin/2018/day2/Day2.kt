package `2018`.day2

import java.lang.RuntimeException

fun main(args: Array<String>) {

    val numbers = readInputData()

    val result1 = partOne(numbers)
    println("Result1: $result1")

    val result2 = partTwo(numbers)
    println("Result2: $result2")
}

private fun readInputData(): List<String> =
    object {}.javaClass.classLoader.getResourceAsStream("2018/day2.txt")
        ?.reader()
        ?.readLines()
        ?: throw RuntimeException("input not found")

fun partOne(codes: List<String>): Int =
    codes.count { it.hasSetOfSize(2) } * codes.count { it.hasSetOfSize(3)}

fun partTwo(codes: List<String>): String {
    for(code in codes) {
        for (other in codes - code) {
            if (code.differsByOneCharacterFrom(other))
                return code.sharedCharactersWith(other)
        }
    }
    return ""
}

fun String.hasSetOfSize(size: Int) = groupBy { it }.any {it.value.size == size}

fun String.differsByOneCharacterFrom(other: String): Boolean {
    return foldIndexed(0) { index, acc: Int, c ->
        if (c != other[index]) acc +1
        else acc
    } == 1
}

fun String.sharedCharactersWith(other: String): String {
    return mapIndexed {index, c ->
        if (c == other[index]) c else ""
    }.joinToString ("")

}