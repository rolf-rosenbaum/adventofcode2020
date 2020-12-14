package day14

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

typealias Mem = MutableMap<Long, Long>
typealias MemVal = String

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day14.txt")
        .reader()
        .readLines()

fun partOne(input: List<String>): Long {
    var mask: String = ""
    val memory: Mem = mutableMapOf()
    input.forEach {
        val instr = it.split(" = ")
        if (instr.first() == "mask") {
            mask = instr[1]
        } else {
            val mem = instr.first().split("[", "]")
            val address = mem[1].toLong()
            memory[address] = instr[1].applyMask(mask)
        }
    }
    return memory.values.reduce { sum, value -> sum + value }

}

private fun String.applyMask(mask: String): Long = toLong()
    .toString(2)
    .padStart(36, '0')
    .mapIndexed { index, c ->
        if (mask[index] != 'X') mask[index] else c
    }.joinToString("").toLong(2)

private fun String.applyMask2(mask: String): List<Long> {
    var addresses = mutableListOf<Long>()

    return replaceXs(
        toLong().toString(2).padStart(36, '0')
            .mapIndexed { index, c ->
                if (mask[index] == '0') c
                else mask[index]
            }.joinToString("")
    ).map { it.toLong(2) }
}

fun partTwo(input: List<String>): Long {
    val memory: Mem = mutableMapOf()
    var mask = ""
    input.forEach {
        val instr = it.split(" = ")
        if (instr.first() == "mask") {
            mask = instr[1]
        } else {
            val mem = instr.first().split("[", "]")
            val addresses = mem[1].applyMask2(mask)
            addresses.forEach { addr -> memory[addr] = instr[1].toLong() }
        }
    }
    return memory.values.reduce { sum, value -> sum + value }
}

private fun replaceXs(original: String): List<String> {
    return if (original.contains("X")) {
        replaceXs(original.replaceFirst("X", "0")) +
                replaceXs(original.replaceFirst("X", "1"))
    } else {
        listOf(original)
    }
}
