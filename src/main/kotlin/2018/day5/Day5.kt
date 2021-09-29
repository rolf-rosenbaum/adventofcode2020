package `2018`.day5

import kotlin.math.abs


fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

fun readInputData(file: String = "2018/day5.txt") =
    object {}.javaClass.classLoader.getResourceAsStream(file)
        ?.reader()
        ?.readLines()
        ?.first() ?: ""


fun partOne(polymers: String): Int {

    return polymers.react().length
}

fun partTwo(polymers: String): Int {

    val shortest = ('a'..'z').minByOrNull {
        val candidate = polymers.replace(it.toString(), "", true).toCharArray()
        candidate.toString().react().length
    }

    return polymers.replace(shortest!!.toString(), "", true).react().length
}

fun String.react(): String {
    return fold(mutableListOf<Char>()) { done, c ->
        when {
            done.firstOrNull() matches c -> done.removeAt(0)
            else -> done.add(0, c)
        }
        done
    }
        .reversed()
        .joinToString("")

//    var result = reactOnce(polymers)
//    while (result != reactOnce(result.toCharArray())) {
//        result = reactOnce(result.toCharArray())
//    }
//    return result
}

private fun reactOnce(polymers: CharArray): String {
    if (polymers.size < 2) return polymers.joinToString("")
    val removedIndices = mutableListOf<Int>()

    var i = 0
    do {
        if (abs(polymers[i].toInt() - polymers[i + 1].toInt()) == 32) {
            removedIndices.add(i)
            removedIndices.add(i + 1)
            i++
        }
    } while (++i < polymers.size - 1)
    var result = ""
    polymers.indices.forEach {
        if (!removedIndices.contains(it)) {
            result += polymers[it]
        }
    }
    return result
}

private infix fun Char?.matches(other: Char) =
    if (this == null) false
    else abs(this.toInt() - other.toInt()) == 32