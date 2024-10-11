package com.ar4uk.onlineshopcomputers.domain.model

data class Items(
    var title: String = "",
    var description: String = "",
    var picUrl: List<String> = listOf(),
    var model: List<String> = listOf(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0,
    var showRecommended: Boolean = false,
    var categoryId: Int = 0
)