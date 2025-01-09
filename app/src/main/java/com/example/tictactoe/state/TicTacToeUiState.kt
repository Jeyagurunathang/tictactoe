package com.example.tictactoe.state

data class TicTacToeUiState (
    val playerSymbol: String = "X",
    val playerScore: Int = 0,
    val aiScore: Int = 0,
    val gridBoxes: List<GridCellUiState> = ListOfGrid.gridCells(),
    val isRestart: Boolean = false,
    val isEnd: Boolean = false,
    val isDropDownClicked: Boolean = false,
    val isTryToChangeSymbolAgain: Boolean = false,
    val isPlayersTurn: Boolean = true
)

data class GridCellUiState(
    val cellSymbol: String = "",
    val columnPosition: Int = 0,
    val rowPosition: Int = 0
)

object ListOfGrid {
    fun gridCells(): List<GridCellUiState> {
        return listOf(
            GridCellUiState(),
            GridCellUiState(),
            GridCellUiState(),
            GridCellUiState(),
            GridCellUiState(),
            GridCellUiState(),
            GridCellUiState(),
            GridCellUiState(),
            GridCellUiState()
        )
    }
}