package com.softdough.grow.data.repository

import com.softdough.grow.datasource.remote.AccountRemoteDataSourceImpl
import com.softdough.grow.domain.model.Account
import com.softdough.grow.domain.repository.AccountRepository
import io.reactivex.Single

class AccountRepositoryImpl(
    private val remoteDataSource: AccountRemoteDataSourceImpl,
    private val localDataSource: AccountRemoteDataSourceImpl
) : AccountRepository {
    override fun getAccount(cached: Boolean): Single<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAccount(account: Account): Single<Account> {
        return remoteDataSource.setAccount(account)
    }

}