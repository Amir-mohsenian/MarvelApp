package com.example.marvelapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelapp.model.Character

@Database(entities = [Character::class],
    version = 1,
    exportSchema = false)
abstract class MarvelDatabase: RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}