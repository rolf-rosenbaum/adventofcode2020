package day5

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")
//
//    println("" + toSeatId("BFFFBBFRRR") + "(567)")
//    println("" + toSeatId("FFFBBBFRRR") + "(119)")
//    println("" + toSeatId("BBFFBBFRLL") + "(820)")
//    println("" + toSeatId("FBFBBFFRLR") + "(357)")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day5.txt")
        .reader()
        .readLines()


fun partOne(passes: List<String>): Int {

    val foo = passes.maxByOrNull { s ->
        toSeatId(s)
    }
    return toSeatId(foo!!)


}

private fun toSeatId(s: String): Int {
    val row = s.substring(0, 7)
        .replace("F", "0")
        .replace("B", "1")
        .toInt(2)

    val col = s.substring(7, 10)
        .replace("L", "0")
        .replace("R", "1")
        .toInt(2)

    return row * 8 + col
}

fun partTwo(passes: List<String>): Int {

    val sortedSeats = passes.map {
        toSeatId(it)
    }.sorted()

    (0..sortedSeats.size).forEachIndexed { index, i ->
        if (index !in sortedSeats && index+1 in sortedSeats && index -1 in sortedSeats)
            return index
    }
    return 0
}
