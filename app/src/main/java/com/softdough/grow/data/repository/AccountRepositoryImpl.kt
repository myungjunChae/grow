package com.softdough.grow.data.repository

import android.accounts.Account
import com.softdough.grow.datasource.local.AccountLocalDataSourceImpl
import com.softdough.grow.datasource.remote.AccountRemoteDataSourceImpl
import com.softdough.grow.domain.repository.AccountRepository
import io.reactivex.rxjava3.core.Single

class AccountRepositoryImpl(
    private val remoteDataSource: AccountRemoteDataSourceImpl,
    private val localDataSource: AccountLocalDataSourceImpl
) : AccountRepository {

    override fun get(): Single<Account> {

    }

    override fun set(account: Account) {

    }
}