package day6

fun main() {

    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day6.txt")
        .reader()
        .readText()
        .split("\n\n")

fun partOne(questions: List<String>): Int =
    questions.fold(0) { count: Int, s ->
        count + ('a'..'z').count {
            s.contains(it)
        }
    }

fun partTwo(questions: List<String>) =
    questions.fold(0) { count, questions ->
        count + ('a'..'z').count {
            questions.split("\n").all { question -> question.contains(it) }
        }
    }
