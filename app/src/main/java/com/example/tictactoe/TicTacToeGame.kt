package com.example.tictactoe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicTacToeGameScreen(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.fillMaxSize().background(
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.primary
                )
            )
        )
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
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.extra_large)))
            /*
                A Box & Row which is responsible for displaying the dropdown menu to select the player symbol
             */
            PlayerSymbolDropDown()
        }
    }
}

@Composable
fun PlayerSymbolDropDown(
    modifier: Modifier = Modifier
) {
    var dropdown by remember { mutableStateOf(false) }

    Box {
        Row (
            modifier = Modifier.background(
                MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.medium
            ).padding(
                vertical = dimensionResource(R.dimen.small),
                horizontal = dimensionResource(R.dimen.medium)
            ).clickable { dropdown = true },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.primary_symbol),
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
        }

        DropdownMenu(
            expanded = dropdown,
            onDismissRequest = { dropdown = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.primary_symbol)) },
                onClick = {dropdown = false},
                colors = MenuDefaults.itemColors(
                    textColor = MaterialTheme.colorScheme.primary
                )
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.secondary_symbol)) },
                onClick = {dropdown = false},
                colors = MenuDefaults.itemColors(
                    textColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}