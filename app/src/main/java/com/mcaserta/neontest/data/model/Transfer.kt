package com.mcaserta.neontest.data.model

import com.google.gson.annotations.SerializedName

data class Transfer (
    @SerializedName("id")
    var id: Int,

    @SerializedName("client_id")
    var clientId: Int,

    @SerializedName("token")
    var token: String,

    @SerializedName("value")
    var amount: Double,

    @SerializedName("contact")
    var contact: Contact? = null
)