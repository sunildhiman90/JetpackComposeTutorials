package com.codingambitions.composetutorial15gestures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.codingambitions.composetutorial15gestures.ui.theme.ComposeTutorial15GesturesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorial15GesturesTheme {
                ImagesVerticalGrid(feedItems = TestData.testItemsList)
            }
        }
    }
}


// Every kind of physical object interaction with app called uses pointer

// 1. Composable inbuilt support: onClick in BUtton
// 2. gesture modifiers : Applied to composables: -> clickable, draggable scrollable etc.
// 3. Low level, using pointerInput :- pointerInput -> detectTapGEstures, detectDragGestures etc..

@Composable
fun ImagesVerticalGrid(feedItems: List<TestDataItem>) {

    var selectedImageItem by rememberSaveable {
        mutableStateOf<TestDataItem?>(null)
    }
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(items = TestData.testItemsList, key = {
            it.id
        }) { feed ->
            Image(
                painter = rememberAsyncImagePainter(model = feed.url),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(1.dp)
                    .height(140.dp)
                    .wrapContentWidth()
                    //Custom on Tap
                    /*.pointerInput(Unit) {
                        awaitEachGesture {
                            //await for first down event
                            awaitFirstDown().let {
                                it.consume()
                                //selectedImageItem = feed
                            }
                            //await for first up event
                            waitForUpOrCancellation()?.let {
                                it.consume()
                                selectedImageItem = feed
                            }

                        }
                    }*/
                    // Gesture Recognisers
                    /*.pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                selectedImageItem = feed
                            }
                        )
                    }*/
                    // High level
                    .clickable {
                        selectedImageItem = feed
                    }


            )

        }
    }

    if (selectedImageItem != null) {
        FullScreenImage(feedItem = selectedImageItem) {
            selectedImageItem = null
        }
    }


}

@Composable
fun FullScreenImage(feedItem: TestDataItem?, onDismiss: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray.copy(alpha = 0.75f))
            .clickable { onDismiss() }
        )
        {

        }
        Image(
            painter = rememberAsyncImagePainter(model = feedItem?.url),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTutorial15GesturesTheme {
    }
}