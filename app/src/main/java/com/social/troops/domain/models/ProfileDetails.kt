package com.social.troops.domain.models


import com.google.gson.annotations.SerializedName

data class ProfileDetails(
        @SerializedName("id")
        val id: Int,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("last_name")
        val lastName: String,
        @SerializedName("gender")
        val gender: Int,
        @SerializedName("profilepic")
        val profilepic: String,
        @SerializedName("picapproved")
        val picapproved: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("address")
        val address: String,
        @SerializedName("address_2")
        val address2: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("zip")
        val zip: String,
        @SerializedName("latitude")
        val latitude: String,
        @SerializedName("longitude")
        val longitude: String,
        @SerializedName("search_radius")
        val searchRadius: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("confirmation_code")
        val confirmationCode: String,
        @SerializedName("confirmed")
        val confirmed: Int,
        @SerializedName("is_term_accept")
        val isTermAccept: Int,
        @SerializedName("accountname")
        val accountname: String,
        @SerializedName("dob")
        val dob: String,
        @SerializedName("age")
        val age: Int,
        @SerializedName("aboutme")
        val aboutme: String,
        @SerializedName("created_by")
        val createdBy: String,
        @SerializedName("updated_by")
        val updatedBy: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("deleted_at")
        val deletedAt: String,
        @SerializedName("stripe_id")
        val stripeId: String,
        @SerializedName("card_brand")
        val cardBrand: String,
        @SerializedName("card_last_four")
        val cardLastFour: String,
        @SerializedName("trial_ends_at")
        val trialEndsAt: String,
        @SerializedName("braintree_id")
        val braintreeId: String,
        @SerializedName("paypal_email")
        val paypalEmail: String,
        @SerializedName("adminnote")
        val adminnote: String
)