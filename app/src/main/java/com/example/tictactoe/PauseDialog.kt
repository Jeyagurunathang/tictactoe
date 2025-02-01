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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun PauseDialog(
    modifier: Modifier = Modifier
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    val activity = LocalContext.current as Activity
    var showDialog by remember {mutableStateOf(false)}

    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver {_, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                showDialog = true
            }
        }

        lifeCycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false }
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
                    text = "😪️",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = stringResource(R.string.game_pause),
                    style = MaterialTheme.typography.labelLarge
                )
                OutlinedButton (
                    onClick = { activity.finishAndRemoveTask() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(width = dimensionResource(R.dimen.border), color = MaterialTheme.colorScheme.primary),
                    contentPadding = PaddingValues(dimensionResource(R.dimen.medium))
                ) {
                    Text(
                        text = "End Game",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Button(
                    onClick = { showDialog = false },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    contentPadding = PaddingValues(dimensionResource(R.dimen.medium))
                ) {
                    Text(
                        text = "Resume",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }

}