package com.example.rezo

import com.google.gson.annotations.SerializedName

data class ReprésentationsClass(
    val nombre: Int,
    val description: String,
    @SerializedName("representation")
    val representation: String = ""
)
