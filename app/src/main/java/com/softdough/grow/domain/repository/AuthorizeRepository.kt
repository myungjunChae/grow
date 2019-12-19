package com.softdough.grow.domain.repository

import io.reactivex.Single

interface AuthorizeRepository{

    fun loginKakao(kakaoToken: String) : Single<Boolean>

    fun getJwtToken() : Single<String>

    fun setJwtToken(jwtToken: String?)

}