package com.softdough.grow.domain.usecase

import com.softdough.grow.data.repository.AuthorizeRepositoryImpl
import io.reactivex.Single

class AuthorizeUseCase(private val authorizeRepository : AuthorizeRepositoryImpl){
    fun getJwtToken(): Single<String> {
        return authorizeRepository.getJwtToken()
    }

    fun loginKakao(kakaoToken : String) : Single<Boolean>{
        return authorizeRepository.loginKakao(kakaoToken)
    }
}