package com.example.marvelapp.model

import com.google.gson.annotations.SerializedName

class ApiResponse<T>(
    @SerializedName("offset") val offset: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val result: List<T>
) {

}