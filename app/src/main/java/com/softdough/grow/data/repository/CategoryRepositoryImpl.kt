package com.softdough.grow.data.repository

import com.softdough.grow.data.datasource.CategoryLocalDataSource
import com.softdough.grow.data.datasource.CategoryRemoteDataSource
import com.softdough.grow.domain.model.Category
import com.softdough.grow.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Single

class CategoryRepositoryImpl(
    private val remoteDataSource: CategoryRemoteDataSource,
    private val localDataSource: CategoryLocalDataSource
) : CategoryRepository {
    //전체 카테고리
    override fun get(cached: Boolean): Single<List<Category>> =
        when (cached) {
            false -> remoteDataSource.get()
                .flatMap { localDataSource.set() }
            true -> localDataSource.get()
                .onErrorResumeNext { get(false) }
        }

    //특정 타입 카테고리
    override fun get(type: String, cached: Boolean): Single<List<Category>> =
        when (cached) {
            false -> remoteDataSource.get(type)
                .flatMap { localDataSource.set() }
            true -> localDataSource.get(type)
                .onErrorResumeNext { get(false) }
        }
}