package br.com.felipefaustini.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.felipefaustini.data.database.dao.UserDao
import br.com.felipefaustini.data.database.dbutils.DB_NAME
import br.com.felipefaustini.data.database.dbutils.DB_VERSION
import br.com.felipefaustini.data.models.db.DBUser

@Database(
    entities = [DBUser::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class EmojiDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: EmojiDatabase? = null

        fun getDatabase(context: Context): EmojiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmojiDatabase::class.java,
                    DB_NAME
                )
                    .build()

                INSTANCE = instance

                instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}