package com.softdough.grow.datasource.local

import android.accounts.Account
import com.softdough.grow.data.datasource.AccountLocalDataSource
import io.reactivex.Single

class AccountLocalDataSourceImpl(private val pref : SharedPreference) : AccountLocalDataSource{

    override fun getAccount(): Single<com.softdough.grow.domain.model.Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAccount(account: com.softdough.grow.domain.model.Account) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}