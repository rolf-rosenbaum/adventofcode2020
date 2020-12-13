package day11

fun main() {
//    val result1 = partOne()
//    println("Result1: $result1")

    val result2 = partTwo()
    println("Result2: $result2")
}

val seats = seats(readInputData())
const val maxX = 99
const val maxY = 98


private fun readInputData(): List<String> =
    {}.javaClass.classLoader.getResourceAsStream("test.txt")
        .reader()
        .readLines()

fun partOne(): Int {

    var now = seats
    var next = now.nextGeneration()

    var gens = 1
    while (true) {
        println("Generation: $gens")
        println("Count: ${now.count()}")
        if (next == now) {
            return now.filterIsInstance<Cell.Seat>().filter { it.occupied }.count()
        }
        gens++
        now = next
        next = now.nextGeneration()
    }

    return -1
}

fun partTwo(): Int {

    var now = seats
    var next = now.nextGeneration2()

    var gens = 1
    while (true) {
        println("Generation: $gens")
        println("Count: ${now.size}")
        if (next == now) {
            return now.filterIsInstance<Cell.Seat>()
                .filter { it.occupied }
                .count()
        }
        gens++
        now = next
        next = now.nextGeneration2()
    }

    return -1
}

fun seats(input: List<String>): List<Cell> {
    val seats = mutableListOf<Cell>()
    input.mapIndexed { y, line ->
        for (x in line.indices) {
            if (line[x] == 'L') seats.add(Cell.Seat(x, y))
            else seats.add(Cell.Floor(x, y))
        }
    }
    return seats
}

fun List<Cell>.isOccupiedAt(x: Int, y: Int): Boolean {
    return contains(Cell.Seat(x, y, true))
}

fun List<Cell>.nextGeneration(): List<Cell> {
    val next = mutableListOf<Cell>()
    next.addAll(this)
    for (x in 0 until maxX) {
        for (y in 0..maxY) {
            if (isSeat(x, y)) {
                if (countOccupiedNeighbors(Cell.Seat(x, y)) == 0) {
                    next.add(Cell.Seat(x, y, true))
                }
            }
        }
    }
    return next.filter {
        countOccupiedNeighbors(it) < 4
    }
}

fun List<Cell>.nextGeneration2(): List<Cell> {
    val next = mutableSetOf<Cell>()
    next.addAll(this)
    for (x in 0 until maxX) {
        for (y in 0..maxY) {
            if (isSeat(x, y)) {
                if (countVisibleOccupiedSeats(Cell.Seat(x, y)) == 0)
                    next.remove(Cell.Seat(x, y))
                next.add(Cell.Seat(x, y, true))
            }
        }
    }
    return next.filterIsInstance<Cell.Seat>()
        .filter {
            countVisibleOccupiedSeats(it) < 5
        }
}

fun isSeat(x: Int, y: Int): Boolean = seats.contains(Cell.Seat(x, y))

private fun List<Cell>.countVisibleOccupiedSeats(cell: Cell): Int {
    val directions = listOf(
        Vector(0, -1),
        Vector(1, -1),
        Vector(1, 0),
        Vector(1, 1),
        Vector(0, 1),
        Vector(-1, 1),
        Vector(-1, 0),
        Vector(-1, -1),
    )
    var visibleSeats = mutableListOf<Cell.Seat>()

    directions.forEach { direction ->
        val seat = this.filterIsInstance<Cell.Seat>()
            .firstOrNull { c ->
                c.isInDirectionFrom(cell, direction)
            }
        if (seat != null) {
            visibleSeats.add(seat)
        }
    }
    return visibleSeats.count { it.occupied }
}

private fun List<Cell>.countOccupiedNeighbors(cell: Cell): Int {
    return this.filterIsInstance<Cell.Seat>()
        .filter { it.isNeighbor(cell) }
        .filter { it.occupied }
        .count()
}

private fun Cell.isNeighbor(otherCell: Cell): Boolean {
    return this != otherCell && x - otherCell.x in -1..1 && y - otherCell.y in -1..1
}

data class Vector(val x: Int, val y: Int)

sealed class Cell(open val x: Int, open val y: Int) {
    class Floor(x: Int, y: Int) : Cell(x, y)
    data class Seat(override val x: Int, override val y: Int, val occupied: Boolean = false) : Cell(x, y)

    operator fun minus(c: Cell): Cell = Cell.Seat(x - c.x, y - c.y)

    fun isInDirectionFrom(other: Cell, direction: Vector): Boolean {
        if (this == other) return false

        val v = other - this
        if (direction == Vector(0, -1)) return v.x == 0 && v.y < 0
        if (direction == Vector(1, -1)) return v.x > 0 && v.y < 0 && v.x == -v.y
        if (direction == Vector(1, 0)) return v.x > 0 && v.y == 0
        if (direction == Vector(1, 1)) return v.x > 0 && v.y > 0 && v.x == v.y
        if (direction == Vector(0, 1)) return v.x == 0 && v.y > 0
        if (direction == Vector(-1, 1)) return v.x < 0 && v.y > 0 && v.x == -v.y
        if (direction == Vector(-1, 0)) return v.x < 0 && v.y == 0
        if (direction == Vector(-1, -1)) return v.x < 0 && v.y < 0 && v.x == v.y

        return false
    }


}

