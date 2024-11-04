package com.ravemaster.carapiapp.data.remote.models

data class Trim(
    val created: String,
    val description: String,
    val id: Int,
    val invoice: Int,
    val make_model: MakeModel,
    val make_model_id: Int,
    val modified: String,
    val msrp: Int,
    val name: String,
    val year: Int
)