package com.softdough.grow.datasource.remote

import com.softdough.grow.datasource.model.AccountEntity
import com.softdough.grow.datasource.model.CategoryEntity
import com.softdough.grow.datasource.model.RoutineEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

//TODO : jwt token 맵핑
interface AccountApi {
    @Headers("Authorization : jwt")
    @GET("account/")
    fun getAccount() : Single<AccountEntity>
}

interface CategoryApi {
    //전체 카테고리
    @Headers("Authorization : jwt")
    @GET("category/")
    fun getAllCategory() : Single<List<CategoryEntity>>

    //타입별 카테고리
    @Headers("Authorization : jwt")
    @GET("category/{type}")
    fun getTypedCategory(@Path("type") categoryType: String) : Single<List<CategoryEntity>>

    //TODO : 카테고리 POST
}

interface RoutineApi {
    @Headers("Authorization : jwt")
    @GET("routine/{category_id}")
    fun getRoutine(@Path("category_id") categoryId: Long) : Single<List<RoutineEntity>>

    //TODO : 루틴 POST
}