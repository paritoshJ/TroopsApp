package com.app.marketplace.sellBuyDevice.data.remote

import com.social.troops.domain.base.BaseListResponse
import com.social.troops.domain.base.BaseResponse
import com.social.troops.domain.models.ProfileDetails
import com.social.troops.domain.models.User
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.http.*
import java.io.File

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun requestUserLogin(@Field("email") email: String,
                         @Field("password") password: String,
                         @Field("device_type") devicetype: String,
                         @Field("device_id") deviceId: String): Deferred<BaseResponse<User>>


    @FormUrlEncoded
    @POST("updaterole")
    fun requestUpdateUserRole(@Field("userid") email: String,
                         @Field("roleid") password: String
                         ): Deferred<BaseResponse<User>>


    // provider : facebook   (Type of social login facebook or google)
    // provider_id : qwer134srf    (social user id)
    @FormUrlEncoded
    @POST("social-login")
    fun requestUserSocialLogin(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("provider") provider: String,
        @Field("provider_id") provider_id: String
    ): Deferred<BaseResponse<User>>

    @FormUrlEncoded
    @POST("register")
    fun requestUserRegistration(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("mobileno") mobile: String

    ): Deferred<BaseResponse<Void>>

    @FormUrlEncoded
//    @POST("password/create")
    @POST("forgetPassword")
    fun requestForgotPassword(@Field("email") email: String): Deferred<BaseResponse<Void>>

    @FormUrlEncoded
    @POST("ResetPassword")
    fun requestResetPassword(
        @Field("password") password: String,
        @Field("password_confirmation") confirmPassword: String,
        @Field("otp") otp: String
    ): Deferred<BaseResponse<Void>>

    @FormUrlEncoded
    @POST("changepassword")
    fun requestChangePassword(
        @Field("old_password") currentPassword: String,
        @Field("password") newPassword: String
    ): Deferred<BaseResponse<Void>>

    @GET("get/profile")
    fun requestUserProfile(): Deferred<BaseResponse<ProfileDetails>>

    @Multipart
    @POST("updateprofile")
    fun requestUpdateUserProfile(
        @Part("first_name") firstName: RequestBody,
        @Part("last_name") lastName: RequestBody,
        @Part("mobileno") mobile: RequestBody,
        @Part  file: MultipartBody.Part
    ): Deferred<BaseResponse<User>>




//
//    @GET("get/offers")
//    fun requestOffers(
//        @Query("name") name: String,
//        @Query("page") pageNumber: Int
//    ): Deferred<BaseResponse<BaseListResponse<Product>>>










    @FormUrlEncoded
    @POST("check-otp")
    fun requestVerifyForgotPasswordOtp(@Field("otp") otp: String): Deferred<BaseResponse<Void>>

    @FormUrlEncoded
    @POST("register/activate")
    fun requestVerifyRegistrationOtp(
        @Field("otp") otp: String,
        @Field("mobile_otp") mobileOtp: String
    ): Deferred<BaseResponse<Void>>

    @FormUrlEncoded
    @POST("password/create")
    fun requestResendForgotPasswordOtp(@Field("email") email: String): Deferred<BaseResponse<Void>>

    @FormUrlEncoded
    @POST("register/resend-otp")
    fun requestResendRegistrationOtp(@Field("email") email: String, @Field("mobile") mobile: String): Deferred<BaseResponse<Void>>



}