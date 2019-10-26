package com.softdough.grow.datasource.local

import android.accounts.Account
import com.softdough.grow.data.datasource.AccountLocalDataSource
import io.reactivex.rxjava3.core.Single

class AccountLocalDataSourceImpl(private val sharedPreference : SharedPreference) : AccountLocalDataSource{
    override fun get(): Single<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(accout: Account) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}