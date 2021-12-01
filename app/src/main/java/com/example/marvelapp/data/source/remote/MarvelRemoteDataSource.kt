package com.example.marvelapp.data.source.remote

import com.example.marvelapp.model.Character
import com.example.marvelapp.repository.Resource
import kotlinx.coroutines.flow.Flow

interface MarvelRemoteDataSource {
    fun fetchAllCharacters(): Flow<Resource<List<Character>?>>
}