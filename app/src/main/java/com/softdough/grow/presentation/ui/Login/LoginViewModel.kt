package com.softdough.grow.presentation.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softdough.grow.domain.usecase.AuthorizeUseCase
import com.softdough.grow.presentation.GlobalApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val authorizeUseCase: AuthorizeUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val jwtToken = MutableLiveData<String>()
    val isSignIn = MutableLiveData<Boolean>()

    fun loginKakao(kakaoToken: String) {
        compositeDisposable.add(
            authorizeUseCase.loginKakao(kakaoToken)
                .subscribeOn(Schedulers.io())
                .map{
                    getJwtToken()
                    it
                }
                .subscribe({ isSignIn.postValue(it) }, {})
        )
    }

    fun getJwtToken() {
        compositeDisposable.add(
            authorizeUseCase.getJwtToken()
                .subscribeOn(Schedulers.io())
                .map{
                    GlobalApplication.jwtToken = it
                    it
                }
                .subscribe({ jwtToken.postValue(it) }, { e -> println(e) })
        )
    }
}