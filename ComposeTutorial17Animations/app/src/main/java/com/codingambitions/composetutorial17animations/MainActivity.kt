package com.codingambitions.composetutorial17animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.codingambitions.composetutorial17animations.ui.theme.ComposeTutorial17AnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorial17AnimationsTheme {
                //AnimateDpAsStateExample()
                //AnimateColorAsStateExample()
                //AnimateMultipleValuesExample()
                //AnimateContentSizeExample()
                //AnimateVisibilityExample()
                //AnimateInfiniteExample()
                AnimateComposableChangeExample()
            }
        }
    }
}


//ANimate single value
@Composable
fun AnimateDpAsStateExample() {

    var expanded by remember {
        mutableStateOf(false)
    }

    val boxSize by animateDpAsState(
        targetValue = if (expanded) 200.dp else 100.dp,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(boxSize)
                .clip(RoundedCornerShape(size = 2.dp))
                .background(Color.Blue)
                .clickable {
                    expanded = !expanded
                }
        )
    }
}

//ANimate single value
@Composable
fun AnimateColorAsStateExample() {

    var expanded by remember {
        mutableStateOf(false)
    }

    val boxSize = 200.dp

    val color by animateColorAsState(
        targetValue = if (expanded) Color.Blue else Color.Red,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(boxSize)
                .clip(RoundedCornerShape(size = 2.dp))
                .background(color)
                .clickable {
                    expanded = !expanded
                }
        )
    }
}

//Animate Multiple Values Example
@Composable
fun AnimateMultipleValuesExample() {

    var expanded by remember {
        mutableStateOf(false)
    }

    val transition = updateTransition(targetState = expanded, label = "updateTransition")

    val boxSize by transition.animateDp(
        transitionSpec = {
            spring(stiffness = Spring.StiffnessLow)
        },
        label = ""
    ) { state ->
        if (state) 200.dp else 100.dp
    }

    val color by transition.animateColor(
        transitionSpec = {
            spring(stiffness = Spring.StiffnessLow)
        },
        label = ""
    ) { state ->
        if (state) Color.Blue else Color.Red
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(boxSize)
                .clip(RoundedCornerShape(size = 2.dp))
                .background(color)
                .clickable {
                    expanded = !expanded
                }
        )
    }
}

// ANimate content size
@Composable
fun AnimateContentSizeExample() {

    var expanded by remember {
        mutableStateOf(false)
    }

    val fulltext =
        "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                expanded = !expanded
            }) {
                Text(text = "Expand/Collapse")
            }
            Text(
                text = fulltext,
                maxLines = if (expanded) 6 else 2,
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = 0.5f))
                    .animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessLow))
            )
        }

    }
}


// ANimate visibility
@Composable
fun AnimateVisibilityExample() {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                expanded = !expanded
            }) {
                Text(text = "Toggle Visibility")
            }

            AnimatedVisibility(
                visible = expanded,
                enter = scaleIn() + expandVertically(),
                exit = scaleOut() + shrinkVertically()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = TestData.testItemsList.first().url),
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    contentDescription = ""
                )
            }

        }

    }
}

// Animate Infinite Example
@Composable
fun AnimateInfiniteExample() {

    var expanded by remember {
        mutableStateOf(false)
    }

    val transition = rememberInfiniteTransition(label = "")

    val boxSize by transition.animateFloat(
        initialValue = 100f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(boxSize.dp)
                .clip(RoundedCornerShape(size = 2.dp))
                .background(Color.Blue)
                .clickable {
                    expanded = !expanded
                }
        )

    }
}

enum class UiState {
    Loading,
    Loaded
}

//Animate Composable Change Example
@Composable
fun AnimateComposableChangeExample() {

    var uiState by remember {
        mutableStateOf(UiState.Loading)
    }

    AnimatedContent(
        targetState = uiState, modifier = Modifier.clickable {
            uiState = when (uiState) {
                UiState.Loading -> UiState.Loaded
                UiState.Loaded -> UiState.Loading
            }
        },
        transitionSpec = {
            scaleIn(animationSpec = spring(stiffness = Spring.StiffnessLow)) togetherWith scaleOut(
                animationSpec = spring(stiffness = Spring.StiffnessLow))
        },
        label = ""
    ) { targetState ->
        when (targetState) {
            UiState.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(48.dp))
                }
            }

            UiState.Loaded -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = TestData.testItemsList.first().url),
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth(),
                        contentDescription = ""
                    )
                }
            }
        }

    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTutorial17AnimationsTheme {
    }
}