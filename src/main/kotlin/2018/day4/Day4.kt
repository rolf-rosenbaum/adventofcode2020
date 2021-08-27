package `2018`.day4

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

fun readInputData(file: String = "2018/day4.txt"): List<GuardEvent> =
    object {}.javaClass.classLoader.getResourceAsStream(file)
        ?.reader()
        ?.readLines()
        ?.map { it.toGuardEvent() }
        ?.sortedBy { it.timeStamp }
        ?.fillGuardIds()
        ?: throw RuntimeException("input not found")


fun partOne(events: List<GuardEvent>): Int {

    val mostSleepingGuardsId = events.maxByOrNull { events.timeAsleep(it.guardId) }?.guardId!!
    val mostSleptMinute = (0..59).maxByOrNull { minute ->
        events
            .toShifts()
            .filter { it.guardId == mostSleepingGuardsId }
            .count { it.minutes[minute] == '#' }
    }!!

    return mostSleptMinute * mostSleepingGuardsId
}

fun partTwo(input: List<GuardEvent>): Int {

    val result = input.toShifts().groupBy { it.guardId }.map { (id, shifts) ->
        (0..59).maxByOrNull { minute ->
            shifts.count {it.minutes[minute] == '#'}
        } to id
    }.maxByOrNull { (minute, id) ->
        input.toShifts().filter { it.guardId == id }.count {it.minutes[minute!!] == '#'}
    }!!

    return result.first!! * result.second

}

data class GuardEvent(
    val timeStamp: LocalDateTime,
    val guardId: Int? = null,
    val event: Event
)


enum class Event {
    BEGIN_SHIFT, FALL_ASLEEP, WAKE_UP;

    companion object {
        fun fromString(event: String): Event = when (event) {
            "wakes" -> WAKE_UP
            "falls" -> FALL_ASLEEP
            "Guard" -> BEGIN_SHIFT
            else -> throw IllegalArgumentException()
        }
    }
}

data class Shift(
    val guardId: Int,
    val date: LocalDate,
    val minutes: MutableList<Char>
)

fun List<GuardEvent>.toShifts(): List<Shift> {

    return this.groupBy { it.timeStamp.toLocalDate() }
        .flatMap { (date, events) ->
            events.groupBy { it.guardId }
                .flatMap { (_, events) ->
                    val foo = events.filterNot { it.event == Event.BEGIN_SHIFT }.map { it.timeStamp.minute to it.event }
                    val iter = foo.iterator()
                    val computedMinutes = mutableListOf<Char>()
                    var current = 0
                    while (iter.hasNext()) {
                        val bar = iter.next()
                        if (bar.second == Event.FALL_ASLEEP) {
                            (current until bar.first).map { computedMinutes.add('.') }
                        } else {
                            (current until bar.first).map { computedMinutes.add('#') }
                        }
                        current = bar.first
                    }
                    if (computedMinutes.size < 60) {
                        (computedMinutes.size until 60).map { computedMinutes.add('.') }
                    }
                    events.map {
                        Shift(
                            guardId = it.guardId!!,
                            date = date,
                            minutes = computedMinutes
                        )
                    }
                }
        }.distinct()
}

fun List<GuardEvent>.timeAsleep(guardId: Int?): Int {
    return asSequence().filter { it.guardId == guardId }
        .filter { it.timeStamp.hour == 0 }
        .filterNot { it.event == Event.BEGIN_SHIFT }
        .zipWithNext()
        .filter { it.first.event == Event.FALL_ASLEEP }
        .sumBy { it.second.timeStamp.minute - it.first.timeStamp.minute }
}

fun List<GuardEvent>.fillGuardIds(): List<GuardEvent> {
    var currentId = first().guardId
    return map {
        if (it.guardId == null) {
            it.copy(guardId = currentId)
        } else {
            currentId = it.guardId
            it
        }
    }
}

fun String.toGuardEvent(): GuardEvent {
    val foo = this.split("] ")
    val timeStamp = LocalDateTime.parse(foo.first().substring(1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    val eventString = foo[1]
    val bar = eventString.split(" ")
    val eventType = Event.fromString(bar.first())
    val guardId = if (eventType == Event.BEGIN_SHIFT) bar[1].substring(1).toInt() else null
    return GuardEvent(timeStamp = timeStamp, event = eventType, guardId = guardId)
}