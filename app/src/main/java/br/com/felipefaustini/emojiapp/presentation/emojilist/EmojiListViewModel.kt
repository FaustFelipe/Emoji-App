package br.com.felipefaustini.emojiapp.presentation.emojilist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.felipefaustini.domain.models.Emoji
import br.com.felipefaustini.domain.usecases.emojilist.IEmojiListUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.emojiapp.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class EmojiListViewModel @Inject constructor(
    private val emojiListUseCase: IEmojiListUseCase
): BaseViewModel() {

    private val _emojisLiveData = MutableLiveData<List<Emoji>>()
    val emojisLiveData: LiveData<List<Emoji>> = _emojisLiveData

    init {
        loadEmojis()
    }

    fun loadEmojis() {
        launchDataLoad {
            emojiListUseCase.getEmoji().collect {
                when(val result = it) {
                    is Result.Error -> {

                    }
                    is Result.Success -> {
                        _emojisLiveData.postValue(result.data)
                    }
                }
            }
        }
    }

}