package day11

typealias Seats = Array<CharArray>
typealias Seat = Pair<Int, Int>

fun main() {

    val result1 = partOne()
    println("Result1: $result1")

    val result2 = partTwo()
    println("Result2: $result2")
}

val input = readInputData()

private fun readInputData(): List<String> =
    {}.javaClass.classLoader.getResourceAsStream("day11.txt")
        .reader()
        .readLines()

fun partOne(): Int = findStableMap(4, ::countImmediateNeighbors)

fun partTwo(): Int = findStableMap(5, ::countFarNeighbors)

private val seats: Seats = input.map { it.toCharArray() }.toTypedArray()

private fun findStableMap(tolerance: Int, countFunction: (Seats, Seat) -> Int): Int =
    generateSequence(seats) { it.next(tolerance, countFunction) }
        .zipWithNext()
        .first { it.first.contentDeepEquals(it.second) }
        .first
        .occupied()

private fun Seats.next(tolerance: Int, countFunction: (Seats, Seat) -> Int): Seats =
    this.mapIndexed { x, row ->
        row.mapIndexed { y, spot ->
            val occupied = countFunction(this, Seat(x, y))
            when {
                spot == 'L' && occupied == 0 -> '#'
                spot == '#' && occupied >= tolerance -> 'L'
                else -> spot
            }
        }.toCharArray()
    }.toTypedArray()

private fun countImmediateNeighbors(seats: Seats, seat: Seat): Int =
    neighbors
        .map { it + seat }
        .filter { it in seats }
        .count { seats[it.first][it.second] == '#' }

private fun countFarNeighbors(seats: Seats, seat: Seat): Int =
    neighbors
        .mapNotNull { findSeatOnVector(seats, seat, it) }
        .count { it == '#' }

private fun findSeatOnVector(seats: Seats, seat: Seat, vector: Seat): Char? =
    generateSequence(seat + vector) { it + vector }
        .map { if (it in seats) seats[it.first][it.second] else null }
        .first { it == null || it != '.' }

private fun Seats.occupied(): Int =
    this.sumBy { it.count { row -> row == '#' } }

private operator fun Seats.contains(seat: Seat): Boolean =
    seat.first in this.indices && seat.second in this.first().indices

private operator fun Seat.plus(that: Seat): Seat =
    Seat(this.first + that.first, this.second + that.second)

private val neighbors = sequenceOf(
    -1 to -1, -1 to 0, -1 to 1,
    0 to -1, 0 to 1,
    1 to -1, 1 to 0, 1 to 1
)
