package com.example.marvelapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.marvelapp.data.source.remote.MarvelRemoteDataSource
import com.example.marvelapp.model.Character
import com.example.marvelapp.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarvelMainViewModel @Inject constructor(
    private val dataSource: MarvelRemoteDataSource
) : ViewModel() {

    fun loadAllCharacters():
            LiveData<Resource<List<Character>?>> =
        dataSource.fetchAllCharacters().asLiveData()

}