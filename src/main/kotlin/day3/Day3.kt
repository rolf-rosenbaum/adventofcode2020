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

fun partOne(slope: List<String>): Long = traverse(slope, 3)

fun partTwo(slope: List<String>): Long = listOf(1, 3, 5, 7)
    .map {
        traverse(slope, it)
    }
    .reduce { acc, i -> acc * i } * traverse(slope, 1, 2)


private fun traverse(slope: List<String>, horizontalStep: Int, verticalStep: Int = 1): Long {
    var treeCount = 0
    var pos = 0
    slope.forEachIndexed { index, row ->
        if (index % verticalStep == 0) {
            if (row[pos % row.length] == '#') {
                treeCount++
            }
            pos+=horizontalStep
        }
    }
    return treeCount.toLong()
}
