package com.example.trivial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trivial.ui.theme.TrivialTheme
import com.example.trivial.view.GameView
import com.example.trivial.view.LaunchView
import com.example.trivial.view.MenuView
import com.example.trivial.view.ResultView
import com.example.trivial.view.Routes
import com.example.trivial.viewmodel.TrivialViewModel

class MainActivity : ComponentActivity() {
    val trivialViewModel: TrivialViewModel by viewModels<TrivialViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TrivialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Launch.route
                    ) {
                        composable(Routes.Launch.route) { LaunchView(modifier = Modifier.padding(innerPadding), trivialViewModel, navController) }
                        composable(Routes.Menu.route) { MenuView(modifier = Modifier.padding(innerPadding), trivialViewModel, navController) }
                        composable(Routes.Game.route) { GameView(modifier = Modifier.padding(innerPadding), trivialViewModel, navController) }
                        composable(Routes.Result.route) { ResultView(modifier = Modifier.padding(innerPadding), trivialViewModel, navController) }
                    }
                }
            }
        }
    }
}