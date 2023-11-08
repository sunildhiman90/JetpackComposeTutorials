package com.codingambitions.composetutorial16gesturespart2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.codingambitions.composetutorial16gesturespart2.ui.theme.ComposeTutorial16GesturesPart2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorial16GesturesPart2Theme {
                ImagesVerticalGrid(feedItems = TestData.testItemsList)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesVerticalGrid(feedItems: List<TestDataItem>) {

    var selectedImageItem by rememberSaveable {
        mutableStateOf<TestDataItem?>(null)
    }

    var lonPressSelectedImageItem by rememberSaveable {
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
                    // Gesture Recognisers, We cna use when we need to get and use the offsets of the taps
                    /*.pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                selectedImageItem = feed
                            },
                            onDoubleTap = {

                            },
                            onLongPress = {

                            }
                        )
                    }*/
                    // High level
                    .combinedClickable(
                        onClick = {
                            selectedImageItem = feed
                        },
                        onDoubleClick = {

                        },
                        onLongClick = {
                            lonPressSelectedImageItem = feed
                        }
                    )


            )

        }
    }

    if (selectedImageItem != null) {
        FullScreenImage(feedItem = selectedImageItem) {
            selectedImageItem = null
        }
    }

    if (lonPressSelectedImageItem != null) {
        BottomSheet(feedItem = lonPressSelectedImageItem) {
            lonPressSelectedImageItem = null
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

        var zoomed by remember {
            mutableStateOf(false)
        }

        var zoomOffset by remember {
            mutableStateOf(Offset.Zero)
        }

        Image(
            painter = rememberAsyncImagePainter(model = feedItem?.url),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            val newOffset = Offset((-(it.x - (size.width / 2f))).coerceIn(-size.width / 2f,  size.width/2f ), 0f)
                            zoomOffset = if (zoomed) Offset.Zero else newOffset
                            zoomed = !zoomed
                        }
                    )
                }
                .graphicsLayer {
                    scaleX = if (zoomed) 2f else 1f
                    scaleY = if (zoomed) 2f else 1f
                    translationX = zoomOffset.x
                    translationY = zoomOffset.y
                }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(feedItem: TestDataItem?, onDismiss: () -> Unit) {

    ModalBottomSheet(onDismissRequest = { onDismiss() }) {
        ListItem(headlineContent = {
            Text(text = "Share")
        }, leadingContent = {
            Icon(Icons.Default.Share, null)
        })

        ListItem(headlineContent = {
            Text(text = "Remove")
        }, leadingContent = {
            Icon(Icons.Default.Delete, null)
        })
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTutorial16GesturesPart2Theme {

    }
}