fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { it ->
            val result = it.split("").mapNotNull { it.toIntOrNull() }
            result.first() * 10 + result.last()
        }
    }

    fun preprocessFromFront(it: String): String {
        var processing = true
        var inputString = it
        while (processing) {
            var front = ""
            var back = ""
            for (i in 0..inputString.length) {
                front = inputString.take(i)
                    .replace("one", "1")
                    .replace("two", "2")
                    .replace("three", "3")
                    .replace("four", "4")
                    .replace("five", "5")
                    .replace("six", "6")
                    .replace("seven", "7")
                    .replace("eight", "8")
                    .replace("nine", "9")
                back = inputString.drop(i)
                if (inputString.length != front.length + back.length)
                    break
            }
            if (inputString.length == (front.length + back.length))
                processing = false
            else
                inputString = front + back
        }

        return inputString
    }

    fun preprocessFromBack(it: String): String {
        var processing = true
        var inputString = it
        while (processing) {
            var front = ""
            var back = ""
            for (i in 0..inputString.length) {
                back = inputString.takeLast(i)
                    .replace("one", "1")
                    .replace("two", "2")
                    .replace("three", "3")
                    .replace("four", "4")
                    .replace("five", "5")
                    .replace("six", "6")
                    .replace("seven", "7")
                    .replace("eight", "8")
                    .replace("nine", "9")
                front = inputString.dropLast(i)
                if (inputString.length != front.length + back.length)
                    break
            }
            if (inputString.length == (front.length + back.length))
                processing = false
            else
                inputString = front + back
        }

        return inputString
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { it ->
            val resultFront = preprocessFromFront(it).split("").mapNotNull { it.toIntOrNull() }
            val resultBack = preprocessFromBack(it).split("").mapNotNull { it.toIntOrNull() }
            println(resultFront.first() * 10 + resultBack.last())
            resultFront.first() * 10 + resultBack.last()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
