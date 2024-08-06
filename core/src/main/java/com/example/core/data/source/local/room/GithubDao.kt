package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.GithubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao {
    @Query("SELECT * FROM github")
    fun getAllGithub(): Flow<List<GithubEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithub(github: List<GithubEntity>)
}