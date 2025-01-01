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
    }

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

        Log.d(TAG, newGridList.toString())

        return newGridList
    }
}