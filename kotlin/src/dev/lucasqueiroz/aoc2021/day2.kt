package dev.lucasqueiroz.aoc2021

import java.awt.Point

class Day2 {
    init {
        part1()
        part2()
    }

    fun part1() {
        val lines = readLines(2)
        var point = Point(0, 0)

        for (line in lines) {
            with (line) {
                when {
                    startsWith("forward ") -> {
                        val value = line.replace("forward ", "").toInt()
                        point.x += value
                    }
                    startsWith("down ") -> {
                        val value = line.replace("down ", "").toInt()
                        point.y += value
                    }
                    startsWith("up ") -> {
                        val value = line.replace("up ", "").toInt()
                        point.y -= value
                    }
                }
            }
        }

        println(point.x * point.y)
    }

    fun part2() {
        val lines = readLines(2)
        var point = Point(0, 0)
        var aim = 0

        for (line in lines) {
            with (line) {
                when {
                    startsWith("forward ") -> {
                        val value = line.replace("forward ", "").toInt()
                        point.x += value
                        point.y += aim * value
                    }
                    startsWith("down ") -> {
                        val value = line.replace("down ", "").toInt()
                        aim += value
                    }
                    startsWith("up ") -> {
                        val value = line.replace("up ", "").toInt()
                        aim -= value
                    }
                }
            }
        }

        println(point.x * point.y)
    }
}

fun main() {
    Day2()
}