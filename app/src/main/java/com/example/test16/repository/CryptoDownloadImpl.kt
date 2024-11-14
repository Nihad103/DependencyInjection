package com.example.test16.repository

import com.example.test16.model.CryptoModel
import com.example.test16.service.CryptoApi
import com.example.test16.util.Resource

class CryptoDownloadImpl(val api: CryptoApi) : CryptoDownload {
    override suspend fun downloadCrypto(): Resource<List<CryptoModel>> {
        return try {
            val response = api.getData()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error!", null)
            }
        }catch (e: Exception) {
            Resource.error("No data!", null)
        }

    }
}