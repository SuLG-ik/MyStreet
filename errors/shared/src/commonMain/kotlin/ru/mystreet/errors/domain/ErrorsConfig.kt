package ru.mystreet.errors.domain

import ru.mystreet.errors.data.AtomicUniqueIdGenerator
import kotlin.reflect.KClass

interface ErrorHandlersConfig {

    fun findHandler(value: Any): ErrorHandler<*>?

}

@Suppress("FunctionName")
fun DefaultErrorResolversConfig(
    vararg handlers: ErrorHandlerFactory<*>,
    uniqueIdGenerator: UniqueIdGenerator = AtomicUniqueIdGenerator(),
): ErrorHandlersConfig {
    return ErrorHandlersConfig(
        { HandleableErrorResolver(it).asHandler() }, *handlers,
        uniqueIdGenerator = uniqueIdGenerator,
    )
}

fun ErrorHandlersConfig(
    vararg handlers: ErrorHandlerFactory<*>,
    uniqueIdGenerator: UniqueIdGenerator = AtomicUniqueIdGenerator(),
): ErrorHandlersConfig {
    return DefaultErrorHandlersConfig(uniqueIdGenerator, handlers)
}

private class DefaultErrorHandlersConfig(
    uniqueIdGenerator: UniqueIdGenerator,
    handlers: Array<out ErrorHandlerFactory<*>>,
) : ErrorHandlersConfig {

    private val handlers =
        handlers.map { it.invoke(uniqueIdGenerator) }
            .associateBy { it.type }
            .toMutableMap()

    private val handlersCache = mutableMapOf<KClass<*>, ErrorHandler<*>?>()

    override fun findHandler(value: Any): ErrorHandler<*>? {
        val type = value::class
        if (handlersCache.containsKey(value))
            return handlersCache[value]
        val handler = findInResolvers(value, type)
        handlersCache[type] = handler
        return handler
    }

    private fun findInResolvers(value: Any, type: KClass<*>): ErrorHandler<*>? {
        var generalResolver: ErrorHandler<*>? = null
        handlers.forEach {
            if (it.key == type) return it.value
            if (it.key.isInstance(value) && generalResolver == null)
                generalResolver = it.value
        }
        return generalResolver
    }

}