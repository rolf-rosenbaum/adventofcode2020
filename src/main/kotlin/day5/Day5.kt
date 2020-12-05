package day5

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day5.txt")
        .reader()
        .readLines()


fun partOne(passes: List<String>) =
    passes.maxByOrNull { s ->
        s.toSeatId()
    }?.toSeatId() ?: 0

fun partTwo(passes: List<String>): Int {

    val sortedSeats = passes.map {
        it.toSeatId()
    }.sorted()

    (0..sortedSeats.size).forEachIndexed { index, i ->
        if (index !in sortedSeats && index + 1 in sortedSeats && index - 1 in sortedSeats)
            return index
    }
    return 0
}

private fun String.toSeatId(): Int = substring(0, 7)
    .replace("F", "0")
    .replace("B", "1")
    .toInt(2) * 8 +
        substring(7, 10)
            .replace("L", "0")
            .replace("R", "1")
            .toInt(2)
