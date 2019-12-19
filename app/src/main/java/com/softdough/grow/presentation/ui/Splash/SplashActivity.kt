package com.softdough.grow.presentation.ui.Splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.softdough.grow.R
import com.softdough.grow.databinding.ActivitySplashBinding
import com.softdough.grow.injectionFeature
import com.softdough.grow.presentation.GlobalApplication
import com.softdough.grow.presentation.base.BaseActivity
import com.softdough.grow.presentation.bindColor
import com.softdough.grow.presentation.startActivityWithFinish
import com.softdough.grow.presentation.ui.Login.LoginActivity
import com.softdough.grow.presentation.ui.UserInfo.UserInfoActivity
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private val vm: SplashViewModel by viewModel()
    override val resourceId: Int = R.layout.activity_splash
    override val statusBarColor: Int by bindColor(R.color.colorWhite)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(application)
            injectionFeature()
        }

        vm.jwtToken.observe(this, Observer { checkLoginBefore(it) })

        binding.button.setOnClickListener {
            vm.getJwtToken()
        }

    }

    private fun checkLoginBefore(jwtToken: String?) {
        Toast.makeText(this, "JWT : ${jwtToken}", Toast.LENGTH_SHORT).show()

        //TODO rxjava에서 어떻게 null check할건지
        when (jwtToken == "") {
            true -> {
                //로그인
                startActivityWithFinish<LoginActivity>()
            }
            false -> {
                //메인 액티비티
                GlobalApplication.jwtToken = jwtToken!!
                println(GlobalApplication.jwtToken)
                startActivityWithFinish<UserInfoActivity>()
                //TODO : 서버 데이터가 새로 갱신되었을 때, 데이터 갱신에 대한 고지
            }

            //TODO : 토큰은 존재하는데 계정이 존재하지 않을 때,
        }
    }
}