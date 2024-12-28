package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.viewmodel.TicTacToeViewModel

@Composable
fun TicTacToeGameScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    viewModel: TicTacToeViewModel = viewModel()
) {
    val gameUiState by viewModel.ticTacToeState.collectAsState()
    Column (
        modifier = modifier.fillMaxSize().background(
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.primary
                )
            )
        )
            .windowInsetsPadding(WindowInsets.safeContent),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        // Displaying the player symbol
        DefinePlayerSymbol()

        // Displaying the score of player and computer
        ScoreSection()

        // A composable which displays the main game grid cells
        GameGridCells(
            gameUiState = gameUiState,
            gameViewModel = viewModel
        )

        // A Column which displays the game control buttons like "Restart" and "End Game"
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameControlButton()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToeGameScreenPreview() {
    TicTacToeTheme {
        TicTacToeGameScreen(innerPadding = PaddingValues(dimensionResource(R.dimen.small)))
    }
}