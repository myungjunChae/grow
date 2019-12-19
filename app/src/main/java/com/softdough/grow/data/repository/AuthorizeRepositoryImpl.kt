package com.softdough.grow.data.repository

import com.softdough.grow.datasource.local.AuthorizeLocalDataSourceImpl
import com.softdough.grow.datasource.remote.AuthorizeRemoteDataSourceImpl
import com.softdough.grow.domain.repository.AuthorizeRepository
import io.reactivex.Single

class AuthorizeRepositoryImpl(
    private val remoteDataSource: AuthorizeRemoteDataSourceImpl,
    private val localDataSource: AuthorizeLocalDataSourceImpl
) : AuthorizeRepository {

    override fun loginKakao(kakaoToken: String): Single<Boolean> {
        return remoteDataSource.loginKakao(kakaoToken)
            .flatMap {
                val jwtToken = it.body()?.get("jwt")?.asString
                println("auth : ${jwtToken}")

                jwtToken.also { setJwtToken(it) }

                val isSignIn = it.body()?.get("isSignIn")?.asBoolean
                println("isSignIn : ${isSignIn}")

                Single.just(isSignIn)
            }
    }

    override fun getJwtToken(): Single<String> {
        return localDataSource.getJwtToken()
    }

    override fun setJwtToken(jwtToken: String?) {
        jwtToken!!.also { localDataSource.setJwtToken(it) }
    }
}