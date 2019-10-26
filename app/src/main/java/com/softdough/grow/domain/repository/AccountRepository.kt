package com.softdough.grow.domain.repository

import android.accounts.Account
import io.reactivex.rxjava3.core.Single

interface AccountRepository{
    fun get() : Single<Account>

    fun set(account: Account)
}