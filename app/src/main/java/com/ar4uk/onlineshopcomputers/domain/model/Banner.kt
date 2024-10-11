package com.ar4uk.onlineshopcomputers.domain.model

import com.google.firebase.firestore.Exclude

data class Banner(
    @Exclude
    var id: String? = null,
    val url: String? = null,
)
