package com.amjadalwareh.androidutils.networking

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AddressAdapter {

    fun getAddress(addressCallback: AddressCallback) {
        val addressService = getRetrofit()?.create(AddressService::class.java) as AddressService

        addressService.getAddress().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val jsonObject = JSONObject(response.body()?.string())
                    addressCallback.getAddress(jsonObject.getString("ip"))
                } else addressCallback.getAddress("0.0.0.0")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                addressCallback.getAddress("0.0.0.0")
            }
        })
    }

    private fun getRetrofit(): Retrofit? {
        return Retrofit.Builder()
                .baseUrl("https://amjadalwareh.com")
                .client(OkHttpClient().newBuilder()
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .followSslRedirects(false)
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}