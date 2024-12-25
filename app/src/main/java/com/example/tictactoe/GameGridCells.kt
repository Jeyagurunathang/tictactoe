package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun GameGridCells(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.extra_large))
    ) {
        items(9) { box ->
            Box (
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = MaterialTheme.shapes.medium
                    )
//                    .border(
//                        width = dimensionResource(R.dimen.medium),
//                        color = MaterialTheme.colorScheme.onPrimary
//                    )
                    .padding(dimensionResource(R.dimen.extra_large))
            ) {
                Text(
                    text = box.toString(),
                    modifier = Modifier.padding(dimensionResource(R.dimen.extra_large)),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun GameGridCellsPreview() {
    TicTacToeTheme {
        GameGridCells()
    }
}