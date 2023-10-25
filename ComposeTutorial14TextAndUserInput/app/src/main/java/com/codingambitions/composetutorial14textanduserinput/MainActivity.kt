package com.codingambitions.composetutorial14textanduserinput

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.codingambitions.composetutorial14textanduserinput.ui.theme.ComposeTutorial14TextAndUserInputTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorial14TextAndUserInputTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {

                    composable(route = "login") {

                        //LoginScreen1(navController = navController)
                        LoginScreen2(navController = navController)

                    }

                    composable("home") {

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "Home")
                            TextButton(modifier = Modifier.fillMaxWidth(), onClick = {
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
fun LoginScreen1(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(46.dp))

        var username by remember {
            mutableStateOf("")
        }

        TextField(
            value = username,
            textStyle = TextStyle(
                brush = Brush.linearGradient(mutableListOf(Color.Blue, Color.Red))
            ),
            onValueChange = {
                username = it
            },
            label = {
                Text(text = "Username")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        var password by remember {
            mutableStateOf("")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                navController.navigate("home")
            })

        )

    }
}


@Composable
fun LoginScreen2(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("Build Annotated")
            }
            withStyle(style = SpanStyle(color = Color.Blue, fontStyle = FontStyle.Italic)) {
                append(" String")
            }
        }

        Text(text = text, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(46.dp))

        var username by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = username,
            textStyle = TextStyle(
                brush = Brush.linearGradient(mutableListOf(Color.Blue, Color.Red))
            ),
            onValueChange = {
                username = it
            },
            label = {
                Text(text = "Username")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        var password by remember {
            mutableStateOf("")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                navController.navigate("home")
            })

        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTutorial14TextAndUserInputTheme {

    }
}