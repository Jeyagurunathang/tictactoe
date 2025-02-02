package com.example.tictactoe.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.data.WinningSequencePosition
import com.example.tictactoe.data.WinningSequences
import com.example.tictactoe.state.GridCellUiState
import com.example.tictactoe.state.ListOfGrid
import com.example.tictactoe.state.TicTacToeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

private const val TAG = "Game viewmodel"
private const val TAG1 = "playersposition"
private const val TAG2 = "aipositions"
private const val TAG3 = "winner"
private const val TAG4 = "uistate"

class TicTacToeViewModel : ViewModel() {
    private val _ticTacToeUiState = MutableStateFlow(TicTacToeUiState())
    private val playerSymbolPositions = mutableSetOf<WinningSequencePosition>()
    private val playersWinningSequence = mutableSetOf<WinningSequencePosition>()
    private val aiMovingSequence = mutableSetOf<WinningSequencePosition>()
    private var playerScore by mutableIntStateOf(0)
    private var aiScore by mutableIntStateOf(0)

    val ticTacToeState: StateFlow<TicTacToeUiState> = _ticTacToeUiState.asStateFlow()

    // Function to update the grid cell to display the player symbol
    fun updateGridCell(
        rowIndex: Int,
        columnIndex: Int
    ) {
        val selectedGridCell = _ticTacToeUiState.value.gridBoxes.filter { it.rowPosition == rowIndex && it.columnPosition == columnIndex}

        if ((selectedGridCell.isNotEmpty() && selectedGridCell.last().cellSymbol == "") || selectedGridCell.isEmpty()) {
            _ticTacToeUiState.update { currentState ->
                currentState.copy(
                    gridBoxes = updateGridBox(
                        rowId = rowIndex,
                        colId = columnIndex,
                        currentState = currentState
                    ),
                    isTryToChangeSymbolAgain = false
                )
            }

            changingPlayersTurn()

            if (!_ticTacToeUiState.value.isPlayersTurn) updatePlayerSymbolPosition()
        }
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
                playerSymbol = symbol,
                aiSymbol = if (symbol == "O") "X" else "O"
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
    private fun updatePlayerSymbolPosition() {
        val gridsWithPlayerSymbol = _ticTacToeUiState.value.gridBoxes.filter { it.cellSymbol == _ticTacToeUiState.value.playerSymbol }

        gridsWithPlayerSymbol.forEach {
            playerSymbolPositions.add(WinningSequencePosition(rowId = it.rowPosition, colId = it.columnPosition))
        }

        predictPlayerWinningSequences()
    }

    // Function to get the players winning position based on the player's first move
    private fun predictPlayerWinningSequences() {
        val playersLastMove = playerSymbolPositions.last()

        val winningSequences = WinningSequences.getWinningSequences()

        /*

            playersLastMove => contains the last movement of the player
            winningSequence => It will contain all the possible winning sequences to win the game
            aiMovingSequence => Initially it will contain nothing

         */
        for (winningSequence in winningSequences.values) {
            if (playersLastMove in winningSequence && !winningSequence.any { it in aiMovingSequence }) {
                winningSequence.forEach {
                    if (it != playersLastMove) playersWinningSequence.add(it)
                }
            }
        }

        playersWinningSequence.removeAll(playerSymbolPositions)

        if (playerSymbolPositions.size >= 3) whoIsWonTheGame()

        if (
            !_ticTacToeUiState.value.isGameCompleted &&
            _ticTacToeUiState.value.gridBoxes.any { it.cellSymbol == "" } &&
            !_ticTacToeUiState.value.isDraw
        ) {
            aiTurn()
        }

        playersWinningSequence.removeAll(aiMovingSequence)
    }

    // Function to make the AI the place its symbol
    private fun aiTurn() {

        viewModelScope.launch {
            delay(1000)

            if (playerSymbolPositions.size >= 2) {
                for (winningSequence in WinningSequences.getWinningSequences()) {
                    if (
                        playerSymbolPositions.intersect(winningSequence.value.toSet()).size == 2 &&
                        aiMovingSequence.intersect(winningSequence.value.toSet()).isEmpty()
                    ) {
                        aiMovingSequence.add(winningSequence.value.minus(playerSymbolPositions)[0])

                        val (rowId, colId) = aiMovingSequence.last()

                        updateGridCell(rowIndex = rowId, columnIndex = colId)

                        return@launch
                    }
                }
            }

            aiMovingSequence.add(playersWinningSequence.random())

            val (rowId, colId) = aiMovingSequence.last()

            updateGridCell(rowIndex = rowId, columnIndex = colId)
        }
    }

    // Function to predict who is won the game
    private fun whoIsWonTheGame() {
        for (winningSequence in WinningSequences.getWinningSequences()) {
            if (playerSymbolPositions.containsAll(winningSequence.value)) {
                playerScore.inc()
                _ticTacToeUiState.update {
                    it.copy(
                        isGameCompleted = true,
                        playerScore = it.playerScore.inc(),
                        whoWon = "Player",
                        isDraw = false
                    )
                }
            } else if (aiMovingSequence.containsAll(winningSequence.value)) {
                aiScore.inc()
                _ticTacToeUiState.update {
                    it.copy(
                        isGameCompleted = true,
                        aiScore = it.aiScore.inc(),
                        whoWon = "Computer",
                        isDraw = false
                    )
                }
            }
        }

        if (!_ticTacToeUiState.value.isGameCompleted && _ticTacToeUiState.value.gridBoxes.all{ it.cellSymbol != "" }) {
            _ticTacToeUiState.update {
                it.copy(
                    isDraw = true
                )
            }

            Log.d(TAG, _ticTacToeUiState.value.toString())
        }
    }

    // Function to restart the game
    fun restartGame() {
        _ticTacToeUiState.update {
            it.copy(
                playerSymbol = "X",
                aiSymbol= "O",
                playerScore = 0,
                aiScore = 0,
                gridBoxes = ListOfGrid.gridCells(),
                isRestart = false,
                isDropDownClicked = false,
                isTryToChangeSymbolAgain = false,
                isPlayersTurn = true,
                isGameCompleted = false,
                isDraw = false
            )
        }
    }

    // Function to restart the game
    fun nextRound() {
        playerSymbolPositions.removeAll(playerSymbolPositions)
        aiMovingSequence.removeAll(aiMovingSequence)
        playersWinningSequence.removeAll(playersWinningSequence)

        _ticTacToeUiState.update {
            it.copy(
                gridBoxes = ListOfGrid.gridCells(),
                isRestart = false,
                isDropDownClicked = false,
                isTryToChangeSymbolAgain = false,
                isPlayersTurn = true,
                isGameCompleted = false,
                isDraw = false
            )
        }

        Log.d(TAG4, _ticTacToeUiState.value.toString())
    }
}