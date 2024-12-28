package com.example.tictactoe.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tictactoe.state.GridCell
import com.example.tictactoe.state.TicTacToeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "Game viewmodel"

class TicTacToeViewModel : ViewModel() {
    private val _ticTacToeUiState = MutableStateFlow(TicTacToeState())

    val ticTacToeState: StateFlow<TicTacToeState> = _ticTacToeUiState.asStateFlow()

    fun updateGridCell(
        rowIndex: Int,
        columnIndex: Int
    ) {
        _ticTacToeUiState.update { currentState ->
            currentState.copy(
                gridBoxes = updateGridBox(
                    rowIndex = rowIndex,
                    columnIndex = columnIndex,
                    currentState = currentState
                )
            )
        }

        Log.d(TAG, _ticTacToeUiState.value.toString())
    }

    private fun updateGridBox (
        rowIndex: Int,
        columnIndex: Int,
        currentState: TicTacToeState
    ): MutableList<GridCell> {
        val gridBoxesList = mutableListOf<GridCell>()

        currentState.gridBoxes.forEachIndexed { index, gridCell ->
            if (index == columnIndex) {
                gridCell.cellSymbol = "X"
                gridCell.rowPosition = rowIndex
                gridCell.columnPosition = columnIndex
            }

            gridBoxesList.add(gridCell)
        }

        return gridBoxesList
    }
}