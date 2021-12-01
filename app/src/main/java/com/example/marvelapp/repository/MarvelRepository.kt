package com.example.marvelapp.repository

import com.example.marvelapp.data.source.local.MarvelDao
import com.example.marvelapp.data.source.remote.MarvelRemoteDataSource
import com.example.marvelapp.data.source.remote.MarvelService
import com.example.marvelapp.data.source.remote.provideData
import com.example.marvelapp.model.Character
import kotlinx.coroutines.flow.Flow


class MarvelRepository(
    private val dao: MarvelDao,
    private val marvelService: MarvelService
) : MarvelRemoteDataSource {

    override fun fetchAllCharacters(): Flow<Resource<List<Character>?>> =
        provideData(
            loadDb = { dao.loadAllCharactersDistinctUntilChanged() },
            createCall = {
                marvelService.getCharacters(
                    apiKey = "c0a432008a7db09d71c25a71e745d81c",
                    hash = "d65811088bb03cc06b5ef0540628ea89",
                    timestamp = 1634237337
                )
            },
            shouldFetch = { cacheData -> cacheData == null }, removeDb = { dao.removeAllCharacters() }
        ) { dao.addCharacters(it.data.result) }
}