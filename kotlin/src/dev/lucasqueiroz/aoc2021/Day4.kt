package dev.lucasqueiroz.aoc2021

class Day4 {
    init {
        Part1()
    }

    class BingoBoard(private val board: String) {
        private val rows: List<List<Int>> = initRows()
        private val columns: List<List<Int>> = initColumns()
        private var calledNumbers: MutableList<Int> = mutableListOf()

        private fun initRows(): List<List<Int>> {
            val rows = mutableListOf<List<Int>>()
            val startingRows = board.split("\n")
            for(row in startingRows) {
                rows.add(row.chunked(3).map{ row -> row.replace(" ", "").toInt() })
            }
            return rows
        }

        private fun initColumns(): List<List<Int>> {
            val columns = mutableListOf<MutableList<Int>>()
            for(row in rows) {
                for((colIndex, col) in row.withIndex()) {
                    if(colIndex >= columns.size) {
                        columns.add(mutableListOf())
                    }
                    columns[colIndex].add(col)
                }
            }
            return columns
        }

        fun callNumber(number: Int) {
            calledNumbers.add(number)
        }

        fun isWinner(): Boolean {
            rows.forEach { row -> if(hasAllItems(row)) return true }
            columns.forEach { column -> if(hasAllItems(column)) return true }
            return false
        }

        fun getScore(): Int {
            val unmarked = rows.sumOf { row -> row.sumOf { item -> if (!calledNumbers.contains(item)) item else 0 } }
            val last = calledNumbers.last()
            return unmarked * last
        }

        private fun hasAllItems(items: List<Int>): Boolean {
            for(item in items) {
                if(!calledNumbers.contains(item)) {
                    return false
                }
            }
            return true
        }
    }

    class Part1 {
        var boards: MutableList<BingoBoard> = mutableListOf()

        init {
            run()
        }

        private fun run() {
            val lines = readLines(4)
            val calledNumbers = lines[0]

            for(i in 2..lines.size step 6) {
                val board = BingoBoard(lines.subList(i, i + 5).joinToString("\n"))
                boards.add(board)
            }

            for(calledNumber in calledNumbers.split(",")) {
                for(board in boards) {
                    board.callNumber(calledNumber.toInt())
                    if(board.isWinner()) {
                        println(board.getScore())
                        return
                    }
                }
            }
        }
    }
}

fun main() {
    Day4()
}