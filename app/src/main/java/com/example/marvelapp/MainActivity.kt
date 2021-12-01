package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelapp.ui.MarvelMainViewModel
import com.example.marvelapp.ui.components.MarvelTabRow
import com.example.marvelapp.ui.theme.MarvelAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MarvelMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelAppTheme(darkTheme = true) {
                MarvelMainScreen()
            }
        }
    }
}

//Whole home screen
@ExperimentalPagerApi
@Composable
fun MarvelMainScreen() {
    val allScreens = MarvelScreen.values().toList()
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = MarvelScreen.fromRout(backstackEntry.value?.destination?.route)
    Scaffold(bottomBar = {
        MarvelTabRow(
            allScreens = allScreens,
            onTabSelected = { screen -> navController.navigate(screen.name) },
            currentScreen = currentScreen
        )
    }
    ) { innerPadding ->
        MarvelHomeNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )

    }

}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarvelAppTheme(darkTheme = true) {
        MarvelMainScreen()
    }
}