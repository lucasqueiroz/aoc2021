package dev.lucasqueiroz.aoc2021

class Day1 {
    init {
        part1()
        part2()
    }

    private fun part1() {
        val lines = readLines(1)
        var larger = 0
        var lastSum = 0
        var firstSum = true

        for (line in lines) {
            val intValue = line.toInt()
            if (firstSum) {
                firstSum = false
            } else if (intValue > lastSum) {
                larger += 1
            }
            lastSum = intValue
        }

        println(larger)
    }

    private fun part2() {
        val lines = readLines(1)
        var larger = 0
        var lastSum = 0
        var firstSum = true

        lines.forEachIndexed loop@{ index, element ->
            if (index + 1 >= lines.size - 1) {
                return@loop
            }

            val current: Int = element.toInt()
            val second: Int = lines[index+1].toInt()
            val third: Int = lines[index+2].toInt()
            val sum = current + second + third

            if (firstSum) {
                firstSum = false
            } else if (sum > lastSum) {
                larger += 1
            }
            lastSum = sum
        }

        println(larger)
    }
}

fun main() {
    Day1()
}