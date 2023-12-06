fun main() {
    data class Card(val winningNumbers: Set<Int>, val lotteryNumbers: Set<Int>) {
        fun getPoints(): Int {
            var points = 0
            winningNumbers.forEach {
                if (lotteryNumbers.contains(it)) {
                    if (points == 0) {
                        points++
                    } else {
                        points *= 2
                    }
                }
            }
            return points
        }

        fun getMatchesCount(): Int {
            return winningNumbers.count(lotteryNumbers::contains)
        }
    }

    fun parseInput(input: List<String>): List<Card> {
        return input.map { card ->
            val numbers = card.split(":")[1]
                .split("|")
            val winningNumbers = numbers[0].trim().split("\\s+".toRegex()).map {
                it.toInt()
            }.toSet()
            val lotteryNumbers = numbers[1].trim().split("\\s+".toRegex()).map {
                it.toInt()
            }.toSet()

            Card(winningNumbers, lotteryNumbers)
        }
    }

    fun part1(cards: List<Card>): Int {
        return cards.sumOf {
            it.getPoints()
        }
    }

    fun part2(cards: List<Card>): Int {
        val counts = cards.map { 1 }.toMutableList()
        cards.forEachIndexed { index, card ->
            val score = card.getMatchesCount()
            for (i in index+1..<index+1+score)
                counts[i] += counts[index]
        }
        return counts.sum()
    }

    val input = readInput("Day04")
    val cards = parseInput(input)
    println(part1(cards))
    println(part2(cards))
}