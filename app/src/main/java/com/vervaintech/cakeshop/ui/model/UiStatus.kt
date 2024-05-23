package com.vervaintech.cakeshop.ui.model

sealed class UiStatus {
    data object None : UiStatus()
    data object Loading : UiStatus()
    data object Success : UiStatus()
    data object Error : UiStatus()
}
