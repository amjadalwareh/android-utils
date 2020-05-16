package com.amjadalwareh.androidutils.networking

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AddressService {
    @GET("/address.php")
    fun getAddress(): Call<ResponseBody>
}