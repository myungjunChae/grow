package com.softdough.grow.domain.repository

import com.softdough.grow.domain.model.Account
import io.reactivex.Single

interface AccountRepository{
    fun getAccount(cached: Boolean) : Single<Account>

    fun setAccount(account: Account) : Single<Account>
}