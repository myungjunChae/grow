package com.softdough.grow.datasource.remote

import com.softdough.grow.datasource.model.AccountEntity
import com.softdough.grow.datasource.model.CategoryEntity
import com.softdough.grow.datasource.model.RoutineEntity
import io.reactivex.Single
import retrofit2.http.*

//TODO : jwt token 맵핑
interface AccountApi {
    @Headers("Authorization : jwt")
    @GET("account")
    fun getAccount(): Single<AccountEntity>
}

interface CategoryApi {
    //전체 카테고리
    //@Headers("Authorization : jwt")
    @GET("category")
    fun getAllCategory(): Single<List<CategoryEntity>>

    //타입별 카테고리
    //@Headers("Authorization : jwt")
    @GET("category/{type}")
    fun getTypedCategory(@Path("type") type: String): Single<List<CategoryEntity>>

    //@Headers("Authorization : jwt")
    @FormUrlEncoded
    @POST("category")
    fun setCategory(@Field("categoryType") type: String, @Field("categoryName") name: String): Single<List<CategoryEntity>>
}

interface RoutineApi {
    //@Headers("Authorization : jwt")
    @GET("routine/{category_id}")
    fun getRoutine(@Path("category_id") categoryId: Long): Single<List<RoutineEntity>>

    //TODO : 카테고리 id 필드 점검
    @FormUrlEncoded
    @POST("routine")
    fun setRoutine(@Field("categoryId") categoryId: Long, @Field("routineName") name: String): Single<List<RoutineEntity>>
}