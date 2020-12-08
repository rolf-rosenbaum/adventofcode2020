package day7

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day7.txt")
        .reader()
        .readLines()

fun partOne(input: List<String>): Int = bagsWithGold(readBags(input).first, "shiny gold", mutableSetOf())


fun partTwo(input: List<String>) = nestedBags(readBags(input).second)

private fun bagsWithGold(bagsIn: Bags, colour: Colour, containingColours: Colours): Int {

    bagsIn[colour]?.forEach { bagsWithGold(bagsIn, it, containingColours + it) }

    return containingColours.size
}


private fun nestedBags(bags: BagsCount, colour: String = "shiny gold"): Int =
    bags.getValue(colour).scan(0) { acc, it ->
        (acc + it.first) + it.first * nestedBags(bags, it.second)
    }.last()

private fun readBags(inputList: List<String>): Pair<Bags, BagsCount> {

    val bagsIn = mutableMapOf<Colour, Colours>().withDefault { mutableSetOf() }
    val bags = mutableMapOf<Colour, ColourCounts>().withDefault { mutableListOf() }

    inputList.forEach { line ->
        val colour = line.split(" bags").first()
        "(\\d+) (.+?) bags?[,.]".toRegex().findAll(line.split("contain ")[1]).forEach { b ->
            val count = b.destructured.component1()
            val colourInside = b.destructured.component2()
            bagsIn[colourInside] = bagsIn.getValue(colourInside) + colour
            bags[colour] = bags.getValue(colour) + Pair(count.toInt(), colourInside)
        }
    }

    return bagsIn to bags
}

operator fun <T> MutableSet<T>.plus(e: T): MutableSet<T> {
    this.add(e)
    return this
}

operator fun <T> MutableList<T>.plus(e: T): MutableList<T> {
    this.add(e)
    return this
}

typealias Colour = String
typealias ColourCount = Pair<Int, Colour>
typealias ColourCounts = MutableList<ColourCount>
typealias Colours = MutableSet<Colour>
typealias Bags = Map<Colour, Colours>
typealias BagsCount = Map<Colour, ColourCounts>

