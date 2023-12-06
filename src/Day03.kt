fun main() {
    data class Point(val x: Int, val y: Int)

    data class Engine(val sumOfPartNumbers: Int, val sumOfGearRatios: Int)

    fun parseEngine(input: List<String>): Engine {
        var sumOfPartNumbers = 0
        val gears = mutableMapOf<Point, MutableList<Int>>()

        input.forEachIndexed { y, line ->
            var x = 0
            while (x < line.length) {
                if (!line[x].isDigit()) {
                    x++
                    continue
                }

                val number = line.substring(x).takeWhile { it.isDigit() }

                val top = (x..<x + number.length).map { Point(it, y - 1) }
                val bottom = (x..<x + number.length).map { Point(it, y + 1) }
                val left = (y - 1..y + 1).map { Point(x - 1, it) }
                val right = (y - 1..y + 1).map { Point(x + number.length, it) }

                listOf(top, bottom, left, right).flatten()
                    .filter { it.y in input.indices && it.x in line.indices }
                    .forEach { n ->
                        if (input[n.y][n.x] != '.') {
                            sumOfPartNumbers += number.toInt()
                        }
                        if (input[n.y][n.x] == '*') {
                            gears.getOrPut(n) { mutableListOf() }.add(number.toInt())
                        }
                    }

                x += number.length
            }
        }

        return Engine(
            sumOfPartNumbers,
            gears.filterValues { it.size == 2 }.values.sumOf { it.reduce { acc, i -> acc * i } })
    }

    fun part1(engine: Engine): Int {
        return engine.sumOfPartNumbers
    }

    fun part2(engine: Engine): Int {
        return engine.sumOfGearRatios
    }

    val input = readInput("Day03")
    val engine = parseEngine(input)
    println(part1(engine))
    println(part2(engine))
}