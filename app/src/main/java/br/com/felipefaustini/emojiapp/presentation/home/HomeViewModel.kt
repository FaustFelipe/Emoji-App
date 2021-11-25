package br.com.felipefaustini.emojiapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.felipefaustini.domain.models.Emoji
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.emojiapp.presentation.BaseViewModel
import br.com.felipefaustini.emojiapp.utils.EventLiveData
import kotlinx.coroutines.flow.collect

class HomeViewModel(
    private val homeUseCase: IHomeUseCase
): BaseViewModel() {

    var username: String = ""

    private val _userSavedLiveData = MutableLiveData<Unit>()
    val userSavedLiveData: LiveData<Unit> = _userSavedLiveData

    private val _emojiLiveData = MutableLiveData<Emoji>()
    val emojiLiveData: LiveData<Emoji> = _emojiLiveData

    fun getUser() {
        launchDataLoad {
            homeUseCase.getUser(username).collect {
                when(val result = it) {
                    is Result.Error -> {
                        when(result.error) {
                            is ErrorEntity.InvalidFields -> _errorUnitLiveData.postValue(Unit)
                            else -> _errorLiveData.postValue("Error") // TODO change it
                        }
                    }
                    is Result.Success -> {
                        _userSavedLiveData.postValue(Unit)
                    }
                }
            }
        }
    }

    fun getEmoji() {
        launchDataLoad {
            homeUseCase.getEmoji().collect {
                when(val result = it) {
                    is Result.Error -> {
                        _errorLiveData.postValue("Error") // TODO change it
                    }
                    is Result.Success -> {
                        val emoji = result.data.random()
                        _emojiLiveData.postValue(emoji)
                    }
                }
            }
        }
    }

}