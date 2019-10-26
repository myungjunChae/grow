package com.softdough.grow.datasource.remote

import android.accounts.Account
import com.softdough.grow.data.datasource.AccountRemoteDataSource
import io.reactivex.rxjava3.core.Single

class AccountRemoteDataSourceImpl(private val api : AccountApi) : AccountRemoteDataSource{
    override fun get(): Single<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(accout: Account): Single<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}