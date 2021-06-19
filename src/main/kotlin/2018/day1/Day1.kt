package `2018`.day1

fun main(args: Array<String>) {

    val numbers = readInputData()

    val result1 = partOne(numbers)
    println("Result1: $result1")

    val result2 = partTwo(numbers)
    println("Result2: $result2")
}

private fun readInputData(): List<String> =
    object {}.javaClass.classLoader.getResourceAsStream("2018/day1.txt")
        .reader()
        .readLines()

fun partOne(numbers: List<String>) = numbers.sumBy { it.toInt() }


fun partTwo(numbers: List<String>): Int {
    val seenOnes = mutableSetOf<Int>()
    var sum = 0
    return numbers
        .map { it.toInt() }
        .toInfiniteSequence()
        .map {
            sum += it
            sum
        }.first { !seenOnes.add(sum) }

}

fun <T> List<T>.toInfiniteSequence(): Sequence<T> = sequence {
    while(true) yieldAll(this@toInfiniteSequence)
}