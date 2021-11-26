package br.com.felipefaustini.domain.usecases.emojilist

import br.com.felipefaustini.domain.models.Emoji
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface IEmojiListUseCase {
    fun getEmoji(): Flow<Result<List<Emoji>>>
}