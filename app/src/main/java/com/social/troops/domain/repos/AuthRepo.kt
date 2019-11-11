package com.social.troops.domain.repos

import com.social.troops.data.RepoListener
import com.social.troops.data.remote.ApiClient
import com.social.troops.data.remote.RemoteRepo
import com.social.troops.domain.annotations.DataRequestType
import com.social.troops.domain.base.BaseResponse
import com.social.troops.domain.models.User
import kotlinx.coroutines.Deferred

class AuthRepo(val repoListener: RepoListener) {

    suspend fun requestUserLogin(email: String, password: String): BaseResponse<User>? {
        return object : RemoteRepo<BaseResponse<User>> {
            override val deferred: Deferred<BaseResponse<User>>
                get() = ApiClient.apiService.requestUserLogin(email, password,"android","'")
            override val dataRequestType: Int
                get() = DataRequestType.USER_LOGIN
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }

    suspend fun requestUserLogin(email: String, name: String, provider: String, provider_id: String): BaseResponse<User>? {
        return object : RemoteRepo<BaseResponse<User>> {
            override val deferred: Deferred<BaseResponse<User>>
                get() = ApiClient.apiService.requestUserLogin(email, name, provider, provider_id)
            override val dataRequestType: Int
                get() = DataRequestType.USER_LOGIN
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }

    suspend fun requestUpdateUserRole(userid: String,role:String): BaseResponse<User>? {
        return object : RemoteRepo<BaseResponse<User>> {
            override val deferred: Deferred<BaseResponse<User>>
                get() = ApiClient.authorizedApiService.requestUpdateUserRole(userid,role)
            override val dataRequestType: Int
                get() = DataRequestType.USER_UPDATE_ROLE
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }

    suspend fun requestForgotPassword(email: String): BaseResponse<Void>? {
        return object : RemoteRepo<BaseResponse<Void>> {
            override val deferred: Deferred<BaseResponse<Void>>
                get() = ApiClient.apiService.requestForgotPassword(email)
            override val dataRequestType: Int
                get() = DataRequestType.FORGOT_PASSWORD
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }

    suspend fun requestResetPassword(password: String, confirmPassword: String, otp: String): BaseResponse<Void>? {
        return object : RemoteRepo<BaseResponse<Void>> {
            override val deferred: Deferred<BaseResponse<Void>>
                get() = ApiClient.apiService.requestResetPassword( password, confirmPassword, otp)
            override val dataRequestType: Int
                get() = DataRequestType.RESET_PASSWORD
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }

    suspend fun requestChangePassword(currentPassword: String, newPassword: String,
                                      confirmNewPassword: String): BaseResponse<Void>? {
        return object : RemoteRepo<BaseResponse<Void>> {
            override val deferred: Deferred<BaseResponse<Void>>
                get() = ApiClient.authorizedApiService.requestChangePassword(currentPassword, newPassword)
            override val dataRequestType: Int
                get() = DataRequestType.CHANGE_PASSWORD
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }



    suspend fun requestUserRegistration(firstName: String, lastName: String, email: String, password: String,
                                        mobile: String
                                        ): BaseResponse<Void>? {
        return object : RemoteRepo<BaseResponse<Void>> {
            override val deferred: Deferred<BaseResponse<Void>>
                get() = ApiClient.apiService.requestUserRegistration(firstName, lastName, email, password, mobile)
            override val dataRequestType: Int
                get() = DataRequestType.USER_REGISTER
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }

    suspend fun requestVerifyForgotPasswordOtp(otp: String): BaseResponse<Void>? {
        return object : RemoteRepo<BaseResponse<Void>> {
            override val deferred: Deferred<BaseResponse<Void>>
                get() = ApiClient.apiService.requestVerifyForgotPasswordOtp(otp)
            override val dataRequestType: Int
                get() = DataRequestType.VERIFY_OTP_FORGOT_PASSWORD
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }

    suspend fun requestVerifyRegistrationOtp(otp: String, mobileOtp: String): BaseResponse<Void>? {
        return object : RemoteRepo<BaseResponse<Void>> {
            override val deferred: Deferred<BaseResponse<Void>>
                get() = ApiClient.apiService.requestVerifyRegistrationOtp(otp, mobileOtp)
            override val dataRequestType: Int
                get() = DataRequestType.VERIFY_OTP_REGISTRATION
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }

    suspend fun requestResendForgotPasswordOtp(email: String): BaseResponse<Void>? {
        return object : RemoteRepo<BaseResponse<Void>> {
            override val deferred: Deferred<BaseResponse<Void>>
                get() = ApiClient.apiService.requestResendForgotPasswordOtp(email)
            override val dataRequestType: Int
                get() = DataRequestType.RESEND_OTP_FORGOT_PASSWORD
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }

    suspend fun requestResendRegistrationOtp(email: String, mobile: String): BaseResponse<Void>? {
        return object : RemoteRepo<BaseResponse<Void>> {
            override val deferred: Deferred<BaseResponse<Void>>
                get() = ApiClient.apiService.requestResendRegistrationOtp(email, mobile)
            override val dataRequestType: Int
                get() = DataRequestType.RESEND_OTP_REGISTRATION
            override val repoListener: RepoListener
                get() = this@AuthRepo.repoListener
        }.executeApiRequest()
    }



}