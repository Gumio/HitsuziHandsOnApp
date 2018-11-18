package com.gumioji.hitsuziqiitaviewerapp.api

import com.gumioji.hitsuziqiitaviewerapp.data.Item
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApiService {
    @GET("/api/v2/items")
    fun items(@Query("query") query: String? = null,
              @Query("page") page: Int = 1,
              @Query("per_page") perPage: Int = 50): Observable<List<Item>>

    companion object {
        fun create(): QiitaApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl("http://qiita.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(QiitaApiService::class.java)
        }
    }
}