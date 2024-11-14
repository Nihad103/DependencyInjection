package com.example.test16.repository

import com.example.test16.model.CryptoModel
import com.example.test16.util.Resource

interface CryptoDownload {
    suspend fun downloadCrypto() : Resource<List<CryptoModel>>
}