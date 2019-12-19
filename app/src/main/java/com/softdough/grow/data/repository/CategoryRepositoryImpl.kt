package com.softdough.grow.data.repository

import com.softdough.grow.data.datasource.CategoryLocalDataSource
import com.softdough.grow.data.datasource.CategoryRemoteDataSource
import com.softdough.grow.datasource.local.CategoryLocalDataSourceImpl
import com.softdough.grow.datasource.remote.CategoryRemoteDataSourceImpl
import com.softdough.grow.domain.model.Category
import com.softdough.grow.domain.repository.CategoryRepository
import io.reactivex.Single

class CategoryRepositoryImpl(
    private val remoteDataSource: CategoryRemoteDataSourceImpl,
    private val localDataSource: CategoryLocalDataSourceImpl
) : CategoryRepository {

    //전체 카테고리
    override fun get(cached: Boolean): Single<List<Category>> =
        when (cached) {
            false -> remoteDataSource.get()
                .map { Categories ->
                    Categories.map {
                        localDataSource.set(it.type, listOf(it))
                    }
                    Categories
                }

            true -> localDataSource.get()
                .onErrorResumeNext { get(false) }
        }

    //특정 타입 카테고리
    override fun get(type: String, cached: Boolean): Single<List<Category>> =
        when (cached) {
            false -> remoteDataSource.get(type)
                .map { Categories ->
                    Categories.map {
                        localDataSource.set(it.type, listOf(it))
                    }
                    Categories
                }
            true -> localDataSource.get(type)
                .onErrorResumeNext { get(type, false) }
                //TODO 이 라인이 single로 다시 한번 랩핑할 수도 있음. 에러조심
        }

    override fun set(type: String, name: String): Single<List<Category>> =
        remoteDataSource.set(type, name)
            .flatMap { Categories ->
                localDataSource.set(Categories.first().type, Categories)
            }
}