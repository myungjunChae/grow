package com.softdough.grow.presentation

import android.app.Application
import android.content.Context
import android.util.Log
import com.kakao.auth.*
import com.kakao.util.helper.CommonProtocol
import com.kakao.util.helper.Utility

class GlobalApplication : Application() {

    init {
        instance = this
    }

    companion object {
        //About Local
        var jwtToken : String = ""

        //About Kakao
        private var instance: GlobalApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    private class KakaoSDKAdapter : KakaoAdapter() {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         * @return Session의 설정값.
         */
        override fun getSessionConfig(): ISessionConfig {
            return object : ISessionConfig {
                override fun getAuthTypes(): Array<AuthType> {
                    return arrayOf(AuthType.KAKAO_LOGIN_ALL)
                }

                override fun isUsingWebviewTimer(): Boolean {
                    return false
                }

                override fun isSecureMode(): Boolean {
                    return false
                }

                override fun getApprovalType(): ApprovalType? {
                    return ApprovalType.INDIVIDUAL
                }

                override fun isSaveFormData(): Boolean {
                    return true
                }
            }
        }

        override fun getApplicationConfig(): IApplicationConfig {
            return IApplicationConfig { applicationContext() }
        }
    }

    override fun onCreate() {
        super.onCreate()
        KakaoSDK.init(KakaoSDKAdapter())
    }
}