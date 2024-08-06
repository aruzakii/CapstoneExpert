package com.example.core.domain.usecase

import com.example.core.data.GithubRepository
import com.example.core.data.Resource
import com.example.core.domain.model.Github
import com.example.core.domain.repository.IGithubRepository
import kotlinx.coroutines.flow.Flow

class GithubInteractor(private val githubRepository: IGithubRepository): GithubUseCase {
    override fun getAllGithub() = githubRepository.getAllGithub()
}