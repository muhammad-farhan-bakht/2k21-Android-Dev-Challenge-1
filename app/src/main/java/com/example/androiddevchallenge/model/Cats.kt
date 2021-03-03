package com.example.androiddevchallenge.model

import androidx.annotation.IdRes

data class Cats(
    val name: String,
    @IdRes val image: Int,
    val age: String,
    val color: String,
    val weight: String,
    val story: String,
    val sex: String
)
