package br.com.felipefaustini.data.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {

    @ToJson
    fun toJson(value: Date): String? {
        return try {
            val dateFormat = SimpleDateFormat(FORMAT, Locale("pt", "BR"))
            dateFormat.format(value)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    @FromJson
    fun fromJson(value: String): Date? {
        return try {
            val dateFormat = SimpleDateFormat(FORMAT, Locale("pt", "BR"))
            dateFormat.parse(value)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    }

}