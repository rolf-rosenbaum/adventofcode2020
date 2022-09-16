package `2018`.day6

import java.awt.Point
import kotlin.math.abs


typealias Field = Set<Coordinate>


fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

fun readInputData(file: String = "2018/day6.txt") =
    object {}.javaClass.classLoader.getResourceAsStream(file)
        .reader()
        .readLines()
        .asSequence()
        .map { it.toCoordinate() }
        .sortedBy { it.y }
        .sortedBy { it.x }
        .toSet()


fun partOne(field: Field): Int {
    val xRange = field.minOf { it.x }..field.maxOf { it.x }
    val yRange = field.minOf { it.y }..field.maxOf { it.y }
    val infinite: MutableSet<Coordinate> = mutableSetOf()

    return xRange.asSequence().flatMap { x ->
        yRange.asSequence().map { y ->
            val closest = field.map { it to it.distanceFrom(x, y) }.sortedBy { it.second }.take(2)
            if (field.isOnEdge(x, y)) {
                infinite.add(closest[0].first)
            }
            closest[0].first.takeUnless { closest[0].second == closest[1].second }
        }
    }
        .filterNot { it in infinite }
        .groupingBy { it }
        .eachCount()
        .maxBy { it.value }!!
        .value
}

fun partTwo(field: Field): Int {
    TODO()
}

data class Coordinate(
    val x: Int,
    val y: Int
) {
    fun distanceFrom(x: Int, y: Int): Int {
        return abs(this.x - x) + abs(this.y - y)
    }
}

fun Field.isOnEdge(x: Int, y: Int): Boolean {
    return x == minOf { it.x } || x == maxOf { it.y } || x == minOf { it.y } || y == maxOf { it.y }

}

fun String.toCoordinate(): Coordinate {
    val c = this.split(", ")
    return Coordinate(c.first().toInt(), c[1].toInt())
}

 

