package com.example.core.utils

import com.example.core.data.source.local.entity.GithubDetailEntity
import com.example.core.data.source.local.entity.GithubEntity
import com.example.core.data.source.remote.response.DetailUserGithubResponse
import com.example.core.data.source.remote.response.ItemsItem
import com.example.core.domain.model.Github
import com.example.core.domain.model.GithubDetail

object DataMapper {
    fun mapResponsesToEntities(input: List<ItemsItem>): List<GithubEntity> {
        val githubList = ArrayList<GithubEntity>()
        input.map {
            val github = GithubEntity(
                id = it.id,
                reposUrl = it.reposUrl,
                followersUrl = it.followersUrl,
                followingUrl = it.followingUrl,
                login = it.login,
                avatarUrl = it.avatarUrl,
            )
           githubList.add(github)
        }
        return githubList
    }

    fun mapEntitiesToDomain(input: List<GithubEntity>): List<Github> =
        input.map {
            Github(
                id = it.id,
                reposUrl = it.reposUrl,
                followersUrl = it.followersUrl,
                followingUrl = it.followingUrl,
                login = it.login,
                avatarUrl = it.avatarUrl,
            )
        }

    fun mapDomainToEntity(input: Github) = GithubEntity(
        id = input.id,
        reposUrl = input.reposUrl,
        followersUrl = input.followersUrl,
        followingUrl = input.followingUrl,
        login = input.login,
        avatarUrl = input.avatarUrl,
    )


    fun mapResponsesToEntities2(input: DetailUserGithubResponse) =
        GithubDetailEntity(
        id =  input.id,
        followers = input.followers,
        following = input.following,
        avatarUrl = input.avatarUrl,
        login = input.login,
        name = input.name
    )

    fun mapEntitiesToDomain2(input: GithubDetailEntity)= GithubDetail (
        id =  input.id,
        followers = input.followers,
        following = input.following,
        avatarUrl = input.avatarUrl,
        login = input.login,
        name = input.name
    )


    fun mapDomainToEntity2(input: Github) = GithubEntity(
        id = input.id,
        reposUrl = input.reposUrl,
        followersUrl = input.followersUrl,
        followingUrl = input.followingUrl,
        login = input.login,
        avatarUrl = input.avatarUrl,
    )
}