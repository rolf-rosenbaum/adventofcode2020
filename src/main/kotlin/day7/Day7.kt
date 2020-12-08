package day7

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo()
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day7.txt")
        .reader()
        .readLines()

fun partOne(input: List<String>): Int {
    val bags = input.map {
        it.toBag()
    }

    bags.groupBy {
        it.color
    }.map { entry ->
        entry.value.forEach { bag ->
            bag.bagsContained.forEach { (innerBag, count) ->
//                innerBag.bagsContained.
            }
        }
    }

    return bags.count {
        it.containsBagOfColor("shiny gold")
    }
}

fun partTwo() = 0

private fun String.toBag(): Bag {
    val words = split(" ")
    return Bag(
        color = words[0] + " " + words[1],
        bagsContained = containedBags(words.subList(4, words.size))
    )
}

private fun containedBags(restWords: List<String>): MutableList<Pair<Bag, Int>> {

    if (restWords[0] == "no") return mutableListOf()

    var i = 0
    var resultList = mutableListOf<Pair<Bag, Int>>()
    do {
        resultList.add(Bag(restWords[i + 1] + " " + restWords[i + 2]) to restWords[i].toInt())
        i += 4
    } while (!restWords[i - 1].endsWith("."))

    return resultList
}

data class Bag(
    val color: String,
    val bagsContained: MutableList<Pair<Bag, Int>> = mutableListOf()
) {
    fun containsBagOfColor(color: String): Boolean {
        return bagsContained.any { it.first.color == color }
    }
}

fun List<Bag>.containsBagOfColor(color: String): Boolean {
    this.forEach { bag ->
        return if (bag.containsBagOfColor(color)) {
            true
        } else {
            bag.bagsContained
                .map { it.first }.containsBagOfColor(color)
        }
    }
    return false
}