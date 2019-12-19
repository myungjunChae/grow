package com.softdough.grow.presentation.ui.Splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softdough.grow.domain.usecase.AuthorizeUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SplashViewModel(private val authorizeUseCase: AuthorizeUseCase) : ViewModel() {

    val jwtToken = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    fun getJwtToken() {
        compositeDisposable.add(
            authorizeUseCase.getJwtToken()
                .subscribeOn(Schedulers.io())
                .subscribe({jwtToken.postValue(it)}, {e -> println(e)})
        )
    }
}