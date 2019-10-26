package com.softdough.grow.data.datasource

import android.accounts.Account
import io.reactivex.rxjava3.core.Single

interface AccountRemoteDataSource{
    fun get() : Single<Account>

    fun set(accout: Account) : Single<Account>
}

interface AccountLocalDataSource{
    fun get() : Single<Account>

    fun set(accout: Account)
}