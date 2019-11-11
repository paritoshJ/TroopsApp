package com.social.troops.domain.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id")
        val id: Int,
        @SerializedName("role_id")
        val roleId: Int,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("last_name")
        val lastName: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("email_verified_at")
        val emailVerifiedAt: String,
        @SerializedName("status")
        val status: Int,


        @SerializedName("address")
        val address: String,
        @SerializedName("notification_status")
        val notificationStatus: Int,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("verification_token")
        val verificationToken: String,

        @SerializedName("gender")
        val gender: Int,
        @SerializedName("profle_photo")
        val profilepic: String,
        @SerializedName("picapproved")
        val picapproved: String,
        @SerializedName("mobileno")
        val phone: String,
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


        @SerializedName("account_type_id")
        val accountTypeId: Int,
        @SerializedName("access_token")
        val token: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(roleId)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(email)
        parcel.writeString(emailVerifiedAt)
        parcel.writeInt(status)
        parcel.writeString(address)
        parcel.writeInt(notificationStatus)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(verificationToken)
        parcel.writeInt(gender)
        parcel.writeString(profilepic)
        parcel.writeString(picapproved)
        parcel.writeString(phone)
        parcel.writeString(address2)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(country)
        parcel.writeString(zip)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeInt(searchRadius)
        parcel.writeString(confirmationCode)
        parcel.writeInt(confirmed)
        parcel.writeInt(isTermAccept)
        parcel.writeString(accountname)
        parcel.writeString(dob)
        parcel.writeInt(age)
        parcel.writeString(aboutme)
        parcel.writeInt(accountTypeId)
        parcel.writeString(token)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}