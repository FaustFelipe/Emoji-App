package br.com.felipefaustini.data.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.felipefaustini.data.database.dbutils.COLUMN_USERS_ID
import br.com.felipefaustini.data.database.dbutils.COLUMN_USERS_LOGIN
import br.com.felipefaustini.data.database.dbutils.COLUMN_USERS_NAME
import br.com.felipefaustini.data.database.dbutils.COLUMN_USERS_USER_ID
import br.com.felipefaustini.data.database.dbutils.TABLE_USERS

@Entity(tableName = TABLE_USERS)
data class DBUser(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_USERS_ID)
    var id: Long = 0,

    @ColumnInfo(name = COLUMN_USERS_LOGIN)
    var login: String,

    @ColumnInfo(name = COLUMN_USERS_NAME)
    var name: String,

    @ColumnInfo(name = COLUMN_USERS_USER_ID)
    var userId: Long

)