package com.codingambitions.jetpackcomposetutorial7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.codingambitions.jetpackcomposetutorial7.ui.theme.JetpackComposeTutorial7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorial7Theme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {

                    navigation(startDestination = "username", route = "login") {
                        composable(route = "username") {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Username",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                TextButton(onClick = {
                                    navController.navigate("password")
                                }) {
                                    Text(text = "Next")
                                }
                            }
                        }
                        composable(route = "password") {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Password",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                TextButton(onClick = {
                                    navController.navigate("tabs") {
                                        // clear back stack for nested navigation
                                        popUpTo("login")
                                    }
                                }) {
                                    Text(text = "Submit and Go To Home")
                                }
                            }
                        }
                    }


                    composable(route = "tabs") {
                        TabsNavGraph()
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
                            Text(
                                text = "Account Screen",
                                style = MaterialTheme.typography.headlineMedium
                            )
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
fun TabsNavGraph() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        label = { Text(stringResource(screen.resource)) },
                        icon = {
                            Icon(
                                imageVector = if (screen.route == "home") Icons.Default.Home else Icons.Default.AccountBox,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            navController.navigate(screen.route) {


                                // findStartDestination -> Finds the actual start destination of the graph,
                                // handling cases where the graph's starting destination is itself a NavGraph(nested navigation)

                                // popUpTo :-  clears the back stack and the state of all
                                // destinations between the current destination and the NavOptionsBuilder.popUpTo ID
                                // But if we use saveState = true, it will save that state( back stack and the state of all
                                // destinations between the current destination and the NavOptionsBuilder.popUpTo ID)
                                // before it clears backstack entries upto popUpTo ID,
                                // and later it restore that backstack if we use restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                launchSingleTop = true

                                restoreState = true

                            }
                        },
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(startDestination = "dashboard", route = "home") {
                composable(route = "dashboard") {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                        Text(
//                            text = "Home",
//                            style = MaterialTheme.typography.headlineMedium
//                        )
//                        TextButton(onClick = {
//                            navController.navigate("profile_detail")
//                        }) {
//                            Text(text = "Go To Nested Profile Detail")
//                        }
                    }
                }
                composable(route = "profile_detail") {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Profile Detail",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        TextButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Text(text = "Go Back")
                        }
                    }
                }
            }
            composable(route = "profile") {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "User Profile",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }

    }
}

val items = listOf(
    Screen.Home,
    Screen.Profile,
)

sealed class Screen(val route: String, @StringRes val resource: Int) {
    object Home : Screen("home", R.string.home)
    object Profile : Screen("profile", R.string.profile)
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