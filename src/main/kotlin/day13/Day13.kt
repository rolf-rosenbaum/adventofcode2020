package day13

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo()
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day13.txt")
        .reader()
        .readLines()

fun partOne(input: List<String>): Int {
    val departureTime = input.first().toInt()

    val schedules = input[1].split(",")
        .filterNot { it == "x" }
        .map { it.toInt() }
    val maxId = schedules.maxOf { it }
    val max = departureTime + maxId
    val myBus = schedules.minByOrNull { id ->
        (departureTime..max).first {
            it % id == 0
        }
    }!!
    val delay = ((departureTime / myBus) + 1) * myBus - departureTime
    return myBus * delay
}
private val indexedBusses: List<IndexedBus> = readInputData()
    .last()
    .split(",")
    .mapIndexedNotNull { index, s -> if (s == "x") null else IndexedBus(index, s.toLong()) }

fun partTwo(): Long {
    var stepSize = indexedBusses.first().interval
    var time = 0L
    indexedBusses.forEach { (index, interval) ->
        while ((time + index) % interval != 0L) {
            time += stepSize
        }
        stepSize *= interval
    }
    return time
}

data class IndexedBus(val index: Int, val interval: Long)