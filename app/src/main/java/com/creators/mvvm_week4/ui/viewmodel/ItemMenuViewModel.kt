package com.creators.mvvm_week4.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creators.mvvm_week4.util.ResponseStateItemMenu
import com.creators.myapilearning.data.api.ApiClient
import com.creators.myapilearning.data.api.ItemMenuRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class ItemMenuViewModel @Inject constructor(
    val apiClientItemMenu: ApiClient
) : ViewModel() {

    val itemmenuModel: LiveData<ResponseStateItemMenu>
        get() = _itemMenuModel
    private val _itemMenuModel: MutableLiveData<ResponseStateItemMenu> by lazy {
        MutableLiveData<ResponseStateItemMenu>()
    }

    fun fetchItemMenu() {
        try {
            //starting the API call
            _itemMenuModel.postValue(ResponseStateItemMenu.Loading)

            //IO ->
            //Main ->
            viewModelScope.launch(Dispatchers.Main) {
                val result = apiClientItemMenu.getItemMenu(
                    body = ItemMenuRequest(2)
                )
                if (result.data.isEmpty()) {
                    _itemMenuModel.postValue(ResponseStateItemMenu.Fail("No records found!"))
                } else {
                    _itemMenuModel.postValue(ResponseStateItemMenu.Success(result))
                }
            }
        } catch (e: SocketTimeoutException) {
            _itemMenuModel.postValue(ResponseStateItemMenu.Fail(e.message.toString()))
        } catch (e: Exception) {
            _itemMenuModel.postValue(ResponseStateItemMenu.Fail(e.message.toString()))
        }
    }
}