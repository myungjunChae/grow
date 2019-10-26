package com.softdough.grow.domain.usecase

import android.accounts.Account
import com.softdough.grow.data.repository.AccountRepositoryImpl
import io.reactivex.rxjava3.core.Single

class AccountUseCase(private val accountRepository: AccountRepositoryImpl){
    fun get() : Single<Account> {

    }

    fun set(account: Account){

    }
}