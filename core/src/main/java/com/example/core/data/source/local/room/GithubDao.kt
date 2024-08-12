package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.GithubDetailEntity
import com.example.core.data.source.local.entity.GithubEntity
import com.example.core.domain.model.GithubDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao {
    @Query("SELECT * FROM github")
    fun getAllGithub(): Flow<List<GithubEntity>>

    @Query("SELECT * FROM githubdetail")
    fun getDetailGithub(): Flow<GithubDetailEntity>

    @Query("DELETE FROM github")
    suspend fun deleteAllGithub()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithub(github: List<GithubEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubDetail(githubDetail: GithubDetailEntity)

    @Query("DELETE FROM githubdetail")
    suspend fun deleteGithubDetail()
}