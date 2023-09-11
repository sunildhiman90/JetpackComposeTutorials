package com.codingambitions.compopsetutorial6lazystaggeredgrids

import kotlin.random.Random


data class TestDataItem(
    var id: String,
    var title: String,
    var url: String
)

object TestData {
    val imagesList = listOf(
        //"https://stat5.bollywoodhungama.in/wp-content/uploads/2021/10/Gadar-2.jpg",
        //"https://stat4.bollywoodhungama.in/wp-content/uploads/2023/01/Pathaan-25.jpg",
        "https://fastly.picsum.photos/id/472/200/200.jpg?hmac=PScxKeNxgxcauarhbWIWesyo4VsouCtfdX8fNTy9HRI",
        //"https://stat4.bollywoodhungama.in/wp-content/uploads/2023/01/Sakshi-Malik-2-1.jpg",
        //"https://stat4.bollywoodhungama.in/wp-content/uploads/2023/02/Selfiee-17.jpg",
        "https://fastly.picsum.photos/id/579/200/300.jpg?hmac=9MD8EV4Jl9EqKLkTj5kyNdBUKQWyHk2m4pE4UCBGc8Q",
        "https://fastly.picsum.photos/id/870/200/300.jpg?blur=2&grayscale&hmac=ujRymp644uYVjdKJM7kyLDSsrqNSMVRPnGU99cKl6Vs",
    )

    val testItemsList = List(50) {
        val random = (0..2).random()
        TestDataItem(
            id = Random.nextInt(100, 100000).toString(),
            url = imagesList[random],
            title = "Title $it"
        )
    }

}
