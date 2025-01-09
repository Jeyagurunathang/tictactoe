package com.example.tictactoe.viewmodel

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
                    cellSymbol = currentState.playerSymbol,
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

    // Function to make the AI place a symbol on the grid cell


    // Function to get a random number between a defined range
    private fun getRandomNumber(start: Int, end: Int): Int {
        return (start..end).random()
    }
}