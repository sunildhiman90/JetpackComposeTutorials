package com.codingambitions.composetutorial16gesturespart2

import kotlin.random.Random


object TestData {

//    val imagesList = listOf(
//        "https://stat5.bollywoodhungama.in/wp-content/uploads/2021/10/Gadar-2.jpg",
//        "https://stat4.bollywoodhungama.in/wp-content/uploads/2023/01/Pathaan-25.jpg",
//        //"https://stat4.bollywoodhungama.in/wp-content/uploads/2023/02/Selfiee-17.jpg",
//        "https://stat4.bollywoodhungama.in/wp-content/uploads/2023/01/The-Crew-2.jpg",
//        "https://media5.bollywoodhungama.in/wp-content/uploads/2023/02/Disha-Patani-1.jpg",
//    )

    val imagesList = listOf(
        "https://img.freepik.com/free-photo/wide-angle-shot-single-tree-growing-clouded-sky-during-sunset-surrounded-by-grass_181624-22807.jpg",
        "https://img.freepik.com/free-photo/beautiful-view-greenery-bridge-forest-perfect-background_181624-17827.jpg",
        "https://img.freepik.com/free-photo/green-field-tree-blue-skygreat-as-backgroundweb-banner-generative-ai_1258-153069.jpg",
        "https://img.freepik.com/free-photo/waterfall-nature-thailand_335224-989.jpg",
        "https://img.freepik.com/free-photo/beautiful-shot-crystal-clear-lake-snowy-mountain-base-during-sunny-day_181624-5459.jpg",
        "https://img.freepik.com/free-photo/aerial-view-koh-hong-island-krabi-thailand_335224-1378.jpg",
    )

    val testItemsList = List(44) {
        val random = (0..5).random()
        TestDataItem(
            id = Random.nextInt(100, 100000).toString(),
            url = imagesList[random],
            title = "Title $it"
        )
    }

}


data class TestDataItem(
    var id: String,
    var title: String,
    var url: String
)