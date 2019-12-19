package com.softdough.grow.domain.usecase

import com.softdough.grow.domain.model.Account
import com.softdough.grow.domain.repository.AccountRepository
import io.reactivex.Single

class AccountUseCase(private val accountRepository: AccountRepository) {

    fun setUserInfo(account: Account) : Single<Account> {
        return accountRepository.setAccount(account)
    }
}

