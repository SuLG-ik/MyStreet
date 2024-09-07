package ru.mystreet.errors.data.resolver

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloCompositeException
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.apollographql.apollo3.exception.NoDataException
import ru.mystreet.errors.domain.ErrorConfigResolver
import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.ErrorLevel
import ru.mystreet.errors.domain.UniqueIdGenerator
import ru.mystreet.errors.domain.exception.InternalException
import ru.mystreet.errors.domain.exception.ObjectNotFoundException
import ru.mystreet.errors.domain.exception.PermissionDeniedException
import ru.mystreet.errors.domain.exception.UnknownException

fun ApolloResponse<*>.throwDefaultErrors(name: String) {
    exception?.let { throw it }
    errors?.forEach {
        when (it.extensions?.get("errorType")) {
            "NOT_FOUND" -> throw ObjectNotFoundException(name)
            "INTERNAL" -> throw InternalException(message = it.toString())
            "PERMISSION_DENIED" -> throw PermissionDeniedException()
            else -> throw UnknownException(message = it.toString())
        }
    }
}

class ApolloExceptionResolver(
    private val idGenerator: UniqueIdGenerator,
) : ErrorConfigResolver<ApolloException> {

    override fun resolve(value: ApolloException): ErrorInfo {
        return when (value) {
            is ApolloNetworkException -> resolve(value)
            is NoDataException -> resolve(value)
            is ApolloCompositeException -> resolve(value)
            else -> unknown(value)
        }
    }

    private fun resolve(value: NoDataException): ErrorInfo {
        val cause = value.cause
        return if (cause is ApolloException)
            resolve(cause)
        else
            unknown(value)
    }

    private fun resolve(value: ApolloNetworkException): ErrorInfo {
        return ErrorInfo(
            uniqueId = idGenerator.next(),
            data = ErrorInfo.ErrorData.Raw(
                title = "Ошибка подключения",
                content = "Проверьте интернет-соедиение и повторите ещё раз"
            ),
            config = ErrorInfo.Config(key = "NetworkException", isShownMultiple = false),
            parent = value,
        )
    }

    private fun unknown(value: ApolloException): ErrorInfo {
        return ErrorInfo(
            uniqueId = idGenerator.next(),
            data = ErrorInfo.ErrorData.Raw(
                title = "Неизвестная ошибка, уже чиним",
                content = "Повторите попытку позднее",
            ),
            config = ErrorInfo.Config(
                key = "UnknownException",
                isShownMultiple = false,
                level = ErrorLevel.ERROR,
            ),
            parent = value,
        )
    }

    private fun resolve(value: ApolloCompositeException): ErrorInfo {
        throw IllegalArgumentException("ApolloCompositeException does not supported", value)
    }

}