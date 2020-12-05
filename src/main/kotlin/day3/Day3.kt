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

fun partOne(forrest: List<String>): Long = traverse(forrest, 3)

fun partTwo(forrest: List<String>): Long = listOf(1, 3, 5, 7)
    .map {
        traverse(forrest, it)
    }.reduce { acc, i -> acc * i } * traverse(forrest, 1, 2)


private fun traverse(forrest: List<String>, horizontalStep: Int, verticalStep: Int = 1): Long {
    var treeCount = 0
    var pos = 0
    forrest.forEachIndexed { index, row ->
        if (index % verticalStep == 0) {
            if (row[pos % row.length] == '#') {
                treeCount++
            }
            pos+=horizontalStep
        }
    }
    return treeCount.toLong()
}
