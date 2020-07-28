package program

typealias Board = Array<Array<Boolean>>

fun emptyBoard(side: Int = 10): Board = Array(side) { Array(side) { false } }
var iteration = 0

fun main() {
    val side = 10


    var board = emptyBoard(side)

    board[0][1] = true
    board[1][2] = true
    board[2][0] = true
    board[2][1] = true
    board[2][2] = true

    fun moveBoard(board: Board): Board {
        fun update(i: Int, j: Int, current: Boolean): Boolean {

            val neighbors = listOf(
                board.getOrNull(i - 1)?.getOrNull(j - 1) == true,
                board.getOrNull(i - 1)?.getOrNull(j) == true,
                board.getOrNull(i - 1)?.getOrNull(j + 1) == true,
                board.getOrNull(i)?.getOrNull(j - 1) == true,
                board.getOrNull(i)?.getOrNull(j + 1) == true,
                board.getOrNull(i + 1)?.getOrNull(j - 1) == true,
                board.getOrNull(i + 1)?.getOrNull(j) == true,
                board.getOrNull(i + 1)?.getOrNull(j + 1) == true
            ).count { it }

            return if (current) {
                when {
                    neighbors <= 1 -> false
                    neighbors >= 4 -> false
                    neighbors in 2..3 -> true
                    else -> current
                }
            } else {
                if (neighbors == 3) {
                    true
                } else {
                    current
                }
            }
        }

        val resultBoard = emptyBoard(side)
        for (i in board.indices) {
            for (j in board[i].indices) {
                resultBoard[i][j] = update(i, j, board[i][j])
            }
        }
        return resultBoard
    }

    while (true) {
        printBoard(board)
        board = moveBoard(board)
        Thread.sleep(1000L)
    }
}

fun printBoard(board: Board) {
    println("Iteration: ${++iteration}")
    board.forEach { row ->
        print(" ")
        row.forEach { value ->
            print("[${if (value) 'X' else ' '}]")
        }
        println()
    }
    println()
}