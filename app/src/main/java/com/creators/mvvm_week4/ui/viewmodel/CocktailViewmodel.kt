package com.creators.mvvm_week4.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creators.mvvm_week4.util.ResponseState
import com.creators.myapilearning.data.api.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class CocktailViewmodel @Inject constructor(
    val apiClientCocktail: ApiClient
) : ViewModel() {

    val cocktailModel: LiveData<ResponseState>
        get() = _cocktailModel
    private val _cocktailModel: MutableLiveData<ResponseState> by lazy {
        MutableLiveData<ResponseState>()
    }

    fun fetchCocktail() {
        try {
            //starting the API call
            _cocktailModel.postValue(ResponseState.Loading)

            //IO ->
            //Main ->
            viewModelScope.launch(Dispatchers.Main) {
                val result = apiClientCocktail.getCocktails()
                if (result.drinks.isNullOrEmpty()) {
                    _cocktailModel.postValue(ResponseState.Fail("No records found!"))
                } else {
                    _cocktailModel.postValue(ResponseState.Success(result))
                }
            }
        } catch (e: SocketTimeoutException) {
            _cocktailModel.postValue(ResponseState.Fail(e.message.toString()))
        } catch (e: Exception) {
            _cocktailModel.postValue(ResponseState.Fail(e.message.toString()))
        }

    }

}