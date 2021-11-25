package br.com.felipefaustini.data.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.felipefaustini.data.database.dbutils.COLUMN_EMOJIS_ID
import br.com.felipefaustini.data.database.dbutils.COLUMN_EMOJIS_NAME
import br.com.felipefaustini.data.database.dbutils.COLUMN_EMOJIS_URL
import br.com.felipefaustini.data.database.dbutils.TABLE_EMOJIS

@Entity(tableName = TABLE_EMOJIS)
data class DBEmoji(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_EMOJIS_ID)
    var id: Long = 0,

    @ColumnInfo(name = COLUMN_EMOJIS_NAME)
    var name: String,

    @ColumnInfo(name = COLUMN_EMOJIS_URL)
    var url: String

)
