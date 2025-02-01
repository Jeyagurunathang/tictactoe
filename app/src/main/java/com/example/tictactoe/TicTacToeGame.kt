package com.example.tictactoe

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
    val lifecycleowner = LocalLifecycleOwner.current

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
        DefinePlayerSymbol(
            gameViewModel = viewModel,
            gameUiState = gameUiState
        )

        // Displaying the score of player and computer
        ScoreSection(gameUiState = gameUiState)

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
            GameControlButton(
                gameViewModel = viewModel
            )
        }

        // A dialog box which is used to display the winner of the game
        WinningDialogBox(
            gameViewModel = viewModel,
            gameUiState = gameUiState
        )

        // A dialog box which is displayed when the app goes to background
        PauseDialog()
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToeGameScreenPreview() {
    TicTacToeTheme {
        TicTacToeGameScreen(innerPadding = PaddingValues(dimensionResource(R.dimen.small)))
    }
}