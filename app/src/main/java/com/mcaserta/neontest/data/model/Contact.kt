package com.mcaserta.neontest.data.model

import com.google.gson.annotations.SerializedName

data class Contact (
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("phone")
    var phone: String,

    @SerializedName("photoUrl")
    var photoUrl: String)