package com.example.marvelapp.model

import com.google.gson.annotations.SerializedName

class BaseApiResponse<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: ApiResponse<T>
) {
}