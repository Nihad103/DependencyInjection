package com.example.test16.service

import com.example.test16.model.CryptoModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoApi {

    //  https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getData() : Response<List<CryptoModel>>
}