package com.softdough.grow.datasource.local

import com.softdough.grow.data.datasource.AuthorizeLocalDataSource
import io.reactivex.Single

class AuthorizeLocalDataSourceImpl(private val pref: SharedPreference) :
    AuthorizeLocalDataSource {

    override fun getJwtToken(): Single<String> {
        return pref.run { getValue("JWT", STRING_TYPE) }
            ?.run { Single.just(this) }
            ?: Single.just("")
    }

    override fun setJwtToken(jwtToken: String): String {
        return pref.run { setValue("JWT", jwtToken) }
    }

}