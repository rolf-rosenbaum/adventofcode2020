package day17

fun main() {

    val input = readInputData()
    val source = initSource(input)

    val result1 = partOne(source)
    println("Result1: $result1")

    val result2 = partTwo(source)
    println("Result2: $result2")
}

typealias EnergySource = Set<Cell>

private fun initSource(lines: List<String>): EnergySource {
    val result = mutableSetOf<Cell>()
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
            if (c == '#')
                result.add(Cell(x, y))
        }
    }
    return result
}

private fun EnergySource.countNeighbors(cell: Cell): Int {
    return count { it.isNeighbor(cell) }
}

var cycle: Int = 0
fun EnergySource.next(): EnergySource {
    println("Generation: $cycle")
    prettyPrint()
    cycle++
    val next = mutableSetOf<Cell>()
    return filter { countNeighbors(it) in 2..3 }.toSet() + populateDeadCells()
}


private fun EnergySource.populateDeadCells(): Set<Cell> {
    val newCells = mutableSetOf<Cell>()
    newCells.addAll(findAllDeadNeighbors().filter { countNeighbors(it) == 3 })
    return newCells
}

private fun EnergySource.findAllDeadNeighbors(): Set<Cell> {
    val candidates = mutableSetOf<Cell>()

    forEach { cell ->
        candidates.addAll(findDeadNeighbors(cell))
    }
    return candidates
}

private fun EnergySource.findDeadNeighbors(cell: Cell): Set<Cell> {
    val candidates = mutableSetOf<Cell>()
    (cell.x - 1..cell.x + 1).forEach { x ->
        (cell.y - 1..cell.y + 1).forEach { y ->
            (cell.z - 1..cell.z + 1).forEach { z ->
                (cell.w - 1..cell.w + 1).forEach { w ->
                    if (!contains(Cell(x, y, z, w))) {
                        candidates.add(Cell(x, y, z, w))
                    }
                }
            }
        }
    }
    return candidates
}

private fun EnergySource.prettyPrint() {
    val minLayer = minByOrNull { it.z }?.z ?: 0
    val minX = minByOrNull { it.x }?.x ?: 0
    val minY = minByOrNull { it.y }?.y ?: 0
    val maxLayer = maxByOrNull { it.z }?.z ?: 0
    val maxX = maxByOrNull { it.x }?.x ?: 0
    val maxY = maxByOrNull { it.y }?.y ?: 0

    for (layer in minLayer..maxLayer) {
        println("Layer: $layer")
        for (y in minY..maxY) {
            var line = ""
            for (x in minX..maxX) {
                line += if (contains(Cell(x, y, layer))) "#" else "."
            }
            println(line)
        }
        println()
    }
}


private fun readInputData(): List<String> =
    {}.javaClass.classLoader.getResourceAsStream("day17.txt")
        .reader()
        .readLines()

fun partOne(source: Set<Cell>): Int = generateSequence(source) { it.next() }.drop(6).first().count()

fun partTwo(input: Set<Cell>): Int = 0

data class Cell(val x: Int, val y: Int, val z: Int = 0, val w: Int = 0) {
    fun isNeighbor(other: Cell): Boolean {
        return this != other
                && other.x in x - 1..x + 1
                && other.y in y - 1..y + 1
                && other.z in z - 1..z + 1
                && other.w in w - 1..w + 1
    }
}