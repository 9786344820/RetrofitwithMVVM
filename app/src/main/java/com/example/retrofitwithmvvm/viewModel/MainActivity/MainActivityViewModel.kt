package com.example.retrofitwithmvvm.viewModel.MainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitwithmvvm.Model.CommonErrorResult.ResultError
import com.example.retrofitwithmvvm.Model.DataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: MainActivityRepository): ViewModel() {


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val _successResponse = MutableLiveData<DataResponse>()
    var successResponse: LiveData<DataResponse> = _successResponse

    private val _errorMessage = MutableLiveData<ResultError>()
    var errorMessage: LiveData<ResultError> = _errorMessage


    fun getData(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler)  {
            try {
                val response = repository.getData()
                _successResponse.postValue(response)
            }
            catch (e : TimeoutException){
                _errorMessage.postValue(
                    ResultError(
                        -2,
                        e.message.toString()
                    )
                )
            }
            catch (e: HttpException) {
                _errorMessage.postValue(ResultError(e.code(), e.message()))
            }
            catch (e: Exception) {
                _errorMessage.postValue(ResultError(-1, e.message.toString()))
            }
        }
    }
}