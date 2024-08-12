package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.DetailUserGithubResponse
import com.example.core.data.source.remote.response.ItemsItem
import com.example.core.domain.model.Github
import com.example.core.domain.model.GithubDetail
import com.example.core.domain.repository.IGithubRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GithubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGithubRepository {

    override fun getAllGithub(querry: String): Flow<Resource<List<Github>>> =
        object : NetworkBoundResource<List<Github>, List<ItemsItem>>() {
            override fun loadFromDB(): Flow<List<Github>> {
                return localDataSource.getAllGithub().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Github>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ItemsItem>>> {
                return remoteDataSource.getAllGithub(querry)
            }

            override suspend fun deleteOldData() {
               localDataSource.deleteAllGithub()
            }


            override suspend fun saveCallResult(data: List<ItemsItem>) {
                val githubList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGithub(githubList)
            }
        }.asFlow()

    override fun getDetailUser(username: String) : Flow<Resource<GithubDetail>> =
        object : NetworkBoundResource<GithubDetail, DetailUserGithubResponse>(){
            override fun loadFromDB(): Flow<GithubDetail> {
                return localDataSource.getDetailGithub().map {
                    DataMapper.mapEntitiesToDomain2(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailUserGithubResponse>> {
              return remoteDataSource.getDetailUSer(username)
            }

            override suspend fun deleteOldData() {
                localDataSource.deleteGithubDetail()
            }

            override suspend fun saveCallResult(data: DetailUserGithubResponse) {
                val githubdetail = DataMapper.mapResponsesToEntities2(data)
                localDataSource.insertGithubDetail(githubdetail)
            }

            override fun shouldFetch(data: GithubDetail?): Boolean = true

        }.asFlow()



//    override fun getFavoriteTourism(): Flow<List<Github>> {
//        return localDataSource.getFavoriteTourism().map {
//            DataMapper.mapEntitiesToDomain(it)
//        }
//    }

//    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
//        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
//        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
//    }
}
