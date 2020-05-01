package com.diyas.ecommerceuts.data

import com.google.gson.annotations.SerializedName

data class ResponseLogin (
    @SerializedName("message") val message : String,
    @SerializedName("status") val status : Boolean,
    @SerializedName("data") val data : DataLogin
)
