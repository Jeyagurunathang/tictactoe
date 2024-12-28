package com.example.tictactoe.state

data class TicTacToeState (
    val playerSymbol: String = "X",
    val playerScore: Int = 0,
    val aiScore: Int = 0,
    val gridBoxes: List<GridCell> = XOGridLayout.gridCells(),
    val isRestart: Boolean = false,
    val isEnd: Boolean = false
)

data class GridCell(
    var cellSymbol: String = "",
    var columnPosition: Int = 0,
    var rowPosition: Int = 0
)

object XOGridLayout {
    fun gridCells(): List<GridCell> {
        return listOf(
            GridCell(),
            GridCell(),
            GridCell(),
            GridCell(),
            GridCell(),
            GridCell(),
            GridCell(),
            GridCell(),
            GridCell()
        )
    }
}