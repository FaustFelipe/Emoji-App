package br.com.felipefaustini.emojiapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.felipefaustini.emojiapp.utils.EventLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>(false)
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    protected val _errorLiveData = EventLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    protected val _errorUnitLiveData = EventLiveData<Unit>()
    val errorUnitLiveData: LiveData<Unit> = _errorUnitLiveData

    protected fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _loadingLiveData.value = true
                block()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loadingLiveData.value = false
            }
        }
    }

}