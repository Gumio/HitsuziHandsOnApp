package com.gumioji.hitsuziqiitaviewerapp.repository

import com.gumioji.hitsuziqiitaviewerapp.api.QiitaApiService
import com.gumioji.hitsuziqiitaviewerapp.data.Item
import io.reactivex.Observable

object QiitaRepository {
    private val apiService: QiitaApiService = QiitaApiService.create()
    fun searchItem(text: String? = null): Observable<List<Item>> {
        return apiService.items(text)
    }
}