package com.example.tictactoe

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.tictactoe.state.TicTacToeUiState
import com.example.tictactoe.viewmodel.TicTacToeViewModel

@Composable
fun DrawDialogBox(
    modifier: Modifier = Modifier,
    gameViewModel: TicTacToeViewModel,
    gameUiState: TicTacToeUiState
) {
    val activity = LocalContext.current as Activity

    if (gameUiState.isDraw) {
        Dialog(
            onDismissRequest = {gameViewModel.nextRound()}
        ) {
            Column (
                modifier = Modifier
                    .wrapContentSize()
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = MaterialTheme.shapes.large
                    )
                    .padding(dimensionResource(R.dimen.extra_large)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_large)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "😮",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = stringResource(R.string.game_draw),
                    style = MaterialTheme.typography.labelLarge
                )
                OutlinedButton (
                    onClick = {gameViewModel.nextRound()},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(width = dimensionResource(R.dimen.border), color = MaterialTheme.colorScheme.primary),
                    contentPadding = PaddingValues(dimensionResource(R.dimen.medium))
                ) {
                    Text(
                        text = stringResource(R.string.next_game),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Button(
                    onClick = {
                        activity.finishAndRemoveTask()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    contentPadding = PaddingValues(dimensionResource(R.dimen.medium))
                ) {
                    Text(
                        text = stringResource(R.string.end_game),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}