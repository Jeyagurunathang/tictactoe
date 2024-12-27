package com.example.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoe.state.TicTacToeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TicTacToeViewModel : ViewModel() {
    private val _ticTacToeUiState = MutableStateFlow(TicTacToeState())

    val ticTacToeState: StateFlow<TicTacToeState> = _ticTacToeUiState.asStateFlow()
}