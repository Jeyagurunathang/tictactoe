package com.example.tictactoe

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource

@Composable
fun GameControlButton(
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(0.6F).padding(vertical = dimensionResource(R.dimen.medium)),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = BorderStroke(width = dimensionResource(R.dimen.border), color = MaterialTheme.colorScheme.onPrimary),
        contentPadding = PaddingValues(dimensionResource(R.dimen.medium))
    ) {
        Text(
            text = stringResource(R.string.restart),
            style = MaterialTheme.typography.labelLarge
        )
    }
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth(0.6F),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(dimensionResource(R.dimen.medium))
    ) {
        Text(
            text = stringResource(R.string.end_game),
            style = MaterialTheme.typography.labelLarge
        )
    }
}