package com.example.tictactoe

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.tictactoe.state.TicTacToeUiState
import com.example.tictactoe.viewmodel.TicTacToeViewModel

@Composable
fun DefinePlayerSymbol(
    modifier: Modifier = Modifier,
    gameViewModel: TicTacToeViewModel,
    gameUiState: TicTacToeUiState
) {
    /*
    A Row which determines the player symbol section
    */
    Row (
        modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.large)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.player_symbol),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.extra_large)))
        /*
            A Box & Row which is responsible for displaying the dropdown menu to select the player symbol
         */
        PlayerSymbolDropDown(
            gameViewModel = gameViewModel,
            gameUiState = gameUiState
        )
    }
}

@Composable
fun PlayerSymbolDropDown(
    modifier: Modifier = Modifier,
    gameViewModel: TicTacToeViewModel,
    gameUiState: TicTacToeUiState
) {
    Box {
        Row (
            modifier = Modifier.background(
                MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.medium
            ).padding(
                vertical = dimensionResource(R.dimen.small),
                horizontal = dimensionResource(R.dimen.medium)
            ).clickable { gameViewModel.dropDownFunctionality() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = gameUiState.playerSymbol,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small)))
            Icon(
                painter = painterResource(R.drawable.menu_down),
                contentDescription = "Menu down",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            // Displaying a toast message when the user tries to change the symbol
            // in the middle of the game
            if (gameUiState.isTryToChangeSymbolAgain) {
                val toast = Toast.makeText(LocalContext.current, R.string.cannot_change_symbol, Toast.LENGTH_LONG)
                toast.show()
            }
        }

        DropdownMenu(
            expanded = gameUiState.isDropDownClicked,
            onDismissRequest = { gameViewModel.dropDownFunctionality() }
        ) {
            val playerPrimarySymbol = stringResource(R.string.primary_symbol)
            val playerSecondarySymbol = stringResource(R.string.secondary_symbol)

            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.primary_symbol)) },
                onClick = {
                    gameViewModel.dropDownFunctionality(symbol = playerPrimarySymbol)
                },
                colors = MenuDefaults.itemColors(
                    textColor = MaterialTheme.colorScheme.primary
                )
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.secondary_symbol)) },
                onClick = {
                    gameViewModel.dropDownFunctionality(symbol = playerSecondarySymbol)
                },
                colors = MenuDefaults.itemColors(
                    textColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}