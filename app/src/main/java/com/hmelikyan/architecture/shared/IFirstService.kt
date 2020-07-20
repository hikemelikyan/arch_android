package com.hmelikyan.architecture.shared

import com.hmelikyan.architecture.model.ResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IFirstService{

    @GET("todos/{id}")
    suspend fun getTodos(@Path("id") id:Int):Response<ResponseItem>

}