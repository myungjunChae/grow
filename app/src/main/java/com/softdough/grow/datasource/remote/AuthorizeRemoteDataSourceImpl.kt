package com.softdough.grow.datasource.remote

import android.telecom.Call
import android.util.Log
import com.softdough.grow.data.datasource.AuthorizeRemoteDataSource
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Callback
import retrofit2.Response


class AuthorizeRemoteDataSourceImpl(private val api: AuthApi) : AuthorizeRemoteDataSource {

    override fun loginKakao(kakaoToken: String): Single<Response<JsonObject>> {
        val `object` = JsonObject().apply {
            addProperty("access_token", kakaoToken)
        }

        return api.getJwtToken(`object`)
    }
}