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
    val cellSymbol: String = "",
    val columnPosition: Int = 0,
    val rowPosition: Int = 0
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