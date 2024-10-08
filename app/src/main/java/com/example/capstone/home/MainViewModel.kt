package com.example.capstone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.usecase.GithubUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class MainViewModel(githubUseCase: GithubUseCase): ViewModel() {
//    var querry = ""
//
//    val github = githubUseCase.getAllGithub(querry).asLiveData()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    // StateFlow untuk menyimpan data berdasarkan query
    val github = _query
        .flatMapLatest { query ->
            githubUseCase.getAllGithub(query)
                .stateIn(viewModelScope, SharingStarted.Lazily, Resource.Loading())
        }

    // Fungsi untuk memperbarui query
    fun setQuery(newQuery: String) {
        _query.value = newQuery
    }

}