package com.softdough.grow.datasource.remote

import com.google.gson.JsonObject
import com.softdough.grow.data.datasource.AccountRemoteDataSource
import com.softdough.grow.datasource.model.mapToDomain
import com.softdough.grow.domain.model.Account
import io.reactivex.Single

class AccountRemoteDataSourceImpl(private val api : AccountApi) : AccountRemoteDataSource{

    override fun getAccount(): Single<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAccount(account: Account): Single<Account> {
        val `object` = JsonObject().apply{
            addProperty("userName", account.name)
            addProperty("weight", account.weight)
            addProperty("height", account.height)
            addProperty("age", account.age)
            addProperty("gender", account.gender)
        }

        return api.setAccount(`object`).map { it.mapToDomain() }
    }

}