package com.example.marvelapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

// TODO: 10/23/2021 change to sealed class
enum class MarvelScreen(val icon: ImageVector) {
    Home(icon = Icons.Filled.Home),
    Characters(icon = Icons.Filled.ThumbUp),
    Library(icon = Icons.Filled.Favorite),
    Profile(icon = Icons.Filled.Person);

    companion object {
        fun fromRout(route: String?): MarvelScreen =
            when(route?.substringBefore("/")) {
                Home.name -> Home
                Characters.name -> Characters
                Library.name -> Library
                Profile.name -> Profile
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}