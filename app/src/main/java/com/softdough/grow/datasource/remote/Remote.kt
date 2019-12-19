package com.softdough.grow.datasource.remote

import com.google.gson.JsonObject
import com.softdough.grow.datasource.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*


interface AuthApi {
    @POST("oauth/login/kakao")
    fun getJwtToken(@Body json: JsonObject): Single<Response<JsonObject>>
}

interface AccountApi {
    @PUT("account")
    fun setAccount(@Body json: JsonObject): Single<AccountEntity>
}

interface CategoryApi {
    //전체 카테고리
    @GET("category")
    fun getAllCategory(): Single<List<CategoryEntity>>

    //타입별 카테고리
    @GET("category/all/")
    fun getTypedCategory(@Query("categoryType") type: String): Single<List<CategoryEntity>>

    //@Headers("Authorization : jwt")
    @POST("category")
    fun setCategory(@Body body: JsonObject): Single<List<CategoryEntity>>
}

interface RoutineApi {
    @GET("category/routines/")
    fun getRoutine(@Query("categoryId") categoryId: Long): Single<List<RoutineEntity>>

    @POST("routine")
    fun setRoutine(@Body json: JsonObject): Single<RoutineEntity>

    @POST("routine/exercise")
    fun linkRoutineExercise(@Body json: JsonObject) : Single<RoutineEntity>
}

interface ExerciseApi {
    @GET("exercise/all")
    fun getExerciseAll() : Single<List<ExerciseEntity>>

    @GET("routine/exercise-list")
    fun getExercise(@Query("routineId") routineId: Long) : Single<ExercisePojo>
}

interface SetInfoApi{
    @GET("set-info")
    fun getSetInfo(@Query("routineId") routineId : Long, @Query("exerciseId") exerciseId : Long): Single<SetPojo>

    @POST("set-info")
    fun setSetInfo(@Body json: JsonObject): Single<SetPojo>

    @PUT("set-info")
    fun putSetInfo()
}