package com.example.mad.ui.screens.numberDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mad.data.api.util.Resource
import com.example.mad.data.model.Numbers
import com.example.mad.ui.repository.NumbersRepository
import kotlinx.coroutines.launch

class NumbersViewModel(application: Application) : AndroidViewModel(application) {
    private val numbersRepository = NumbersRepository()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from Activity for error showing
     * Encapsulation :)
     */
    val numberResource: LiveData<Resource<Numbers>>
        get() = _numberResource

    //initialize it with an Empty type of Resource
    private val _numberResource: MutableLiveData<Resource<Numbers>> = MutableLiveData(Resource.Empty())

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getNumber(numberType: String) {
        //set resource type to loading
        _numberResource.value = Resource.Loading()

        viewModelScope.launch {
            _numberResource.value = numbersRepository.getRandomNumber(numberType)
        }
    }
}