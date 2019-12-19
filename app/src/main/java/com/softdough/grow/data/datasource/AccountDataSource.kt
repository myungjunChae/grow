package com.softdough.grow.data.datasource

import com.softdough.grow.domain.model.Account
import io.reactivex.Single

interface AccountRemoteDataSource{
    fun getAccount() : Single<Account>

    fun setAccount(account: Account) : Single<Account>
}

interface AccountLocalDataSource{
    fun getAccount() : Single<Account>

    fun setAccount(account: Account)
}