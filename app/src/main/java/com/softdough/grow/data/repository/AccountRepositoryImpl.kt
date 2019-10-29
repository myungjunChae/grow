package com.softdough.grow.data.repository

import android.accounts.Account
import com.softdough.grow.datasource.local.AccountLocalDataSourceImpl
import com.softdough.grow.datasource.remote.AccountRemoteDataSourceImpl
import com.softdough.grow.domain.repository.AccountRepository
import io.reactivex.Single

class AccountRepositoryImpl(
    private val remoteDataSource: AccountRemoteDataSourceImpl,
    private val localDataSource: AccountLocalDataSourceImpl
) : AccountRepository {
    override fun get(cached: Boolean): Single<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(account: Account) {

    }
}