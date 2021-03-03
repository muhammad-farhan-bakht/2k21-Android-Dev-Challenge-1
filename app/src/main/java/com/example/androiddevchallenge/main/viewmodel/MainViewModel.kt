package com.example.androiddevchallenge.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.main.arch.Screen
import com.example.androiddevchallenge.model.Cats

class MainViewModel : ViewModel() {
    private val _currentScreen = MutableLiveData<Screen>(Screen.HomeScreen)
    val currentScreen: LiveData<Screen> = _currentScreen

    fun onBackPressed(): Boolean {
        val current = _currentScreen.value == Screen.HomeScreen
        _currentScreen.value = Screen.HomeScreen
        return current
    }

    fun onCatSelected(selectedCat: Cats) {
        _currentScreen.value = Screen.DetailsScreen(selectedCat)
    }
}