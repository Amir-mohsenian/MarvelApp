package com.example.marvelapp

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvelapp.ui.characters.CharactersScreen
import com.example.marvelapp.ui.home.HomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@Composable
fun MarvelHomeNavGraph(navController: NavHostController, modifier: Modifier) {
    val name = remember {
        mutableStateOf(false)
    }

    NavHost(
        navController = navController,
        startDestination = MarvelScreen.Home.name,
        modifier = modifier
    ) {
        composable(MarvelScreen.Home.name) {
            HomeScreen()
        }
        composable(MarvelScreen.Characters.name) {
            CharactersScreen()
        }
        composable(MarvelScreen.Library.name) {
            Text(text = "Here is Library Page")
        }
        composable(MarvelScreen.Profile.name) {
            Text(text = "Here is Profile Page")
        }
    }
}