package day16

fun main() {

    val input = readInputData()

    val result1 = partOne()
    println("Result1: $result1")

    val result2 = partTwo()
    println("Result2: $result2")
}

val input = readInputData()
val myTicket = input.drop(22).first().split(",").map(String::toInt)
val rules = input.take(20).map { it.toRule() }
val tickets = readOtherTickets(input)
val validTickets = tickets.filter(Ticket::isValid)
val possibleRules = rules.initPossibleRules(validTickets)

typealias Range = Pair<Int, Int>
typealias Ticket = List<Int>

data class Rule(val name: String, val ranges: List<Range>) {
    fun validates(number: Int): Boolean = ranges.any { number in it.first..it.second }
    fun isDepartureRule() = name.startsWith("departure")
}

fun Ticket.isValid(): Boolean = all {
    rules.any { rule ->
        rule.validates(it)
    }
}

fun List<Rule>.applyingRule(validTickets: List<Ticket>, index: Int): Rule {
    val usedFields = mutableListOf<Int>()
    return possibleRules.toSortedMap(compareBy { possibleRules[it]?.size })
        .map { (rule, fields) ->
            rule to fields.first {
                if (!usedFields.contains(it)) {
                    usedFields.add(it)
                    true
                } else false
            }
        }.first { it.second == index }.first
}

private fun List<Rule>.initPossibleRules(
    validTickets: List<Ticket>,
): MutableMap<Rule, MutableList<Int>> {

    val fittingRules: MutableMap<Rule, MutableList<Int>> = mutableMapOf()
    for (index in indices) {
        filter { rule ->
            validTickets.all { ticket ->
                rule.validates(ticket[index])
            }
        }.forEach { rule ->
            if (fittingRules.containsKey(rule)) {
                fittingRules[rule]?.add(index)
            } else {
                fittingRules[rule] = mutableListOf(index)
            }
        }
    }
    return fittingRules
}

fun String.toRule(): Rule {
    val tmp = split(": ")
    val name = tmp.first()
    val ranges = tmp.component2().split(" or ").map {
        val rangeStrings = it.split("-")
        Range(rangeStrings.first().toInt(), rangeStrings.component2().toInt())
    }
    return Rule(name, ranges)
}

private fun readOtherTickets(input: List<String>): List<Ticket> =
    input.drop(25) // first tickets start at line 25
        .map { it.split(",").map(String::toInt) }

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day16.txt")
        .reader()
        .readLines()

fun partOne(): Int {

    return tickets.fold(0) { errorRate, ticket ->
        errorRate + ticket.sumBy { field ->
            if (rules.none { rule -> rule.validates(field) }) field else 0
        }
    }
}

fun partTwo(): Long {
    return rules.indices.map { index ->
        index to rules.applyingRule(validTickets, index) }
        .toMap()
        .filterValues { it.isDepartureRule() }
        .keys.fold(1L) { acc: Long, index -> myTicket[index].toLong() * acc }

}
