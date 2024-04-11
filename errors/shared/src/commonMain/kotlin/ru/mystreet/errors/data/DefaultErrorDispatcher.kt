package ru.mystreet.errors.data

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.mystreet.errors.domain.DefaultErrorResolversConfig
import ru.mystreet.errors.domain.ErrorConfigResolver
import ru.mystreet.errors.domain.ErrorHandlerFactory
import ru.mystreet.errors.domain.ErrorHandlersConfig
import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.ErrorPostProcessor
import ru.mystreet.errors.domain.ErrorPostProcessorController
import ru.mystreet.errors.domain.MutableErrorDispatcher

@Suppress("FunctionName")
fun DefaultErrorDispatcher(
    errorPostProcessors: Array<ErrorPostProcessor> = emptyArray(),
    handlers: Array<ErrorHandlerFactory<*>> = emptyArray(),
): MutableErrorDispatcher {
    return DefaultErrorDispatcher(
        resolversConfig = DefaultErrorResolversConfig(handlers = handlers),
        errorPostProcessor = ErrorPostProcessorController(errorPostProcessors),
    )
}

private class DefaultErrorDispatcher(
    private val resolversConfig: ErrorHandlersConfig,
    private val errorPostProcessor: ErrorPostProcessor,
) : MutableErrorDispatcher {

    override val errors: MutableSharedFlow<ErrorInfo> =
        MutableSharedFlow(extraBufferCapacity = 16, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun dispatch(error: Any) {
        val handler = resolversConfig.findHandler(error) ?: return
        @Suppress("UNCHECKED_CAST")
        dispatch(error, handler.resolver as ErrorConfigResolver<Any>)
    }

    private fun dispatch(error: Any, resolver: ErrorConfigResolver<Any>) {
        val info = resolver.resolve(error)
        errorPostProcessor.process(info)
        errors.tryEmit(info)
    }

}