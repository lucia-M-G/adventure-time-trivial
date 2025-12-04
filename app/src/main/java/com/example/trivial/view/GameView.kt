package com.example.trivial.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.trivial.model.Game
import com.example.trivial.viewmodel.TrivialViewModel

@Composable
fun GameView(trivialViewModel: TrivialViewModel, navController: NavController) {
    val valor by trivialViewModel.valorGame.observeAsState(initial = Game(0, "", false, 10))

    Column {
        Button(
            onClick = { trivialViewModel.controlRonda() }
        ) {
            Text(text = "Incrementar Ronda")
        }
        Text(text = valor.ronda.toString())
    }
}