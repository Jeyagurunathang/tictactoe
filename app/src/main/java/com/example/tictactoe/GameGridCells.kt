package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun GameGridCells(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
//        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium)),
//        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.extra_large))
    ) {
        items(9) { box ->
            Box (
                modifier = Modifier
//                    .background(
//                        color = MaterialTheme.colorScheme.onPrimary,
//                        shape = MaterialTheme.shapes.medium
//                    )
                    // 1st vertical line
                    .drawBehind {
                        val borderSize = 3.dp.toPx()
                        drawLine(
                            color = when(box) {
                                1, 3, 5, 7 -> Color.White
                                else -> Color.Transparent
                            },
                            start = when (box) {
                                1, 7 -> Offset(0F + borderSize / 2, 0F)
                                3, 5 -> Offset(0F, 0F + borderSize / 2)
                                else -> Offset(0F, 0F)
                            },
                            end = when(box) {
                                1, 7 -> Offset(0F + borderSize / 2, size.height)
                                3, 5 -> Offset(size.width, 0F + borderSize / 2)
                                else -> Offset(0F, 0F)
                            },
                            strokeWidth = borderSize
                        )
                    }
                    // 2nd vertical line
                    .drawBehind {
                        val borderSize = 3.dp.toPx()
                        drawLine(
                            color = Color.White,
                            start = when(box) {
                                1, 7 -> Offset(size.width - borderSize / 2, 0F)
                                3, 5 -> Offset(0F, size.height - borderSize / 2)
                                else -> Offset(0F, 0F)
                            },
                            end = when(box) {
                                1, 7 -> Offset(size.width - borderSize / 2, size.height)
                                3, 5 -> Offset(size.width, size.height - borderSize / 2)
                                else -> Offset(0F, 0F)
                            },
                            strokeWidth = borderSize
                        )
                    }
                    .border(
                        width = when (box) {
                            4 -> 3.dp
                            else -> 0.dp
                        },
                        color = when (box) {
                            4 -> MaterialTheme.colorScheme.onPrimary
                            else -> Color.Transparent
                        }
                    )
                    .padding(dimensionResource(R.dimen.extra_large))
                    .clickable {},
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = box.toString(),
                    modifier = Modifier.padding(dimensionResource(R.dimen.extra_large)),
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