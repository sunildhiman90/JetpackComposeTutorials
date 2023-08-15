package com.codingambitions.jetpackcomposetutorial7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codingambitions.jetpackcomposetutorial7.ui.theme.JetpackComposeTutorial7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorial7Theme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {

                    composable(route = "login") {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Login Screen", style = MaterialTheme.typography.headlineMedium)
                            TextButton(onClick = {
                                navController.navigate("home/123/1")
                                //navController.navigate("account")
                            }) {
                                Text(text = "Go To Screen 2")
                            }
                        }
                    }

                    // Required arguments
                    composable(route = "home/{userId}/{id}") { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")
                        val id = backStackEntry.arguments?.getString("id")
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Home Screen", style = MaterialTheme.typography.headlineMedium)
                            Text(text = "userId=$userId")
                            Text(text = "id=$id")
                            TextButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Text(text = "Go Back")
                            }
                        }
                    }

                    // Optional arguments
                    composable(
                        route = "account?userId={userId}",
                        arguments = listOf(navArgument("userId") { defaultValue = "1" })
                    ) { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Account Screen", style = MaterialTheme.typography.headlineMedium)
                            Text(text = "userId=$userId")
                            TextButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Text(text = "Go Back")
                            }
                        }
                    }
                }


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTutorial7Theme {
        Greeting("Android")
    }
}