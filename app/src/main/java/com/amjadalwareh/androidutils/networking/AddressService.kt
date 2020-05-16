package com.amjadalwareh.androidutils.networking

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface AddressService {
    @GET("/address")
    fun getAddress(): Call<JSONObject>
}