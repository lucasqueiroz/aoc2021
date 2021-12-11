package dev.lucasqueiroz.aoc2021

class Day4 {
    init {
        Part1()
        Part2()
    }

    class BingoBoard(private val board: String) {
        private val rows: List<List<Int>> = initRows()
        private val columns: List<List<Int>> = initColumns()
        private var calledNumbers: MutableList<Int> = mutableListOf()

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

        private fun initRows(): List<List<Int>> {
            val rows = mutableListOf<List<Int>>()
            val startingRows = board.split("\n")
            startingRows.forEach { row -> rows.add(row.chunked(3).map{ row -> row.replace(" ", "").toInt() }) }
            return rows
        }

        private fun initColumns(): List<List<Int>> {
            val columns = mutableListOf<MutableList<Int>>()
            rows.forEach { row -> row.forEachIndexed { i, item ->
                    if(i >= columns.size) { columns.add(mutableListOf()) }
                    columns[i].add(item)
                }
            }
            return columns
        }

        private fun hasAllItems(items: List<Int>): Boolean {
            items.forEach { item -> if(!calledNumbers.contains(item)) return false }
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
            val calledNumbers = lines[0].split(",").map{ num -> num.toInt() }

            for(i in 2..lines.size step 6) {
                val board = BingoBoard(lines.subList(i, i + 5).joinToString("\n"))
                boards.add(board)
            }

            calledNumbers.forEach { num ->
                boards.forEach { board ->
                    board.callNumber(num)
                    if(board.isWinner()) {
                        println(board.getScore())
                        return
                    }
                }
            }
        }
    }

    class Part2 {
        var boards: MutableList<BingoBoard> = mutableListOf()

        init {
            run()
        }

        private fun run() {
            val lines = readLines(4)
            val calledNumbers = lines[0].split(",").map{ num -> num.toInt() }
            var winningBoards = mutableListOf<BingoBoard>()

            for(i in 2..lines.size step 6) {
                val board = BingoBoard(lines.subList(i, i + 5).joinToString("\n"))
                boards.add(board)
            }

            calledNumbers.forEach { num ->
                boards.filter { board -> !(winningBoards.contains(board)) }.forEach { board ->
                    board.callNumber(num)
                    if(board.isWinner()) {
                        winningBoards.add(board)
                    }
                }
            }

            println(winningBoards.last().getScore())
        }
    }
}

fun main() {
    Day4()
}