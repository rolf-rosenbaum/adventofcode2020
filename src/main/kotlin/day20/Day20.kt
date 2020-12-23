package day20

import kotlin.math.abs

fun main() {

    val input = readInputData()

    val result1 = partOne()
    println("Result1: $result1")

    val result2 = partTwo()
    println("Result2: $result2")
}

typealias Staple = List<Tile>

data class Tile(val id: Long, val pixels: Set<Pixel>) {
    fun prettyPrint() {
        for (y in 0..9) {
            for (x in 0..9) {
                if (pixels.contains(Pixel(x, y))) print("#") else print(".")
            }
            println()
        }
    }

    fun rotateRight() = Tile(id, pixels.map { Pixel(9 - it.y - 1, it.x) }.toSet())

    fun flipHorizontally() = Tile(id, pixels.map { Pixel(it.x, abs(it.y - 9)) }.toSet())

    fun flipVertically() = Tile(id, pixels.map { Pixel(abs(9 - it.x), it.y) }.toSet())


    fun fitsToLeft(other: Tile): Boolean =
        pixels.filter { it.x == 9 }.map { it.y }.sorted() == other.pixels.filter { it.x == 0 }.map { it.y }.sorted()

    fun fitsToTop(other: Tile): Boolean =
        pixels.filter { it.y == 9 }.sortedBy { it.x } == other.pixels.filter { it.y == 0 }.sortedBy { it.x }

}

data class Pixel(val x: Int, val y: Int)

val input = readInputData()
val staple: Staple = readStaple(input)

fun Staple.rotate(): Staple {
    val tmp = first()
    return drop(1) + tmp
}

fun List<List<Tile>>.prettyPrint() {

}


fun readStaple(input: String): Staple {
    val result = mutableListOf<Tile>()
    val iterator = input.iterator()

    val blocks = input.split("\n\n")
    blocks.forEach { block ->
        val lines = block.split("\n")
        val id = lines.first().split(" ", ":").component2().toLong()
        val pixels = mutableSetOf<Pixel>()
        lines.drop(1).take(10).forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == '#') {
                    pixels.add(Pixel(x, y))
                }
            }
        }
        result.add(Tile(id, pixels))
    }
    return result
}

fun repairImage(tiles: Staple, image: MutableList<MutableList<Tile>>, x: Int, y: Int): Long {
    println("Recursion depth: ${y * 12 + x}")
    if (tiles.isEmpty()) { // we're done
        image[0].forEach { it.prettyPrint() }
        return 100
    }

    val newX = (x + 1) % 12
    val newY = if (x == 11) y + 1 else y
    tiles.forEach { tile ->
        var flippedHorizontally = false
        var flippedVertically = false
        var flippedBoth = false
        var candidate = tile
        for (i in 0..3) {
            while (!(flippedVertically && flippedHorizontally && flippedBoth)) {
                if (x == 0) {
                    if (y == 0) {
                        image[x].add(y, candidate)
                        return repairImage(tiles.filterNot { it == candidate }, image, newX, newY)
                    } else {
                        if (candidate.fitsToTop(image[x][y - 1])) {
                            image[x].add(y, candidate)
                            return repairImage(tiles.filterNot { it == candidate }, image, newX, newY)
                        }
                    }
                } else {
                    if (y == 0) {
                        if (candidate.fitsToLeft(image[x - 1][y])) {
                            image[x].add(y, candidate)
                            return repairImage(tiles.filterNot { it == candidate }, image, newX, newY)
                        }
                    } else {
                        if (candidate.fitsToLeft(image[x - 1][y]) && candidate.fitsToTop(image[x][y - 1])) {
                            image[x].add(y, candidate)
                            return repairImage(tiles.filterNot { it == candidate }, image, newX, newY)
                        }
                    }
                }
                if (!flippedHorizontally) {
                    candidate = candidate.flipHorizontally()
                    flippedHorizontally = true
                } else {
                    if (!flippedVertically) {
                        candidate = candidate.flipHorizontally().flipVertically()
                        flippedVertically = true
                    } else if (!flippedBoth) {
                        candidate = candidate.flipHorizontally()
                        flippedBoth = true
                    }
                }
            }
            candidate = candidate.rotateRight()
        }
    }
    return -1
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day20.txt")
        .reader()
        .readText()

fun partOne(): Long {
    readStaple(input)
    println(staple.size)
    val image = mutableListOf(mutableListOf<Tile>())
    for (i in 0..11) {
        image.add(mutableListOf())
    }

    repeat(staple.size) {
        println(repairImage(staple.rotate(), image, 0, 0))
    }
    return -1
}


fun partTwo(): Int = 0
