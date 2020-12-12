package day12

import day12.Command.North
import day12.Command.South
import day12.Direction.EAST
import kotlin.math.abs

fun main() {
    val input = readInputData()

    val result1 = partOne(input)
    println("Result1: $result1")

    val result2 = partTwo(input)
    println("Result2: $result2")
}

private fun readInputData() =
    {}.javaClass.classLoader.getResourceAsStream("day12.txt")
        .reader()
        .readLines().map(::toCommand)

fun toCommand(it: String) = when (it.first()) {
    'F' -> Command.Forward(it.substring(1).toInt())
    'L' -> Command.Left(it.substring(1).toInt())
    'R' -> Command.Right(it.substring(1).toInt())
    'E' -> Command.East(it.substring(1).toInt())
    'W' -> Command.West(it.substring(1).toInt())
    'S' -> South(it.substring(1).toInt())
    'N' -> North(it.substring(1).toInt())
    else -> throw IllegalArgumentException()
}

fun partOne(commands: List<Command>): Int {
    val ship: Ship = Ship(Vector(0, 0), EAST)

    commands.forEach { ship.execute(it) }
    return abs(ship.position.x) + abs(ship.position.y)
}

fun partTwo(commands: List<Command>): Int {
    val ship: Ship = Ship(Vector(0, 0), EAST)
    var wayPoint = Vector(10, 1)

    commands.forEach { command ->
        wayPoint = when (command) {
            is Command.Forward -> {
                ship.forward(wayPoint, command.steps)
                wayPoint
            }
            is Command.Left -> wayPoint.rotateCounterClockwise(command.steps)
            is Command.Right -> wayPoint.rotateClockwise(command.steps)
            else -> Vector(
                wayPoint.x + command.direction.x * command.steps,
                wayPoint.y + command.direction.y * command.steps
            )
        }
    }
    return abs(ship.position.x) + abs(ship.position.y)
}

enum class Direction(val x: Int, val y: Int) {
    EAST(1, 0),
    WEST(-1, 0),
    NORTH(0, 1),
    SOUTH(0, -1),
    FORWARD(0, 0),
    LEFT(0, 0),
    RIGHT(0, 0);

    fun turnRight(angle: Int): Direction {
        val directions = listOf(NORTH, EAST, SOUTH, WEST)
        val steps = angle / 90
        val index = directions.indexOf(this)
        return directions[(index + steps) % 4]
    }

    fun turnLeft(angle: Int): Direction {
        val directions = listOf(NORTH, WEST, SOUTH, EAST)
        val steps = angle / 90
        val index = directions.indexOf(this)
        return directions[(index + steps) % 4]
    }
}

data class Vector(val x: Int, val y: Int) {
    fun rotateClockwise(angle: Int): Vector {
        val xs = listOf(x,  y, -x, -y)
        val ys = listOf(y, -x, -y,  x)

        return Vector(xs[(angle / 90) % 4], ys[(angle / 90) % 4])
    }
    fun rotateCounterClockwise(angle: Int): Vector = rotateClockwise(360 - angle)
}

data class Ship(var position: Vector, var direction: Direction) {
    fun execute(command: Command) = when (command) {
        is Command.Forward -> position =
            Vector(position.x + direction.x * command.steps, position.y + direction.y * command.steps)
        is Command.Left -> direction = direction.turnLeft(command.steps)
        is Command.Right -> direction = direction.turnRight(command.steps)
        else -> position =
            Vector(position.x + command.direction.x * command.steps, position.y + command.direction.y * command.steps)
    }
    fun forward(wayPoint: Vector, steps: Int) {
        position = Vector(x = position.x + steps * wayPoint.x, y = position.y + steps * wayPoint.y)
    }
}

sealed class Command(val direction: Direction, open val steps: Int) {
    class North(steps: Int) : Command(Direction.NORTH, steps)
    class South(steps: Int) : Command(Direction.SOUTH, steps)
    class East(steps: Int) : Command(EAST, steps)
    class West(steps: Int) : Command(Direction.WEST, steps)
    class Forward(steps: Int) : Command(Direction.FORWARD, steps)
    class Left(steps: Int) : Command(Direction.LEFT, steps)
    class Right(steps: Int) : Command(Direction.RIGHT, steps)
}
