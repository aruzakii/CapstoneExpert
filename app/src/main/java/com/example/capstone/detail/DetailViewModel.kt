package com.example.capstone.detail

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

class DetailViewModel(githubUseCase: GithubUseCase): ViewModel() {
//    val github = githubUseCase.getDetailUser("aruzakii").asLiveData()
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> get() = _username

    // StateFlow untuk menyimpan data berdasarkan query
    val github = _username
        .flatMapLatest { username ->
            githubUseCase.getDetailUser(username)
                .stateIn(viewModelScope, SharingStarted.Lazily, Resource.Loading())
        }

    // Fungsi untuk memperbarui query
    fun setUsername(newUsername: String) {
        _username.value = newUsername
    }
}