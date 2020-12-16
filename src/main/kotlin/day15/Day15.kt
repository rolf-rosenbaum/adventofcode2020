package day15

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day15.txt")
        .reader()
        .readText().split(",").map(String::toInt)

fun partOne(input: List<Int>) =  game(input).drop(2019).first()


fun partTwo(input: List<Int>) = game(input).drop(29999999).first()

fun game(input: List<Int>): Sequence<Int> = sequence {
    yieldAll(input)

    val mem = input.mapIndexed{index, number -> number to index}.toMap().toMutableMap()
    var turns = input.size
    var nextNumber = 0
    while (true) {
        yield(nextNumber)
        val lastTime = mem[nextNumber] ?: turns
        mem[nextNumber] = turns
        nextNumber = turns++ - lastTime
    }
}
