package day3

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day3.txt")
        .reader()
        .readLines()

fun partOne(slope: List<String>): Int {
    return slope.traverse(3)
}

fun partTwo(slope: List<String>): Long {
    val result = listOf(1, 3, 5, 7).map {
        slope.traverse(it)
    }.reduce { acc, i -> acc * i }

    var tmp = 0
    var pos = 0
    slope.forEachIndexed { index, row ->
        if (index % 2 == 0) {
            if (row[pos % row.length] == '#') {
                tmp++
            }
            pos++
        }
    }
    return result.toLong() * tmp
}

private fun List<String>.traverse(steps: Int): Int {
    var treeCount = 0


    forEachIndexed { index, row ->
        if (row[(index * steps) % (row.length)] == '#') {
            treeCount++
        }
    }
    return treeCount
}
