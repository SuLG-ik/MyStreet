package ru.sulgik.core.graphql

import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retryWhen
import ru.mystreet.errors.domain.ErrorDispatcher
import kotlin.math.exp
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds


fun exponentialDuration(firstDuration: Duration): RetryDuration {
    return ExponentialRetryDuration(firstDuration)
}

private class ExponentialRetryDuration(private val firstDuration: Duration) : RetryDuration {
    override fun getDuration(attempt: Long, maxAttempts: Long): Duration {
        return (firstDuration.inWholeMilliseconds * exp((attempt).toFloat())).toInt().milliseconds
    }

}

interface RetryDuration {

    fun getDuration(attempt: Long, maxAttempts: Long): Duration

}

class ApolloRetryInterceptor(
    private val maxAttempts: Long,
    private val retryDuration: RetryDuration,
    private val shouldRetry: (ApolloException) -> Boolean,
    private val errorDispatcher: ErrorDispatcher,
) : ApolloInterceptor {

    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain,
    ): Flow<ApolloResponse<D>> {
        return chain.proceed(request)
            .onEach {
                val exception = it.exception
                if (exception != null && shouldRetry(exception)) {
                    errorDispatcher.dispatch(exception)
                    throw exception
                }
            }.retryWhen { _, attempt ->
                delay(retryDuration.getDuration(attempt, maxAttempts))
                true
            }
    }

}