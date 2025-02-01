package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.tictactoe.state.TicTacToeUiState

@Composable
fun ScoreSection(
    modifier: Modifier = Modifier,
    gameUiState: TicTacToeUiState
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.player),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = if (gameUiState.isPlayersTurn) {
                Modifier.background(
                    color = Color.Blue,
                    shape = MaterialTheme.shapes.small
                ).padding(horizontal = dimensionResource(R.dimen.medium))
            } else {
                modifier
            }
        )

        /*
            A Row to display the scores of player and computer
         */
        Row (
            modifier = Modifier.wrapContentSize()
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(
                horizontal = dimensionResource(R.dimen.large),
                vertical = dimensionResource(R.dimen.small)
            )
        ) {
            Text(
                text = gameUiState.playerScore.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.large)))
            Text(
                text = "-",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.large)))
            Text(
                text = gameUiState.aiScore.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = stringResource(R.string.computer),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = if (!gameUiState.isPlayersTurn) {
                Modifier.background(
                    color = Color.Red,
                    shape = MaterialTheme.shapes.small
                ).padding(horizontal = dimensionResource(R.dimen.medium))
            } else {
                modifier
            }
        )
    }
}