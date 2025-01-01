package com.example.tictactoe

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.state.TicTacToeUiState
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.viewmodel.TicTacToeViewModel

private const val TAG = "Game grid cell"

@Composable
fun GameGridCells(
    modifier: Modifier = Modifier,
    gameViewModel: TicTacToeViewModel = viewModel(),
    gameUiState: TicTacToeUiState
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(dimensionResource(R.dimen.large))
    ) {
        items(gameUiState.gridBoxes.size) { box ->
            Card (
                modifier = Modifier
                    // 1st vertical line
                    .drawBehind {
                        val borderSize = 5.dp.toPx()
                        drawLine(
                            color = when (box) {
                                1, 3, 5, 7 -> Color.White
                                else -> Color.Transparent
                            },
                            start = when (box) {
                                1, 7 -> Offset(0F + borderSize / 2, 0F)
                                3, 5 -> Offset(0F, 0F + borderSize / 2)
                                else -> Offset(0F, 0F)
                            },
                            end = when (box) {
                                1, 7 -> Offset(0F + borderSize / 2, size.height)
                                3, 5 -> Offset(size.width, 0F + borderSize / 2)
                                else -> Offset(0F, 0F)
                            },
                            strokeWidth = borderSize
                        )
                    }
                    // 2nd vertical line
                    .drawBehind {
                        val borderSize = 5.dp.toPx()
                        drawLine(
                            color = Color.White,
                            start = when (box) {
                                1, 7 -> Offset(size.width - borderSize / 2, 0F)
                                3, 5 -> Offset(0F, size.height - borderSize / 2)
                                else -> Offset(0F, 0F)
                            },
                            end = when (box) {
                                1, 7 -> Offset(size.width - borderSize / 2, size.height)
                                3, 5 -> Offset(size.width, size.height - borderSize / 2)
                                else -> Offset(0F, 0F)
                            },
                            strokeWidth = borderSize
                        )
                    }
                    .border(
                        width = when (box) {
                            4 -> 5.dp
                            else -> 0.dp
                        },
                        color = when (box) {
                            4 -> MaterialTheme.colorScheme.onPrimary
                            else -> Color.Transparent
                        }
                    )
                    .padding(dimensionResource(R.dimen.medium))
                    .clickable {
                        gameViewModel.updateGridCell(
                            rowIndex = when(box) {
                                0, 1, 2 -> 1
                                3, 4, 5 -> 2
                                else -> 3
                            },
                            columnIndex = box
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = gameUiState.gridBoxes[box].cellSymbol,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(dimensionResource(R.dimen.large)).align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameGridCellsPreview() {
    TicTacToeTheme {
        GameGridCells(gameUiState = TicTacToeUiState())
    }
}
