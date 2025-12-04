package com.example.trivial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trivial.model.Game
import com.example.trivial.model.Launch
import com.example.trivial.model.Menu
import com.example.trivial.model.Result

class TrivialViewModel: ViewModel() {
    private val _valorLaunch = MutableLiveData(Launch(3))
    private val _valorMenu = MutableLiveData(Menu(0, false))
    private val _valorGame = MutableLiveData(Game(1, "", false, 0))
    private val _valorResult = MutableLiveData(Result(0, false))

    val valorLaunch: LiveData<Launch> = _valorLaunch
    val valorMenu: LiveData<Menu> = _valorMenu
    val valorGame: LiveData<Game> = _valorGame
    val valorResult: LiveData<Result> = _valorResult

    fun controlRonda() {
        val currentGame = _valorGame.value
        if (currentGame.ronda >= 10) {
            _valorGame.value = currentGame.copy(
                ronda = 1
            )
        } else {
            _valorGame.value = currentGame.copy(
                ronda = currentGame.ronda + 1
            )
        }
    }
}