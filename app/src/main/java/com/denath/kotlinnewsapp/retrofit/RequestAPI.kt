package com.denath.kotlinnewsapp.retrofit

import com.denath.kotlinnewsapp.models.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface RequestAPI {
    @get:GET("search?api-key=5336517f-3d76-40df-a72c-015f57961863")
    val response:Observable<Response>
}