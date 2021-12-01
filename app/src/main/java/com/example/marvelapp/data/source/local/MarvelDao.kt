package com.example.marvelapp.data.source.local

import androidx.room.*
import com.example.marvelapp.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteCharacter(character: Character)

    @Insert
    suspend fun addCharacters(characters: List<Character>)

    @Query("SELECT * FROM characters")
    fun loadAllCharacters(): Flow<List<Character>?>

    fun loadAllCharactersDistinctUntilChanged() = loadAllCharacters().distinctUntilChanged()

    @Delete(entity = Character::class)
    suspend fun removeAllCharacters()

}