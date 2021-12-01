package com.example.marvelapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val charId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String) {
}