package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.GithubDetailEntity
import com.example.core.data.source.local.entity.GithubEntity

@Database(entities = [GithubEntity::class,GithubDetailEntity::class], version = 2, exportSchema = false)
abstract class GithubDatabase: RoomDatabase() {
     abstract fun githubDao(): GithubDao
}