package com.pickpick.pickpick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.pickpick.pickpick.core.ui.theme.PickPickTheme
import com.pickpick.pickpick.presentation.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PickPickTheme {
                NavGraph(
                    navController = navController,
                )
            }
        }
    }
}
