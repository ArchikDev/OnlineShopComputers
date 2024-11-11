package com.ar4uk.onlineshopcomputers.presentation.select

import com.ar4uk.onlineshopcomputers.domain.model.App

sealed class State {
    data object Initial: State()
    data object Loading: State()
    data class Content(val checkList: List<App>): State()
}