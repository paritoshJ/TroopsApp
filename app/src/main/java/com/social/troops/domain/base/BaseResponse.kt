package com.social.troops.domain.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status") @Expose val status: Boolean,
    @SerializedName("message") @Expose val message: String,
    @SerializedName("code") @Expose val code: Int,
    @SerializedName("data") @Expose val data: T
)