package com.example.myfoods.Api

import com.example.myfoods.Util.Constants
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitsInstance {
    companion object {
        private val retrofit by lazy  {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = okhttp3.OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
        }
        val api by lazy {
            retrofit.create(ApiInterface::class.java)
        }
    }
}