package com.example.tictactoe.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tictactoe.state.GridCellUiState
import com.example.tictactoe.state.TicTacToeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "Game viewmodel"

class TicTacToeViewModel : ViewModel() {
    private val _ticTacToeUiState = MutableStateFlow(TicTacToeUiState())

    val ticTacToeState: StateFlow<TicTacToeUiState> = _ticTacToeUiState.asStateFlow()

    // Function to update the grid cell to display the player symbol
    fun updateGridCell(
        rowIndex: Int,
        columnIndex: Int
    ) {
        _ticTacToeUiState.update { currentState ->
            currentState.copy(
                gridBoxes = updateGridBox(
                    rowId = rowIndex,
                    colId = columnIndex,
                    currentState = currentState
                )
            )
        }

        changingPlayersTurn()

        if (!_ticTacToeUiState.value.isPlayersTurn) winningSequences()
    }

    // Function to set the player symbol to the particular clicked grid cell box
    private fun updateGridBox(
        rowId: Int,
        colId: Int,
        currentState: TicTacToeUiState
    ): List<GridCellUiState> {
        val newGridList = mutableListOf<GridCellUiState>()
        currentState.gridBoxes.mapIndexed { index, gridCell ->
            if (index == colId) {
                newGridList.add(gridCell.copy(
                    cellSymbol = if (currentState.isPlayersTurn) currentState.playerSymbol else currentState.aiSymbol,
                    columnPosition = colId,
                    rowPosition = rowId
                ))
            } else {
                newGridList.add(gridCell)
            }
        }

        return newGridList
    }

    // Function to open and close the player symbol drop down menu
    fun dropDownFunctionality(
        symbol: String = "X"
    ) {
        val numberOfGridCellsUpdated = _ticTacToeUiState.value.gridBoxes.filter { it.cellSymbol == "" }.size

        if (numberOfGridCellsUpdated == 9) {
            _ticTacToeUiState.update { currentState ->
                currentState.copy(
                    isDropDownClicked = !currentState.isDropDownClicked
                )
            }

            if (!_ticTacToeUiState.value.isDropDownClicked) {
                updatePlayerSymbol(symbol = symbol)
            }
        } else {
            _ticTacToeUiState.update {
                it.copy(isTryToChangeSymbolAgain = true)
            }
        }
    }

    // Function to get the player symbol
    private fun updatePlayerSymbol(
        symbol: String
    ) {
        _ticTacToeUiState.update { currentState ->
            currentState.copy(
                playerSymbol = symbol
            )
        }
    }

    // Function to indicate who is currently playing by giving some background color
    private fun changingPlayersTurn() {
        _ticTacToeUiState.update {
            it.copy(isPlayersTurn = !it.isPlayersTurn)
        }
    }

    // Function to store the players symbol positions
    private fun getPlayerSymbolPosition(): Map<Int, List<GridCellUiState>> {
        val gridBoxesWithPlayerSymbol = _ticTacToeUiState.value.gridBoxes.filter { it.cellSymbol == "X" }.groupBy { it.rowPosition }
        Log.d(TAG, gridBoxesWithPlayerSymbol.toString())

        return gridBoxesWithPlayerSymbol
    }

    // Function to find the winning sequences for the players move
    private fun winningSequences() {
        val playersWinningSequence = mutableMapOf<Int, List<List<Int>>>()
        val playerSymbolGridBoxes = getPlayerSymbolPosition()
        val rowPositionOfPlayerSymbol = playerSymbolGridBoxes.keys.toList().last()
        val winningSequencesList = mutableListOf<List<Int>>()

        val endingRowPositionRange = when(rowPositionOfPlayerSymbol) {
            1 -> 2
            2 -> 5
            else -> 8
        }

        for (i in ((rowPositionOfPlayerSymbol - 1) * 3) ..endingRowPositionRange) {
            winningSequencesList.add(listOf(rowPositionOfPlayerSymbol, i))
        }

        playersWinningSequence[rowPositionOfPlayerSymbol] = winningSequencesList
        Log.d(TAG, playersWinningSequence.toString())
    }
}