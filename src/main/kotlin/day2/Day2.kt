package day2

fun main() {
    val pwdList = readInputData()

    val result1 = partOne(pwdList)
    println("Result1: $result1")

    val result2 = partTwo(pwdList)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day2.txt")
        .reader()
        .readLines()
        .map { it.split(" ") }.toPwdList()

fun partOne(pwdList: List<Pair<Rule, String>>): Int {
    return pwdList.count { it.first.matches(it.second) }
}

fun partTwo(pwdList: List<Pair<Rule, String>>): Int {
    return pwdList.count { it.first.matchesPArtTwo(it.second) }
}

fun List<List<String>>.toPwdList() =
    map { entry ->
        val minAndMax = entry.first().split("-")
        Pair<Rule, String>(
            Rule(
                min = minAndMax.first().toInt(),
                max = minAndMax[1].toInt(),
                char = entry[1].first()
            ),
            entry[2]
        )
    }

data class Rule(val char: Char, val min: Int, val max: Int) {
    fun matches(testString: String): Boolean =
        testString.count { it == char } in min..max

    fun matchesPArtTwo(testString: String): Boolean =
        (testString[min - 1] == char) xor (testString[max - 1] == char)
}