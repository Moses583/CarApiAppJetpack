package com.ravemaster.carapiapp.presentation

import com.ravemaster.carapiapp.data.remote.models.ApiResponse

data class MyStates (
    val apiResponse: ApiResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null

)