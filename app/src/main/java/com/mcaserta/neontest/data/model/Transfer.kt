package com.mcaserta.neontest.data.model

import com.google.gson.annotations.SerializedName

data class Transfer (
    @SerializedName("id")
    var id: Int,

    @SerializedName("clientId")
    var clientId: Int,

    @SerializedName("token")
    var token: String,

    @SerializedName("amount")
    var amount: Double
)