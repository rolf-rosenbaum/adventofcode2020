package day8


fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day8.txt")
        .reader()
        .readLines()


fun partOne(input: List<String>): Int {
    return runOperations(input).first

}

fun partTwo(input: List<String>): Int {
    val instructionsLists: MutableList<List<String>> = mutableListOf(mutableListOf())
    val flipped = mutableListOf<Int>()

    for (i in input.indices) {
        if (i !in flipped) {
            val operation = input[i]
            val oldOp = operation.split(" ")
            if (oldOp.first() == "jmp") {
                val newOp = "nop " + oldOp[1]
                val newList = input.toMutableList()
                newList.removeAt(i)
                newList.add(i, newOp)
                instructionsLists.add(newList)
                flipped.add(i)
            }
        }
    }
    instructionsLists.forEach {
        val result = runOperations(it)
        if (result.second == input.size) {
            return result.first
        }
    }
    return -1
}

private fun runOperations(input: List<String>): Pair<Int, Int> {
    val executedOps = mutableListOf<Int>()
    var result = 0
    var index = 0
    while (index < input.size) {
        if (index in executedOps) {
            return result to index
        }
        if (index !in 0..index) {
            return 0 to 0
        }
        val operation = input[index].split(" ")
        when {
            operation.first() == "acc" -> {
                result += operation[1].toInt()
                executedOps.add(index)
                index++
            }
            operation.first() == "nop" -> {
                executedOps.add(index)
                index++
            }
            operation.first() == "jmp" -> {
                executedOps.add(index)
                index += operation[1].toInt()
            }
        }

    }
    return result to index
}


