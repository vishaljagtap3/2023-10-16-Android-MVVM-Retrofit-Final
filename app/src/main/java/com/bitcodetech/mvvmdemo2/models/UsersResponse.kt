package com.bitcodetech.mvvmdemo2.models

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("data")
    val users : ArrayList<User>
)