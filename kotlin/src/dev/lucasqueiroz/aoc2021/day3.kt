package dev.lucasqueiroz.aoc2021

import dev.lucasqueiroz.aoc2021.getOrAdd

class Day3 {
    init {
        Part1()
    }

    class Part1 {
        var bitPos: MutableList<MutableMap<Char, Int>> = mutableListOf(mutableMapOf())
        var currentIndex: Int = 0

        init {
            run()
        }

        private fun initBitList(strLen: Int) {
            if(bitPos.size < strLen) {
                for(i in 1 until strLen) {
                    bitPos.add(i, mutableMapOf())
                }
            }
        }

        private fun saveBit(bit: Char, strLen: Int) {
            if(bitPos[currentIndex].containsKey(bit)) {
                bitPos[currentIndex][bit] = bitPos[currentIndex][bit]!! + 1
            } else {
                bitPos[currentIndex][bit] = 1
            }
            currentIndex++
            if(currentIndex >= strLen) {
                currentIndex = 0
            }
        }

        private fun run() {
            val lines = readLines(3)
            val str = lines.joinToString("")
            val strLen = str.length / lines.size
            var gammaRate = ""
            var epsilonRate = ""

            initBitList(strLen)

            for(c in str) {
                saveBit(c, strLen)
            }

            for(bit in bitPos) {
                gammaRate += if(bit['0']!! > bit['1']!!) {
                    "0"
                } else {
                    "1"
                }

                epsilonRate += if(bit['0']!! < bit['1']!!) {
                    "0"
                } else {
                    "1"
                }
            }

            val gammaRateDecimal = Integer.parseInt(gammaRate, 2)
            val epsilonRateDecimal = Integer.parseInt(epsilonRate, 2)
            println(gammaRateDecimal * epsilonRateDecimal)
        }
    }
}

fun main() {
    Day3()
}