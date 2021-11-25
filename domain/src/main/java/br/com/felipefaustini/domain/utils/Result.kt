package br.com.felipefaustini.domain.utils

sealed class ErrorEntity {
    object InvalidFields: ErrorEntity()
    object Unauthorized: ErrorEntity()
    object ServiceUnavailable: ErrorEntity()
    object Unknown : ErrorEntity()
}

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val error: ErrorEntity): Result<Nothing>()
}