package dev.lucasqueiroz.aoc2021

class Day3 {
    init {
        Part1()
        Part2()
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
            val lines = readLines(3).toMutableList()
            val str = lines.joinToString("")
            val strLen = str.length / lines.size
            var gammaRate = ""
            var epsilonRate = ""

            for(i in 0 until strLen) {
                gammaRate += findMostCommonInPos(lines, i)
                epsilonRate += findLeastCommonInPos(lines, i)
            }

            val gammaRateDecimal = Integer.parseInt(gammaRate, 2)
            val epsilonRateDecimal = Integer.parseInt(epsilonRate, 2)
            println(gammaRateDecimal * epsilonRateDecimal)
        }
    }

    class Part2() {

        init {
            run()
        }

        private fun run() {
            val lines = readLines(3)

            var o2Lines = lines.toMutableList()
            var o2LinesSize = o2Lines.size
            var co2Lines = lines.toMutableList()
            var co2LinesSize = o2Lines.size
            var i = 0
            while(o2LinesSize > 1) {
                val mostCommon = findMostCommonInPos(o2Lines, i)
                o2Lines = o2Lines.filter { line -> line[i] == mostCommon }.toMutableList()
                o2LinesSize = o2Lines.size
                i++
            }

            i = 0
            while(co2LinesSize > 1) {
                val leastCommon = findLeastCommonInPos(co2Lines, i)
                co2Lines = co2Lines.filter { line -> line[i] == leastCommon }.toMutableList()
                co2LinesSize = co2Lines.size
                i++
            }

            val o2Decimal = Integer.parseInt(o2Lines[0], 2)
            val co2Decimal = Integer.parseInt(co2Lines[0], 2)
            println(o2Decimal * co2Decimal)
        }
    }
}

private fun findMostCommonInPos(lines: MutableList<String>, pos: Int): Char {
    var str = lines.map { line -> line[pos] }.joinToString("")
    if(str.replace("0", "").length < str.replace("1", "").length) {
        return '0'
    }
    return '1'
}

private fun findLeastCommonInPos(lines: MutableList<String>, pos: Int): Char {
    var str = lines.map { line -> line[pos] }.joinToString("")
    if(str.replace("0", "").length < str.replace("1", "").length) {
        return '1'
    }
    return '0'
}

fun main() {
    Day3()
}