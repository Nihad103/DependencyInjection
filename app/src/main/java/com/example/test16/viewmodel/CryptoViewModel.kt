package com.example.test16.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test16.model.CryptoModel
import com.example.test16.repository.CryptoDownload
import com.example.test16.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoViewModel(
    private var cryptoDownloadRepository : CryptoDownload
): ViewModel() {

    var cryptoList = MutableLiveData<Resource<List<CryptoModel>>>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }

    fun getDataFromApi() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val resource = cryptoDownloadRepository.downloadCrypto()
            withContext(Dispatchers.Main) {
                resource.data?.let {
                    cryptoList.value = resource
                }
            }

        }
    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}