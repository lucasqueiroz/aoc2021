package dev.lucasqueiroz.aoc2021

import java.io.File

fun readLines(day: Int): List<String> {
    return File("resources/day-$day.txt").readLines()
}

fun <E> MutableList<E>.getOrAdd(index: Int, toAdd: E): E {
    return if (index < this.size) {
        this.add(toAdd)
        this.last()
    } else {
        this[index]
    }
}