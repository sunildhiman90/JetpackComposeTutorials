package com.codingambitions.compsoetutorial5lazygrids


import kotlin.random.Random


data class TestDataItem(
    var id: String,
    var title: String,
    var url: String
)

object TestData {
    val imagesList = listOf(
        "https://stat5.bollywoodhungama.in/wp-content/uploads/2021/10/Gadar-2.jpg",
        "https://stat4.bollywoodhungama.in/wp-content/uploads/2023/01/Pathaan-25.jpg",
        //"https://stat4.bollywoodhungama.in/wp-content/uploads/2023/01/Sakshi-Malik-2-1.jpg",
       //"https://stat4.bollywoodhungama.in/wp-content/uploads/2023/02/Selfiee-17.jpg",
        "https://stat4.bollywoodhungama.in/wp-content/uploads/2023/01/The-Crew-2.jpg",
        "https://media5.bollywoodhungama.in/wp-content/uploads/2023/02/Disha-Patani-1.jpg",
    )

    val testItemsList = List(50) {
        val random = (0..3).random()
        TestDataItem(
            id = Random.nextInt(100, 100000).toString(),
            url = imagesList[random],
            title = "Title $it"
        )
    }

}
