package com.codingambitions.composetutorial11compositionlocal

import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.codingambitions.composetutorial11compositionlocal.ui.theme.ComposeTutorial11CompositionLocalTheme

data class User(val id: String, val name: String)

val UserLocal = compositionLocalOf<User> { error("composition local is not initialized") }
lateinit var userLoggedIn: User

// Pass the data down the composition tree without explicitly passing the data
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorial11CompositionLocalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(text = "Login")

                                TextButton(modifier = Modifier.fillMaxWidth(), onClick = {
                                    userLoggedIn = User(id = "1", name = "CodingAmbitions")
                                    navController.navigate("home")
                                }) {

                                    Text(text = "Go to Home")
                                }

                                TextButton(onClick = {
                                    navController.popBackStack()
                                }) {

                                    Text(text = "Go Back")
                                }
                            }
                        }
                        composable("home") {
                            CompositionLocalProvider(UserLocal provides userLoggedIn) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(text = "Home")
                                    Text(text = "${UserLocal.current}")
                                    val orientation = LocalConfiguration.current.orientation
                                    if(orientation == Configuration.ORIENTATION_LANDSCAPE) {

                                    } else {

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// LocalContext
// LocalConfiguration
// LocalLifecycleOwner
// LocalView