package com.example.trivial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trivial.model.GameModel

class TrivialViewModel: ViewModel() {
    private val _valorGame = MutableLiveData(GameModel(pregunta = "", ronda = 1, respuestaCorrecta = false))
    val valorGame: LiveData<GameModel> = _valorGame
}