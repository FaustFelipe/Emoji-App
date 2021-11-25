package br.com.felipefaustini.data.utils

import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import retrofit2.Response
import java.net.HttpURLConnection

internal fun <Y: Any, T: Any> handleResponseCall(call: Response<Y>, result: (Y) -> T): Result<T> {
    return if (call.isSuccessful) {
        val data = call.body()!!
        val dataFormatted = result.invoke(data)
        Result.Success(dataFormatted)
    } else {
        handleApiCodeException(call.code())
    }
}

internal fun handleApiCodeException(code: Int): Result.Error {
    return when (code) {
        HttpURLConnection.HTTP_UNAUTHORIZED -> Result.Error(ErrorEntity.Unauthorized)
        HttpURLConnection.HTTP_UNAVAILABLE -> Result.Error(ErrorEntity.ServiceUnavailable)
        else -> Result.Error(ErrorEntity.Unknown)
    }
}