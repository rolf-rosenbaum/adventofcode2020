package day22

fun main() {
    val result1 = partOne()
    println("Result1: $result1")

    val result2 = partTwo()
    println("Result2: $result2")
}

private val deck1 =
    listOf(4, 14, 5, 49, 3, 48, 41, 39, 18, 15, 46, 23, 32, 16, 19, 27, 47, 17, 29, 26, 33, 6, 10, 38, 45)
private val deck2 =
    listOf(1, 24, 7, 44, 20, 40, 42, 50, 37, 21, 43, 9, 12, 8, 34, 13, 28, 36, 25, 35, 22, 2, 11, 30, 31)

fun play(deck1: List<Int>, deck2: List<Int>): Long {

    if (deck1.isEmpty()) {
        return deck2.reversed().foldIndexed(0) { index, acc, i -> acc + i * (index + 1) }
    }
    if (deck2.isEmpty()) {
        return deck1.reversed().foldIndexed(0) { index, acc, i -> acc + i * (index + 1) }
    }
    val deck1First = deck1.first()
    val deck2First = deck2.first()
    return if (deck1First > deck2First) {
        play(deck1.drop(1) + deck1First + deck2First, deck2.drop(1))
    } else {
        play(deck1.drop(1), deck2.drop(1) + deck2First + deck1First)

    }
}

fun partOne(): Long {
    return play(deck1, deck2)
}

fun partTwo(): Int {
    return 0
}
