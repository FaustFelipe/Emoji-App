package br.com.felipefaustini.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.felipefaustini.data.models.db.DBEmoji

@Dao
interface EmojiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dbEmoji: DBEmoji): Long

    @Query("SELECT * FROM emojis")
    fun findAll(): List<DBEmoji>

}