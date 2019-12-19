package com.softdough.grow.data.datasource

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Callback
import retrofit2.Response

interface AuthorizeRemoteDataSource {
    fun loginKakao(kakaoToken: String): Single<Response<JsonObject>>
}

interface AuthorizeLocalDataSource {

    fun getJwtToken(): Single<String>

    fun setJwtToken(jwtToken: String) : String
}