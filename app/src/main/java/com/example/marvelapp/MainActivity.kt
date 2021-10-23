package com.example.marvelapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.example.marvelapp.MarvelScreen.*
import com.example.marvelapp.ui.components.MarvelTabRow


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelAppTheme(darkTheme = true) {
                MarvelMainScreen()
            }
        }
    }
}

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
        MarvelNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )

    }

}

@Composable
fun MarvelNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Home.name,
        modifier = modifier
    ) {
        composable(Home.name) {
            Text(text = "Here is Home Page")
        }
        composable(Characters.name) {
            Text(text = "Here is Characters Page")
        }
        composable(Library.name) {
            Text(text = "Here is Library Page")
        }
        composable(Profile.name) {
            Text(text = "Here is Profile Page")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarvelAppTheme(darkTheme = true) {
        MarvelMainScreen()
    }
}