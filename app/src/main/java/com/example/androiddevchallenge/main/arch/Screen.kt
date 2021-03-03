package com.example.androiddevchallenge.main.arch

import com.example.androiddevchallenge.model.Cats

sealed class Screen {
    object HomeScreen : Screen()
    class DetailsScreen(val catModel: Cats) : Screen()
}
