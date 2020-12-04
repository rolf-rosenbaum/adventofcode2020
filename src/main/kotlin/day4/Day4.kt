package day4

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

val requiredFields = listOf("byr:", "iyr", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")

data class Passport(
    val byr: Int,
    val iyr: Int,
    val eyr: Int,
    val hgt: Height,
    val hcl: String,
    val ecl: String,
    val pid: String
) {
    fun isValid(): Boolean =
        byr in 1920..2002 &&
                iyr in 2010..2020 &&
                eyr in 2020..2030 &&
                hcl.matches(Regex("#[0-9a-f]{6}")) &&
                ecl in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") &&
                pid.length == 9 && pid.isAllDigits() &&
                hgt.isValid()

    companion object {
        fun create(input: Map<String, String>): Passport = Passport(
            byr = input["byr"]?.replace("byr:", "")?.toInt() ?: 0,
            iyr = input["iyr"]?.replace("iyr:", "")?.toInt() ?: 0,
            eyr = input["eyr"]?.replace("eyr:", "")?.toInt() ?: 0,
            hcl = input["hcl"]?.replace("hcl:", "") ?: "",
            hgt = Height.create(input["hgt"]?.replace("hgt:", "")!!),
            ecl = input["ecl"]?.replace("ecl:", "") ?: "",
            pid = input["pid"]?.replace("pid:", "") ?: ""
        )
    }
}

data class Height(
    val height: Int,
    val measure: String
) {
    companion object {
        fun create(input: String): Height =
            Height(input!!.substring(0, input.length - 2).toInt(), input.substring(input.length - 2))
    }

    fun isValid(): Boolean {
        return if (measure == "in") height in 59..76 else if (measure == "cm") height in 150..193 else false
    }
}

private fun String.isAllDigits(): Boolean {
    this.forEach {
        if (!it.isDigit())
            return false
    }
    return true
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day4.txt")
        .reader()
        .readText().split("\n\n")


fun partOne(passportString: List<String>): Int {
    return passportString.count {
        it.containsAll(requiredFields)
    }
}

private fun String.containsAll(strings: List<String>): Boolean {
    strings.forEach {
        if (!this.contains(it)) return false
    }
    return true
}

fun partTwo(strings: List<String>): Int {

    return strings.filter { it.containsAll(requiredFields) }
        .map {
            Passport.create(
                it.split(" ", "\n")
                    .associateBy { s ->  s.split(":").first() }
            )
        }
        .count { it.isValid() }
}

