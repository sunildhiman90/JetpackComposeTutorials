package com.codingambitions.compsoetutorial5lazygrids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.codingambitions.compsoetutorial5lazygrids.ui.theme.CompsoeTutorial5LazyGridsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompsoeTutorial5LazyGridsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "LazyHorizontalGrid", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))
        LazyHorizontalGrid(
            modifier = Modifier.height(250.dp),
            rows = GridCells.Fixed(3)
        ) {
            items(TestData.testItemsList) { image ->
                AsyncImage(
                    modifier = Modifier.padding(2.dp),
                    model = image.url,
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "LazyVerticalGrid", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(TestData.testItemsList) { image ->
                AsyncImage(
                    modifier = Modifier.padding(2.dp),
                    model = image.url,
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompsoeTutorial5LazyGridsTheme {
        App()
    }
}