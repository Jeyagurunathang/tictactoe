package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.tictactoe.ui.theme.TicTacToeTheme

private const val TAG = "activity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TicTacToeGameScreen(
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Moved to background")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Moved to foreground")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "started")
    }
}