package com.example.mad.data.model

import com.google.gson.annotations.SerializedName

data class Numbers(
    @SerializedName("text") val text: String,
    @SerializedName("number") val number: Long,
    @SerializedName("found") val found: Boolean,
    @SerializedName("type") val type: String
)