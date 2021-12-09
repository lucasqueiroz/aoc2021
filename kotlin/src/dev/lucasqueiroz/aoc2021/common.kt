package dev.lucasqueiroz.aoc2021

import java.io.File

fun readLines(day: Int): List<String> {
    return File("resources/day-$day.txt").readLines()
}