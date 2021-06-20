package `2018`.day3

import java.lang.RuntimeException

fun main(args: Array<String>) {

    val claims = readInputData()

    val result1 = partOne(claims)
    println("Result1: $result1")

    val result2 = partTwo(claims)
    println("Result2: $result2")
}

typealias Square = Pair<Int, Int>

private fun readInputData(): List<Claim> =
    object {}.javaClass.classLoader.getResourceAsStream("2018/day3.txt")
        ?.reader()
        ?.readLines()
        ?.map { line ->
            line.toClaim()
        }
        ?: throw RuntimeException("input not found")


fun partOne(claims: List<Claim>): Int {
    return findDoubleClaimedQuares(claims).size
}

fun findDoubleClaimedQuares(claims: List<Claim>): Set<Square> {
    val doubleClaimed = mutableSetOf<Square>()
    val claimed = mutableSetOf<Square>()
    for (claim in claims) {
        claim.squares.forEach {
            if (!claimed.add(it)) {
                doubleClaimed.add(it)
            }
        }
    }
    return doubleClaimed

}

fun partTwo(claims: List<Claim>): String {
    val doubleClaimed = findDoubleClaimedQuares(claims)

    return claims.first {
        it.squares.none { square ->
            doubleClaimed.contains(square)
        }
    }.id
}

data class Claim(
    val id: String,
    val squares: List<Square>

)

fun String.toClaim(): Claim {
    val foo = this.split(" @ ")
    val id = foo.first().substring(1)
    val bar = foo[1].split(": ")
    val left = bar.first().split(",").first().toInt()
    val top = bar.first().split(",")[1].toInt()
    val width = bar[1].split("x").first().toInt()
    val height = bar[1].split("x")[1].toInt()
    val squares = mutableListOf<Pair<Int, Int>>()
    (left until width + left).forEach { x ->
        (top until top + height).forEach { y ->
            squares.add(Pair(x, y))
        }
    }

    return Claim(id = id, squares)

}
