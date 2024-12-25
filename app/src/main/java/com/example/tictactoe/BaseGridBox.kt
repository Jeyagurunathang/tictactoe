package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource

@Composable
fun BaseGridBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.small
            )
            .padding(
                vertical = dimensionResource(R.dimen.large),
                horizontal = dimensionResource(R.dimen.extra_large)
            )
    ) {
        Text(
            text = "x"
        )
    }
}