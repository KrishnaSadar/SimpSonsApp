package com.example.mlsc_task.models

import com.google.gson.annotations.SerializedName

data class chareCter(
    @SerializedName("quote")
    val quote: String,
    @SerializedName("character")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("characterDirection")
    val characterDirection: String
)
