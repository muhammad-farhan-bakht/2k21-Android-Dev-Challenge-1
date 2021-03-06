/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.androiddevchallenge.main.arch.Screen
import com.example.androiddevchallenge.main.viewmodel.MainViewModel
import com.example.androiddevchallenge.model.Cats
import com.example.androiddevchallenge.ui.details.DetailsScreen
import com.example.androiddevchallenge.ui.home.HomeScreen
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    private val navigationViewModel by viewModels<MainViewModel>()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val currentScreen: Screen by navigationViewModel.currentScreen.observeAsState(Screen.HomeScreen)
                MyApp(
                    currentScreen = currentScreen,
                    catClicked = { catModel ->
                        navigationViewModel.onCatSelected(catModel)
                    },
                    onBackPressed = {
                        onBackPressed()
                    }
                )
            }
        }
    }

    override fun onBackPressed() {
        if (navigationViewModel.onBackPressed()) {
            super.onBackPressed()
        }
    }
}

// Start building your app here!
@ExperimentalFoundationApi
@Composable
fun MyApp(currentScreen: Screen = Screen.HomeScreen, catClicked: (Cats) -> Unit = {}, onBackPressed: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        when (currentScreen) {
            is Screen.DetailsScreen -> DetailsScreen(currentScreen.catModel) {
                onBackPressed.invoke()
            }
            Screen.HomeScreen -> HomeScreen(catClicked)
        }
    }
}
