package gj.tesonet.data.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("username") val name: String,
                val password: String)
