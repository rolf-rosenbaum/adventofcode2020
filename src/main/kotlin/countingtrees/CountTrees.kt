package countingtrees

fun main() {

    val input = readInputData()

    val result1 = countTrees(input)
    println("Result1: $result1")

}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("input.txt")
        .reader()
        .readLines()

fun countTrees(slope: List<String>): Int = -1
