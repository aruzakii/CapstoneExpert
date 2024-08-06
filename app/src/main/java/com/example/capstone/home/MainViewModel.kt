package com.example.capstone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.GithubUseCase

class MainViewModel(githubUseCase: GithubUseCase): ViewModel() {
    val github = githubUseCase.getAllGithub().asLiveData()
}