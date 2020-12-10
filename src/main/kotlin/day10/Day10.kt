package day10

fun main() {

    val input = readInputData()

    val result1 = partOne(input.toMutableList())
    println("Result1: $result1")

    val result2 = partTwo(input.toMutableList())
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day10.txt")
        .reader()
        .readLines().map(String::toInt)


fun partOne(input: MutableList<Int>): Int {
    input.add(0, 0)
    val (ones, threes) = input.sorted()
        .zipWithNext { a, b ->
            b - a
        }.partition {
            it == 1
        }
    return ones.size * (threes.size + 1)

}


fun partTwo(input: MutableList<Int>): Long {
    input.add(0, 0)

    val options = mutableListOf<Long>()
    val differences = input.sorted()
        .zipWithNext { a, b ->
            b - a
        }

    var index = 0
    while (index < differences.size) {
        var count = 0
        while (index < differences.size && differences[index] == 1) {
            count++
            index++
        }
        if (count > 1) {
            val element = 2.pow(count - 1) - 1
            options.add((0..element).map {
                it.toString(2).padStart(count - 1, '0')
            }.filterNot {
                it.contains("000")
            }.count().toLong())
        }
        count = 0
        index++
    }
    return options.fold(1) { acc, i -> acc * i}
}

fun Int.pow(exp:Int): Long {
    return if (exp<0) 0
    else  (0 until exp).fold(1) { acc, i -> acc * 2}
}

