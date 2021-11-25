package br.com.felipefaustini.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.felipefaustini.data.models.db.DBUser

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dbUser: DBUser): Long

    @Query("SELECT * FROM users WHERE login = :login LIMIT 1")
    suspend fun findUser(login: String): DBUser

    @Query("SELECT EXISTS(SELECT * FROM users WHERE login = :login LIMIT 1)")
    suspend fun isExisting(login: String): Boolean

}