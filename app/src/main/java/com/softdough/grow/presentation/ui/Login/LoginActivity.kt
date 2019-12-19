package com.softdough.grow.presentation.ui.Login

import android.content.Intent
import android.os.Bundle
import com.softdough.grow.R
import com.softdough.grow.databinding.ActivityLoginBinding
import com.softdough.grow.presentation.base.BaseActivity
import com.softdough.grow.presentation.bindColor
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kakao.auth.*
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger
import com.softdough.grow.presentation.GlobalApplication
import com.softdough.grow.presentation.ui.UserInfo.UserInfoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val vm: LoginViewModel by viewModel()
    override val resourceId: Int = R.layout.activity_login
    override val statusBarColor: Int by bindColor(R.color.colorWhite)

    private val callback: SessionCallback? by lazy {
        SessionCallback()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.statusBarColor = statusBarColor
        binding

        vm.isSignIn.observe(this, Observer { redirectSignupActivity() })

        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            return

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback);
    }

    private inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            vm.loginKakao(Session.getCurrentSession().tokenInfo.accessToken)
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null) {
                Logger.e(exception)
            }
        }
    }

    private fun redirectSignupActivity() {
        Toast.makeText(this, "IsSignIn : ${vm.isSignIn.value}", Toast.LENGTH_SHORT).show()

        when (vm.isSignIn.value) {
            true -> {
                // 이미 계정이 존재
                //TODO : 루틴 액티비티 연결
                /*val intent = Intent(this, UserInfoActivity::class.java)
                startActivity(intent)
                finish()*/
            }
            false -> {
                // 계정이 존재하지 않음
                val intent = Intent(this, UserInfoActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

}