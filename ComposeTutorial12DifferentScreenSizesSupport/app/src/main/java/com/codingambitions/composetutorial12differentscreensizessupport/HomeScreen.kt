package com.codingambitions.composetutorial12differentscreensizessupport

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane

@Composable
fun HomeScreen(
    navigationType: NavigationType,
    userId: Int?,
    isOnlyDetailScreen: Boolean,
    goToUserDetail: (Int) -> Unit,
    onDetailBackPressed: () -> Unit,
) {

    //custom back handler
    BackHandler {
        println("BackHandler")
        onDetailBackPressed()
    }

    if (navigationType == NavigationType.PERMANENT_NAV_DRAWER) {

        TwoPane(
            first = { HomeSingleScreen(goToUserDetail = goToUserDetail) },
            second = {
                UserDetailScreen(userId = userId, isOnlyDetailScreen = isOnlyDetailScreen) {

                }
            },
            strategy = HorizontalTwoPaneStrategy(splitFraction = 0.45f, gapWidth = 16.dp),
            displayFeatures = mutableListOf()
        )

    } else {

        if(userId != null && isOnlyDetailScreen && navigationType == NavigationType.NAV_RAIL) {
            UserDetailScreen(userId = userId, isOnlyDetailScreen = isOnlyDetailScreen) {
                onDetailBackPressed()
            }
        } else {
            HomeSingleScreen(goToUserDetail = goToUserDetail)
        }

    }

}

@Composable
fun HomeSingleScreen(
    goToUserDetail: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(8.dp)
    ) {

        items(45) { index ->
            Card(shape = RoundedCornerShape(15.dp), modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                .clickable {
                    println("userID=$index")
                    goToUserDetail(index)
                }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.dummy_user),
                        contentDescription = "",
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(text = "User $index")
                }
            }
        }
    }
}