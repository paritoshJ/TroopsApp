package com.app.marketplace.sellBuyDevice.data.remote

import com.app.marketplace.sellBuyDevice.domain.base.BaseListResponse
import com.app.marketplace.sellBuyDevice.domain.base.BaseResponse
import com.app.marketplace.sellBuyDevice.domain.models.*
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

    @FormUrlEncoded
    @POST("check/email")
    fun requestCheckEmailAvailability(@Field("email") email: String): Deferred<BaseResponse<Void>>

    @GET("get/offer/categories")
    fun requestCategories(
        @Query("name") name: String,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Category>>>

    @GET("get/offer/categories")
    fun requestCategories(@Query("page") pageNumber: Int): Deferred<BaseResponse<BaseListResponse<Category>>>

    @FormUrlEncoded
    @POST("ProductDetail")
    fun requestProductDetails(@Field("product_id") offerId: Int,@Field("user_id") userId: Int): Deferred<BaseResponse<Product>>



    @GET("get/offers")
    fun requestOffers(
        @Query("name") name: String,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Product>>>

    @FormUrlEncoded
    @POST("searchproduct")
    fun requestProductsWithFilter(
        @Field("productname") name: String,
        @Field("page") pageNumber: Int,
        @Field("cat_id[]") catArray: ArrayList<String>,
        @Field("device_name[]") deviceArray: ArrayList<String>,
        @Field("phone_condition[]") conditionArray: ArrayList<String>,
        @Field(" min-price") minValue: String,
        @Field(" max-price") maxValue: String
    ): Deferred<BaseResponse<BaseListResponse<Product>>>


    @GET("Productlist")
    fun requestProducts(@Query("page") pageNumber: Int): Deferred<BaseResponse<BaseListResponse<Product>>>

    // offer_type : 1 or 2
    // 1=online offer, 2=in-store offer
    @GET("get/featuredoffers")
    fun requestFeaturedOffers(@Query("offer_type") offerType: Int): Deferred<BaseResponse<BaseListResponse<Product>>>

    @GET("Categorylist")
    fun requestFeaturedCategories(@Query("offer_type") offerType: Int): Deferred<BaseResponse<List<Category>>>
    @GET("Devicelist")
    fun requestFeaturedDevices(): Deferred<BaseResponse<DeviceTypeResponse>>

    @GET("get/vendors")
    fun requestVendors(
        @Query("offer_type") offerType: Int,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Vendor>>>

    @GET("get/vendors")
    fun requestAllVendors(
        @Query("name") searchValue: String,
        @Query("lat") latitudeValue: String,
        @Query("long") longitudeValue: String,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Vendor>>>

    @GET("get/vendors")
    fun requestAllVendors(
        @Query("name") searchValue: String,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Vendor>>>

    @GET("get/vendors")
    fun requestAllVendors(
        @Query("lat") latitudeValue: String,
        @Query("long") longitudeValue: String, @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Vendor>>>

    @GET("get/vendors")
    fun requestAllVendors(@Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Vendor>>>


    //    http://fako.projectstatus.in/api/get/vendor-offers?vendor_id=32&offer_type=1
    @GET("get/vendor-offers")
    fun requestVendorOffers(
        @Query("vendor_id") vendorId: Int,
        @Query("offer_type") offerType: Int,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Product>>>

    @GET("get/vendor-offers")
    fun requestVendorOffers(
        @Query("vendor_id") vendorId: Int,
        @Query("name") name: String,
        @Query("offer_type") offerType: Int,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Product>>>

    @GET("get/vendor-offers")
    fun requestVendorOffers(
        @Query("vendor_id") vendorId: Int,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Product>>>

    @GET("get/vendor-offers")
    fun requestVendorOffers(
        @Query("vendor_id") vendorId: Int,
        @Query("name") name: String,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Product>>>

    @FormUrlEncoded
    @POST("search/suggest-offers")
    fun requestOfferSuggestions(@Field("search") searchValue: String): Deferred<BaseResponse<List<OfferSuggestion>>>

    @GET("get/category-offers")
    fun requestCategoryOffers(
        @Query("category_id") categoryId: Int,
        @Query("name") name: String,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Product>>>

    @GET("get/category-offers")
    fun requestCategoryOffers(
        @Query("category_id") categoryId: Int,
        @Query("page") pageNumber: Int
    ): Deferred<BaseResponse<BaseListResponse<Product>>>

    @FormUrlEncoded
    @POST("search/offers")
    fun requestHomeSearch(@Field("search") searchValue: String): Deferred<BaseResponse<AllSearch>>

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

    //    /api/wallet/available-balance
//    @FormUrlEncoded
    @GET("wallet/available-balance")
    fun requestGetWalletDetails(): Deferred<BaseResponse<String>>

    @GET("wallet/history")
    fun requestGetWalletHistory(@Query("page") pageNumber: Int): Deferred<BaseResponse<BaseListResponse<WalletHistory>>>

    @GET("redeem/list")
    fun requestGetRedeemHistory(): Deferred<BaseResponse<List<RedeemHistory>>>

    @FormUrlEncoded
    @POST("redeem/request")
    fun requestRedeemWalletBalance(
        @Field("bank_id") bankAccountId: Int,
        @Field("amount") amount: String,
        @Field("message") message: String
    ): Deferred<BaseResponse<Void>>

    @FormUrlEncoded
    @POST("bank/create")
    fun requestAddRedeemAccount(
        @Field("name") bankName: String, @Field("account_name") accountName: String,
        @Field("account_no") accountNumber: String,
        @Field("ifsc_code") IFSC_Code: String
    ): Deferred<BaseResponse<Void>>

    @GET("bank/delete")
    fun requestDeleteRedeemAccount(@Query("id") accountId: Int): Deferred<BaseResponse<Void>>

    @GET("bank/list")
    fun requestGetRedeemBankAccounts(): Deferred<BaseResponse<List<RedeemAccount>>>

    @GET("apply/offer")
    fun requestApplyOffer(
        @Query("offer_id") offerId: Int,
        @Query("category_id") categoryId: Int
    ): Deferred<BaseResponse<Void>>


    @GET("pages/about-us")
    fun requestAboutUsInfo(): Deferred<BaseResponse<WebPageInfo>>

    @GET("pages/terms-and-conditions")
    fun requestTermsConditionsInfo(): Deferred<BaseResponse<WebPageInfo>>

    @GET("pages/privacy-policy")
    fun requestPrivacyPolicyInfo(): Deferred<BaseResponse<WebPageInfo>>

    @GET("faqs")
    fun requestFAQs(): Deferred<BaseResponse<List<Faq>>>

    @FormUrlEncoded
    @POST("contact-us/submit")
    fun requestSubmitContactUsData(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("message") message: String
    ): Deferred<BaseResponse<Void>>


    @FormUrlEncoded
    @POST("change/saveReferalCode")
    fun requestSaveReferal(
        @Field("refCode") currentPassword: String
    ): Deferred<BaseResponse<Void>>

    @GET("get/refercode")
    fun requestReferralCode(): Deferred<BaseResponse<ReferInfo>>


    @GET("searchfilterbyitem")
    fun requestFilterList(): Deferred<BaseResponse<FilterResponseSearch>>

    /*Seller user after login*/
    @GET("myproductlist")
    fun requestSellerMyProductList(@Query("page") pageNumber: Int): Deferred<BaseResponse<BaseListResponse<Product>>>


    /*Buyer user after login*/
    @GET("myInterestforbuyer")
    fun requestBuyerMyProductList(@Query("page") pageNumber: Int): Deferred<BaseResponse<BaseListResponse<Product>>>

     /*Sent Message*/
    @FormUrlEncoded
    @POST("sentmessage")
    fun requestSendMessage( @Field("product_id") porduct_id: Int,
                            @Field("sender_id") sender_id: Int,
                            @Field("receiver_id") receiver_id: Int,
                            @Field("message") message: String): Deferred<BaseResponse<Message>>


    /*Sent Message*/
    @FormUrlEncoded
    @POST("allmessage")
    fun requestAllMessage(@Field("product_id") porduct_id: Int,
                          @Field("sender_id") sender_id: Int
                            ): Deferred<BaseResponse<List<Message>>>

    /*Sent Message*/
    @FormUrlEncoded
    @POST("sellermessageforproduct")
    fun requestSellerMessageForProduct( @Field("product_id") porduct_id: Int
    ): Deferred<BaseResponse<BaseListResponse<Message>>>


    /*Sent Message*/
    @FormUrlEncoded
    @POST("addtowatchlist")
    fun requestProductAddToWatchList( @Field("product_id") porduct_id: Int,
                                      @Field("sender_id") sender_id: Int,
                                      @Field("receiver_id") receiver_id: Int,
                                      @Field("status") status: Int
    ): Deferred<BaseResponse<Void>>


    /*Sent Message*/
    @FormUrlEncoded
    @POST("deleteproduct")
    fun requestDeleteProduct( @Field("product_id") porduct_id: Int
    ): Deferred<BaseResponse<Void>>

}