package com.softdough.grow.domain.repository

import android.accounts.Account
import io.reactivex.Single

interface AccountRepository{
    fun get(cached: Boolean) : Single<Account>

    fun set(account: Account)
}