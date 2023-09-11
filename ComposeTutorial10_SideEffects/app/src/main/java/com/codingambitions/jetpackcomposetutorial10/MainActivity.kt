package com.codingambitions.jetpackcomposetutorial10

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codingambitions.jetpackcomposetutorial10.ui.theme.JetpackComposeTutorial10Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// ANy app state change that happens outside the composable is called SideEffect
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorial10Theme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            TextButton(onClick = {
                                navController.navigate("detail")
                            }) {
                                Text(text = "Go To Detail")
                                DisposableEffectExample(onStart = {
                                    println("log onStart")
                                }, onStop = {
                                    println("log onStop")
                                })
                            }
                            Text(text = "Home")
                        }
                    }

                    composable("detail") {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            TextButton(onClick = {

                            }) {
                                Text(text = "Go Back")
                            }
                            LaunchedEffectExample()
                            Text(text = "Detail")
                        }
                    }
                }
            }
        }
    }
}


// Logging and Analytics
// Performing One-Time Initialization such as setting up a connection to a Bluetooth device,
// loading data from a file, or initializing a library.
// using the compose state inside non compose code(firebase analytics, logging etc.)
@Composable
fun SideEffectExample(someData: Int) {

    SideEffect {
        loadFileData()
        initializeSomeLibrary()
    }

    // Some UI here, using the library and file data here in composable
}

fun initializeSomeLibrary() {

}

fun loadFileData() {

}
//TRIGGER:-  triggers on first recomposition of parent only


// call suspend functions from inside the scope of composable
// Fetching Data from a Network
// Performing Image Processing
// uses separate internal coroutine scope
@Composable
fun LaunchedEffectExample() {

    val isLoading = remember { mutableStateOf(true) }
    val data = remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(isLoading.value) {
        if (isLoading.value) {
            // api calling, and update data here
            val dataFetched = fetchData()
            data.value = dataFetched
            isLoading.value = false
        }
    }

    //change ui here depending upon data

    if (isLoading.value) {
        CircularProgressIndicator()
    } else {
        LazyColumn {
            items(data.value) { item ->
                Text(text = item)
            }
        }
    }

}
//TRIGGER:-  triggers on first composition or key change


//Adding and removing event listeners
//register and unregister lifecycle observers
//Starting and stopping animations
//Bind and unbinding sensors resources such as Camera, LocationManager, etc
@Composable
fun DisposableEffectExample(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit,
    onStop: () -> Unit
) {
    // Safely update the current lambdas when a new one is provided
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnStop by rememberUpdatedState(onStop)

    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                currentOnStart()
            } else if (event == Lifecycle.Event.ON_STOP) {
                currentOnStop()
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
//TRIGGER:-  triggers on first composition or key change


// launch coroutine, call suspend functions from outside the scope of composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberCoroutineScopeExample(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->
        Column(Modifier.padding(contentPadding)) {
            Button(
                onClick = {
                    // Create a new coroutine in the event handler to show a snackbar
                    scope.launch {
                        snackbarHostState.showSnackbar("Something happened!")
                    }
                }
            ) {
                Text("Press me")
            }
        }
    }

}

// Simulate a network call by suspending the coroutine for 2 seconds
private suspend fun fetchData(): List<String> {
    // Simulate a network delay
    delay(2000)
    return listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
}
