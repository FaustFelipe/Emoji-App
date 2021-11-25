package br.com.felipefaustini.data.models.mappers

import br.com.felipefaustini.data.models.db.DBEmoji
import br.com.felipefaustini.domain.models.Emoji

object EmojiMapper {

    fun map(key: String, value: String) = DBEmoji(
        name = key,
        url = value
    )

    fun map(dbEmoji: DBEmoji) = Emoji(
        name = dbEmoji.name,
        url = dbEmoji.url
    )

}