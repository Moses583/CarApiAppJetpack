package com.ravemaster.carapiapp.data.remote.models

data class ApiResponse(
    val make: String,
    val model: String,
    val specs: Specs,
    val trim: String,
    val trims: List<Trim>,
    val year: Int
)