package com.softdough.grow.presentation.ui.UserInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softdough.grow.R
import com.softdough.grow.domain.model.Account
import com.softdough.grow.domain.usecase.AccountUseCase
import com.softdough.grow.domain.usecase.AuthorizeUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserInfoViewModel(
    private val accountUseCase: AccountUseCase,
    private val authorizeUseCase: AuthorizeUseCase
) : ViewModel() {

    val genderPage = MutableLiveData<Int>()
    val currentPage = MutableLiveData<Int>()

    val jwtToken = MutableLiveData<String>()

    val genderResources = listOf(R.drawable.image_male, R.drawable.image_female)

    private val compositeDisposable = CompositeDisposable()

    init {
        currentPage.value = 0
        genderPage.value = 0
    }

    fun getJwtToken() {
        compositeDisposable.add(
            authorizeUseCase.getJwtToken()
                .subscribeOn(Schedulers.io())
                .subscribe({ jwtToken.postValue(it) }, { e -> println(e) })
        )
    }


    fun setUserInfo(account: Account) {
        compositeDisposable.add(
            accountUseCase.setUserInfo(account)
                .subscribeOn(Schedulers.io())
                .subscribe({}, {})
        )
    }
}