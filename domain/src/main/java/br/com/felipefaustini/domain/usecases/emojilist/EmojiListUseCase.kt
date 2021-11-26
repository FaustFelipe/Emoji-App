package br.com.felipefaustini.domain.usecases.emojilist

import br.com.felipefaustini.domain.models.Emoji
import br.com.felipefaustini.domain.repository.EmojiRepository
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EmojiListUseCase(
    private val repository: EmojiRepository
): IEmojiListUseCase {

    override fun getEmoji(): Flow<Result<List<Emoji>>> {
        return repository.findAllEmojis()
            .map { Result.Success(it) }
    }

}