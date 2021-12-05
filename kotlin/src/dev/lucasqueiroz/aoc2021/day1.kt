package dev.lucasqueiroz.aoc2021

import dev.lucasqueiroz.aoc2021.readLines

fun main() {
    val lines = readLines(1)
    var larger = 0
    var last = 0
    var first = true

    for(line in lines) {
        val intValue = line.toInt()
        if(first) {
            first = false
        } else if(intValue > last) {
            larger += 1
        }
        last = intValue
    }

    print(larger)
}